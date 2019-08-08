package com.shahbaz.quotemvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahbaz.quotemvvm.data.db.AppDatabase
import com.shahbaz.quotemvvm.data.db.entities.Quote
import com.shahbaz.quotemvvm.data.network.MyApi
import com.shahbaz.quotemvvm.data.network.SafeApiRequest
import com.shahbaz.quotemvvm.util.Corotunies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class QuoteRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {


    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Corotunies.Io {
            db.getQuoteDao().saveAllQuotes(quotes)
        }

    }

    suspend fun getQuotes(): LiveData<List<Quote>> {

        return withContext(Dispatchers.IO) {
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }


    }

    private suspend fun fetchQuotes() {
        if (isFetchNeeded()) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(): Boolean {

        return true
    }
}