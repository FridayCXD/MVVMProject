package com.mvvm.model.viewpager2.home

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.mvvm.model.db.User


/**
 * VPHomeContract.kt
 * Info: 首页接口
 * Created by cxd on 2020-03-31 20:21
 */

object VPHomeContract {
    interface IView {
        fun delete(view: View)
        fun add(view: View)
        fun findAll(view: View)
    }

    interface IViewModel {
        val userData: MutableLiveData<List<User>>
    }

    interface IModel {
        fun getCacheData(): MutableLiveData<List<User>>
    }
}