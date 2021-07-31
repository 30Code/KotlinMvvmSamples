package cn.linhome.home.ui.search

import android.os.Bundle
import cn.linhome.common.base.BaseActivity
import cn.linhome.common.constant.Constant
import cn.linhome.home.R
import cn.linhome.home.databinding.ActivitySearchBinding
import com.alibaba.android.arouter.facade.annotation.Route

/**
 *  des : 搜索
 *  Created by 30Code
 *  date : 2021/7/31
 */
@Route(path = Constant.ARouterPath.PATH_SEARCH)
class SearchActivity : BaseActivity<ActivitySearchBinding>() {

    override fun getLayoutId(): Int = R.layout.activity_search

    override fun initActivity(savedInstanceState: Bundle?) {

        mBinding?.run {

        }

    }
}