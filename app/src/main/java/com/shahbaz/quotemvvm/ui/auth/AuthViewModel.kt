package com.shahbaz.quotemvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.shahbaz.quotemvvm.data.db.AppDatabase
import com.shahbaz.quotemvvm.data.repositories.UserRepository
import com.shahbaz.quotemvvm.util.ApiException
import com.shahbaz.quotemvvm.util.Corotunies
import com.shahbaz.quotemvvm.util.NoInternetException
import org.jetbrains.annotations.NonNls

class AuthViewModel(
    private val repository: UserRepository

) : ViewModel() {

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    var authListener: AuthListener? = null


    fun getLoggedInUser() = repository.getUser()


    fun onLogin(view: View) {
        Intent(view.context, LoginActivity::class.java).also {
            view.context.startActivity(it)

        }
    }

    fun onSignUp(view: View) {
        Intent(view.context, SignupActivity::class.java).also {
            view.context.startActivity(it)

        }
    }

    fun onSignupButtonClick(view: View) {


        authListener?.onStarted()

        if (name.isNullOrEmpty()) {
            authListener?.onFailure("name is required")
            return
        }

        if (email.isNullOrEmpty()) {

            authListener?.onFailure(" email is required")
            return

        }
        if (password.isNullOrEmpty()) {
            authListener?.onFailure(" password is required")
            return
        }
        if (confirmPassword != password) {
            authListener?.onFailure(" Confirm password didn't match")
            return
        }



        Corotunies.main {


            try {
                val authResponse = repository.signUpUser(name!!, email!!, password!!)



                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailure(authResponse.message!!)


            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }

    fun onLoginButtonClick(view: View) {

        authListener?.onStarted()

        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {

            authListener?.onFailure("invalid email and password")
            return

        }



        Corotunies.main {


            try {
                val authResponse = repository.loginUser(email!!, password!!)



                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }

                authListener?.onFailure(authResponse.message!!)


            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            } catch (e: NoInternetException) {
                authListener?.onFailure(e.message!!)
            }


        }


    }


}