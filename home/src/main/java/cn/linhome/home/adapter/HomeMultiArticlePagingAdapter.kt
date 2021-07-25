package cn.linhome.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.linhome.common.base.renderHtml
import cn.linhome.common.entity.UserArticleDetail
import cn.linhome.home.databinding.ItemBannerBinding
import cn.linhome.home.databinding.ItemHomeArticleBinding
import cn.linhome.home.entity.BannerData

/**
 *  des : HomeArticleAdapter
 *  Created by 30Code
 *  date : 2021/7/25
 */
class HomeMultiArticlePagingAdapter : PagingDataAdapter<UserArticleDetail, RecyclerView.ViewHolder>(differCallback) {

    private var mListBanner : MutableList<BannerData> = mutableListOf()

    fun addListBanner(list: MutableList<BannerData>) {
        mListBanner = list
    }

    companion object {
        const val TYPE_BANNER = 0
        const val TYPE_ARTICLE = 1

        val differCallback = object : DiffUtil.ItemCallback<UserArticleDetail>() {

            override fun areItemsTheSame(
                oldItem: UserArticleDetail,
                newItem: UserArticleDetail
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: UserArticleDetail,
                newItem: UserArticleDetail
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0) {
            return TYPE_BANNER
        }
        return TYPE_ARTICLE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == TYPE_BANNER) {
            (holder as BannerVH).bindData(mListBanner)
        } else {
            getItem(position - 1)?.let {
                (holder as ArticleVH).bindData(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_BANNER) {
            return BannerVH(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        }

        val articleVH = ArticleVH(ItemHomeArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
        articleVH.itemView.setOnClickListener {

        }
        return articleVH
    }

    internal class BannerVH(val binding : ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data : MutableList<BannerData>) {

        }

    }

    internal class ArticleVH(val binding : ItemHomeArticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(data : UserArticleDetail) {
            binding.article = data
            binding.title = data.title.renderHtml()
        }

    }

}