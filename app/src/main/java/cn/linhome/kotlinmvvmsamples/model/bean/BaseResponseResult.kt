package cn.linhome.kotlinmvvmsamples.model.bean

/**
 *  des : BaseResponse
 *  Created by 30Code
 *  date : 2020/4/24
 */
data class BaseResponseResult<out T> (
    val errorCode : Int,
    val errorMsg : String,
    val data : T
)