package com.shahbaz.quotemvvm.ui.auth

import androidx.lifecycle.LiveData
import com.shahbaz.quotemvvm.data.db.entities.User

interface AuthListener {

    fun onStarted()
    fun onSuccess(user: User)
    fun onFailure(message: String)
}