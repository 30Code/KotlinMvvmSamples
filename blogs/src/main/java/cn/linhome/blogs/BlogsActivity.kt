package cn.linhome.blogs

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cn.linhome.common.Constant
import com.alibaba.android.arouter.facade.annotation.Route

@Route(path = Constant.ARouterPath.PATH_LOGIN)
class BlogsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blogs)
    }
}