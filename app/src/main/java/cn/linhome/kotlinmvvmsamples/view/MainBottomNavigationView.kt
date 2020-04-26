package cn.linhome.kotlinmvvmsamples.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.lib.selectionmanager.FSelectManager
import cn.linhome.lib.selectionmanager.FSelectViewManager
import kotlinx.android.synthetic.main.view_main_bottom_tab.view.*

/**
 * 首页底部菜单栏
 */
class MainBottomNavigationView : FrameLayout {

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?) : super(context!!) {
        init()
    }

    private val mSelectionManager = FSelectViewManager<TabMainMenuView?>()

    private var mCallback: Callback? = null

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.view_main_bottom_tab, this, true)
        initTabs()
        initSelectManager()
    }

    private fun initTabs() {
        view_tab_home.configImage()
            .setImageResIdNormal(R.drawable.ic_home_gray_24dp)
            .setImageResIdSelected(R.drawable.ic_home_black_24dp)
            .setSelected(false)
        view_tab_square.configImage()
            .setImageResIdNormal(R.drawable.ic_square_gray_24dp)
            .setImageResIdSelected(R.drawable.ic_square_black_24dp)
            .setSelected(false)
        view_tab_wechat.configImage()
            .setImageResIdNormal(R.drawable.ic_wechat_gray_24dp)
            .setImageResIdSelected(R.drawable.ic_wechat_black_24dp)
            .setSelected(false)
        view_tab_knowledge_system.configImage()
            .setImageResIdNormal(R.drawable.ic_apps_gray_24dp)
            .setImageResIdSelected(R.drawable.ic_apps_black_24dp)
            .setSelected(false)
        view_tab_project.configImage()
            .setImageResIdNormal(R.drawable.ic_project_gray_24dp)
            .setImageResIdSelected(R.drawable.ic_project_black_24dp)
            .setSelected(false)
    }

    private fun initSelectManager() {

        mSelectionManager.addCallback(object : FSelectManager.Callback<TabMainMenuView?> {

            override fun onNormal(item: TabMainMenuView?) {

            }

            override fun onSelected(item: TabMainMenuView?) {
                getCallback().onTabSelected(mSelectionManager.indexOf(item))
            }

        })

        mSelectionManager.setItems(view_tab_home, view_tab_square, view_tab_wechat, view_tab_knowledge_system, view_tab_project)
    }

    fun selectTab(index: Int) {
        mSelectionManager.performClick(index)
    }

    fun setCallBack(callback : Callback) {
        this.mCallback = callback
    }

    private fun getCallback() : Callback {
        if (mCallback == null) {
            mCallback = object : Callback {
                override fun onTabSelected(index: Int) {}
                override fun onTabReselected(index: Int) {}
            }
        }
        return mCallback as Callback
    }

    interface Callback {
        fun onTabSelected(index: Int)
        fun onTabReselected(index: Int)
    }

    companion object {
        const val INDEX_HOME = 0
        const val INDEX_SQUARE = 1
        const val INDEX_WECHAT = 2
        const val INDEX_KNOWLEDGESYSTEM = 3
        const val INDEX_PROJECT = 4
    }
}