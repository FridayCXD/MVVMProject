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

/**
 * @ClassName: ListActivity
 * @Description: ViewPager2 使用Fragment的方式
 * @Author: xd on  2020-03-09 22:39
 */
class VP2FragmentActivity : AppCompatActivity(), VP2Contract.IView {
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_list
        )
        binding.apply {
            view = this@VP2FragmentActivity
        }
        binding.listViewPager2.let {
            it.adapter = VPAdapter(this@VP2FragmentActivity)
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
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
        private val fragments: SparseArray<Fragment> = SparseArray()

        init {
            fragments.put(
                PAGE_HOME,
                VP2HomeFragment.getInstance(
                    PAGE_HOME
                )
            )
            fragments.put(
                PAGE_NEWS,
                VP2HomeFragment.getInstance(PAGE_NEWS)
            )
            fragments.put(
                PAGE_MINE,
                VP2HomeFragment.getInstance(
                    PAGE_MINE
                )
            )
        }

        override fun getItemCount(): Int {
            return fragments.size()
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
}