package cn.linhome.kotlinmvvmsamples.ui.share

import android.graphics.Paint
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.base.BasePagingDataAdapter
import cn.linhome.common.base.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.bean.UserArticleDetail
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ItemUserArticleBinding
import java.lang.Exception

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/20
 */
class UserArticlePagingSource(val repository: UserArticleRepository) : PagingSource<Int, UserArticleDetail>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserArticleDetail> {
        val page = params.key ?: 0
        return try {
            val articles = repository.fetchUserArticles(page) ?: mutableListOf()
            LoadResult.Page(
                data = articles,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (articles.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserArticleDetail>): Int? = state.anchorPosition

}

class UserArticlePagingAdapter : BasePagingDataAdapter<UserArticleDetail, ItemUserArticleBinding>(
    DIFF_CALLBACK) {

    var userListener : ((Int, String) -> Unit)? = null

    override fun getLayoutId(): Int = R.layout.item_user_article

    override fun setVariable(
        data: UserArticleDetail,
        position: Int,
        holder: BaseViewHolder<ItemUserArticleBinding>
    ) {
        holder.binding.article = data
        holder.binding.title = data.title.renderHtml()
        holder.binding.shareUser.let {
            it.paint.flags = it.paint.flags or Paint.UNDERLINE_TEXT_FLAG
            it.setOnClickListener {
                userListener?.invoke(data.userId, data.shareUser)
            }
        }
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