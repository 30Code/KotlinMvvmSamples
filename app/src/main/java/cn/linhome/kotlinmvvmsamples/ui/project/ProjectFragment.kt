package cn.linhome.kotlinmvvmsamples.ui.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.setupWithViewPager2
import cn.linhome.common.entity.ArticleCategoriesData
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

    private val mListProjectCategory = mutableListOf<ArticleCategoriesData>()

    private val mViewModel by viewModel<ProjectViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun actionOnViewInflate() {
        getListProjectCategory()
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            vpProject.run {
                offscreenPageLimit = 3
                adapter = object : FragmentStateAdapter(this@ProjectFragment) {
                    override fun getItemCount(): Int = mListProjectCategory.size

                    override fun createFragment(position: Int): Fragment = chooseFragment(position)

                }
            }

            tabLayout.setupWithViewPager2(vpProject, mListProjectCategory)
        }
    }

    private fun chooseFragment(position: Int): Fragment {
        return ProjectTypeFragment.newInstance(mListProjectCategory[position].id)
    }

    private fun getListProjectCategory() {
        launch {
            mViewModel.getCategories().collectLatest {
                mListProjectCategory.clear()
                mListProjectCategory.addAll(it)
                mBinding?.vpProject?.adapter?.notifyDataSetChanged()
            }
        }
    }
}