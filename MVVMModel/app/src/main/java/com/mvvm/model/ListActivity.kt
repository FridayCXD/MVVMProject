package com.mvvm.model

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @ClassName: ListActivity
 * @Description: 描述
 * @Author: xd on  2020-03-09 22:39
 */
class ListActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    companion object {
        fun skip(context: Context){
            val intent = Intent(context,ListActivity::class.java)
            context.startActivity(intent)
        }
    }
}