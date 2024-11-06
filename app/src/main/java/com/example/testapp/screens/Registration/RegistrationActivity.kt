package com.example.testapp.screens.Registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.screens.adapters.VPAdapter
import com.example.testapp.databinding.ActivityRegistrationBinding
import com.example.testapp.screens.LoginActivity
import com.example.testapp.R

class RegistrationActivity : AppCompatActivity() {

    private val fragList = listOf(
        RegistrationFragment1.newInstance(),
        RegistrationFragment2.newInstance(),
        RegistrationFragment3.newInstance(),
        CompleteRegistrationFragment.newInstance()
    )

    private var _binding:   ActivityRegistrationBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityRegistrationBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registration)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        _binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Disable swiping from User
        binding.vp2.setUserInputEnabled(false);

        val adapter = VPAdapter(this, fragList)
        binding.vp2.adapter = adapter

        binding.ibBack.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun setLinearLayoutVisibility(visible: Boolean) {
        binding.llCreateAccount.visibility = if (visible) View.VISIBLE else View.GONE
    }
}