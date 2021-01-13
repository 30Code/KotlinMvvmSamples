package cn.linhome.kotlinmvvmsamples.ui.main.fragment

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.ui.home.HomePageFragment
import cn.linhome.kotlinmvvmsamples.ui.navigation.NavigationFragment
import cn.linhome.kotlinmvvmsamples.ui.project.ProjectTypeFragment
import cn.linhome.kotlinmvvmsamples.ui.square.SquareFragment
import cn.linhome.kotlinmvvmsamples.ui.system.SystemFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_home.*

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class HomeFragment : BaseFragment() {

//    private var isLogin by Preference(Preference.IS_LOGIN, false)

    private val titleList = arrayOf("首页", "广场", "最新项目", "体系", "导航")
    private val fragmentList = arrayListOf<Fragment>()
    private val homeFragment by lazy { HomePageFragment() } // 首页
    private val squareFragment by lazy { SquareFragment() } // 广场
    private val lastedProjectFragment by lazy { ProjectTypeFragment() } // 最新项目
    private val systemFragment by lazy { SystemFragment() } // 体系
    private val navigationFragment by lazy { NavigationFragment() } // 导航
    private var mOnPageChangeCallback: ViewPager2.OnPageChangeCallback? = null

    init {
        fragmentList.add(homeFragment)
        fragmentList.add(squareFragment)
        fragmentList.add(lastedProjectFragment)
        fragmentList.add(systemFragment)
        fragmentList.add(navigationFragment)
    }

    override fun attachLayoutRes(): Int = R.layout.fragment_home

    override fun initView(view: View) {
        initViewPager()
        addFab.setOnClickListener {
//            if (!isLogin) Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_login)
//            else Navigation.findNavController(viewPager).navigate(R.id.action_tab_to_share)
        }
    }

    override fun lazyLoad() {

    }

    private fun initViewPager() {
        viewPager.offscreenPageLimit = 1
        viewPager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]

            override fun getItemCount() = titleList.size
        }


        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = titleList[position]
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        if (mOnPageChangeCallback == null) mOnPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                if (position == 1) addFab.show() else addFab.hide()
            }
        }
        mOnPageChangeCallback?.let { viewPager.registerOnPageChangeCallback(it) }
    }

    override fun onStop() {
        super.onStop()
        mOnPageChangeCallback?.let { viewPager.unregisterOnPageChangeCallback(it) }
    }


    private fun refreshView() {
//        homeFragment.refresh()
//        lastedProjectFragment.refresh()
    }
}