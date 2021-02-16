package com.jayden.codelab_android_dagger.view.registration

import com.jayden.codelab_android_dagger.data.user.UserManager
import com.jayden.codelab_android_dagger.di.ActivityScope
import javax.inject.Inject

/**
 * Kotlin 에서 구성자에 Annotation 을 적용하려면 constructor 키워드를 명시해야 한다.
 *
 * @Inject Annotation 을 통해 Dagger 는
 *  - RegisterationViewModel 타입 인스턴스를 만드는 방법
 *  - RegisterationViewModel 은 UserManager 를 파라미터로 사용하고 있으므로 종속성이 존재
 * 를 알 수 있다.
 */
@ActivityScope
class RegistrationViewModel @Inject constructor(private val userManager: UserManager) {

    private var username: String? = null
    private var password: String? = null
    private var acceptedTCs: Boolean? = null

    fun updateUserData(username: String, password: String) {
        this.username = username
        this.password = password
    }

    fun acceptTCs() {
        acceptedTCs = true
    }

    fun registerUser() {
        assert(username != null)
        assert(password != null)
        assert(acceptedTCs == true)

        userManager.registerUser(username!!, password!!)
    }
}