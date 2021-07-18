package cn.linhome.kotlinmvvmsamples.ui

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.common.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {

    override fun transparentStatusBar() {
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        window.navigationBarColor = Color.TRANSPARENT
        window.statusBarColor = Color.TRANSPARENT
        supportActionBar?.hide()
    }

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun initActivity(savedInstanceState: Bundle?) {
//        delayLaunch(2000) {
//            startActivity<MainActivity>()
//            finish()
//        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_BACK)
            true
        else super.onKeyDown(keyCode, event)
    }
}