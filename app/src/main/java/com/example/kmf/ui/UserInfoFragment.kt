package com.example.kmf.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.Observer
import com.example.kmf.R
import com.example.kmf.data.User
import com.example.kmf.databinding.UserInfoBinding
import com.example.kmf.vm.AuthViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@DelicateCoroutinesApi
class UserInfoFragment : DialogFragment() {

    private lateinit var binding: UserInfoBinding

    private val viewModule: AuthViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UserInfoBinding.bind(inflater.inflate(R.layout.user_info, container))

        binding.button.setOnClickListener { viewModule.getUser(
            binding.usernameField.text.toString()
        ) }

        observeUser()

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
        }
    }

    private fun observeUser() {
        viewModule.userLiveData.observe(viewLifecycleOwner, userObserver)
    }

    private val userObserver = Observer<User?> {
        setUserInfo(it)
    }

    @SuppressLint("SetTextI18n")
    private fun setUserInfo(user: User) {
        binding.login.text = "Username: ${user.username}"
        binding.name.text = "Name: ${user.firstName}"
        binding.surname.text = "Surname: ${user.lastName}"
        binding.email.text = "Email: ${user.email}"
        binding.phone.text = "Phone: ${user.phone}"
        binding.status.text = "Status: ${user.userStatus.toString()}"
    }

}