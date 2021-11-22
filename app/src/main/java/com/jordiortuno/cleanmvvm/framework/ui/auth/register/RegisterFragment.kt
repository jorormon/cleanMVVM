package com.jordiortuno.cleanmvvm.framework.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jordiortuno.cleanmvvm.R
import com.jordiortuno.cleanmvvm.databinding.RegisterFragmentBinding
import com.jordiortuno.cleanmvvm.framework.ui.common.HtmlUtilities
import com.jordiortuno.cleanmvvm.framework.ui.common.makeLinks

class RegisterFragment : Fragment() {
    val viewModel by viewModels<RegisterViewModel>()

    private var _binding: RegisterFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = RegisterFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupTermsPrivacyPolicy()
        binding.goLogin.setOnClickListener {
            goLogin()
        }

    }

    private fun setupTermsPrivacyPolicy() {
        binding.textAcceptTerms.text = HtmlUtilities.fromHtml(
            getString(R.string.first_privacy_label) +
                    " " +
                    "<font color='#439EF6'>" +
                    getString(R.string.privacy_policy) +
                    "</font>" + " " +
                    getString(R.string.and) +
                    " " + "<font color='#439EF6'>" +
                    getString(R.string.terms_and_conditions) +
                    "</font>" +
                    " " +
                    getString(R.string.second_privacy_label)
        )

        binding.textAcceptTerms.makeLinks(
            Pair(getString(R.string.privacy_policy),View.OnClickListener {
                readPrivacyPolicy()
            }),
            Pair(getString(R.string.terms_and_conditions),View.OnClickListener {
                readTerms()
            }))
    }
    private fun readTerms(){

    }
    private fun readPrivacyPolicy(){

    }
    private fun goLogin(){
        val action = RegisterFragmentDirections.globalLoginFragment()
        findNavController().navigate(action)
    }
}