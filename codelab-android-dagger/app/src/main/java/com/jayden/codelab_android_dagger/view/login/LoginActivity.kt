package com.jayden.codelab_android_dagger.view.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.databinding.ActivityLoginBinding
import com.jayden.codelab_android_dagger.view.main.MainActivity
import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    lateinit var loginComponent: LoginComponent

    @Inject
    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        loginComponent = (application as App).appComponent.loginComponent().create()
        loginComponent.inject(this)

        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        with(binding) {
            username.isEnabled = false
            username.setText(loginViewModel.getUsername())

            password.doOnTextChanged { _, _, _, _ -> error.visibility = View.VISIBLE }

            login.setOnClickListener {
                loginViewModel.login(username.text.toString(), password.text.toString())
            }

            unregister.setOnClickListener {
                loginViewModel.unregister()
                val intent = Intent(this@LoginActivity, RegistrationActivity::class.java).apply {
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
            }
        }
    }

    private fun setUpObservers() {
        loginViewModel.loginState.observe(this) { state ->
            when (state) {
                is LoginSuccess -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                is LoginError -> binding.error.visibility = View.VISIBLE
            }
        }
    }
}

sealed class LoginViewState
object LoginSuccess : LoginViewState()
object LoginError : LoginViewState()