package cn.linhome.kotlinmvvmsamples.ui.project

import cn.linhome.common.entity.ProjectDetailData
import cn.linhome.common.api.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des : ProjectRepository
 *  Created by 30Code
 *  date : 2021/7/21
 */
class ProjectRepository(val api : ApiService) {

    /**
     * 项目分类
     */
    suspend fun getProjectCategories() = withContext(Dispatchers.IO) {
        api.projectCategory().data
    }

    /**
     * 加载分类下的项目列表
     */
    suspend fun getProjects(page : Int, pid : Int) : MutableList<ProjectDetailData> = withContext(Dispatchers.IO) {
//        val cookie = FPreferencesUtil.getString(Constant.DiskKey.COOKIE, "")
        api.projectList(page, pid).data.datas
    }

}