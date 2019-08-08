package com.shahbaz.quotemvvm.data.network.responses

import com.shahbaz.quotemvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful: Boolean? = null,
    val message: String? = null,
    val user: User? = null


)

