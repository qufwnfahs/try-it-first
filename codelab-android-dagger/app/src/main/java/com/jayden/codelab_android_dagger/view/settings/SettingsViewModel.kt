package com.jayden.codelab_android_dagger.view.settings

import androidx.lifecycle.ViewModel
import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.data.user.UserRepository
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val userManager: UserManager
) : ViewModel() {

    fun refreshNotifications() {
        userRepository.refreshUnreadNotifications()
    }

    fun logout() {
        userManager.logout()
    }
}