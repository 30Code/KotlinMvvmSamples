package cn.linhome.kotlinmvvmsamples.model.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 *  des : BaseRepository
 *  Created by 30Code
 *  date : 2020/4/24
 */
open class BaseRepository {

    suspend fun <T : Any> apiCall(call: suspend () -> BaseResponse<T>): BaseResponse<T> {
        return call.invoke()
    }

    suspend fun <T : Any> safeApiCall(call: suspend () -> ResultData<T>, errorMessage: String): ResultData<T> {
        return try {
            call()
        } catch (e: Exception) {
            // An exception was thrown when calling the API so we're converting this to an IOException
            ResultData.Error(IOException(errorMessage, e))
        }
    }

    suspend fun <T : Any> executeResponse(response: BaseResponse<T>, successBlock: (suspend CoroutineScope.() -> Unit)? = null,
                                          errorBlock: (suspend CoroutineScope.() -> Unit)? = null): ResultData<T> {
        return coroutineScope {
            if (response.errorCode == -1) {
                errorBlock?.let { it() }
                ResultData.Error(IOException(response.errorMsg))
            } else {
                successBlock?.let { it() }
                ResultData.Success(response.data)
            }
        }
    }

}