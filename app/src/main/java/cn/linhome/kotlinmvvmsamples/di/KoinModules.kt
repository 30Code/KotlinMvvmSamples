package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.common.network.RetrofitManager
import cn.linhome.common.ui.LoadingDialog
import cn.linhome.common.vm.AppViewModel
import cn.linhome.kotlinmvvmsamples.ui.main.MainActivity
import cn.linhome.kotlinmvvmsamples.ui.main.MainRepository
import cn.linhome.kotlinmvvmsamples.ui.main.MainViewModel
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
}

val repositoryModule = module {
    single { MainRepository(get()) }
}

val fragmentModule = module {

}

val adapterModule = module {

}

val dialogModule = module {
    scope<MainActivity> {
        scoped {
            LoadingDialog()
        }
    }
}