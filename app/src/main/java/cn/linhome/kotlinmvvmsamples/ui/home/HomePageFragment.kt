package cn.linhome.kotlinmvvmsamples.ui.home

import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.HomePageArticleAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseVMFragment
import cn.linhome.kotlinmvvmsamples.databinding.FragmentHomepageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class HomePageFragment : BaseVMFragment<FragmentHomepageBinding>(R.layout.fragment_homepage) {

    private val mHomePageViewModel by viewModel<HomePageViewModel>()
    private val homePageAdapter by lazy { HomePageArticleAdapter() }

    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()

    override fun initView() {
        binding.run {
            viewModel = mHomePageViewModel
            adapter = homePageAdapter
        }
    }

    override fun initData() {

    }

    override fun startObserve() {

    }
}