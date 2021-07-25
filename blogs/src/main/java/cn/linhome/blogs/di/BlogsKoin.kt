package cn.linhome.blogs.di

import cn.linhome.blogs.api.BlogsApiService
import cn.linhome.blogs.ui.BlogArticlesFragment
import cn.linhome.blogs.ui.BlogArticlesPagingAdapter
import cn.linhome.blogs.vm.BlogsArticleViewModel
import cn.linhome.blogs.ui.BlogsRepository
import cn.linhome.common.network.RetrofitManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 *  des : BlogsKoin
 *  Created by 30Code
 *  date : 2021/7/25
 */
val moduleBlogs = module {

    single { RetrofitManager.initRetrofit().getService(BlogsApiService::class.java) }

    viewModel { BlogsArticleViewModel(get()) }

    single { BlogsRepository(get()) }

    scope<BlogArticlesFragment> {
        scoped { BlogArticlesPagingAdapter() }
    }

}