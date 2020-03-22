package com.mvvm.model

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * CommonViewModel.kt
 * Info: 通用的ViewModel
 * Created by cxd on 2020-03-22 11:31
 */

class CommonViewModel(bundle: Bundle? = null): ViewModel(),CommonContract.IViewModel {

    class Factory(private val bundle: Bundle?, app: Application)
        : ViewModelProvider.AndroidViewModelFactory(app){
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return CommonViewModel(bundle) as T
        }
    }
}