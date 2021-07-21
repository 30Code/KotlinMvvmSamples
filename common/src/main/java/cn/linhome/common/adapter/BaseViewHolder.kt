package cn.linhome.common.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 *  des : RecyclerView Adapter View Holder 基类
 *  Created by 30Code
 *  date : 2021/7/20
 */
open class BaseViewHolder<VB : ViewDataBinding>(val binding: VB) : RecyclerView.ViewHolder(binding.root)