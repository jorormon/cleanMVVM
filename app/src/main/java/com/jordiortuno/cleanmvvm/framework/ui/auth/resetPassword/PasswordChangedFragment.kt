package com.jordiortuno.cleanmvvm.framework.ui.auth.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jordiortuno.cleanmvvm.databinding.PasswordChangedFragmentBinding


class PasswordChangedFragment : Fragment() {
    private var _binding: PasswordChangedFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = PasswordChangedFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            toLogin()
        }
    }

    private fun toLogin(){
        val action = PasswordChangedFragmentDirections.globalLoginFragment()
        findNavController().navigate(action)
    }

}