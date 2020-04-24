package cn.linhome.kotlinmvvmsamples.http

import cn.linhome.kotlinmvvmsamples.BuildConfig
import cn.linhome.kotlinmvvmsamples.http.constant.HttpConstant
import cn.linhome.kotlinmvvmsamples.http.interceptor.CacheInterceptor
import cn.linhome.kotlinmvvmsamples.http.interceptor.CookieInterceptor
import cn.linhome.kotlinmvvmsamples.http.interceptor.HeaderInterceptor
import cn.linhome.lib.utils.context.FContext
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
abstract class RetrofitFactory<T> {

    var mService : T
    private var mBaseUrl = ""
    private var mRetrofit : Retrofit? = null

    abstract fun baseUrl() : String

    abstract fun attachService() : Class<T>

    init {
        mBaseUrl = this.baseUrl()
        if (mBaseUrl.isEmpty()) {
            throw RuntimeException("base url can not be empty!")
        }
        mService = getRetrofit()!!.create(this.attachService())
    }

    /**
     * 获取 Retrofit 实例对象
     */
    private fun getRetrofit() : Retrofit? {
        if (mRetrofit == null) {
            synchronized(RetrofitFactory::class.java) {
                if (mRetrofit == null) {
                    mRetrofit = Retrofit.Builder()
                        .baseUrl(mBaseUrl)
                        .client(attachOkHttpClient())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                }
            }
        }
        return mRetrofit
    }

    /**
     * 获取 OkHttpClient 实例对象
     * 子类可重写，自定义 OkHttpClient
     */
    open fun attachOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient().newBuilder()
        val httpLoggingInterceptor = HttpLoggingInterceptor()
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
            addInterceptor(CookieInterceptor())
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