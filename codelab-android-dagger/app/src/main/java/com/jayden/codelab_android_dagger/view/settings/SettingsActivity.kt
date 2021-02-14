package com.jayden.codelab_android_dagger.view.settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.R
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.data.user.UserRepository
import com.jayden.codelab_android_dagger.databinding.ActivitySettingsBinding
import com.jayden.codelab_android_dagger.view.login.LoginActivity

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private val settingsViewModel: SettingsViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return modelClass.getConstructor(UserRepository::class.java, UserManager::class.java).newInstance(
                    (application as App).userManager.userRepository,
                    (application as App).userManager
                )
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
    }

    private fun setUpViews() {
        with(binding) {
            refresh.setOnClickListener {
                settingsViewModel.refreshNotifications()
            }

            logout.setOnClickListener {
                settingsViewModel.logout()
                val intent = Intent(this@SettingsActivity, LoginActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
            }
        }
    }
}