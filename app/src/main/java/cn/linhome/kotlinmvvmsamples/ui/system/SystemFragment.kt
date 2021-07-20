package cn.linhome.kotlinmvvmsamples.ui.system

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentSystemBinding

/**
 *  des : SystemFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class SystemFragment : BaseFragment<FragmentSystemBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_system

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }
}