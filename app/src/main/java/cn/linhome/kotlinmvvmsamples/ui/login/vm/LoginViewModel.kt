package cn.linhome.kotlinmvvmsamples.ui.login.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.CoroutinesDispatcherProvider
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import cn.linhome.kotlinmvvmsamples.model.repository.LoginRepository
import cn.linhome.kotlinmvvmsamples.ui.login.LoginUiState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class LoginViewModel(val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {

    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")

    private val uiState = MutableLiveData<LoginUiState<LoginData>>()

    fun getUiState() : LiveData<LoginUiState<LoginData>> {
        return uiState
    }

    @ExperimentalCoroutinesApi
    fun login() {
        launchOnUI {
            repository.loginFlow(userName.get() ?: "用户名不可为空", passWord.get() ?: "密码不可为空")
                .collect {
                    uiState.postValue(it)
                }
        }
    }

    val verifyInput: (String) -> Unit = { loginDataChanged() }

    private fun loginDataChanged() {
        uiState.value = LoginUiState(enableLoginButton = isInputValid(userName.get()
            ?: "", passWord.get() ?: ""))
    }

    private fun isInputValid(userName: String, passWord: String) = userName.isNotBlank() && passWord.isNotBlank()
}