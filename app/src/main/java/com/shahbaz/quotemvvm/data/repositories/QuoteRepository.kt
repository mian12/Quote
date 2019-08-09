package com.shahbaz.quotemvvm.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahbaz.quotemvvm.data.db.AppDatabase
import com.shahbaz.quotemvvm.data.db.entities.Quote
import com.shahbaz.quotemvvm.data.network.MyApi
import com.shahbaz.quotemvvm.data.network.SafeApiRequest
import com.shahbaz.quotemvvm.data.preferences.PreferencesProvider
import com.shahbaz.quotemvvm.util.Corotunies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


private const val MINIMUM_INTERVAL = 6

class QuoteRepository(
    private val api: MyApi,
    private val db: AppDatabase,
    private val prefernce: PreferencesProvider
) : SafeApiRequest() {


    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {
            saveQuotes(it)
        }
    }

    private fun saveQuotes(quotes: List<Quote>) {
        Corotunies.Io {
            prefernce.saveLastSavedAt(LocalDateTime.now().toString())
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
        val lastSavedAt = prefernce.getLastSavedAt()

        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))) {
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    private fun isFetchNeeded(lastSaveAt: LocalDateTime): Boolean {

        return ChronoUnit.HOURS.between(lastSaveAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }
}