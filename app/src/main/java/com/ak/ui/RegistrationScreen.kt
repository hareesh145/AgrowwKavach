package com.ak.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ak.databinding.RegistrationLayoutBinding

class RegistrationScreen : AppCompatActivity() {

    lateinit var binding: RegistrationLayoutBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}