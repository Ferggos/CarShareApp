package com.example.testapp.screens.Main

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.testapp.screens.adapters.VPAdapter
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.R


class MainActivity : AppCompatActivity() {

    private val fragList = listOf(
        HomePage.newInstance(),
        Favourites.newInstance(),
        Settings.newInstance(),
        Profile.newInstance()
    )

    private var _binding:   ActivityMainBinding? = null

    private val binding
        get() = _binding ?: throw  IllegalStateException("Binding for ActivityRegistrationBinding must not be null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Disable swiping from User
        binding.vp2.setUserInputEnabled(false);

        val adapter = VPAdapter(this, fragList)
        binding.vp2.adapter = adapter
        binding.ibHome.setImageResource(R.drawable.ic_home_pushed)

        binding.ibHome.setOnClickListener {
            changePage(binding, 0)
        }

        binding.ibMarks.setOnClickListener {
            changePage(binding, 1)
        }

        binding.ibSettings.setOnClickListener {
            changePage(binding, 2)
        }
    }


    private fun changePage(binding: ActivityMainBinding, selectedItem: Int) {

        binding.ibHome.setImageResource(if (selectedItem == 0) R.drawable.ic_home_pushed else R.drawable.ic_home)
        binding.ibMarks.setImageResource(if (selectedItem == 1) R.drawable.ic_bookmark_pushed else R.drawable.ic_bookmark)
        binding.ibSettings.setImageResource(if (selectedItem == 2) R.drawable.ic_settings_pushed else R.drawable.ic_settings)

        binding.vp2.currentItem = selectedItem
    }
}