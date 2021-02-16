package com.jayden.codelab_android_dagger.view.registration.fragment.termsandconditions

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jayden.codelab_android_dagger.App
import com.jayden.codelab_android_dagger.databinding.FragmentTermsAndConditionsBinding
import com.jayden.codelab_android_dagger.view.registration.RegistrationActivity
import com.jayden.codelab_android_dagger.view.registration.RegistrationViewModel
import javax.inject.Inject

class TermsAndConditionsFragment : Fragment() {

    private var _binding: FragmentTermsAndConditionsBinding? = null
    val binding: FragmentTermsAndConditionsBinding
        get() = _binding!!

    @Inject
    lateinit var registrationViewModel: RegistrationViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)

        (requireActivity() as RegistrationActivity).registrationComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTermsAndConditionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpViews()
    }

    private fun setUpViews() {
        with(binding) {
            next.setOnClickListener {
                registrationViewModel.acceptTCs()
                (activity as RegistrationActivity).onTermsAndConditionsAccepted()
            }
        }
    }
}