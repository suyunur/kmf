package com.example.kmf.network

import android.util.Log
import com.example.kmf.api.MainApi
import com.example.kmf.data.User
import com.example.kmf.data.UserDAO
import kotlinx.coroutines.*

@DelicateCoroutinesApi
class MainRepository(
    private val api: MainApi,
    private val userDAO: UserDAO
    ) {

    private var userInfo: User? = null

    fun register(user: User) {
        GlobalScope.launch(Dispatchers.IO) {
            api.auth(user = user)
        }
    }

    suspend fun getUser(username: String) {
        withContext(Dispatchers.IO) {
            val result = api.getUser(username)
            result
            insertUser(result)
            userInfo = userDAO.getUser(username)[0]
            Log.d("NURBEKINFO", userInfo.toString())
        }
    }

    fun getUserInfo() = userInfo

    private fun insertUser(user: User?) {
        userDAO.insertUser(user)
    }

}