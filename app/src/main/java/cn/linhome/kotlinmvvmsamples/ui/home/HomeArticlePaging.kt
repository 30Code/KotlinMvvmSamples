package cn.linhome.kotlinmvvmsamples.ui.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ItemHomeArticleBinding
import java.lang.Exception

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/22
 */
class HomeArticlePagingSource(val repository: HomeArticleRepository) : PagingSource<Int, UserArticleDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserArticleDetail> {
        val page = params.key ?: 0
        return try {
            val articles = repository.getHomeArticles(page) ?: mutableListOf()
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserArticleDetail>): Int? = null

}

class HomeArticlePagingAdapter : BasePagingDataAdapter<UserArticleDetail, ItemHomeArticleBinding>(
    DIFF_CALLBACK) {

    var userListener : ((Int, String) -> Unit)? = null

    override fun getLayoutId(): Int = R.layout.item_home_article

    override fun setVariable(
        data: UserArticleDetail,
        position: Int,
        holder: BaseViewHolder<ItemHomeArticleBinding>
    ) {
        holder.binding.article = data
        holder.binding.title = data.title.renderHtml()
//        holder.binding.shareUser.let {
//            it.paint.flags = it.paint.flags or Paint.UNDERLINE_TEXT_FLAG
//            it.setOnClickListener {
//                userListener?.invoke(data.userId, data.shareUser)
//            }
//        }
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