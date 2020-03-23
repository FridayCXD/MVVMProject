package com.mvvm.model.viewpager2

import android.view.View


/**
 * VP2Contract.kt
 * Info:
 * Created by cxd on 2020-03-22 18:50
 */

object VP2Contract {
    interface IView{
        fun onClosePage(view:View)
    }
    interface IViewModel
    interface IModel
}