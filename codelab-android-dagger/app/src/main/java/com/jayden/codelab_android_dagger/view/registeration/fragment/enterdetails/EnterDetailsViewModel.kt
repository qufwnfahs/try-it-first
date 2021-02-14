package com.jayden.codelab_android_dagger.view.registeration.fragment.enterdetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jayden.codelab_android_dagger.view.registeration.fragment.EnterDetailsError
import com.jayden.codelab_android_dagger.view.registeration.fragment.EnterDetailsSuccess
import com.jayden.codelab_android_dagger.view.registeration.fragment.EnterDetailsViewState

private const val MAX_LENGTH = 5

class EnterDetailsViewModel : ViewModel() {

    private val _enterDetailsState = MutableLiveData<EnterDetailsViewState>()
    val enterDetailsState: LiveData<EnterDetailsViewState>
        get() = _enterDetailsState

    fun validateInput(username: String, password: String) {
        when {
            username.length < MAX_LENGTH -> _enterDetailsState.value =
                EnterDetailsError("Username has to be longer than 4 characters")
            password.length < MAX_LENGTH -> _enterDetailsState.value =
                EnterDetailsError("Passwrod has to be longer than 4 characters")
            else -> _enterDetailsState.value = EnterDetailsSuccess
        }
    }
}