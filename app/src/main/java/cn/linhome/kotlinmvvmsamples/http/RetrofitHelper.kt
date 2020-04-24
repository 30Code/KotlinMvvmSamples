package cn.linhome.kotlinmvvmsamples.http

import cn.linhome.kotlinmvvmsamples.model.api.ApiService

/**
 *  des : RetrofitHelper
 *  Created by 30Code
 *  date : 2020/4/24
 */
object RetrofitHelper : RetrofitFactory<ApiService>() {

    override fun baseUrl(): String = ApiService.BASE_URL

    override fun attachService(): Class<ApiService> = ApiService::class.java
}