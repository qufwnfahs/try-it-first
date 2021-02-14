package com.jayden.codelab_android_dagger.di

import com.jayden.codelab_android_dagger.data.user.local.SharedPreferenceStorage
import com.jayden.codelab_android_dagger.data.user.local.Storage
import dagger.Binds
import dagger.Module

/**
 * Storage 가 인터페이스이므로 Dagger 에게 생성하는 방법을 알려주어야 한다.
 */
@Module
abstract class StorageModule {
    // Makes Dagger provide SharedPreferencesStorage when a Storage type is requested.
    @Binds
    abstract fun provideStorage(storage: SharedPreferenceStorage): Storage
}