package cn.linhome.kotlinmvvmsamples.ui.login

import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseVMActivity
import cn.linhome.kotlinmvvmsamples.databinding.ActivityLoginBinding
import cn.linhome.kotlinmvvmsamples.ui.login.vm.LoginViewModel
import cn.linhome.lib.utils.context.FToast
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : login
 *  Created by 30Code
 *  date : 2020/4/24
 */
class LoginActivity : BaseVMActivity() {

    private var mUserName : String = ""

    private var mPwd : String = ""

    private val loginViewModel by viewModel<LoginViewModel>()
    private val binding by binding<ActivityLoginBinding>(R.layout.activity_login)

    override fun startObserve() {

    }

    override fun initData() {
        super.initData()
//        btn_login.setOnClickListener {
//            login()
//        }
    }

//    override fun startObserver() {
//        super.startObserver()
//        mViewModel.apply {
//            mLoginData.observe(this@LoginActivity, Observer {
//                hideLoading()
//                UserBeanDao.insertOrUpdate(it.data)
//                startActivity<MainActivity>()
//                finish()
//            })
//        }
//    }

    private fun validate() : Boolean {
        var valid = true
//        mUserName = et_username.text.toString()
//        mPwd = et_pwd.text.toString()
//        if (mUserName.isEmpty()) {
//            FToast.show(getString(R.string.username_not_empty))
//            valid = false
//            return valid
//        }
//        if (mPwd.isEmpty()) {
//            FToast.show(getString(R.string.password_not_empty))
//            valid = false
//            return valid
//        }
        return valid
    }

    private fun login() {
        if (validate()) {
            showLoading()
//            mViewModel.login(mUserName, mPwd)
        }
    }

    override fun showLoading() {
        showProgressDialog(getString(R.string.login_ing))
    }

    override fun hideLoading() {
        dismissProgressDialog()
    }

    override fun showDefaultMsg(msg: String) {

    }

    override fun showMsg(msg: String) {

    }

    override fun showError(errorMsg: String) {

    }
}