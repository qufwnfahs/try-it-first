package com.jayden.codelab_android_dagger.view.login

import dagger.Subcomponent

@Subcomponent
interface LoginComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): LoginComponent
    }

    fun inject(activity: LoginActivity)
}