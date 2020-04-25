package cn.linhome.kotlinmvvmsamples.ui.login.vm

import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.ext.executeResponse
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class LoginViewModel : BaseViewModel() {

    val mLoginData : MutableLiveData<BaseResponse<LoginData>> = MutableLiveData()

    fun login(username : String, password : String) {
        launch {
            val response = withContext(Dispatchers.IO) {
                repository.login(username, password)
            }
            executeResponse(response,
                {mLoginData.value = response},
                {mErrorMsg.value = response.errorMsg})
        }
    }

}