package cn.linhome.kotlinmvvmsamples.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import cn.linhome.home.di.moduleHome
import cn.linhome.kotlinmvvmsamples.BuildConfig
import cn.linhome.kotlinmvvmsamples.di.*
import cn.linhome.lib.utils.context.FContext
import com.alibaba.android.arouter.launcher.ARouter
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/18
 */
class App  : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = applicationContext

        FContext.set(instance)

        initKoin()
        initARouter()
    }

    /**
     * 初始化Koin
     */
    private fun initKoin() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@App)
            androidFileProperties()
            modules(
                dataSourceModule, repositoryModule, viewModelModule,
                fragmentModule, dialogModule, adapterModule, moduleHome
            )
        }
    }

    /**
     * 初始化ARouter
     */
    private fun initARouter() {
        ARouter.init(this)
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var instance: Context
    }
}