package com.shahbaz.quotemvvm.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.shahbaz.quotemvvm.data.repositories.UserRepository

class ProfileViewModel(userRepository: UserRepository) : ViewModel() {

    val user=userRepository.getUser()
    //
}
