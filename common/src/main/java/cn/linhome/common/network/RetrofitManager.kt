package cn.linhome.common.network

import cn.linhome.common.base.jsonPrint
import cn.linhome.common.base.kLogger
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 *  des :RetrofitManager
 *  Created by 30Code
 *  date : 2021/7/18
 */
object RetrofitManager {
    private val logger = kLogger<RetrofitManager>()
    private var BASE_URL = "https://www.wanandroid.com"

//    val apiService: ApiService = Retrofit.Builder()
//        .baseUrl(BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .client(genericOkClient())
//        .build().create(ApiService::class.java)
//
//
//    private fun genericOkClient(): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
//            logger.jsonPrint(false) { message }
//        }
//
//        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
//
//        return OkHttpClient.Builder()
//            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
//            .readTimeout(10_000, TimeUnit.MILLISECONDS)
//            .writeTimeout(30_000, TimeUnit.MILLISECONDS)
//            .addInterceptor(httpLoggingInterceptor)
//            .build()
//    }

    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cookieJar(LocalCookieJar())
        .addInterceptor(HttpLoggingInterceptor { message ->
                logger.jsonPrint(false) { message }
            }
            .setLevel(HttpLoggingInterceptor.Level.BODY))
        .build()

    private var mRetrofit: Retrofit? = null

    fun initRetrofit(): RetrofitManager {
        mRetrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(mOkClient)
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
}