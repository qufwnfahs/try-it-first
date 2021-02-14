package com.jayden.codelab_android_dagger.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.R
import com.jayden.codelab_android_dagger.data.user.UserRepository
import com.jayden.codelab_android_dagger.data.user.local.Storage
import com.jayden.codelab_android_dagger.databinding.ActivityMainBinding
import com.jayden.codelab_android_dagger.view.login.LoginActivity
import com.jayden.codelab_android_dagger.view.registeration.RegisterationActivity
import com.jayden.codelab_android_dagger.view.settings.SettingsActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by lazy {
        MainViewModel((application as App).userManager.userRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userManager = (application as App).userManager
        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegisterationActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        } else {
            binding = ActivityMainBinding.inflate(layoutInflater)
            setContentView(binding.root)

            setUpViews()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.notifications.text = mainViewModel.notificationText
    }

    private fun setUpViews() {
        with(binding) {
            hello.text = mainViewModel.welcomeText

            settings.setOnClickListener {
                startActivity(Intent(this@MainActivity, SettingsActivity::class.java))
            }
        }
    }
}