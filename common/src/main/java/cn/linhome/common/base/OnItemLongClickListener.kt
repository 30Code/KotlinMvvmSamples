package cn.linhome.common.base

import android.view.View

/**
 *  des : RecyclerList的长按事件
 *  Created by 30Code
 *  date : 2021/7/20
 */
fun interface OnItemLongClickListener {
    fun onItemLongClick(position: Int, view: View?)
}