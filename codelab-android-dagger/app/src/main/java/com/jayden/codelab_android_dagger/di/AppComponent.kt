package com.jayden.codelab_android_dagger.di

import android.content.Context
import com.jayden.codelab_android_dagger.view.main.MainActivity
import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import com.jayden.codelab_android_dagger.view.registration.fragment.EnterDetailsFragment
import com.jayden.codelab_android_dagger.view.registration.fragment.termsandconditions.TermsAndConditionsFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, AppSubcomponents::class])
interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph.
        fun create(@BindsInstance context: Context): AppComponent
    }

    // Classes that can be injected by this Component
    fun registrationComponent(): RegistrationComponent.Factory

    fun inject(activity: MainActivity)
}