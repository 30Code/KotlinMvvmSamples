package cn.linhome.kotlinmvvmsamples.ui.project

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.setupWithViewPager2
import cn.linhome.common.bean.ProjectCategoryData
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentProjectBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : ProjectFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    private val mListProjectCategory = mutableListOf<ProjectCategoryData>()

    private val mViewModel by viewModel<ProjectViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun actionOnViewInflate() {
        launch {
            mViewModel.getCategories().collectLatest {
                mListProjectCategory.clear()
                mListProjectCategory.addAll(it)
//                mBinding?.vpProject?.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            tabLayout.setupWithViewPager2(vpProject, mListProjectCategory)
        }
    }
}