package cn.linhome.home.api

import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.*
import cn.linhome.home.entity.BannerData
import cn.linhome.home.entity.CoinRankData
import cn.linhome.home.entity.CoinRecordData
import retrofit2.Response
import retrofit2.http.*

/**
 *  des : HomeApiService
 *  Created by 30Code
 *  date : 2021/7/18
 */
interface HomeApiService {

    /**
     * 轮播图
     */
    @GET("banner/json")
    suspend fun getBanner() : BaseResultData<MutableList<BannerData>>

    /**
     * 首页文章列表
     */
    @GET("/article/list/{page}/json")
    suspend fun getHomeArticles(@Path("page") page : Int) :BaseResultData<UserArticleData>

    /**
     * 个人积分获取记录
     */
    @GET("/lg/coin/list/{page}/json")
    suspend fun getListCoinRecord(@Path("page") page : Int) : BaseResultData<CoinRecordData>

    /**
     * 积分排行榜
     */
    @GET("/coin/rank/{page}/json")
    suspend fun getListCoinRank(@Path("page") page : Int) : BaseResultData<CoinRankData>
}