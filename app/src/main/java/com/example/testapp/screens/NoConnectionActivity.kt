package com.example.testapp.screens

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.databinding.ActivityNoconnectionBinding
import com.example.testapp.screens.Welcome.WelcomeActivity
import com.example.testapp.R

class NoConnectionActivity : AppCompatActivity() {

    private var _binding: ActivityNoconnectionBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityNoconnectionBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_noconnection)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.noconnection)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _binding = ActivityNoconnectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRepeatConnect.setOnClickListener {
            if (isInternetAvailable()) {
                if (isUserLoggedIn()) {
                    val intent = Intent(this, AuthorizationActivity::class.java)
                    startActivity(intent)
                } else {
                    val intent = Intent(this, WelcomeActivity::class.java)
                    startActivity(intent)
                }
            } else {
                val intent = Intent(this, NoConnectionActivity::class.java)
                startActivity(intent)
            }
            finish()
        }


    }


    private fun isInternetAvailable(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPref = getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        val accessToken = sharedPref.getString("access_token", null)
        return accessToken != null
    }
}