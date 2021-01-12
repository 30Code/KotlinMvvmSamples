package cn.linhome.kotlinmvvmsamples.model.api

/**
 *  des : Result
 *  Created by 30Code
 *  date : 2020/4/24
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}