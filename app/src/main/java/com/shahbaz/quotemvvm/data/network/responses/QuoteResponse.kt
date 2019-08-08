package com.shahbaz.quotemvvm.data.network.responses

import com.shahbaz.quotemvvm.data.db.entities.Quote

data class QuoteResponse(
     val isSuccessful: Boolean,
     val quotes: List<Quote>

)