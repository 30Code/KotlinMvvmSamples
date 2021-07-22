package cn.linhome.kotlinmvvmsamples.ui.home

import android.os.Bundle
import android.view.View
import androidx.paging.LoadState
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.OnItemClickListener
import cn.linhome.common.base.OnItemLongClickListener
import cn.linhome.common.vm.AppViewModel
import cn.linhome.common.widget.ErrorReload
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : HomeFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    private val mViewModel by viewModel<HomeArticleViewModel>()

    private val mAdapter by lifecycleScope.inject<HomeArticlePagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun actionOnViewInflate() {
        getHomeArticles()

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

            }.withLoadStateFooter(
                PagingLoadStateAdapter {
                    mAdapter.retry()
                }
            )

            itemClick = OnItemClickListener { position, view ->
                mAdapter.getItemData(position)?.let {

                }
            }

            itemLongClick = OnItemLongClickListener { position, view ->
                mAdapter.getItemData(position)?.let {

                }
            }

            errorReload = ErrorReload {
                mAdapter.retry()
            }
        }
    }

    private fun getHomeArticles() {
        launch {
            mViewModel.getHomeArticles()
                .catch {
                    mBinding?.statusCode = RequestStatusCode.Error
                }.collectLatest {
                    mAdapter.submitData(it)
                }
        }
    }
}