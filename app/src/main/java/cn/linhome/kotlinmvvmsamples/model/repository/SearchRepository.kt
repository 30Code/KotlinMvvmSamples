package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.model.bean.Hot

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/15
 */
class SearchRepository : BaseRepository() {

    suspend fun getWebSites() : ResultData<List<Hot>> {
        return safeApiCall(call = {requestWebSites()}, errorMessage = "网络错误")
    }

    suspend fun getHotSearch(): ResultData<List<Hot>> {
        return safeApiCall(call = {requestHotSearch()},errorMessage = "网络错误")
    }

    suspend fun searchHot(page: Int, key: String): ResultData<ArticleList> {
        return safeApiCall(call = {requestSearch(page, key)},errorMessage = "网络错误")
    }

    private suspend fun requestWebSites() : ResultData<List<Hot>> =
        executeResponse(RetrofitClient.service.getWebsites())

    private suspend fun requestHotSearch() : ResultData<List<Hot>> =
        executeResponse(RetrofitClient.service.getHot())

    private suspend fun requestSearch(page : Int, key : String) : ResultData<ArticleList> =
        executeResponse(RetrofitClient.service.searchHot(page, key))
}