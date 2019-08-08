package com.shahbaz.quotemvvm.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


const val CURRENT_USER_ID = 0;

@Entity
data class User(
    val id: Int? = 0,
    val name: String? = null,
    val email: String? = null,
    val email_verified_at: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null

) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}

