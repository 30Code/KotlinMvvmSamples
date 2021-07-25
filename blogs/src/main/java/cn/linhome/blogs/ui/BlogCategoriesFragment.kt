package cn.linhome.blogs.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.linhome.blogs.R
import cn.linhome.blogs.databinding.FragmentBlogCategoriesBinding
import cn.linhome.blogs.vm.BlogsArticleViewModel
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.base.setupWithViewPager2
import cn.linhome.common.entity.ArticleCategoriesData
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : BlogCategoriesFragment
 *  Created by 30Code
 *  date : 2021/7/25
 */
class BlogCategoriesFragment : BaseFragment<FragmentBlogCategoriesBinding>() {

    private val mListBlogCategories = mutableListOf<ArticleCategoriesData>()

    private val mViewModel by viewModel<BlogsArticleViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_blog_categories

    override fun actionOnViewInflate() {
        getListProjectCategory()
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
        mBinding?.run {
            vpBlogs.run {
                offscreenPageLimit = 3
                adapter = object : FragmentStateAdapter(this@BlogCategoriesFragment) {
                    override fun getItemCount(): Int = mListBlogCategories.size

                    override fun createFragment(position: Int): Fragment = chooseFragment(position)

                }
            }

            tlBolgCategories.setupWithViewPager2(vpBlogs, mListBlogCategories)
        }
    }

    private fun chooseFragment(position: Int): Fragment {
        return BlogArticlesFragment.newInstance(mListBlogCategories[position].id)
    }

    private fun getListProjectCategory() {
        launch {
            mViewModel.getBlogsCategories().collectLatest {
                mListBlogCategories.clear()
                mListBlogCategories.addAll(it)
                mBinding?.vpBlogs?.adapter?.notifyDataSetChanged()
            }
        }
    }
}