package cn.linhome.kotlinmvvmsamples.ui.share

import android.os.Bundle
import android.view.View
import cn.linhome.common.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentShareBinding

/**
 *  des : ShareFragment
 *  Created by 30Code
 *  date : 2021/7/20
 */
class ShareFragment : BaseFragment<FragmentShareBinding>() {

    override fun getLayoutId(): Int = R.layout.fragment_share

    override fun initFragment(view: View, savedInstanceState: Bundle?) {
    }
}