package com.jayden.codelab_android_dagger

import android.app.Application
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.data.user.local.SharedPreferenceStorage
import com.jayden.codelab_android_dagger.di.AppComponent
import com.jayden.codelab_android_dagger.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}