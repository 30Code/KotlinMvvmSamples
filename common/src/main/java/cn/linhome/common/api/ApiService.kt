package cn.linhome.common.api

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.*
import retrofit2.http.*

/**
 *  des :ApiService
 *  Created by 30Code
 *  date : 2021/7/18
 */
interface ApiService {

    /**
     * 退出
     */
    @GET("/user/logout/json")
    suspend fun loginOut(): BaseResultData<Any?>

    /**
     * 个人积分查询
     */
    @GET("/lg/coin/userinfo/json")
    suspend fun fetchUserCoins(): BaseResultData<CoinsData?>

    /**
     * 广场分享文章列表
     */
    @GET("/user_article/list/{page}/json")
    suspend fun shareArticles(@Path("page") page: Int): BaseResultData<UserArticleData>

    /**
     * 项目分类
     */
    @GET("/project/tree/json")
    suspend fun projectCategory(): BaseResultData<MutableList<ArticleCategoriesData>>

    /**
     * 返回项目分类下的所有项目列表，cid 查看 [ArticleCategoriesData] #id
     */
    @GET("/project/list/{page}/json")
    suspend fun projectList(
        @Path("page") page: Int,
        @Query("cid") cid: Int): BaseResultData<ProjectDetailResult>

    /**
     *  收藏文章，项目
     */
    @POST("/lg/collect/{id}/json")
    suspend fun collectArticleOrProject(@Path("id") id: Int): BaseResultData<Any?>

    /**
     * 文章列表中取消收藏文章
     * http://www.wanandroid.com/lg/uncollect_originId/2333/json
     * @param id
     */
    suspend fun cancelCollectArticle(@Path("id") id: Int): BaseResultData<Any?>

    /**
     * 收藏列表中取消收藏文章
     */
    @POST("/lg/uncollect/{articleId}/json")
    @FormUrlEncoded
    suspend fun unCollectCollection(@Path("articleId") articleId: Int, @Field("originId") originId: Int): BaseResultData<Any?>
}