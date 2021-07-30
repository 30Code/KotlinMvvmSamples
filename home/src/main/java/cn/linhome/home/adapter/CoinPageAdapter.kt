package cn.linhome.home.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.linhome.home.ui.coin.CoinSubFragment

/**
 *  des : 积分，排行版
 *  Created by 30Code
 *  date : 2021/7/30
 */
class CoinPageAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> CoinSubFragment.instance(0)
            else -> CoinSubFragment.instance(1)
        }
    }
}