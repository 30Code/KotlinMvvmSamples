package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitClient
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.ResultData
import cn.linhome.kotlinmvvmsamples.model.bean.ArticleList

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/14
 */
class ProjectLastedRepository() : BaseRepository() {

    suspend fun getLastedProject(page : Int) : ResultData<ArticleList> {
        return safeApiCall(call = {requestLastedProject(page)}, errorMessage = "发生未知错误")
    }

    private suspend fun requestLastedProject(page : Int) : ResultData<ArticleList> =
        executeResponse(RetrofitClient.service.getLastedProject(page))
}