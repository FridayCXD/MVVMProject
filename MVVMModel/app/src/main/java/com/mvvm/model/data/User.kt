package com.mvvm.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Id

/**
 * @ClassName: User
 * @Description: user实体类
 * @Author: xd on  2020-03-03 22:59
 */
@Parcelize
@Entity
data class User(
    @Id
    var id:Long,
    var name: String
) : Parcelable