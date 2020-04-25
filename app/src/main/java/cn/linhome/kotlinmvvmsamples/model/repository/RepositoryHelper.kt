package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.http.RetrofitHelper
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class RepositoryHelper : BaseRepository() {

    suspend fun login(username : String, password : String) : BaseResponse<LoginData> {
        return apiCall { RetrofitHelper.mService.login(username, password) }
    }

}