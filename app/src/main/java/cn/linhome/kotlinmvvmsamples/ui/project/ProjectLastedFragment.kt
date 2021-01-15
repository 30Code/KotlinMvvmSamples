package cn.linhome.kotlinmvvmsamples.ui.project

import androidx.navigation.Navigation
import cn.linhome.kotlinmvvmsamples.BR
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.BaseBindAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseVMFragment
import cn.linhome.kotlinmvvmsamples.databinding.FragmentProjectTypeBinding
import cn.linhome.kotlinmvvmsamples.model.bean.Article
import cn.linhome.kotlinmvvmsamples.ui.project.vm.ProjectLastedViewModel
import cn.linhome.kotlinmvvmsamples.view.CustomLoadMoreView
import com.chad.library.adapter.base.BaseQuickAdapter
import kotlinx.android.synthetic.main.fragment_project_type.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class ProjectLastedFragment : BaseVMFragment<FragmentProjectTypeBinding>(R.layout.fragment_project_type) {

    private val projectLastedViewModel by viewModel<ProjectLastedViewModel>()
    private val projectLastedAdapter by lazy { BaseBindAdapter<Article>(R.layout.item_project_lasted, BR.article) }

    override fun initView() {
        binding.run {
            viewModel = projectLastedViewModel
            adapter = projectLastedAdapter
        }
        initRecycleView()
    }

    private fun initRecycleView() {
        projectLastedAdapter.run {
            onItemChildClickListener = this@ProjectLastedFragment.itemClickListener

            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({loadMore()}, lastedProjectRecycleView)
        }
    }

    private fun loadMore() {
        projectLastedViewModel.getLatestProjectList(false)
    }

    override fun initData() {
        projectLastedViewModel.getLatestProjectList(true)
    }

    override fun startObserve() {
        projectLastedViewModel.getUiState().observe(viewLifecycleOwner, {
            it.showSuccess?.let { list ->
                projectLastedAdapter.run {

                    setEnableLoadMore(false)
                    if (it.isRefresh) replaceData(list.datas)
                    else addData(list.datas)
                    setEnableLoadMore(true)
                    loadMoreComplete()

                }
            }

            if (it.showEnd) projectLastedAdapter.loadMoreEnd()
        })
    }

    private val itemClickListener = BaseQuickAdapter.OnItemChildClickListener { _, view, position ->
        when(view.id) {
            R.id.articleStar -> {
                if (isLogin()) {
                    projectLastedAdapter.run {
                        data[position].run {
                            collect = !collect
                            projectLastedViewModel.collectArticle(id, collect)
                        }
                        notifyDataSetChanged()
                    }
                } else {
                    Navigation.findNavController(lastedProjectRecycleView).navigate(R.id.action_tab_to_login)
                }
            }
        }
    }
}