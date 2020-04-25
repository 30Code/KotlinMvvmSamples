package cn.linhome.kotlinmvvmsamples.model.api

import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 *  des : ApiService
 *  Created by 30Code
 *  date : 2020/4/24
 */
interface ApiService {

    companion object {
        const val BASE_URL = "https://www.wanandroid.com"
    }

    @POST("user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username : String,
                      @Field("password") password : String) : BaseResponse<LoginData>

}