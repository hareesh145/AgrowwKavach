package com.ak.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ak.SharedPref
import com.ak.databinding.LoginBinding
import com.ak.model.LoginModel
import com.ak.ui.home.HomeScreen
import com.ak.util.Utils
import com.ak.viewmodel.LoginViewModel
import com.ak.viewmodel.NetworkRepository

class LoginScreen : AppCompatActivity() {
    lateinit var binding: LoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = LoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            Utils.showProgessBar(this)
            val networkRepository = NetworkRepository()
            val loginViewModel = LoginViewModel(networkRepository)
            loginViewModel.login(
                LoginModel(
                    binding.emailEditText.text.toString(),
                    binding.passwordEditText.text.toString()
                )
            )
            loginViewModel.loginResponse.observe(this) {
                Utils.hideProgressBar()
                if (!it.success) {
                    Toast.makeText(this, it.errorMessage, Toast.LENGTH_SHORT).show()
                    return@observe

                } else {
                    SharedPref.getInstance(this).userProfile = it.userInfo
                    startActivity(Intent(this, HomeScreen::class.java))
                    finish()
                }
            }
            loginViewModel.errorMessage.observe(this) { error ->
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
            }

            loginViewModel.isLoading.observe(this) { isLoading ->
//                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
        }

        binding.createAccountTextView.setOnClickListener {
            startActivity(Intent(this, RegistrationScreen::class.java))
        }
    }
}