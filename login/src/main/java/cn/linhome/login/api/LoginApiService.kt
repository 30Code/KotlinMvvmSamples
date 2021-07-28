package cn.linhome.login.api

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.*
import retrofit2.Response
import retrofit2.http.*

/**
 *  des : LoginApiService
 *  Created by 30Code
 *  date : 2021/7/28
 */
interface LoginApiService {

    /**
     * 登录
     */
    @POST("/user/login")
    @FormUrlEncoded
    suspend fun login(@Field("username") username : String,
                      @Field("password") password: String) : Response<BaseResultData<UserData>>

    /**
     * 注册
     */
    @POST("/user/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("repassword") repassword: String): Response<BaseResultData<UserData>>

}