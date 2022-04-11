package com.example.kmf.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kmf.data.User
import com.example.kmf.network.MainRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.launch


@DelicateCoroutinesApi
class AuthViewModel(
    private val repository: MainRepository
): ViewModel() {

    val userLiveData: LiveData<User?>
        get() = _userLiveData
    private val _userLiveData = MutableLiveData<User?>()

    fun register(
        username: String,
        name: String,
        surname: String,
        email: String,
        password: String,
        phone: String,
        status: Int
    ) = viewModelScope.launch {

        val user = User(
            id = 0,
            username = username,
            firstName = name,
            lastName = surname,
            email = email,
            password = password,
            phone = phone,
            userStatus = status
        )
        repository.register(user)

    }

    fun getUser(
        username: String
    ) = viewModelScope.launch {

        repository.getUser(username)
        Log.d("USERFROMDATABASE", repository.getUserInfo().toString())
        _userLiveData.value = repository.getUserInfo()
    }

}