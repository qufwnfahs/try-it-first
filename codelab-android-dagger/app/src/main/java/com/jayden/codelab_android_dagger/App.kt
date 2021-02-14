package com.jayden.codelab_android_dagger

import android.app.Application
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.data.user.local.SharedPreferenceStorage

class App : Application() {

    val userManager by lazy {
        UserManager(SharedPreferenceStorage(this))
    }
}