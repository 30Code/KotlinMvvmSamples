package cn.linhome.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.linhome.common.R
import cn.linhome.common.databinding.ItemLoadFooterBinding

/**
 *  des : PagingLoadStateAdapter
 *  Created by 30Code
 *  date : 2021/7/20
 */
class PagingLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<PagingLoadStateViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState)
            : PagingLoadStateViewHolder {
        return PagingLoadStateViewHolder.create(parent, retry)
    }

    override fun onBindViewHolder(holder: PagingLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }
}

class PagingLoadStateViewHolder(
    private val binding: ItemLoadFooterBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryLoadData.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        binding.loadMore.isVisible = loadState is LoadState.Loading
        binding.retryLoadData.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): PagingLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(
                R.layout.item_load_footer, parent, false
            )
            return PagingLoadStateViewHolder(
                ItemLoadFooterBinding.bind(view), retry
            )
        }
    }
}