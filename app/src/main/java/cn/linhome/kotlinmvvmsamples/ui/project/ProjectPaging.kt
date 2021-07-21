package cn.linhome.kotlinmvvmsamples.ui.project

import androidx.paging.PagingSource
import androidx.paging.PagingState
import cn.linhome.common.bean.ProjectDetailData

/**
 *  des : ProjectPaging
 *  Created by 30Code
 *  date : 2021/7/20
 */
class ProjectPagingSource(val repository: ProjectRepository, val pid : Int) : PagingSource<Int, ProjectDetailData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProjectDetailData> {
        val page = params.key ?: 0
        return try {
            val projects = repository.getProjects(page, pid) ?: mutableListOf()
            return LoadResult.Page(
                data = projects,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (projects.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ProjectDetailData>): Int? = state.anchorPosition

}