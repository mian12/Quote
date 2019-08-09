package com.shahbaz.quotemvvm.util

import android.app.Application
import com.shahbaz.quotemvvm.data.db.AppDatabase
import com.shahbaz.quotemvvm.data.network.MyApi
import com.shahbaz.quotemvvm.data.network.NetworkConnectionIntercepter
import com.shahbaz.quotemvvm.data.preferences.PreferencesProvider
import com.shahbaz.quotemvvm.data.repositories.QuoteRepository
import com.shahbaz.quotemvvm.data.repositories.UserRepository
import com.shahbaz.quotemvvm.ui.auth.AuthViewFactory
import com.shahbaz.quotemvvm.ui.home.profile.ProfileViewFactory
import com.shahbaz.quotemvvm.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {

        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionIntercepter(instance()) }

        bind() from singleton { MyApi(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { PreferencesProvider(instance()) }


        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from singleton { QuoteRepository(instance(), instance(), instance()) }


        bind() from provider { AuthViewFactory(instance()) }
        bind() from provider { ProfileViewFactory(instance()) }
        bind() from provider { QuotesViewModelFactory(instance()) }


    }

}