package com.jayden.codelab_android_dagger.view.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.databinding.ActivityMainBinding
import com.jayden.codelab_android_dagger.view.login.LoginActivity
import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import com.jayden.codelab_android_dagger.view.settings.SettingsActivity
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var userManager: UserManager

    @Inject
    lateinit var mainViewModel: MainViewModel

    /**
     * Register 후 Main Page 로 이동하지 않음
     *  -> Dagger always provides a new instance of a type (in our case UserManager) when injecting dependencies by default.
     *
     * Scope 를 통해 해결 필요 !
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as App).appComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (!userManager.isUserLoggedIn()) {
            if (!userManager.isUserRegistered()) {
                startActivity(Intent(this, RegistrationActivity::class.java))
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