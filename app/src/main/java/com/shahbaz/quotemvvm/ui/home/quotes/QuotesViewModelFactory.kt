package com.shahbaz.quotemvvm.ui.home.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.shahbaz.quotemvvm.data.repositories.QuoteRepository
import com.shahbaz.quotemvvm.data.repositories.UserRepository

class QuotesViewModelFactory(
    private val quoteRepository: QuoteRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteViewModel(quoteRepository) as T
    }
}