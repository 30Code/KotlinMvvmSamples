package cn.linhome.common.entity

/**
 *  des : UserData
 *  Created by 30Code
 *  date : 2021/7/19
 */
data class UserData(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val token: String,
    val type: Int,
    val username: String
)
