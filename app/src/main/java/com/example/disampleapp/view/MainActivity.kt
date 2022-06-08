package com.example.disampleapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.disampleapp.data.model.LoginBodyRequest
import com.example.disampleapp.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModel<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            delay(3000)
            val request = LoginBodyRequest("john@mail.com", "123456")
            viewModel.userIntent.send(MainIntent.Login(request))
        }
        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.loginState.observe(this) { mainState ->
            when (mainState) {
                is MainState.Loading -> {
                    binding.loginProgressBar.visibility = View.VISIBLE
                }
                is MainState.Success -> {
                    binding.loginProgressBar.visibility = View.GONE
                    Toast.makeText(this, "Welcome ${mainState.data.data.firstName}", Toast.LENGTH_SHORT).show()
                }
                is MainState.Error -> {
                    binding.loginProgressBar.visibility = View.GONE
                    Toast.makeText(this, mainState.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {}
}