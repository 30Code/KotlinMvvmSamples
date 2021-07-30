package cn.linhome.home.ui.coin

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import cn.linhome.common.adapter.PagingLoadStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.widget.RequestStatusCode
import cn.linhome.home.R
import cn.linhome.home.databinding.FragmentCoinSubBinding
import cn.linhome.home.vm.CoinViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : 积分，排行版
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CoinSubFragment : BaseFragment<FragmentCoinSubBinding>() {

    private val mViewModel by viewModel<CoinViewModel>()

    private val mRecordAdapter by lifecycleScope.inject<CoinRecordPagingAdapter>()

    private val mRankAdapter by lifecycleScope.inject<CoinRankPagingAdapter>()

    private val mType by lazy { arguments?.getInt("type", 0) ?: 0 }

    override fun getLayoutId(): Int = R.layout.fragment_coin_sub

    override fun actionOnViewInflate() {
        launch {
            if (mType == 0) {
                mViewModel.getListCoinRecord()
                    .catch { mBinding?.statusCode = RequestStatusCode.Error }
                    .collectLatest { mRecordAdapter.submitData(it) }
            } else if (mType == 1) {
                mViewModel.getListCoinRank()
                    .catch { mBinding?.statusCode = RequestStatusCode.Error }
                    .collectLatest { mRankAdapter.submitData(it) }
            }
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            refreshColor = R.color.colorAccent

            val subAdapter = (if (mType == 0) mRecordAdapter else mRankAdapter).apply {
                addLoadStateListener {
                    refreshing = it.refresh is LoadState.Loading
                    statusCode = when (it.refresh) {
                        is LoadState.Loading -> RequestStatusCode.Loading
                        is LoadState.Error -> RequestStatusCode.Error
                        else -> {
                            if (itemCount == 0) RequestStatusCode.Empty
                            else RequestStatusCode.Succeed
                        }
                    }
                }
            }

            refreshListener = SwipeRefreshLayout.OnRefreshListener {
                subAdapter.refresh()
            }

            adapter = subAdapter.withLoadStateFooter(PagingLoadStateAdapter {
                subAdapter.retry()
            })

            divider = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        }
    }

    companion object {
        fun instance(type : Int) = CoinSubFragment().apply {
            arguments = bundleOf(Pair("type", type))
        }
    }
}