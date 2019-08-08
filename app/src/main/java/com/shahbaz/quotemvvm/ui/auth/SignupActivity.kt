package com.shahbaz.quotemvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.shahbaz.quotemvvm.R
import com.shahbaz.quotemvvm.data.db.entities.User
import com.shahbaz.quotemvvm.databinding.ActivitySignupBinding
import com.shahbaz.quotemvvm.ui.home.HomeActivity
import com.shahbaz.quotemvvm.util.hide
import com.shahbaz.quotemvvm.util.show
import com.shahbaz.quotemvvm.util.snakebar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(),AuthListener,KodeinAware {

    override val kodein by kodein()

    private  val factory:AuthViewFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding:ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)


        binding.viewmodel = viewModel

        viewModel.authListener = this

        viewModel.getLoggedInUser().observe(this, Observer { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }


            }
        })
    }


    override fun onStarted() {
        progress_bar.show()


        // toast("Login Started")
        //root_layout.snakebar("Login Started")
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
        // toast("${user.name} is logged in :)")
        root_layout.snakebar("${user.name} is logged in :)")

    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        // toast(message)
        root_layout.snakebar(message)
    }

}
