package com.mvvm.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.model.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

/**
 * main activity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setListener()
        val adapter = ViewPager2Adapter()
        val list = mutableListOf(1,2,3,4)
        binding.viewPager2.adapter = adapter
        adapter.setList(list)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL

    }

    private fun setListener(){
        findViewById<ImageView>(R.id.backBtn).setOnClickListener{finish()}
        findViewById<TextView>(R.id.title).text = "首页"
        viewPager2?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
                println("$position , $positionOffset")
            }

        })
    }

}
