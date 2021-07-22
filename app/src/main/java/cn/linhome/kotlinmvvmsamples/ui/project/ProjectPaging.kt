package cn.linhome.kotlinmvvmsamples.ui.project

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.ProjectDetailData
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ItemProjectTypeBinding

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

class ProjectTypePagingAdapter : BasePagingDataAdapter<ProjectDetailData, ItemProjectTypeBinding>(
    DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ProjectDetailData>() {
            override fun areItemsTheSame(oldItem: ProjectDetailData, newItem: ProjectDetailData): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ProjectDetailData, newItem: ProjectDetailData): Boolean =
                oldItem == newItem
        }
    }

    override fun getLayoutId(): Int = R.layout.item_project_type

    override fun setVariable(
        data: ProjectDetailData,
        position: Int,
        holder: BaseViewHolder<ItemProjectTypeBinding>
    ) {
        holder.binding.project = data
        holder.binding.title = data.title.renderHtml()
        holder.binding.desc = data.desc.renderHtml()
    }
}