package cn.linhome.kotlinmvvmsamples.ui.home

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentHomeBinding

/**
 *  des : HomeFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_home

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }
}