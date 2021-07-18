package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentMainBinding

/**
 *  des : MainFragment
 *  Created by 30Code
 *  date : 2021/7/18
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_main

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }
}