package cn.linhome.kotlinmvvmsamples.ui.main

import android.content.Intent
import android.os.Bundle
import cn.linhome.common.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.databinding.ActivityMainBinding

/**
 *  des : MainActivity
 *  Created by 30Code
 *  date : 2021/7/18
 */
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun initActivity(savedInstanceState: Bundle?) {

        mBinding?.run {
            netAvailable = true
        }

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