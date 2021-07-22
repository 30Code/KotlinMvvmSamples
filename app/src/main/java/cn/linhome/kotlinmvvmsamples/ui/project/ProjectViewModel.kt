package cn.linhome.kotlinmvvmsamples.ui.project

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import cn.linhome.common.base.constPagerConfig
import cn.linhome.common.entity.ProjectCategoryData
import cn.linhome.common.entity.ProjectDetailData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 *  des : ProjectViewModel
 *  Created by 30Code
 *  date : 2021/7/21
 */
class ProjectViewModel(val repository: ProjectRepository) : ViewModel() {

    /**
     * 项目分类
     */
    fun getCategories() : Flow<MutableList<ProjectCategoryData>> {
        return flow {
            emit(repository.getProjectCategories())
        }
    }

    /**
     * 加载分类下的项目列表分页数据
     */
    fun getProjects(pid : Int) : Flow<PagingData<ProjectDetailData>> {
        return Pager(constPagerConfig) {
            ProjectPagingSource(repository, pid)
        }.flow.apply {

        }.cachedIn(viewModelScope)
    }

}