package cn.linhome.common.bean

/**
 *  des : ProjectDetail
 *  Created by 30Code
 *  date : 2021/7/21
 */
data class ProjectDetailResult(
    val curPage: Int,
    val datas: MutableList<ProjectDetailData>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)

data class ProjectDetailData(
    val apkLink: String,
    val author: String,
    val chapterId: Int,
    val chapterName: String,
    var collect: Boolean,
    val courseId: Int,
    val desc: String,
    val envelopePic: String,
    val fresh: Boolean,
    val id: Int,
    val link: String,
    val niceDate: String,
    val origin: String,
    val prefix: String,
    val projectLink: String,
    val publishTime: Long,
    val superChapterId: Int,
    val superChapterName: String,
    val tags: MutableList<ProjectTag>,
    val title: String,
    val type: Int,
    val userId: Int,
    val visible: Int,
    val zan: Int
)

data class ProjectTag(
    val name: String,
    val url: String
)