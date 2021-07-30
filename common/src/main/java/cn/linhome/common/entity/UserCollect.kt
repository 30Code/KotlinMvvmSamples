package cn.linhome.common.entity

/**
 *  des : 收藏数据
 *  Created by 30Code
 *  date : 2021/7/30
 */
data class UserCollectData(
    val curPage: Int,
    val datas: MutableList<UserCollectDetail>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class UserCollectDetail(
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val originId: Int,
    val publishTime: Long,
    val title: String,
    val userId: Int,
    val visible: Int,
    val zan: Int
)