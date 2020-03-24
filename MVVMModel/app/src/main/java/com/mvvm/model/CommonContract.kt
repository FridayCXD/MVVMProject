package com.mvvm.model

import android.view.View

/**
 * CommonContract.kt
 * Info:
 * Created by cxd on 2020-03-22 11:27
 */

object CommonContract {
    interface IView {
        fun closePage(view: View)
    }

    interface IViewModel
    interface IModel
}