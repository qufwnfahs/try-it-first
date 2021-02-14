package com.jayden.codelab_android_dagger.view.main

import com.jayden.codelab_android_dagger.data.user.UserRepository

class MainViewModel(private val userRepository: UserRepository?) {
    val welcomeText: String
        get() = "Hello ${userRepository?.username}!"

    val notificationText: String
        get() = "You have ${userRepository?.unreadNotifications} unread notifications"
}