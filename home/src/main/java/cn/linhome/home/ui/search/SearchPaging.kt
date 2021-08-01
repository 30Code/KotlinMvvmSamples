package cn.linhome.home.ui.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.home.R
import cn.linhome.home.databinding.ItemHomeArticleBinding

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/8/1
 */
class SearchPagingSource(private val repository: SearchRepository, private val key : String) : PagingSource<Int, UserArticleDetail>() {

    override fun getRefreshKey(state: PagingState<Int, UserArticleDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserArticleDetail> {
        val page = params.key ?: 0

        return try {
            val listArticles = repository.getListSearchArticles(page, key) ?: mutableListOf()
            LoadResult.Page(
                data = listArticles,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (listArticles.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}

class SearchPagingAdapter : BasePagingDataAdapter<UserArticleDetail, ItemHomeArticleBinding>(
    DIFF_CALLBACK) {

    override fun getLayoutId(): Int = R.layout.item_home_article

    override fun setVariable(
        data: UserArticleDetail,
        position: Int,
        holder: BaseViewHolder<ItemHomeArticleBinding>
    ) {
        holder.binding.article = data
        holder.binding.title = data.title.renderHtml()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserArticleDetail>() {
            override fun areItemsTheSame(
                oldItem: UserArticleDetail,
                newItem: UserArticleDetail
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UserArticleDetail,
                newItem: UserArticleDetail
            ): Boolean = oldItem == newItem

        }
    }

}