package cn.linhome.kotlinmvvmsamples.ui.project

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentProjectBinding

/**
 *  des : ProjectFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class ProjectFragment : BaseFragment<FragmentProjectBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_project

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }
}