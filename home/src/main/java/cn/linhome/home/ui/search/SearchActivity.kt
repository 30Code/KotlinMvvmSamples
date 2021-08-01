package cn.linhome.home.ui.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.*
import cn.linhome.common.constant.Constant
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.common.ui.LoadingDialog
import cn.linhome.common.utils.SearchHistoryUtils
import cn.linhome.common.vm.AppViewModel
import cn.linhome.common.vm.CollectionViewModel
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.home.R
import cn.linhome.home.adapter.HistorySearchAdapter
import cn.linhome.home.databinding.ActivitySearchBinding
import cn.linhome.home.entity.HotKeyData
import cn.linhome.home.vm.SearchViewModel
import cn.linhome.lib.utils.context.FResUtil
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.google.android.flexbox.FlexboxLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : 搜索
 *  Created by 30Code
 *  date : 2021/7/31
 */
@Route(path = Constant.ARouterPath.PATH_SEARCH)
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    private val mAppViewModel by viewModel<AppViewModel>()

    private val mViewModel by viewModel<SearchViewModel>()

    private val mCollectionViewModel by viewModel<CollectionViewModel>()

    private val mHistoryAdapter by lifecycleScope.inject<HistorySearchAdapter>()
    private val mSearchAdapter by lifecycleScope.inject<SearchPagingAdapter>()

    private val mLoadingDialog by lifecycleScope.inject<LoadingDialog>()

    private var mIsLoadHotKeysError = false
    private var mKey = ""

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initActivity(savedInstanceState: Bundle?) {

        getListHotKeys()

        mAppViewModel.showLoadingProgress.observe(this, {
            if (it) mLoadingDialog.showAllowStateLoss(supportFragmentManager, "loading")
            else mLoadingDialog.dismiss()
        })

        mBinding.run {
            toolbar.run {
                setNavigationIcon(R.drawable.arrow_back)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }

            refreshColor = R.color.colorAccent
            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                if (mIsLoadHotKeysError) {
                    getListHotKeys()
                } else {
                    mSearchAdapter.refresh()
                }
            }

            editAction = TextView.OnEditorActionListener { v, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH && !v.text.isNullOrBlank()) {
                    getListSearchArticles(v.text.toString())
                }
                true
            }

            errorReload = ErrorReload {
                if (mIsLoadHotKeysError) {
                    getListHotKeys()
                } else {
                    mSearchAdapter.retry()
                }
            }

            mViewModel.resultMode.observe(this@SearchActivity, {
                if (it) {
                    enable = true
                    needOverScroll = true
                    adapter = mSearchAdapter.apply {
                        addLoadStateListener { loadState ->
                            refreshing = loadState.refresh is LoadState.Loading
                            statusCode = when (loadState.refresh) {
                                is LoadState.Loading -> RequestStatusCode.Loading
                                is LoadState.Error -> RequestStatusCode.Error
                                else -> {
                                    if (itemCount == 0) RequestStatusCode.Empty
                                    else RequestStatusCode.Succeed
                                }
                            }
                        }
                    }.withLoadStateFooter(
                        PagingLoadStateAdapter { mSearchAdapter.retry() }
                    )

                    listener = OnItemClickListener { position, _ ->
                        mSearchAdapter.getItemData(position).let {
                            ARouter.getInstance().build(Constant.ARouterPath.PATH_WEBVIEW)
                                .withString(Constant.ExtraType.EXTRA_URL, it?.link)
                                .withString(Constant.ExtraType.EXTRA_TITLE, it?.title)
                                .navigation()
                        }
                    }

                    longListener = OnItemLongClickListener { position, _ ->
                        mSearchAdapter.getItemData(position)?.let { article ->
                            showCollectionDialog(article, position)
                        }
                    }
                } else {
                    enable = false
                    needOverScroll = false
                    adapter = mHistoryAdapter.apply { onKeyRemove = { mViewModel.updateHistory() } }
                    listener = OnItemClickListener { position, _ ->
                        mHistoryAdapter.getItemData(position)?.let { key ->
                            searchContent.setText(key)
                            getListSearchArticles(key)
                        }
                    }
                }
            })

            mViewModel.listHistory.observe(this@SearchActivity, {
                mHistoryAdapter.updateHistory(it)
            })
        }

    }

    private fun showCollectionDialog(article: UserArticleDetail, position: Int) =
        alert(
            if (article.collect) "「${article.title.renderHtml()}」已收藏"
            else " 是否收藏 「${article.title.renderHtml()}」"
        ) {
            yesButton {
                if (!article.collect) launch { collectArticle(article.id, position) }
            }

            if (!article.collect) noButton { }
        }.show()

    @OptIn(ExperimentalCoroutinesApi::class)
    private suspend fun collectArticle(id: Int, position: Int) {
        mCollectionViewModel.collectArticle(id).catch {
            this@SearchActivity.toast(R.string.no_network)
        }.onStart {
            mAppViewModel.showLoading()
        }.onCompletion {
            mAppViewModel.dismissLoading()
        }.collectLatest {
            it.handleResult {
                mSearchAdapter.getItemData(position)?.collect = true
                this@SearchActivity.toast(R.string.add_favourite_succeed)
            }
        }
    }

    /**
     * 搜索热词
     */
    private fun getListHotKeys() {
        launch {
            mViewModel.getListHotKeys()
                .catch {
                    mIsLoadHotKeysError = true
                    mBinding.statusCode = RequestStatusCode.Error
                }.onStart {
                    mBinding.refreshing = true
                    mBinding.statusCode = RequestStatusCode.Loading
                }.collectLatest {
                    addLabel(it)
                    mBinding.refreshing = false
                    mBinding.statusCode = if (it.isEmpty()) RequestStatusCode.Empty else RequestStatusCode.Succeed
                    mBinding.hasHistory = SearchHistoryUtils.hasHistory(this@SearchActivity)
                    mViewModel.updateHistory()
                }
        }
    }

    /**
     * 搜索
     */
    private fun getListSearchArticles(keyword: String) {
        if (mKey == keyword) return

        mKey = keyword
        mViewModel.resultMode.postValue(true)
        mBinding.searchContent.hideSoftInput()
        SearchHistoryUtils.saveHistory(this, keyword.trim())

        launch {
            mViewModel.getListSearchResult(keyword)
                .catch {
                    mIsLoadHotKeysError = false
                }.collectLatest {
                    mSearchAdapter.submitData(it)
                }
        }
    }

    /**
     * 添加热词
     */
    private fun addLabel(hotKeys: MutableList<HotKeyData>) {
        val marginValue = FResUtil.dp2px(8f)
        val paddingValue = FResUtil.dp2px(6f)
        mBinding.keysBox.removeAllViews()

        hotKeys.forEach {
            val name = it.name
            val lp = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                rightMargin = marginValue
                topMargin = marginValue
            }

            val label = TextView(this).apply {
                text = name
                textSize = 14f
                setBackgroundResource(R.drawable.res_layer_white_stroke_corner)
                layoutParams = lp
                setPadding(paddingValue, paddingValue, paddingValue, paddingValue)
                setOnClickListener {
                    mBinding.searchContent.setText(name)
                    getListSearchArticles(name)
                }
            }

            mBinding.keysBox.addView(label)
        }
    }
}