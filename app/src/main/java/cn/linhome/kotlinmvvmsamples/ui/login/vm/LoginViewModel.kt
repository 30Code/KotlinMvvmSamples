package cn.linhome.kotlinmvvmsamples.ui.login.vm

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import cn.linhome.kotlinmvvmsamples.CoroutinesDispatcherProvider
import cn.linhome.kotlinmvvmsamples.base.BaseViewModel
import cn.linhome.kotlinmvvmsamples.ext.executeResponse
import cn.linhome.kotlinmvvmsamples.model.bean.BaseResponse
import cn.linhome.kotlinmvvmsamples.model.bean.LoginData
import cn.linhome.kotlinmvvmsamples.model.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 *  des :
 *  Created by 30Code
 *  date : 2020/4/24
 */
class LoginViewModel(val repository: LoginRepository, val provider: CoroutinesDispatcherProvider) : BaseViewModel() {

    val userName = ObservableField<String>("")
    val passWord = ObservableField<String>("")

//    val mLoginData : MutableLiveData<BaseResponse<LoginData>> = MutableLiveData()
//
//    fun login(username : String, password : String) {
//        launch {
//            val response = withContext(Dispatchers.IO) {
//                repository.login(username, password)
//            }
//            executeResponse(response,
//                {mLoginData.value = response},
//                {mErrorMsg.value = response.errorMsg})
//        }
//    }

}