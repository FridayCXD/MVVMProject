package com.mvvm.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.model.databinding.ActivityMainBinding
import com.mvvm.model.viewpager2.VP2FragmentActivity
import com.mvvm.model.viewpager2.ViewPager2Adapter
import kotlinx.android.synthetic.main.activity_main.*

/**
 * main activity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CommonContract.IViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(
            this,
            CommonViewModel.Factory(intent.extras,App.application)
        ).get(CommonViewModel::class.java)

        setListener()
        val adapters = ViewPager2Adapter()
        val list = mutableListOf(1,2,3,4)
        adapters.setList(list)
        binding.viewPager2.apply {
            orientation = ViewPager2.ORIENTATION_VERTICAL
            adapter = adapters
        }
        adapters.setOnItemClickListener {
            VP2FragmentActivity.skip(this)
        }
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

