package cn.linhome.kotlinmvvmsamples.ext

import cn.linhome.kotlinmvvmsamples.constants.ResponseCode
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
suspend fun executeResponse(
    response: BaseResponse<Any>,
    successBlock: suspend CoroutineScope.() -> Unit,
    errorBlock: suspend CoroutineScope.() -> Unit) {
        coroutineScope {
            if (response.errorCode == ResponseCode.SUCCESS) {
                successBlock()
            } else {
                errorBlock()
            }
        }
}