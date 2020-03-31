package com.mvvm.model.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import javax.sql.DataSource

@Dao
interface CustomerDao {
    /**
     * Insert a customer
     * @param customer Customer.
     */
    @Insert
    fun insert(customer: Customer?)

    /**
     * Insert multiple customers.
     * @param customers Customers.
     */
    @Insert
    fun insertAll(customers: Array<Customer?>?)

    /**
     * Delete all customers
     */
    @Query("DELETE FROM customer")
    fun removeAll()

    /**
     * @return number of customers
     */
    @Query("SELECT * FROM customer")
    fun countCustomers(): Int

    // Keyed
    @Query("SELECT * from customer ORDER BY mLastName DESC LIMIT :limit")
    fun customerNameInitial(limit: Int): List<Customer?>?

    @Query("SELECT * from customer WHERE mLastName < :key ORDER BY mLastName DESC LIMIT :limit")
    fun customerNameLoadAfter(
        key: String?,
        limit: Int
    ): List<Customer?>?

    @Query("SELECT COUNT(*) from customer WHERE mLastName < :key ORDER BY mLastName DESC")
    fun customerNameCountAfter(key: String?): Int

    @Query("SELECT * from customer WHERE mLastName > :key ORDER BY mLastName ASC LIMIT :limit")
    fun customerNameLoadBefore(
        key: String?,
        limit: Int
    ): List<Customer?>?

    @Query("SELECT COUNT(*) from customer WHERE mLastName > :key ORDER BY mLastName ASC")
    fun customerNameCountBefore(key: String?): Int
}