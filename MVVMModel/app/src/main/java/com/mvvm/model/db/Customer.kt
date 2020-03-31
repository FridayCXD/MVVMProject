package com.mvvm.model.db

import androidx.recyclerview.widget.DiffUtil
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * Customer.kt
 * Info:
 * Created by cxd on 2020-03-31 21:16
 */
@Entity
class Customer {
    @PrimaryKey(autoGenerate = true)
    private var mId = 0
    private var mName: String? = null
    private var mLastName: String? = null
    fun getId(): Int {
        return mId
    }

    fun setId(id: Int) {
        mId = id
    }

    fun getName(): String? {
        return mName
    }

    fun setName(name: String?) {
        mName = name
    }

    fun getLastName(): String? {
        return mLastName
    }

    fun setLastName(lastName: String?) {
        mLastName = lastName
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) {
            return true
        }
        if (o == null || javaClass != o.javaClass) {
            return false
        }
        val customer = o as Customer
        if (mId != customer.mId) {
            return false
        }
        if (if (mName != null) mName != customer.mName else customer.mName != null) {
            return false
        }
        return if (mLastName != null) mLastName == customer.mLastName else customer.mLastName == null
    }

    override fun hashCode(): Int {
        var result = mId
        result = 31 * result + if (mName != null) mName.hashCode() else 0
        result = 31 * result + if (mLastName != null) mLastName.hashCode() else 0
        return result
    }

    override fun toString(): String {
        return ("Customer{"
                + "mId=" + mId
                + ", mName='" + mName + '\''
                + ", mLastName='" + mLastName + '\''
                + '}')
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<Customer> =
            object : DiffUtil.ItemCallback<Customer>() {
                override fun areContentsTheSame(
                    oldItem: Customer,
                    newItem: Customer
                ): Boolean {
                    return oldItem == newItem
                }

                override fun areItemsTheSame(
                    oldItem: Customer,
                    newItem: Customer
                ): Boolean {
                    return oldItem.getId() == newItem.getId()
                }
            }
    }
}
