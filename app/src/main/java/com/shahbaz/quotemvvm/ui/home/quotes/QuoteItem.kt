package com.shahbaz.quotemvvm.ui.home.quotes

import com.shahbaz.quotemvvm.R
import com.shahbaz.quotemvvm.data.db.entities.Quote
import com.shahbaz.quotemvvm.databinding.QuoteItemBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote
) : BindableItem<QuoteItemBinding>() {

    override fun getLayout() = R.layout.quote_item

    override fun bind(viewBinding: QuoteItemBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}