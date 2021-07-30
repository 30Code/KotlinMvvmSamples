package cn.linhome.home.ui.collect

import android.os.Bundle
import android.view.View
import androidx.navigation.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.OnItemClickListener
import cn.linhome.common.constant.Constant
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.home.R
import cn.linhome.home.databinding.FragmentCollectBinding
import cn.linhome.home.vm.CollectedArticlesListViewModel
import com.alibaba.android.arouter.launcher.ARouter
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : 收藏列表
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CollectArticlesListFragment : BaseFragment<FragmentCollectBinding>() {

    private val mViewModel by viewModel<CollectedArticlesListViewModel>()

    private val mAdapter by lifecycleScope.inject<CollectedArticlesListPagingAdapter>()

    override fun getLayoutId(): Int = R.layout.fragment_collect

    override fun actionOnViewInflate() {
        launch {
            mViewModel.getListCollectArticles()
                .catch { mBinding?.statusCode = RequestStatusCode.Error }
                .collectLatest { mAdapter.submitData(it) }
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            toolbar.run {
                title = getString(R.string.collection)
                setNavigationIcon(R.drawable.arrow_back)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
            }

            refreshColor = R.color.colorAccent

            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                mAdapter.refresh()
            }

            adapter = mAdapter.apply {
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
                PagingLoadStateAdapter { mAdapter.retry() }
            )

            divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)

            itemClick = OnItemClickListener { position, _ ->
                mAdapter.getItemData(position)?.let {
                    ARouter.getInstance().build(Constant.ARouterPath.PATH_WEBVIEW)
                        .withString(Constant.ExtraType.EXTRA_URL, it.link)
                        .withString(Constant.ExtraType.EXTRA_TITLE, it.title)
                        .navigation()
                }
            }
        }
    }
}