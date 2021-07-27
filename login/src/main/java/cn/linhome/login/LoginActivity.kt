package cn.linhome.login

import android.os.Bundle
import cn.linhome.common.Constant
import cn.linhome.common.base.BaseActivity
import cn.linhome.login.databinding.ActivityLoginBinding
import com.alibaba.android.arouter.facade.annotation.Route

/**
 *  des : LoginActivity
 *  Created by 30Code
 *  date : 2021/7/20
 */
@Route(path = Constant.ARouterPath.PATH_LOGIN)
class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun initActivity(savedInstanceState: Bundle?) {

    }

}