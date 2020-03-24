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
    }


    override fun closePage() {
        onBackPressed()
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

    }

}

