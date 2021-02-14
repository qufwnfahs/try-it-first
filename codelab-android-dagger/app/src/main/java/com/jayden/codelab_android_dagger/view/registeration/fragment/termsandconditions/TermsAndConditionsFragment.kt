package com.jayden.codelab_android_dagger.view.registeration.fragment.termsandconditions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.jayden.codelab_android_dagger.databinding.FragmentTermsAndConditionsBinding
import com.jayden.codelab_android_dagger.view.registeration.RegisterationActivity
import com.jayden.codelab_android_dagger.view.registeration.RegisterationViewModel

class TermsAndConditionsFragment : Fragment() {

    private var _binding: FragmentTermsAndConditionsBinding? = null
    val binding: FragmentTermsAndConditionsBinding
        get() = _binding!!

    private val registerationViewModel by lazy {
        (activity as RegisterationActivity).registerationViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
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
                registerationViewModel.acceptTCs()
                (activity as RegisterationActivity).onTermsAndConditionsAccepted()
            }
        }
    }
}