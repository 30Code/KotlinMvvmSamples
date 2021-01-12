package cn.linhome.kotlinmvvmsamples.model.repository

import cn.linhome.kotlinmvvmsamples.model.api.ApiService
import cn.linhome.kotlinmvvmsamples.model.api.BaseRepository
import cn.linhome.kotlinmvvmsamples.model.api.doError
import cn.linhome.kotlinmvvmsamples.model.api.doSuccess
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import cn.linhome.kotlinmvvmsamples.ui.login.LoginUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart

/**
 *  des : LoginRepository
 *  Created by 30Code
 *  date : 2021/1/11
 */
class LoginRepository(private val service : ApiService) : BaseRepository() {

    @ExperimentalCoroutinesApi
    suspend fun loginFlow(userName: String, passWord: String) = flow {

        // 输入不能为空
        if (userName.isBlank() || passWord.isBlank()) {
            emit(LoginUiState(enableLoginButton = false))
            return@flow
        }

        service.login(userName, passWord).doSuccess { user ->
            emit(LoginUiState(isSuccess = user, enableLoginButton = true))
        }.doError { errorMsg ->
            emit(LoginUiState<LoginData>(isError = errorMsg, enableLoginButton = true))
        }
    }.onStart {
        emit(LoginUiState(isLoading = true))
    }.flowOn(Dispatchers.IO)
        .catch { emit(LoginUiState(isError = it.message, enableLoginButton = true)) }

}