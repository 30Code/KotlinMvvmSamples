package cn.linhome.kotlinmvvmsamples.ui.splash

import android.os.Bundle
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.ui.login.LoginActivity
import cn.linhome.lib.utils.extend.FDelayRunnable
import org.jetbrains.anko.startActivity
import qiu.niorgai.StatusBarCompat

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class SplashActivity : BaseActivity() {

    override fun onCreateContentView(): Int = R.layout.act_splash

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        val init_delayed_time = activity.resources.getInteger(R.integer.init_delayed_time)
        mDelayRunnable.runDelay(init_delayed_time.toLong())
    }

    private val mDelayRunnable: FDelayRunnable = object : FDelayRunnable() {
        override fun run() {
            finish()
            startActivity<LoginActivity>()
        }
    }

    override fun setStatusBar() {
        super.setStatusBar()
        StatusBarCompat.translucentStatusBar(this)
    }
}