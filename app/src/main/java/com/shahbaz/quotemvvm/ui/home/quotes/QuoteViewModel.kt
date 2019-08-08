package com.shahbaz.quotemvvm.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.shahbaz.quotemvvm.data.repositories.QuoteRepository
import com.shahbaz.quotemvvm.util.lazyDeffered

class QuoteViewModel(
     quoteRepository: QuoteRepository
) : ViewModel() {

    val quote by lazyDeffered {
        quoteRepository.getQuotes()
    }
}
