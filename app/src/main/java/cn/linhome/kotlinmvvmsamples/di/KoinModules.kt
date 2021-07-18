package cn.linhome.kotlinmvvmsamples.di

import cn.linhome.common.network.RetrofitManager
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

}

val repositoryModule = module {

}

val fragmentModule = module {

}

val adapterModule = module {

}

val dialogModule = module {

}