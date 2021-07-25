package cn.linhome.blogs.api

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.ArticleCategoriesData
import cn.linhome.common.entity.UserArticleData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 *  des : BlogsApiService
 *  Created by 30Code
 *  date : 2021/7/25
 */
interface BlogsApiService {

    /**
     * 获取公众号目录
     */
    @GET("/wxarticle/chapters/json")
    suspend fun getBlogsCategory() : BaseResultData<MutableList<ArticleCategoriesData>>

    /**
     * 文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getBlogsArticles(@Path("page") page : Int, @Query("cid") cid: Int) :BaseResultData<UserArticleData>

}