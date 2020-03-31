package com.mvvm.model.viewpager2.home

import androidx.lifecycle.MutableLiveData
import com.mvvm.model.App
import com.mvvm.model.db.User


/**
 * VPHomeModel.kt
 * Info:
 * Created by cxd on 2020-03-31 20:23
 */

class VPHomeModel : VPHomeContract.IModel {
    private val userData: MutableLiveData<List<User>> = MutableLiveData()
    override fun getCacheData(): MutableLiveData<List<User>> {
        App.dataBase.userDao().insert(User(1, "南京", "古城"))
        val list = App.dataBase.userDao().findAll()
        userData.postValue(list)
        return userData
    }
}