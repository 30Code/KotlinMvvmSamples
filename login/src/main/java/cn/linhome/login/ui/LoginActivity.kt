package cn.linhome.login.ui

import android.os.Bundle
import android.view.View
import cn.linhome.common.constant.Constant
import cn.linhome.common.base.BaseActivity
import cn.linhome.login.R
import cn.linhome.login.databinding.ActivityLoginBinding
import cn.linhome.login.vm.LoginViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *  des : LoginActivity
 *  Created by 30Code
 *  date : 2021/7/20
 */
@Route(path = Constant.ARouterPath.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private val mViewModel by viewModel<LoginViewModel>()

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initActivity(savedInstanceState: Bundle?) {
        mBinding?.run {
            holder = this@LoginActivity

            toolbar.run {
                setNavigationIcon(R.drawable.arrow_back)
                setNavigationOnClickListener {
                    onBackPressed()
                }
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun login(view: View) {
        val username = mBinding.etUserName.text.toString()
        val password = mBinding.etPassword.text.toString()

        if (username.isBlank()) {
            baseContext?.toast(R.string.username_not_empty)
            return
        }

        if (password.isBlank()) {
            baseContext?.toast(R.string.password_not_empty)
            return
        }

        launch {
            mViewModel.login(username, password).catch {
                baseContext?.toast(R.string.no_network)
            }.onStart {
//                mAppViewModel.showLoading()
            }.onCompletion {
//                mAppViewModel.dismissLoading()
            }.collectLatest {
                if (it.body()?.errorCode == 0) {
                    mViewModel.saveUserInfo(it)
                    baseContext?.toast(R.string.login_success)

                    finish()
                } else {
                    baseContext?.toast(R.string.login_fail)
                }
            }
        }
    }
}