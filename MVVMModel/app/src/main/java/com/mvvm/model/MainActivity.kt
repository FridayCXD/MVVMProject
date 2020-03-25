package com.mvvm.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.mvvm.model.databinding.ActivityMainBinding
import com.mvvm.model.viewpager2.VP2FragmentActivity
import com.mvvm.model.viewpager2.ViewPager2Adapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

/**
 * main activity
 */
class MainActivity : AppCompatActivity(), CommonContract.IView {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CommonContract.IViewModel
    private val list = ArrayList<Int>()
    private val adapterVP2 = ViewPager2Adapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.apply {
            lifecycleOwner = this@MainActivity
            view = this@MainActivity
        }
        viewModel = ViewModelProvider(
            this,
            CommonViewModel.Factory(intent.extras, App.application)
        ).get(CommonViewModel::class.java)

        binding.viewPager2.apply {
            adapter = adapterVP2
        }
        setListener()
        initData()
    }

    private fun initData() {
        list.addAll(listOf(1, 2, 3, 4))
        adapterVP2.setList(list)
        binding.viewPager2.setPageTransformer(DepthPageTransformer())
    }


    override fun closePage(view: View) {
        if (binding.viewPager2.currentItem == 0) {
            onBackPressed()
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }

    override fun onBackPressed() {
        if (binding.viewPager2.currentItem == 0) {
            super.onBackPressed()
        } else {
            binding.viewPager2.currentItem = binding.viewPager2.currentItem - 1
        }
    }

    private fun setListener() {
        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                println("$position")
            }
        })
        adapterVP2.setOnItemClickListener {
            VP2FragmentActivity.skip(this)
        }
        binding.switchBtn.setOnCheckedChangeListener { _, isChecked ->
            binding.viewPager2.isUserInputEnabled = !isChecked
        }
    }

    class DepthPageTransformer : ViewPager2.PageTransformer {
        private val MIN_SCALE = 0.75f
        override fun transformPage(view: View, position: Float) {
            view.apply {
                val pageWidth = width
                when {
                    position < -1 -> { // [-Infinity,-1)
                        alpha = 0f
                    }
                    position <= 0 -> { // [-1,0]
                        alpha = 1f
                        translationX = 0f
                        scaleX = 1f
                        scaleY = 1f
                    }
                    position <= 1 -> { // (0,1]
                        alpha = 1 - position
                        translationX = pageWidth * -position
                        val scaleFactor = (MIN_SCALE + (1 - MIN_SCALE) * (1 - Math.abs(position)))
                        scaleX = scaleFactor
                        scaleY = scaleFactor
                    }
                    else -> { // (1,+Infinity]
                        alpha = 0f
                    }
                }
            }
        }
    }


}

