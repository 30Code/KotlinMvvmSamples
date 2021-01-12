package cn.linhome.kotlinmvvmsamples.ui.login

import cn.linhome.kotlinmvvmsamples.base.BaseViewModel

/**
 *  des : LoginUiState
 *  Created by 30Code
 *  date : 2021/1/11
 */
class LoginUiState<T>(
        isLoading: Boolean = false,
        isSuccess: T? = null,
        isError: String? = null,
        val enableLoginButton: Boolean = false,
        val needLogin: Boolean = false
) : BaseViewModel.UiState<T>(isLoading, false, isSuccess, isError)
