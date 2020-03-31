package com.mvvm.model.viewpager2.home

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mvvm.model.App
import com.mvvm.model.db.User


/**
 * VPHomeViewModel.kt
 * Info:
 * Created by cxd on 2020-03-31 20:26
 */

class VPHomeViewModel(bundle: Bundle?) :ViewModel(), VPHomeContract.IViewModel {
    private val model = VPHomeModel()
    override val userData: MutableLiveData<List<User>> = model.getCacheData()



    class Factory(private val bundle: Bundle?, app: App) :
        ViewModelProvider.AndroidViewModelFactory(app) {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return VPHomeViewModel(bundle) as T
        }
    }
}