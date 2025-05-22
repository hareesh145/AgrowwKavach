package com.ak.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ak.SharedPref
import com.ak.databinding.LoginBinding
import com.ak.model.LoginModel
import com.ak.ui.home.HomeScreen
import com.ak.util.Result
import com.ak.util.Utils
import com.ak.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginScreen : AppCompatActivity() {
    lateinit var binding: LoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            loginViewModel.login(
                LoginModel(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            )
        }
        loginViewModel.loginResponse.observe(this) {
            /* if (!it.success) {
                 Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                 return@observe

             } else {
                 SharedPref.getInstance(this).userProfile = it.userInfo
                 startActivity(Intent(this, HomeScreen::class.java))
                 finish()
             }*/
            when (it) {
                is Result.Loading -> {
                    // Show loading indicator
                    Utils.showProgessBar(this)
                }

                is Result.Success -> {
                    // Hide loading indicator
                    Utils.hideProgressBar()
                    // Handle success
                    val response = it.data
                    SharedPref.getInstance(this).userProfile = response.userInfo
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }

                is Result.Error -> {
                    // Hide loading indicator
                    Utils.hideProgressBar()
                    // Handle error
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
                is Result.Idle -> {
                    // Hide loading indicator
                    Utils.hideProgressBar()
                }
            }
        }

        binding.createAccountTextView.setOnClickListener {
            startActivity(Intent(this, RegistrationScreen::class.java))
        }
    }
}