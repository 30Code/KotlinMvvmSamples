package cn.linhome.home.di

import androidx.fragment.app.Fragment
import cn.linhome.common.adapter.ViewPager2FragmentAdapter
import cn.linhome.common.network.RetrofitManager
import cn.linhome.home.adapter.HomeMultiArticlePagingAdapter
import cn.linhome.home.api.HomeApiService
import cn.linhome.home.ui.coin.CoinRankPagingAdapter
import cn.linhome.home.ui.coin.CoinRecordPagingAdapter
import cn.linhome.home.ui.coin.CoinRepository
import cn.linhome.home.ui.coin.CoinSubFragment
import cn.linhome.home.ui.collect.CollectArticlesListFragment
import cn.linhome.home.ui.collect.CollectedArticlesListPagingAdapter
import cn.linhome.home.ui.collect.CollectedArticlesListRepository
import cn.linhome.home.ui.home.HomeArticleRepository
import cn.linhome.home.ui.home.HomeFragment
import cn.linhome.home.vm.CoinViewModel
import cn.linhome.home.vm.CollectedArticlesListViewModel
import cn.linhome.home.vm.HomeArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : HomeKoin
 *  Created by 30Code
 *  date : 2021/7/23
 */
val moduleHome = module {

    single { RetrofitManager.initRetrofit().getService(HomeApiService::class.java) }

    viewModel { HomeArticleViewModel(get()) }
    viewModel { CoinViewModel(get()) }
    viewModel { CollectedArticlesListViewModel(get()) }

    single { HomeArticleRepository(get()) }
    single { CoinRepository(get()) }
    single { CollectedArticlesListRepository(get()) }

    scope<HomeFragment> {
        scoped {
//            HomeArticlePagingAdapter()
            HomeMultiArticlePagingAdapter()
        }
    }

    factory { (type : Int) ->
        CoinSubFragment.instance(type)
    }

    factory { (holder: Fragment, children: MutableList<Fragment>) ->
        ViewPager2FragmentAdapter(holder, children)
    }

    scope<CoinSubFragment> {
        scoped { CoinRecordPagingAdapter() }
        scoped { CoinRankPagingAdapter() }
    }

    scope<CollectArticlesListFragment> {
        scoped { CollectedArticlesListPagingAdapter() }
    }

}