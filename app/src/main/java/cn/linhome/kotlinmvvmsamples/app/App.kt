package cn.linhome.kotlinmvvmsamples.app

import android.content.Context
import cn.linhome.kotlinmvpsamples.common.GlobalEncryptConverter
import cn.linhome.kotlinmvpsamples.common.GsonObjectConverter
import cn.linhome.kotlinmvvmsamples.BuildConfig
import cn.linhome.kotlinmvvmsamples.di.appModule
import cn.linhome.lib.cache.FDisk
import cn.linhome.lib.utils.extend.FActivityStack
import cn.linhome.library.app.FApplication
import cn.linhome.library.utils.LogUtil
import com.sunday.eventbus.SDEventManager
import de.greenrobot.event.SubscriberExceptionEvent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.properties.Delegates

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class App : FApplication(){

    companion object {
        lateinit var instance : App
        var mContext : Context by Delegates.notNull()

        fun exitApp(isBackground : Boolean){
            FActivityStack.getInstance().finishAllActivity()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        mContext = applicationContext

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

    override fun onCreateMainProcess() {
        LogUtil.isDebug = BuildConfig.DEBUG
        SDEventManager.register(this)
        FActivityStack.getInstance().setDebug(BuildConfig.DEBUG)
        //FDisk
        FDisk.init(this)
        FDisk.setGlobalObjectConverter(GsonObjectConverter())
        FDisk.setGlobalEncryptConverter(GlobalEncryptConverter())
    }

    fun onEventMainThread(event: SubscriberExceptionEvent) {
        LogUtil.e("onEventMainThread:" + event.throwable.toString())
    }

    override fun onTerminate() {
        SDEventManager.unregister(this)
        super.onTerminate()
    }
}