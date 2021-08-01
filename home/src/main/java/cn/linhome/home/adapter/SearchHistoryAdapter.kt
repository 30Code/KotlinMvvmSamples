package cn.linhome.home.adapter

import android.text.TextUtils
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import cn.linhome.common.adapter.BaseRecyclerAdapter
import cn.linhome.common.adapter.BaseViewHolder
import cn.linhome.home.R
import cn.linhome.home.databinding.ItemSearchHistoryBinding

/**
 *  des : HistorySearchAdapter
 *  Created by 30Code
 *  date : 2021/8/1
 */
typealias OnKeyRemove = (String) -> Unit

class HistorySearchAdapter(list: MutableList<String>? = null) : BaseRecyclerAdapter<String>(list) {
    var onKeyRemove: OnKeyRemove? = null

    override fun getLayoutId(viewType: Int): Int = R.layout.item_search_history

    override fun setVariable(data: String, position: Int, holder: BaseViewHolder<ViewDataBinding>) {
        (holder.binding as ItemSearchHistoryBinding).let {
            it.history = data
            it.listener = View.OnClickListener {
                onKeyRemove?.invoke(data)
                val last = mData?.size ?: 0
                mData?.remove(data)
                notifyItemRangeChanged(0, last)
            }
        }
    }

    fun updateHistory(history: MutableList<String>) {
        val result = DiffUtil.calculateDiff(HistoryDiffCall(history, getAdapterData()), true)
        result.dispatchUpdatesTo(this)
        mData = (mData ?: arrayListOf()).apply {
            clear()
            addAll(history)
        }
    }
}

class HistoryDiffCall(private val newList: MutableList<String>?, private val oldList: MutableList<String>?) :
    DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else TextUtils.equals(newList[newItemPosition], oldList[oldItemPosition])

    override fun getOldListSize(): Int = oldList?.size ?: 0

    override fun getNewListSize(): Int = newList?.size ?: 0

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        if (newList.isNullOrEmpty() || oldList.isNullOrEmpty()) false
        else TextUtils.equals(newList[newItemPosition], oldList[oldItemPosition])
}