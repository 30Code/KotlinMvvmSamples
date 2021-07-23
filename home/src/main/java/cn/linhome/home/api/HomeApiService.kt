package cn.linhome.home.api

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.*
import retrofit2.Response
import retrofit2.http.*

/**
 *  des : HomeApiService
 *  Created by 30Code
 *  date : 2021/7/18
 */
interface HomeApiService {

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page : Int) :BaseResultData<UserArticleData>

}