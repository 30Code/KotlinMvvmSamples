package cn.linhome.kotlinmvvmsamples.model.api

import cn.linhome.kotlinmvvmsamples.model.bean.*
import retrofit2.http.*

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

    @GET("/banner/json")
    suspend fun getBanner(): BaseResponse<List<Banner>>

    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page: Int): BaseResponse<ArticleList>

    /**
     * 获取公众号列表
     * http://wanandroid.com/wxarticle/chapters/json
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getWXChapters() : BaseResponse<MutableList<WXChapterBean>>

    /**
     * 知识体系下的文章
     * http://www.wanandroid.com/article/list/0/json?cid=168
     * @param page
     * @param cid
     */
    @GET("article/list/{page}/json")
    suspend fun getArticlesList(@Path("page") page: Int, @Query("cid") cid: Int) : BaseResponse<ArticleResponseBody>

    @GET("/user_article/list/{page}/json")
    suspend fun getSquareArticleList(@Path("page") page: Int): BaseResponse<ArticleList>

    @GET("/article/listproject/{page}/json")
    suspend fun getLastedProject(@Path("page") page: Int): BaseResponse<ArticleList>

}