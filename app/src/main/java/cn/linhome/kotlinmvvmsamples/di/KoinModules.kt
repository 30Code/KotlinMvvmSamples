package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.common.network.ApiService
import cn.linhome.common.network.RetrofitManager
import cn.linhome.common.ui.LoadingDialog
import cn.linhome.common.vm.AppViewModel
import cn.linhome.kotlinmvvmsamples.ui.main.MainActivity
import cn.linhome.kotlinmvvmsamples.ui.main.MainRepository
import cn.linhome.kotlinmvvmsamples.ui.main.MainViewModel
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectRepository
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectTypeFragment
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectTypePagingAdapter
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectViewModel
import cn.linhome.kotlinmvvmsamples.ui.share.ShareFragment
import cn.linhome.kotlinmvvmsamples.ui.share.UserArticlePagingAdapter
import cn.linhome.kotlinmvvmsamples.ui.share.UserArticleRepository
import cn.linhome.kotlinmvvmsamples.ui.share.UserArticleViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import java.util.*

/**
 *  des :KoinModules
 *  Created by 30Code
 *  date : 2021/7/18
 */
val dataSourceModule = module {
    single { RetrofitManager.initRetrofit().getService(ApiService::class.java) }
    single { Calendar.getInstance() }
}

val viewModelModule = module {
    viewModel { AppViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { ProjectViewModel(get()) }
    viewModel { UserArticleViewModel(get()) }
}

val repositoryModule = module {
    single { MainRepository(get()) }
    single { ProjectRepository(get()) }
    single { UserArticleRepository(get()) }
}

val fragmentModule = module {

}

val adapterModule = module {
    scope<ProjectTypeFragment> {
        scoped { ProjectTypePagingAdapter() }
    }
    scope<ShareFragment> {
        scoped { UserArticlePagingAdapter() }
    }
}

val dialogModule = module {
    scope<MainActivity> {
        scoped {
            LoadingDialog()
        }
    }
}