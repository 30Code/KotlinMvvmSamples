package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/15
 */
class CollectRepository : BaseRepository() {

    suspend fun getCollectArticles(page : Int) : ResultData<ArticleList> {
        return safeApiCall(call = {requestCollectArticles(page)}, errorMessage = "网络错误")
    }

    suspend fun collectArticle(articleId: Int): ResultData<ArticleList> {
        return safeApiCall(call = { requestCollectArticle(articleId) }, errorMessage = "网络错误")
    }

    suspend fun unCollectArticle(articleId: Int): ResultData<ArticleList> {
        return safeApiCall(call = { requestCancelCollectArticle(articleId) }, errorMessage = "网络错误")
    }

    private suspend fun requestCollectArticles(page : Int) : ResultData<ArticleList> =
        executeResponse(RetrofitClient.service.getCollectArticles(page))

    private suspend fun requestCollectArticle(articleId: Int): ResultData<ArticleList> =
        executeResponse(RetrofitClient.service.collectArticle(articleId))

    private suspend fun requestCancelCollectArticle(articleId: Int): ResultData<ArticleList> =
        executeResponse(RetrofitClient.service.cancelCollectArticle(articleId))
}