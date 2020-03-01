package com.mvvm.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import com.mvvm.model.databinding.ActivityMainBinding

/**
 * main activity
 */
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        setListener()
    }

    private fun setListener(){
        findViewById<ImageButton>(R.id.backBtn).setOnClickListener{finish()}
        findViewById<TextView>(R.id.title).text = "首页"
    }

}
