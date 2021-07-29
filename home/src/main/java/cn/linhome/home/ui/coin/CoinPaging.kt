package cn.linhome.home.ui.coin

import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BasePagingDataAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.home.R
import cn.linhome.home.databinding.ItemCoinRankBinding
import cn.linhome.home.databinding.ItemCoinRecordBinding
import cn.linhome.home.entity.CoinRankDetail
import cn.linhome.home.entity.CoinRecordDetail

/**
 *  des : CoinPaging
 *  Created by 30Code
 *  date : 2021/7/29
 */
class CoinRecordPagingSource(private val repository: CoinRepository) : PagingSource<Int, CoinRecordDetail>() {

    override fun getRefreshKey(state: PagingState<Int, CoinRecordDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinRecordDetail> {
        val page = params.key ?: 1
        val listRecords = repository.getListCoinRecord(page) ?: mutableListOf()
        return try {
            LoadResult.Page(
                data = listRecords,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (listRecords.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}

class CoinRecordPagingAdapter : BasePagingDataAdapter<CoinRecordDetail, ItemCoinRecordBinding>(
    DIFF_CALLBACK) {

    override fun getLayoutId(): Int = R.layout.item_coin_record

    override fun setVariable(
        data: CoinRecordDetail,
        position: Int,
        holder: BaseViewHolder<ItemCoinRecordBinding>
    ) {
        holder.binding.model = data
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinRecordDetail>() {
            override fun areItemsTheSame(oldItem: CoinRecordDetail, newItem: CoinRecordDetail): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: CoinRecordDetail, newItem: CoinRecordDetail): Boolean =
                oldItem == newItem
        }
    }
}

class CoinRankPagingSource(private val repository: CoinRepository) : PagingSource<Int, CoinRankDetail>() {

    override fun getRefreshKey(state: PagingState<Int, CoinRankDetail>): Int? = null

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinRankDetail> {
        val page = params.key ?: 1

        return try {
            val listRank = repository.getListCoinRank(page) ?: mutableListOf()
            LoadResult.Page(
                data = listRank,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (listRank.isEmpty()) null else page + 1
            )
        } catch (e : Exception) {
            LoadResult.Error(e)
        }
    }

}

class CoinRankPagingAdapter : BasePagingDataAdapter<CoinRankDetail, ItemCoinRankBinding>(
    DIFF_CALLBACK) {

    override fun getLayoutId(): Int = R.layout.item_coin_rank

    override fun setVariable(
        data: CoinRankDetail,
        position: Int,
        holder: BaseViewHolder<ItemCoinRankBinding>
    ) {
        holder.binding.rank = data

        val context = holder.binding.root.context
        holder.binding.coinSpan = SpannableStringBuilder("${data.coinCount}").apply {
            setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.coin_color)),
                0, length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
            setSpan(
                ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                run { append("\t/\t");length },
                run { append("Lv${data.level}");length },
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }

        holder.binding.imageRes = ContextCompat.getDrawable(
            context, when (position) {
                0 -> R.drawable.ic_no_1
                1 -> R.drawable.ic_no_2
                2 -> R.drawable.ic_no_3
                else -> R.drawable.ic_no_other
            }
        )
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<CoinRankDetail>() {
            override fun areItemsTheSame(oldItem: CoinRankDetail, newItem: CoinRankDetail): Boolean =
                oldItem.userId == newItem.userId

            override fun areContentsTheSame(oldItem: CoinRankDetail, newItem: CoinRankDetail): Boolean =
                oldItem == newItem
        }
    }
}