package cn.linhome.kotlinmvvmsamples.ui.square

import cn.linhome.kotlinmvvmsamples.BR
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.BaseBindAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseVMFragment
import cn.linhome.kotlinmvvmsamples.databinding.FragmentHomepageBinding
import cn.linhome.kotlinmvvmsamples.databinding.FragmentSquareBinding
import cn.linhome.kotlinmvvmsamples.model.bean.Article
import cn.linhome.kotlinmvvmsamples.ui.square.vm.SquareViewModel
import cn.linhome.kotlinmvvmsamples.view.CustomLoadMoreView
import cn.linhome.lib.utils.context.FToast
import kotlinx.android.synthetic.main.fragment_square.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class SquareFragment : BaseVMFragment<FragmentSquareBinding>(R.layout.fragment_square) {

    private val squareViewModel by viewModel<SquareViewModel>()
    private val squareAdapter by lazy { BaseBindAdapter<Article>(R.layout.item_square, BR.article) }

    override fun initView() {
        binding.run {
            viewModel = squareViewModel
            adapter = squareAdapter
        }
        initRecycleView()
    }

    override fun initData() {
        squareViewModel.getSquareArticleList(true)
    }

    private fun initRecycleView() {
        squareAdapter.run {
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({ loadMore() }, squareRecycleView)
        }
    }

    private fun loadMore() {
        squareViewModel.getSquareArticleList(false)
    }

    override fun startObserve() {
        squareViewModel.getUiState().observe(viewLifecycleOwner, {

            it.showSuccess?.let { list ->
                squareAdapter.run {

                    setEnableLoadMore(false)
                    if (it.isRefresh) replaceData(list.datas)
                    else addData(list.datas)
                    setEnableLoadMore(true)
                    loadMoreComplete()

                }
            }

            if (it.showEnd) squareAdapter.loadMoreEnd()

            it.showError?.let { message ->
                if (message.isBlank()) {
                    FToast.show("网络异常")
                } else {
                    FToast.show(message)
                }
            }
        })
    }
}