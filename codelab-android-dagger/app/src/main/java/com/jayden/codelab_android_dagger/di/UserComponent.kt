package com.jayden.codelab_android_dagger.di

import com.jayden.codelab_android_dagger.view.main.MainActivity
import com.jayden.codelab_android_dagger.view.settings.SettingsActivity
import dagger.Subcomponent

@LoggedUserScope
@Subcomponent
interface UserComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: SettingsActivity)
}