package cn.linhome.kotlinmvvmsamples.ui.login

import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseVMActivity
import cn.linhome.kotlinmvvmsamples.databinding.ActivityLoginBinding
import cn.linhome.kotlinmvvmsamples.ui.login.vm.LoginViewModel
import cn.linhome.lib.utils.context.FToast
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : login
 *  Created by 30Code
 *  date : 2020/4/24
 */
class LoginActivity : BaseVMActivity() {

    private val mLoginViewModel by viewModel<LoginViewModel>()
    private val mBinding by binding<ActivityLoginBinding>(R.layout.activity_login)

    override fun initView() {
        mBinding.run {
            viewModel = mLoginViewModel
        }
    }

    override fun initData() {

    }

    @ExperimentalCoroutinesApi
    override fun startObserve() {
        mLoginViewModel.apply {
            getUiState().observe(this@LoginActivity, {
                if (it.isLoading) {
                    showLoading()
                }

                it.isSuccess.let {
                    hideLoading()
                    finish()
                }

                it.isError.let { err ->
                    hideLoading()
                    FToast.show(err)
                }

                if (it.needLogin) {
                    mLoginViewModel.login()
                }
            })
        }
    }

    override fun showLoading() {
//        showProgressDialog(getString(R.string.login_ing))
    }

    override fun hideLoading() {
//        dismissProgressDialog()
    }

    override fun showDefaultMsg(msg: String) {

    }

    override fun showMsg(msg: String) {

    }

    override fun showError(errorMsg: String) {

    }
}