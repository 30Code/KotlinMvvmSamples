package cn.linhome.common.network

import cn.linhome.common.BuildConfig
import cn.linhome.common.constant.Constant
import cn.linhome.common.base.jsonPrint
import cn.linhome.common.base.kLogger
import cn.linhome.common.constant.HttpConstant
import cn.linhome.common.network.interceptor.CacheInterceptor
import cn.linhome.common.network.interceptor.HeaderInterceptor
import cn.linhome.common.network.interceptor.SaveCookieInterceptor
import cn.linhome.lib.utils.context.FContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *  des : RetrofitManager
 *  Created by 30Code
 *  date : 2021/7/18
 */
object RetrofitManager {
    private val logger = kLogger<RetrofitManager>()

    private var mRetrofit: Retrofit? = null

    fun initRetrofit(): RetrofitManager {
        mRetrofit = Retrofit.Builder()
            .baseUrl(Constant.BASE_URL)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>): T {
        if (mRetrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit必须初始化")
        } else {
            return mRetrofit!!.create(serviceClass)
        }
    }

    /**
     * 获取 OkHttpClient
     */
    private fun getOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor() { message ->
            logger.jsonPrint(false) { message }
        }
        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.NONE
        }

        //设置 请求的缓存的大小跟位置
        val cacheFile = File(FContext.get().cacheDir, "cache")
        val cache = Cache(cacheFile, HttpConstant.MAX_CACHE_SIZE)

        builder.run {
            addInterceptor(httpLoggingInterceptor)
            addInterceptor(HeaderInterceptor())
            addInterceptor(SaveCookieInterceptor())
            addInterceptor(CacheInterceptor())
            cache(cache)  //添加缓存
            connectTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            readTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            writeTimeout(HttpConstant.DEFAULT_TIMEOUT, TimeUnit.SECONDS)
            retryOnConnectionFailure(true) // 错误重连
            // cookieJar(CookieManager())
        }
        return builder.build()
    }
}