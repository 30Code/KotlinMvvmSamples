package cn.linhome.kotlinmvvmsamples.model.api

import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse

/**
 *  des : BaseRepository
 *  Created by 30Code
 *  date : 2020/4/24
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call : suspend () -> BaseResponse<T>) : BaseResponse<T> {
        return call.invoke()
    }

}