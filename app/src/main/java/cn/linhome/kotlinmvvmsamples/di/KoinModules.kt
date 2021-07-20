package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.common.network.RetrofitManager
import cn.linhome.common.ui.LoadingDialog
import cn.linhome.common.vm.AppViewModel
import cn.linhome.kotlinmvvmsamples.ui.main.MainActivity
import cn.linhome.kotlinmvvmsamples.ui.main.MainRepository
import cn.linhome.kotlinmvvmsamples.ui.main.MainViewModel
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
    single { RetrofitManager.apiService }
    single { Calendar.getInstance() }
}

val viewModelModule = module {
    viewModel { AppViewModel() }
    viewModel { MainViewModel(get()) }
    viewModel { UserArticleViewModel(get()) }
}

val repositoryModule = module {
    single { MainRepository(get()) }
    single { UserArticleRepository(get()) }
}

val fragmentModule = module {

}

val adapterModule = module {
    scope<ShareFragment> {
        scoped { UserArticlePagingAdapter }
    }
}

val dialogModule = module {
    scope<MainActivity> {
        scoped {
            LoadingDialog()
        }
    }
}