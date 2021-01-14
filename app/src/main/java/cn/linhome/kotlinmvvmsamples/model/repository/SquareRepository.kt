package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList
import cn.linhome.kotlinmvvmsamples.utils.isSuccess
import java.io.IOException

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/14
 */
class SquareRepository : BaseRepository() {

    suspend fun getSquareArticleList(page : Int) : ResultData<ArticleList> {
        return safeApiCall(call = {requestSquareArticleList(page)}, errorMessage = "网络异常")
    }

    private suspend fun requestSquareArticleList(page : Int) : ResultData<ArticleList> {
        val response = RetrofitClient.service.getSquareArticleList(page)
        return if (response.isSuccess()) ResultData.Success(response.data)
        else ResultData.Error(IOException(response.errorMsg))
    }

}