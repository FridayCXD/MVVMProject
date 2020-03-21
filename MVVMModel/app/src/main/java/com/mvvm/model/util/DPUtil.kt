package com.mvvm.model.util

import android.content.Context
import android.net.wifi.WifiManager
import android.os.Build
import android.os.Build.VERSION
import android.text.TextUtils
import com.mvvm.model.App
import java.io.InputStreamReader
import java.io.LineNumberReader
import java.lang.reflect.Field


/**
 * DPUtil.kt
 * Info: dip 转换
 * Created by cxd on 2020-03-21 20:07
 */

class DPUtil {
    companion object {
        const val TAG = "DeviceUtils"

        fun dip2fpx(context: Context, pDipValue: Float): Float {
            val dm = context.applicationContext.resources.displayMetrics
            return pDipValue * dm.density
        }

        fun dip2fpx(pDipValue: Float): Float {
            return dip2fpx(App.application, pDipValue)
        }

        fun dip2px(context: Context, dipValue: Float): Int {
            val dm = context.applicationContext.resources.displayMetrics
            return (dipValue * dm.density + 0.5f).toInt()
        }

        fun dip2px(dipValue: Float): Int {
            return dip2px(App.application, dipValue)
        }

        fun getDensityValue(): Float {
            return getDensityValue(App.application)
        }

        fun getDensityValue(context: Context): Float {
            val dm = context.applicationContext.resources.displayMetrics
            return dm.density
        }

        fun getDeviceBrand(): String? {
            return Build.BRAND
        }

        fun getDeviceMode(): String? {
            return Build.MODEL
        }

        fun getDeviceVersionoRelease(): String? {
            return VERSION.RELEASE
        }

        fun getDmDensity(context: Context): Float {
            return getDensityValue(context)
        }

        fun getDmDensityDpi(): Float {
            return getDmDensityDpi(App.application)
        }

        fun getDmDensityDpi(context: Context): Float {
            val dm = context.applicationContext.resources.displayMetrics
            return dm.densityDpi.toFloat()
        }

        fun getScreenHeight(): Int {
            return getScreenHeight(App.application)
        }

        fun getScreenHeight(context: Context): Int {
            val dm = context.applicationContext.resources.displayMetrics
            return if (dm.widthPixels > dm.heightPixels) dm.widthPixels else dm.heightPixels
        }

        fun getScreenWidth(): Int {
            return getScreenWidth(App.application)
        }

        fun getScreenWidth(context: Context): Int {
            val dm = context.applicationContext.resources.displayMetrics
            return if (dm.widthPixels > dm.heightPixels) dm.heightPixels else dm.widthPixels
        }

        fun getStatusHeight(context: Context): Int {
            var c: Class<*>? = null
            var obj: Any? = null
            var field: Field? = null
            var sbar = 0
            try {
                c = Class.forName("com.android.internal.R\$dimen")
                obj = c.newInstance()
                field = c.getField("status_bar_height")
                val x = field[obj].toString().toInt()
                sbar = context.resources.getDimensionPixelSize(x)
            } catch (var6: Exception) {
            }
            return sbar
        }

        fun hasHoneycomb(): Boolean {
            return VERSION.SDK_INT >= 11
        }

        fun isSDKVersionMoreThanSpecifiedNum(sdkNum: Int): Boolean {
            return VERSION.SDK_INT > sdkNum
        }

        fun px2dip(context: Context, pxValue: Float): Int {
            val dm = context.applicationContext.resources.displayMetrics
            return (pxValue / dm.density + 0.5f).toInt()
        }

        fun px2dip(pxValue: Float): Int {
            return px2dip(App.application, pxValue)
        }
    }
}