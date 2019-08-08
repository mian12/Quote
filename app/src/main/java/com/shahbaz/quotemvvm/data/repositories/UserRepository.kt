package com.shahbaz.quotemvvm.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.shahbaz.quotemvvm.data.db.AppDatabase
import com.shahbaz.quotemvvm.data.db.entities.User
import com.shahbaz.quotemvvm.data.network.MyApi
import com.shahbaz.quotemvvm.data.network.SafeApiRequest
import com.shahbaz.quotemvvm.data.network.responses.AuthResponse
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UserRepository(
    private val api: MyApi,
    private val db: AppDatabase
) : SafeApiRequest() {

    suspend fun loginUser(email: String, password: String): AuthResponse {

        return apiRequest { api.loginUser(email!!, password!!) }

    }

    suspend fun signUpUser(name: String, email: String, password: String): AuthResponse {
        return apiRequest { api.signupUser(name!!, email!!, password!!) }

    }


    suspend fun saveUser(user: User) = db.getUserDao().upsert(user)

    fun getUser() = db.getUserDao().getUser()
}