package com.example.testapp.screens.Welcome

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.screens.adapters.VPAdapter
import com.example.testapp.databinding.ActivityWelcomeBinding
import com.example.testapp.screens.AuthorizationActivity
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator
import com.example.testapp.R


class WelcomeActivity : AppCompatActivity() {
    private val fragList = listOf(
        WelcomeFragment1.newInstance(),
        WelcomeFragment2.newInstance(),
        WelcomeFragment3.newInstance()
    )


    private var _binding: ActivityWelcomeBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityWelcomeBinding must not be null")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_welcome)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        _binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Disable swiping from User
        binding.vp2.setUserInputEnabled(false);

        val adapter = VPAdapter(this, fragList)
        binding.vp2.adapter = adapter

        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
        dotsIndicator.attachTo(binding.vp2)

        binding.btnSkip.setOnClickListener{
            saveToken()
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
        }
    }

    fun saveToken()
    {
        val sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean("processCompleted", true)
        editor.apply()
    }
}