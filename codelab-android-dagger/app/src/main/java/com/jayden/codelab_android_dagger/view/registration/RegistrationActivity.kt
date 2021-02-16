package com.jayden.codelab_android_dagger.view.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.R
import com.jayden.codelab_android_dagger.databinding.ActivityRegistrationBinding
import com.jayden.codelab_android_dagger.view.main.MainActivity
import com.jayden.codelab_android_dagger.view.registration.fragment.termsandconditions.TermsAndConditionsFragment
import javax.inject.Inject

class RegistrationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegistrationBinding

    lateinit var registrationComponent: RegistrationComponent

    /**
     * 안드로이드 프레임워크 클래스 (Activity, Fragment, etc) 들은 시스템에 의해서 생성되기 때문에
     * Dagger 가 대신 생성해주지 못한다.
     * 그래서 필요한 인스턴스에 대해서 Field Injection 을 통해 생성해준다.
     *
     * @Inject 가 클래스 구성자에 달리면, Dagger 가 해당 클래스의 인스턴스를 제공하는 방법을 의미한다.
     * @Inject 가 클래스 변수에 달리면, Dagger 가 해당 유형의 인스턴스로 생성해줘야 한다는 것을 의미한다.
     */
    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        // Ask Dagger to inject our dependencies.
        // Fragment Restoration 이슈를 방지하기 위해 super.onCreate() 이전에 inject
        // Activity 의 Restore Phase 중 Fragment 가 Attach 될 수 있고 ActivityBinding 에 접근하길 원할 수 있기 때문에
        registrationComponent = (application as App).appComponent.registrationComponent().create()
        registrationComponent.inject(this)

        super.onCreate(savedInstanceState)

        binding = ActivityRegistrationBinding.inflate(layoutInflater)
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
        registrationViewModel.registerUser()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}