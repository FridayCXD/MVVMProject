package com.mvvm.model.viewpager2

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseArray
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.model.R
import com.mvvm.model.databinding.ActivityListBinding

/**
 * @ClassName: ListActivity
 * @Description: 描述
 * @Author: xd on  2020-03-09 22:39
 */
class ListActivity : AppCompatActivity(){
    lateinit var binding: ActivityListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_list
        )
        binding.listViewPager2.let {
            it.adapter = VPAdapter(this@ListActivity)
            it.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }
        setListener()
    }

    @SuppressLint("SetTextI18n")
    private fun setListener(){
        findViewById<ImageView>(R.id.backBtn).setOnClickListener{ finish() }
        findViewById<TextView>(R.id.title).text = "VP2"
    }

    companion object {
        const val PAGE_HOME = 0
        const val PAGE_OTHERS = 1
        const val PAGE_ME = 2

        fun skip(context: Context){
            val intent = Intent(context, ListActivity::class.java)
            context.startActivity(intent)
        }
    }
    inner class VPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity){
        private val fragments: SparseArray<Fragment> = SparseArray()

        init {
            fragments.put(
                PAGE_HOME, VP2Fragment.getInstance(
                    PAGE_HOME
                ))
            fragments.put(
                PAGE_OTHERS, VP2Fragment.getInstance(
                    PAGE_OTHERS
                ))
            fragments.put(
                PAGE_ME, VP2Fragment.getInstance(
                    PAGE_ME
                ))
        }
        override fun getItemCount(): Int {
           return fragments.size()
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

    }
}