package cn.linhome.kotlinmvvmsamples.ui.main

import android.os.Bundle
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseActivity
import cn.linhome.kotlinmvvmsamples.ui.main.appview.*
import cn.linhome.kotlinmvvmsamples.view.MainBottomNavigationView
import cn.linhome.lib.utils.FViewUtil
import kotlinx.android.synthetic.main.act_main.*

class MainActivity : BaseActivity() {

    private var mHomeView : HomeView? = null
    private var mSquareView : SquareView? = null
    private var mWechatView : WechatView? = null
    private var mKnowledgeSystemView : KnowledgeSystemView? = null
    private var mProjectView : ProjectView? = null

    override fun onCreateContentView(): Int = R.layout.act_main

    override fun init(savedInstanceState: Bundle?) {
        super.init(savedInstanceState)
        mIsExitApp = true

        view_bottom_navigation.setCallBack(object : MainBottomNavigationView.Callback {

            override fun onTabReselected(index: Int) {

            }

            override fun onTabSelected(index: Int) {
                when (index) {
                    MainBottomNavigationView.INDEX_HOME -> {
                        onSelectTabHome()
                    }
                    MainBottomNavigationView.INDEX_SQUARE -> {
                        onSelectTabSquare()
                    }
                    MainBottomNavigationView.INDEX_WECHAT -> {
                        onSelectTabWechat()
                    }
                    MainBottomNavigationView.INDEX_KNOWLEDGESYSTEM -> {
                        onSelectTabKnowledgeSystem()
                    }
                    MainBottomNavigationView.INDEX_PROJECT -> {
                        onSelectTabProject()
                    }
                }
            }

        })

        view_bottom_navigation.selectTab(MainBottomNavigationView.INDEX_WECHAT)
    }

    private fun onSelectTabHome() {
        FViewUtil.toggleView(fl_main_content, getHomeView())
    }

    private fun onSelectTabSquare() {
        FViewUtil.toggleView(fl_main_content, getSquareView())
    }

    private fun onSelectTabWechat() {
        FViewUtil.toggleView(fl_main_content, getWechatView())
    }

    private fun onSelectTabKnowledgeSystem() {
        FViewUtil.toggleView(fl_main_content, getKnowledgeSystemView())
    }

    private fun onSelectTabProject() {
        FViewUtil.toggleView(fl_main_content, getProjectView())
    }

    private fun getHomeView() : HomeView {
        if (mHomeView == null) {
            mHomeView = HomeView(this)
        }
        return mHomeView as HomeView
    }

    private fun getSquareView() : SquareView {
        if (mSquareView == null) {
            mSquareView = SquareView(this)
        }
        return mSquareView as SquareView
    }

    private fun getWechatView() : WechatView {
        if (mWechatView == null) {
            mWechatView = WechatView(this)
        }
        return mWechatView as WechatView
    }

    private fun getKnowledgeSystemView() : KnowledgeSystemView {
        if (mKnowledgeSystemView == null) {
            mKnowledgeSystemView = KnowledgeSystemView(this)
        }
        return mKnowledgeSystemView as KnowledgeSystemView
    }

    private fun getProjectView() : ProjectView {
        if (mProjectView == null) {
            mProjectView = ProjectView(this)
        }
        return mProjectView as ProjectView
    }
}
