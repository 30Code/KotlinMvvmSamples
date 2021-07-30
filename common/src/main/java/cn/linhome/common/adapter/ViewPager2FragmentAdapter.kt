package cn.linhome.common.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 *  des : ViewPager2FragmentAdapter
 *  Created by 30Code
 *  date : 2021/7/30
 */
class ViewPager2FragmentAdapter : FragmentStateAdapter {
    private val mChildFragments = mutableListOf<Fragment>()

    constructor(holder: Fragment, childFragments: MutableList<Fragment>) : super(holder) {
        mChildFragments.clear()
        mChildFragments.addAll(childFragments)
    }

    constructor(holder: FragmentActivity, childFragments: MutableList<Fragment>) : super(holder) {
        mChildFragments.clear()
        mChildFragments.addAll(childFragments)
    }

    override fun getItemCount() = mChildFragments.size

    override fun createFragment(position: Int) = mChildFragments[position]
}