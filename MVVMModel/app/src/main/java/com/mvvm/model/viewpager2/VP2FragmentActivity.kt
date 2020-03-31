package com.mvvm.model.viewpager2

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.model.R
import com.mvvm.model.databinding.ActivityListBinding
import com.mvvm.model.viewpager2.fragment.VP2HomeFragment
import com.mvvm.model.viewpager2.fragment.VPHomeFragment

/**
 * @ClassName: ListActivity
 * @Description: ViewPager2 使用Fragment的方式
 * @Author: xd on  2020-03-09 22:39
 */
class VP2FragmentActivity : AppCompatActivity(), VP2Contract.IView {

    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list)
        binding.apply {
            view = this@VP2FragmentActivity
        }
        //VP2 默认是竖直滑动的
        binding.listViewPager2.apply {
            adapter = VPAdapter(this@VP2FragmentActivity)
        }
    }

    override fun onClosePage(view: View) {
        onBackPressed()
    }

    companion object {
        const val PAGE_HOME = 0
        const val PAGE_NEWS = 1
        const val PAGE_MINE = 2
        fun skip(context: Context) {
            val intent = Intent(context, VP2FragmentActivity::class.java)
            context.startActivity(intent)
        }
    }

    inner class VPAdapter(fragmentActivity: FragmentActivity) :
        FragmentStateAdapter(fragmentActivity) {
        private val fragments = SparseArray<Fragment>(3)


        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            val fragment: Fragment
            when (position) {
                0 -> if (fragments[0] == null) {
                    fragment = VPHomeFragment.getInstance()
                    fragments.put(0, fragment)
                } else {
                    fragment = fragments[0]
                }
                1 -> if (fragments[1] == null) {
                    fragment = VP2HomeFragment.getInstance(PAGE_NEWS)
                    fragments.put(1, fragment)
                } else {
                    fragment = fragments[1]
                }
                else -> if (fragments[2] == null) {
                    fragment = VP2HomeFragment.getInstance(PAGE_MINE)
                    fragments.put(2, fragment)
                } else {
                    fragment = fragments[2]
                }
            }
            return fragment
        }

    }
}