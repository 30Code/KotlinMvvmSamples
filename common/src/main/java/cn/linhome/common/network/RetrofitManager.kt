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
    private val BASE_URL = "https://www.wanandroid.com"

    val apiService : ApiService = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(genericOkClient())
        .build().create(ApiService::class.java)

    private fun genericOkClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
            logger.jsonPrint(false) { message }
        }

        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .connectTimeout(5_000L, TimeUnit.MILLISECONDS)
            .readTimeout(10_000, TimeUnit.MILLISECONDS)
            .writeTimeout(30_000, TimeUnit.MILLISECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }
}