package cn.linhome.kotlinmvvmsamples.ui.main

import android.content.Intent
import android.os.Bundle
import cn.linhome.common.base.BaseActivity
import cn.linhome.common.ui.LoadingDialog
import cn.linhome.common.vm.AppViewModel
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ActivityMainBinding
import org.koin.androidx.scope.lifecycleScope
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : MainActivity
 *  Created by 30Code
 *  date : 2021/7/18
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val mAppViewModel by viewModel<AppViewModel>()

    private val mLoadingDialog by lifecycleScope.inject<LoadingDialog>()

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initActivity(savedInstanceState: Bundle?) {

        mBinding?.run {
            netAvailable = true
        }

        mAppViewModel.showLoadingProgress.observe(this, {
            if (it) {
                mLoadingDialog.showAllowStateLoss(supportFragmentManager, "loading")
            } else {
                mLoadingDialog.dismiss()
            }
        })
    }

    override fun onBackPressed() {

        supportFragmentManager.fragments.first().childFragmentManager.fragments.last().let {
            if (it is MainFragment) {
                startActivity(Intent(Intent.ACTION_MAIN).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    addCategory(Intent.CATEGORY_HOME)
                })
                return
            }
        }

        super.onBackPressed()
    }
}