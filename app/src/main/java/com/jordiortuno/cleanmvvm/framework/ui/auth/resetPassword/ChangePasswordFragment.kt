package com.jordiortuno.cleanmvvm.framework.ui.auth.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.jordiortuno.cleanmvvm.databinding.ChangePasswordFragmentBinding
import com.jordiortuno.cleanmvvm.framework.ui.common.showToast


class ChangePasswordFragment : Fragment() {
    private var _binding: ChangePasswordFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ChangePasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            submitPassword()
        }
        binding.goLogin.setOnClickListener {
            goLogin()
        }
    }

    private fun goLogin() {
        val action = ChangePasswordFragmentDirections.globalLoginFragment()
        findNavController().navigate(action)
    }

    private fun submitPassword() {
        context?.showToast("Submit password",Toast.LENGTH_SHORT)
        val action = ChangePasswordFragmentDirections.toPasswordChangedFragment()
        findNavController().navigate(action)
    }


}