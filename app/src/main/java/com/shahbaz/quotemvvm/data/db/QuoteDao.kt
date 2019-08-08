package com.shahbaz.quotemvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.google.android.material.circularreveal.CircularRevealHelper
import com.shahbaz.quotemvvm.data.db.entities.Quote

@Dao
interface QuoteDao {

    @Insert(onConflict =OnConflictStrategy.REPLACE )
    fun saveAllQuotes(quote: List<Quote>)

    @Query("select * from quote")
    fun getQuotes(): LiveData<List<Quote>>

}