package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import cn.linhome.common.base.BaseFragment
import cn.linhome.common.vm.AppViewModel
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.FragmentMainBinding
import cn.linhome.kotlinmvvmsamples.databinding.UserProfileHeaderBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 *  des : MainFragment
 *  Created by 30Code
 *  date : 2021/7/18
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mAppViewModel by sharedViewModel<AppViewModel>()

    override fun getLayoutId(): Int = R.layout.fragment_main

    private val mHeaderBinding by lazy {
        DataBindingUtil.inflate<UserProfileHeaderBinding>(
            layoutInflater, R.layout.user_profile_header, mBinding?.userProfileDrawer, false)
    }

    override fun actionOnViewInflate() {
        mBinding?.run {
            mHeaderBinding.holder = this@MainFragment
            userProfileDrawer.addHeaderView(mHeaderBinding.root)
        }
    }

    override fun initFragment(view: View, savedInstanceState: Bundle?) {

    }

    fun showWxDialog(view: View) {

    }

    fun headerLogin(view: View) {

    }

    fun userCoins(view: View) {

    }
}