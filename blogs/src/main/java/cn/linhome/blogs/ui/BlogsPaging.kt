package cn.linhome.blogs.ui

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.blogs.R
import cn.linhome.blogs.databinding.ItemBlogArticleBinding
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.UserArticleDetail

/**
 *  des : BlogsPaging
 *  Created by 30Code
 *  date : 2021/7/25
 */
class BlogsPagingSource(val repository: BlogsRepository, val cid : Int) : PagingSource<Int, UserArticleDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserArticleDetail> {
        val page = params.key ?: 0
        return try {
            val projects = repository.getListBlogsArticles(page, cid) ?: mutableListOf()
            return LoadResult.Page(
                data = projects,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (projects.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserArticleDetail>): Int? = null

}

class BlogArticlesPagingAdapter : BasePagingDataAdapter<UserArticleDetail, ItemBlogArticleBinding>(
    DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserArticleDetail>() {
            override fun areItemsTheSame(oldItem: UserArticleDetail, newItem: UserArticleDetail): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserArticleDetail, newItem: UserArticleDetail): Boolean =
                oldItem == newItem
        }
    }

    override fun getLayoutId(): Int = R.layout.item_blog_article

    override fun setVariable(
        data: UserArticleDetail,
        position: Int,
        holder: BaseViewHolder<ItemBlogArticleBinding>
    ) {
        holder.binding.article = data
        holder.binding.title = data.title.renderHtml()
    }
}