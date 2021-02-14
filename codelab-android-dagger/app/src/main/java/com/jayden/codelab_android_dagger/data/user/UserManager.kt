/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jayden.codelab_android_dagger.data.user

import com.jayden.codelab_android_dagger.data.user.local.Storage

private const val REGISTERED_USER_NAME = "registered_username"
private const val PASSWORD_SUFFIX = "password"

class UserManager(private val storage: Storage) {

    var userRepository: UserRepository? = null

    val username: String
        get() = storage.getString(REGISTERED_USER_NAME)

    fun isUserLoggedIn() = userRepository != null

    fun isUserRegistered() = storage.getString(REGISTERED_USER_NAME).isNotEmpty()

    fun registerUser(username: String, password: String) {
        val REGISTERED_USER_PASSWORD = "$username$PASSWORD_SUFFIX"
        storage.setString(REGISTERED_USER_NAME, username)
        storage.setString(REGISTERED_USER_PASSWORD, password)

        userJustLoggedIn()
    }

    fun loginUser(username: String, password: String): Boolean {
        val registeredUser = this.username
        if (registeredUser != username) return false

        val REGISTERED_USER_PASSWORD = "$username$PASSWORD_SUFFIX"
        val registeredPassword = storage.getString(REGISTERED_USER_PASSWORD)
        if (registeredPassword != password) return false

        userJustLoggedIn()
        return true
    }

    fun logout() {
        userRepository = null
    }

    fun unregister() {
        val username = storage.getString(REGISTERED_USER_NAME)
        val REGISTERED_USER_PASSWORD = "$username$PASSWORD_SUFFIX"
        storage.setString(REGISTERED_USER_NAME, "")
        storage.setString(REGISTERED_USER_PASSWORD, "")
        logout()
    }

    private fun userJustLoggedIn() {
        userRepository = UserRepository(this)
    }
}