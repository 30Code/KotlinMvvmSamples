package cn.linhome.kotlinmvvmsamples.ui.project

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.OnItemClickListener
import cn.linhome.common.base.OnItemLongClickListener
import cn.linhome.common.base.handleResult
import cn.linhome.common.constant.Constant
import cn.linhome.common.vm.AppViewModel
import cn.linhome.common.vm.CollectionViewModel
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentProjectTypeBinding
import com.alibaba.android.arouter.launcher.ARouter
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
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : ProjectTypeFragment
 *  Created by 30Code
 *  date : 2021/7/21
 */
class ProjectTypeFragment : BaseFragment<FragmentProjectTypeBinding>() {

    private val mCid by lazy { arguments?.getInt(CID) }

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by viewModel<ProjectViewModel>()

    private val mCollectionViewModel by viewModel<CollectionViewModel>()

    private val mAdapter by lifecycleScope.inject<ProjectTypePagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_project_type

    override fun actionOnViewInflate() {
        launch {
            mCid?.let {
                mViewModel.getProjects(it)
                    .catch {
                        mBinding?.statusCode = RequestStatusCode.Error
                    }.collectLatest {
                        mAdapter.submitData(it)
                    }
            }
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            refreshColor = R.color.colorAccent
            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mAdapter.refresh()
            }

            adapter = mAdapter.apply {
                addLoadStateListener {
                    refreshing = it.refresh is LoadState.Loading
                    statusCode = when (it.refresh) {
                        is LoadState.Loading -> RequestStatusCode.Loading
                        is LoadState.Error -> RequestStatusCode.Error
                        else -> {
                            if (itemCount == 0) {
                                RequestStatusCode.Empty
                            } else {
                                RequestStatusCode.Succeed
                            }
                        }
                    }
                }
            }.withLoadStateFooter(PagingLoadStateAdapter {
                mAdapter.retry()
            })

            itemClick = OnItemClickListener { position, view ->
                mAdapter.getItemData(position)?.let {
                    ARouter.getInstance()
                        .build(Constant.ARouterPath.PATH_WEBVIEW)
                        .withString(Constant.ExtraType.EXTRA_URL, it.link)
                        .withString(Constant.ExtraType.EXTRA_TITLE, it.title)
                        .navigation()
                }
            }

            itemLongClick = OnItemLongClickListener { position, view ->
                mAdapter.getItemData(position)?.let { article ->
                    context?.alert(
                        if (article.collect) "「${article.title}」已收藏"
                        else " 是否收藏 「${article.title}」"
                    ) {
                        yesButton {
                            if (!article.collect) collectArticle(article.id, position)
                        }
                        if (!article.collect) noButton { }
                    }?.show()
                }
            }

            errorReload = ErrorReload {
                mAdapter.retry()
            }
        }
    }

    /**
     * 收藏
     */
    private fun collectArticle(id : Int, position : Int) {
        launch {
            mCollectionViewModel.collectArticle(id)
                .catch { context?.toast(R.string.no_network) }
                .onStart {
                    mAppViewModel.showLoading()
                }.onCompletion {
                    mAppViewModel.dismissLoading()
                }.collectLatest {
                    it.handleResult {
                        mAdapter.getItemData(position)?.collect = true
                        context?.toast(R.string.add_favourite_succeed)
                    }
                }
        }
    }

    companion object {
        private const val CID = "projectCid"
        fun newInstance(cid : Int) : ProjectTypeFragment {
            val fragment = ProjectTypeFragment()
            val bundle = Bundle()
            bundle.putInt(CID, cid)
            fragment.arguments = bundle
            return fragment
        }
    }
}