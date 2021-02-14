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

package com.jayden.codelab_android_dagger.view.registration.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.jayden.codelab_android_dagger.databinding.FragmentEnterDetailsBinding
import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import com.jayden.codelab_android_dagger.view.registration.fragment.enterdetails.EnterDetailsViewModel

class EnterDetailsFragment : Fragment() {

    private var _binding: FragmentEnterDetailsBinding? = null
    private val binding: FragmentEnterDetailsBinding
        get() = _binding!!

    private val registrationViewModel by lazy {
        (activity as RegistrationActivity).registrationViewModel
    }
    private val enterDetailsViewModel: EnterDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEnterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
        setUpObservers()
    }

    private fun setUpViews() {
        with(binding) {
            username.doOnTextChanged { _, _, _, _ -> error.visibility = View.INVISIBLE }
            password.doOnTextChanged { _, _, _, _ -> error.visibility = View.INVISIBLE }

            next.setOnClickListener {
                enterDetailsViewModel.validateInput(username.text.toString(), password.text.toString())
            }
        }
    }

    private fun setUpObservers() {
        enterDetailsViewModel.enterDetailsState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is EnterDetailsSuccess -> {
                    val username = binding.username.text.toString()
                    val password = binding.password.text.toString()
                    registrationViewModel.updateUserData(username, password)

                    (activity as RegistrationActivity).onDetailsEntered()
                }
                is EnterDetailsError -> {
                    binding.error.text = state.error
                    binding.error.visibility = View.VISIBLE
                }
            }
        }
    }
}

sealed class EnterDetailsViewState
object EnterDetailsSuccess : EnterDetailsViewState()
data class EnterDetailsError(val error: String) : EnterDetailsViewState()