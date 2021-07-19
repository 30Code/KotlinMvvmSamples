package cn.linhome.common.base

/**
 *  des : BaseResultData
 *  Created by 30Code
 *  date : 2021/7/19
 */
data class BaseResultData<T>(
    val `data` : T,
    val errorCode: Int,
    val errorMsg: String
)
