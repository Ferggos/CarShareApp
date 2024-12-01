package com.example.testapp.screens.Main

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.testapp.databinding.ActivityMainBinding
import com.example.testapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val fragList = listOf(
        HomePage.newInstance(),
        Favorites.newInstance(),
        Settings.newInstance()
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

        binding.ibHome.setImageResource(R.drawable.ic_home_pushed)
        navigateToFragment(HomePage())

        binding.ibHome.setOnClickListener {
            changeButtonState(binding.ibHome, R.drawable.ic_home_pushed)
            navigateToFragment(HomePage())
        }

        binding.ibMarks.setOnClickListener {
            changeButtonState(binding.ibMarks, R.drawable.ic_bookmark_pushed)
            navigateToFragment(Favorites())
        }

        binding.ibSettings.setOnClickListener {
            changeButtonState(binding.ibSettings, R.drawable.ic_settings_pushed)
            navigateToFragment(Settings())
        }
    }

    private fun changeButtonState(activeButton: ImageButton, pushedIconRes: Int) {
        // Сбрасываем состояние всех кнопок
        binding.ibHome.setImageResource(R.drawable.ic_home)
        binding.ibMarks.setImageResource(R.drawable.ic_bookmark)
        binding.ibSettings.setImageResource(R.drawable.ic_settings)

        // Устанавливаем "нажатое" состояние для активной кнопки
        activeButton.setImageResource(pushedIconRes)
    }

    fun navigateToFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fcv, fragment) // ID вашего контейнера
            .addToBackStack(null) // Добавляем в BackStack для возврата
            .commit()
    }
}