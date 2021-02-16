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
import javax.inject.Inject

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    @Inject
    lateinit var settingsViewModel: SettingsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        val userManager = (application as App).appComponent.userManager()
        userManager.userComponent?.inject(this)

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