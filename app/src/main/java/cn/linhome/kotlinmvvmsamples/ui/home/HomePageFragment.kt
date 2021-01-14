package cn.linhome.kotlinmvvmsamples.ui.home

import android.view.ViewGroup
import android.widget.LinearLayout
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.adapter.HomePageArticleAdapter
import cn.linhome.kotlinmvvmsamples.base.BaseVMFragment
import cn.linhome.kotlinmvvmsamples.databinding.FragmentHomepageBinding
import cn.linhome.kotlinmvvmsamples.ui.home.vm.HomePageViewModel
import cn.linhome.kotlinmvvmsamples.utils.GlideImageLoader
import cn.linhome.kotlinmvvmsamples.utils.dp2px
import cn.linhome.kotlinmvvmsamples.view.CustomLoadMoreView
import cn.linhome.lib.utils.context.FToast
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import kotlinx.android.synthetic.main.fragment_homepage.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class HomePageFragment : BaseVMFragment<FragmentHomepageBinding>(R.layout.fragment_homepage) {

    private val mHomePageViewModel by viewModel<HomePageViewModel>()
    private val homePageAdapter by lazy { HomePageArticleAdapter() }

    private val banner by lazy { Banner(activity) }
    private val bannerImages = mutableListOf<String>()
    private val bannerTitles = mutableListOf<String>()
    private val bannerUrls = mutableListOf<String>()

    override fun initView() {
        binding.run {
            viewModel = mHomePageViewModel
            adapter = homePageAdapter
        }
        initRecycleView()
        initBanner()
    }

    override fun initData() {
        refresh()
    }

    fun refresh() {
        mHomePageViewModel.getHomePageArticleList(true)
    }

    private fun initRecycleView() {
        homePageAdapter.run {
            if (headerLayoutCount > 0) removeAllHeaderView()
            addHeaderView(banner)
            setLoadMoreView(CustomLoadMoreView())
            setOnLoadMoreListener({loadMore()}, homePageRecycleView)
        }
    }

    private fun initBanner() {
        banner.run {
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, banner.dp2px(200))
            setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            setImageLoader(GlideImageLoader())
        }
    }

    private fun loadMore() {
        mHomePageViewModel.getHomePageArticleList(false)
    }

    override fun startObserve() {
        mHomePageViewModel.apply {
            mBanners.observe(viewLifecycleOwner, { it ->
                it?.let {
                    setBanner(it)
                }
            })

            getUiState().observe(viewLifecycleOwner, {
                it.showSuccess?.let { list ->
                    homePageAdapter.run {
                        setEnableLoadMore(false)
                        if (it.isRefresh) replaceData(list.datas)
                        else addData(list.datas)
                        setEnableLoadMore(true)
                        loadMoreComplete()
                    }
                }

                if (it.showEnd) homePageAdapter.loadMoreEnd()

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

    private fun setBanner(bannerList: List<cn.linhome.kotlinmvvmsamples.model.bean.Banner>) {
        for (banner in bannerList) {
            bannerImages.add(banner.imagePath)
            bannerTitles.add(banner.title)
            bannerUrls.add(banner.url)
        }
        banner.setImages(bannerImages)
            .setBannerTitles(bannerTitles)
            .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
            .setDelayTime(3000)
        banner.start()
    }

    override fun onStart() {
        super.onStart()
        banner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        banner.stopAutoPlay()
    }
}