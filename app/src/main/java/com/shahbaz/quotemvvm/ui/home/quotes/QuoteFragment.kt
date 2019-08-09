package com.shahbaz.quotemvvm.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager

import com.shahbaz.quotemvvm.R
import com.shahbaz.quotemvvm.data.db.entities.Quote
import com.shahbaz.quotemvvm.util.Corotunies
import com.shahbaz.quotemvvm.util.hide
import com.shahbaz.quotemvvm.util.show
import com.shahbaz.quotemvvm.util.toast
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quote_fragment.*
import kotlinx.android.synthetic.main.quote_item.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuoteFragment : Fragment(), KodeinAware {


    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()

    private lateinit var viewModel: QuoteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quote_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, factory).get(QuoteViewModel::class.java)

        bindUI()

//        Corotunies.main {
//           val quotes= viewModel.quote.await()
//            quotes.observe(this, Observer {
//                context?.toast("size of quotes list is ${it.size}")
//            })
//
//        }
    }

    private fun bindUI() = Corotunies.main {

        progress_bar.show()

        viewModel.quote.await().observe(this, Observer {
            progress_bar.hide()
            initRecyclerView(it.toQuoteItem())
        })
    }

    private fun initRecyclerView(quoteItem: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(quoteItem)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }
    }


    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }

}
