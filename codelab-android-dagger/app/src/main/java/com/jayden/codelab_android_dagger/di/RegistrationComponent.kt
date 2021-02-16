package com.jayden.codelab_android_dagger.di

import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import com.jayden.codelab_android_dagger.view.registration.fragment.EnterDetailsFragment
import com.jayden.codelab_android_dagger.view.registration.fragment.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}