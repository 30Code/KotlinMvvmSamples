package cn.linhome.home.ui.collect

import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.UserCollectDetail
import cn.linhome.home.R
import cn.linhome.home.databinding.ItemCollectBinding
import cn.linhome.lib.utils.context.FContext

/**
 *  des : CollectedArticlesListPagingSource
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CollectedArticlesListPagingSource(private val repository: CollectedArticlesListRepository) : PagingSource<Int, UserCollectDetail>() {

    override fun getRefreshKey(state: PagingState<Int, UserCollectDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserCollectDetail> {
        val page = params.key ?: 0
        return try {
            val listCollect = repository.getListCollectArticles(page) ?: mutableListOf()
            LoadResult.Page(
                data = listCollect,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (listCollect.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}

class CollectedArticlesListPagingAdapter : BasePagingDataAdapter<UserCollectDetail, ItemCollectBinding>(
    DIFF_CALLBACK) {

    var unCollectListener : ((data: UserCollectDetail, position: Int) -> Unit)? = null

    override fun getLayoutId(): Int = R.layout.item_collect

    override fun setVariable(
        data: UserCollectDetail,
        position: Int,
        holder: BaseViewHolder<ItemCollectBinding>
    ) {
        holder.binding.run {
            entity = data
            if (data.author.isNotEmpty()) {
                author = data.author
            } else {
                author = FContext.get().resources.getString(R.string.anonymous)
            }
            title = data.title.renderHtml()

            isVisibility = data.envelopePic.isNotBlank()

            ivLike.let {
                it.setOnClickListener {
                    unCollectListener?.invoke(data, position)
                }
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserCollectDetail>() {
            override fun areItemsTheSame(oldItem: UserCollectDetail, newItem: UserCollectDetail): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: UserCollectDetail, newItem: UserCollectDetail): Boolean =
                oldItem == newItem
        }
    }
}