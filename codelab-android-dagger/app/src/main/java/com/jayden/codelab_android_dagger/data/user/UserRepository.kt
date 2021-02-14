package com.jayden.codelab_android_dagger.data.user

import javax.inject.Inject
import kotlin.random.Random

class UserRepository @Inject constructor(private val userManager: UserManager) {

    val username: String
        get() = userManager.username

    var unreadNotifications: Int = randomInt()

    fun refreshUnreadNotifications() {
        unreadNotifications = randomInt()
    }
}

fun randomInt(): Int = Random.nextInt(until = 100)