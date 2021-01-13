package cn.linhome.kotlinmvvmsamples.ui.main

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import cn.linhome.kotlinmvvmsamples.R
import cn.linhome.kotlinmvvmsamples.base.BaseFragment
import cn.linhome.kotlinmvvmsamples.ui.main.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_bottom_navigation.*

/**
 *  des :
 *  Created by 30Code
 *  date : 2021/1/13
 */
class MainFragment : BaseFragment() {

    private val fragmentList = arrayListOf<Fragment>()
    private val mainFragment by lazy { HomeFragment() }
    private val blogFragment by lazy { BlogFragment() }
    private val searchFragment by lazy { SearchFragment() }
    private val projectFragment by lazy { ProjectFragment() }
    private val profileFragment by lazy { MyFragment() }

    init {
        fragmentList.run {
            add(mainFragment)
            add(blogFragment)
            add(searchFragment)
            add(projectFragment)
            add(profileFragment)
        }
    }


    override fun attachLayoutRes(): Int = R.layout.fragment_bottom_navigation

    override fun initView(view: View) {
        initViewPager()
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelected)
    }

    override fun lazyLoad() {

    }

    private val onNavigationItemSelected = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                switchFragment(0)
            }
            R.id.blog -> {
                switchFragment(1)
            }
            R.id.search -> {
                switchFragment(2)
            }
            R.id.project -> {
                switchFragment(3)
            }
            R.id.profile -> {
                switchFragment(4)
            }
        }
        true
    }

    private fun switchFragment(position: Int): Boolean {
        mainViewpager.setCurrentItem(position, false)
        return true
    }

    private fun initViewPager() {
        mainViewpager.isUserInputEnabled = false
        mainViewpager.offscreenPageLimit = 2
        mainViewpager.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int) = fragmentList[position]

            override fun getItemCount() = fragmentList.size
        }
    }
}