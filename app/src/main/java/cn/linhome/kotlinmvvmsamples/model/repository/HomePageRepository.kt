package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.model.bean.Banner

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class HomePageRepository : BaseRepository() {

    suspend fun getBanner() : ResultData<List<Banner>> {
        return safeApiCall(call = {requestBanners()}, errorMessage = "")
    }

    private suspend fun requestBanners() : ResultData<List<Banner>> = executeResponse(RetrofitClient.service.getBanner())

    suspend fun getArticleList(page: Int): ResultData<ArticleList> {
        return safeApiCall(call = { requestArticleList(page) }, errorMessage = "")
    }

    private suspend fun requestArticleList(page: Int): ResultData<ArticleList> = executeResponse(RetrofitClient.service.getHomeArticles(page))
}