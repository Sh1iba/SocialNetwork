package com.example.social_network.Activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.social_network.MainActivity
import com.example.social_network.data.model.LoginRequest
import com.example.social_network.data.network.RetrofitInstance.getApiService
import com.example.social_network.databinding.ActivitySignBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SignActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    fun onSign(view: View) {
        val loginText: String = binding.editTextLogin.text.toString()
        val passwordText: String = binding.editTextPassword.text.toString()

        if (loginText.isEmpty() || passwordText.isEmpty()) {
            Toast.makeText(this, "Please enter both email and password", Toast.LENGTH_SHORT).show()
            return
        }
        val loginRequest = LoginRequest(loginText, passwordText)

        lifecycleScope.launch(Dispatchers.Main) {
            try {
                val apiService = getApiService()
                val response = withContext(Dispatchers.IO) {
                    apiService.login(loginRequest)
                }

                Toast.makeText(this@SignActivity, "Login successful!", Toast.LENGTH_SHORT).show()
                Log.d("OtvetServera", response.toString())

                val intent = Intent(this@SignActivity, MainActivity::class.java).apply {
                    putExtra("fullName", response.fullName)
                    putExtra("bio", response.bio)
                }
                startActivity(intent)

            } catch (e: Exception) {

                Toast.makeText(this@SignActivity, "Login failed: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }

    fun onRegestration(view: View){

    }


}

