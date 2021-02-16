package com.jayden.codelab_android_dagger.di

import com.jayden.codelab_android_dagger.view.login.LoginComponent
import com.jayden.codelab_android_dagger.view.registration.RegistrationComponent
import dagger.Module
import javax.inject.Scope

@Module(subcomponents = [RegistrationComponent::class, LoginComponent::class])
class AppSubcomponents

@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope