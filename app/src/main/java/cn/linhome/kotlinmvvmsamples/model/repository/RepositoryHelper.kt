package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitHelper
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.bean.*

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class RepositoryHelper : BaseRepository() {

    suspend fun login(username : String, password : String) : BaseResponse<LoginData> {
        return apiCall { RetrofitHelper.mService.login(username, password) }
    }

    suspend fun getWXChapters() : BaseResponse<MutableList<WXChapterBean>> {
        return apiCall { RetrofitHelper.mService.getWXChapters() }
    }

    suspend fun getArticlesList(page: Int, cid: Int) : BaseResponse<ArticleResponseBody> {
        return apiCall { RetrofitHelper.mService.getArticlesList(page, cid) }
    }

}