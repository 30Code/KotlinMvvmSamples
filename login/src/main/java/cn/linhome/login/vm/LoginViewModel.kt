package cn.linhome.login.vm

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import cn.linhome.common.constant.Constant
import cn.linhome.common.base.BaseResultData
import cn.linhome.common.entity.UserData
import cn.linhome.lib.utils.context.FPreferencesUtil
import cn.linhome.login.ui.LoginRepository
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/7/19
 */
class LoginViewModel(val repository: LoginRepository) : ViewModel() {

    val etUserName = ObservableField<String>()
    val etPassword = ObservableField<String>()

    val btnEnable = ObservableField<Boolean>(true)

    fun login(username: String, password: String) = flow {
//        val username = etUserName.get() ?: ""
//        val password = etPassword.get() ?: ""
//
//        if (username.isBlank() || password.isBlank()) {
//            return@flow
//        }

        emit(repository.login(username, password))
    }

    fun register(username: String, password: String, repass: String) = flow {
        emit(repository.register(username, password, repass))
    }

    val verifyInput : (String) -> Unit = {
        if (isInputValid(etUserName.get() ?: "", etPassword.get() ?: "")) {
            btnEnable.set(true)
        } else {
            btnEnable.set(false)
        }
    }

    private fun isInputValid(userName: String, passWord: String) = userName.isNotBlank() && passWord.isNotBlank()

    /**
     * 存储用户信息
     */
    fun saveUserInfo(info : Response<BaseResultData<UserData>>) {
        if (info.body()?.errorCode == 0) {
            val cookies = StringBuilder()

            info.headers()
                .filter { TextUtils.equals(it.first, "Set-Cookie") }
                .forEach { cookies.append(it.second).append(";") }

            val strCookie = if (cookies.endsWith(";")) {
                cookies.substring(0, cookies.length - 1)
            } else {
                cookies.toString()
            }

            FPreferencesUtil.putString(Constant.DiskKey.COOKIE, strCookie)
            FPreferencesUtil.putInt(Constant.DiskKey.USERID, info.body()?.data?.id ?: 0)
            FPreferencesUtil.putString(Constant.DiskKey.USERNAME, info.body()?.data?.nickname ?: "")
        }
    }
}