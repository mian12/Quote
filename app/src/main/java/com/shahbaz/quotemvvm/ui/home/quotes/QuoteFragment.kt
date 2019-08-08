package com.shahbaz.quotemvvm.ui.home.quotes

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.shahbaz.quotemvvm.R
import com.shahbaz.quotemvvm.util.Corotunies
import com.shahbaz.quotemvvm.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuoteFragment : Fragment(),KodeinAware {


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
        viewModel = ViewModelProviders.of(this,factory).get(QuoteViewModel::class.java)

        Corotunies.main {
           val quotes= viewModel.quote.await()
            quotes.observe(this, Observer {
                context?.toast("size of quotes list is ${it.size}")
            })

        }
    }

}
