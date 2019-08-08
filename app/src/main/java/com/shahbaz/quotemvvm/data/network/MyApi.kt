package com.shahbaz.quotemvvm.data.network

import com.shahbaz.quotemvvm.data.db.entities.Quote
import com.shahbaz.quotemvvm.data.network.responses.AuthResponse
import com.shahbaz.quotemvvm.data.network.responses.QuoteResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun loginUser(
        @Field("email") email: String,
        @Field("password") password: String
    ): Response<AuthResponse>


    @FormUrlEncoded
    @POST("signup")
    suspend fun signupUser(
        @Field("name") name: String,
        @Field("email") email: String,
        @Field("password") password: String

    ): Response<AuthResponse>


    @GET("quotes")
    suspend fun getQuotes(): Response<QuoteResponse>


    companion object {
        operator fun invoke(

            networkConnectionIntercepter: NetworkConnectionIntercepter
        ): MyApi {

            val httpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionIntercepter)
                .build()

            return Retrofit.Builder()
                .client(httpClient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)

        }
    }

}