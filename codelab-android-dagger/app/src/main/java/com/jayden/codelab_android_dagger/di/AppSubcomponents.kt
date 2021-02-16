package com.jayden.codelab_android_dagger.di

import dagger.Module
import javax.inject.Scope

@Module(subcomponents = [RegistrationComponent::class])
class AppSubcomponents

@Scope
@MustBeDocumented
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope