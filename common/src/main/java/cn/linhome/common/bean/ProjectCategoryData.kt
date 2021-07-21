package cn.linhome.common.bean

/**
 *  des : ProjectCategoryData
 *  Created by 30Code
 *  date : 2021/7/21
 */
data class ProjectCategoryData(
    val children: List<Any>,
    val courseId: Int,
    val id: Int,
    val name: String,
    val order: Int,
    val parentChapterId: Int,
    val userControlSetTop: Boolean,
    val visible: Int
)
