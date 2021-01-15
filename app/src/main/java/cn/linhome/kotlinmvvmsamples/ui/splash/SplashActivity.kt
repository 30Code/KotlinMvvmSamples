package cn.linhome.kotlinmvvmsamples.ui.splash

import android.Manifest
import android.os.Bundle
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.constants.Constant
import cn.linhome.kotlinmvvmsamples.dao.UserBeanDao
import cn.linhome.kotlinmvvmsamples.dialog.PermissionDialog
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import cn.linhome.kotlinmvvmsamples.ui.login.LoginActivity
import cn.linhome.kotlinmvvmsamples.ui.main.MainActivity
import cn.linhome.lib.cache.FDisk
import cn.linhome.lib.utils.extend.FDelayRunnable
import com.tbruyelle.rxpermissions2.RxPermissions
import org.jetbrains.anko.startActivity
import qiu.niorgai.StatusBarCompat

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class SplashActivity : BaseActivity() {

    private var mFlag : Boolean = false

    private var mPermissionDialog : PermissionDialog? = null

    override fun getLayoutResId(): Int = R.layout.act_splash

    override fun initView() {
        StatusBarCompat.translucentStatusBar(this)

        requestExternalStoragePermission()
    }

    override fun initData() {
    }

    private fun requestExternalStoragePermission() {
        RxPermissions(this).requestEachCombined(Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE)
            .subscribe { permission ->
                if (permission.granted) {
                    val init_delayed_time = resources.getInteger(R.integer.init_delayed_time)
                    mDelayRunnable.runDelay(init_delayed_time.toLong())
                } else {
                    showPermissionDialog(getString(R.string.text_permission_external_storage_tip))
                }
            }
    }

    private fun showPermissionDialog(tips : String) {
        if (mPermissionDialog == null) {
            mPermissionDialog = PermissionDialog(this)
        }
        mPermissionDialog!!.setPermissionTip(tips)
        mPermissionDialog!!.setPermissionCallBack(object : PermissionDialog.PermissionCallBack {
            override fun onCancel() {
                finish()
            }

            override fun permissionSetting() {
                mFlag = true
            }

        })
        mPermissionDialog!!.show()
    }

    private val mDelayRunnable: FDelayRunnable = object : FDelayRunnable() {
        override fun run() {
            val loginData = UserBeanDao.query()
            if (loginData != null) {
                startActivity<MainActivity>()
            } else {
                startActivity<LoginActivity>()
            }

//            startActivity<MainActivity>()

            finish()
        }
    }

}