package cn.linhome.kotlinmvvmsamples.ui.share

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.*
import cn.linhome.common.vm.AppViewModel
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentShareBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : ShareFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class ShareFragment : BaseFragment<FragmentShareBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by viewModel<UserArticleViewModel>()

    private val mAdapter by lifecycleScope.inject<UserArticlePagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_share

    override fun actionOnViewInflate() {
        fetchSharedArticles()

        mAppViewModel.reloadHomeData.observe(this, {
            mAdapter.refresh()
        })
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            refreshColor = R.color.colorAccent
            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mAdapter.refresh()
            }

            adapter = mAdapter.apply {
                userListener = {id, nick ->

                }

                addLoadStateListener { loadState ->
                    refreshing = loadState.refresh is LoadState.Loading
                    statusCode = when (loadState.refresh) {
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
                mAdapter.refresh()
            })

            itemClick = OnItemClickListener { position, view ->
                mAdapter.getItemData(position)?.let {

                }
            }

            itemLongClick = OnItemLongClickListener { position, view ->
                mAdapter.getItemData(position)?.let {

                }
            }

            gesture = DoubleClickListener {
                doubleTap = {
                    rvArticleList.scrollToTop()
                }
            }

            errorReload = ErrorReload {
                mAdapter.refresh()
            }
        }
    }

    private fun fetchSharedArticles() {
        launch {
            mViewModel.getSharedArticles().catch {
                mBinding?.statusCode = RequestStatusCode.Error
                mBinding?.indicator = resources.getString(R.string.text_place_holder)
            }.collectLatest {
                mBinding?.indicator = resources.getString(R.string.share_articles)
                mAdapter.submitData(it)
            }
        }
    }
}