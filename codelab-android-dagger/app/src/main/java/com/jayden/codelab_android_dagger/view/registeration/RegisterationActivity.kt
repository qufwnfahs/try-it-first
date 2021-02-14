package com.jayden.codelab_android_dagger.view.registeration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.R
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.databinding.ActivityRegisterationBinding
import com.jayden.codelab_android_dagger.view.main.MainActivity
import com.jayden.codelab_android_dagger.view.registeration.fragment.termsandconditions.TermsAndConditionsFragment

class RegisterationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterationBinding

    val registerationViewModel by lazy {
        RegisterationViewModel((application as App).userManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDetailsEntered() {
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            replace(R.id.fragment_container_view, TermsAndConditionsFragment())
            addToBackStack(null)
        }
    }

    fun onTermsAndConditionsAccepted() {
        registerationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}