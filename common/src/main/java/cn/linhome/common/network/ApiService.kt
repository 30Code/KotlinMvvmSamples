package cn.linhome.common.network

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.bean.CoinsData
import cn.linhome.common.bean.UserArticleData
import cn.linhome.common.bean.UserData
import retrofit2.Response
import retrofit2.http.*

/**
 *  des :ApiService
 *  Created by 30Code
 *  date : 2021/7/18
 */
interface ApiService {

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

    /**
     * 退出
     */
    @GET("/user/logout/json")
    suspend fun loginOut(): BaseResultData<Any?>

    /**
     * 个人积分查询
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun fetchUserCoins(@Header("Cookie") cookie: String): BaseResultData<CoinsData?>

    // 广场分享文章列表
    @GET("/user_article/list/{page}/json")
    suspend fun shareArticles(
        @Path("page") page: Int, @Header("Cookie") cookie: String
    ): BaseResultData<UserArticleData>
}