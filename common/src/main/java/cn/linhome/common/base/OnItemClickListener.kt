package cn.linhome.common.base

import android.view.View

/**
 *  des : Paging 下的点击事件
 *  Created by 30Code
 *  date : 2021/7/20
 */
fun interface OnItemClickListener {
    fun onItemClick(position: Int, view: View?)
}