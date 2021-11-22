package com.jordiortuno.cleanmvvm.framework.ui.auth.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.jordiortuno.cleanmvvm.databinding.ResetPasswordFragmentBinding

class ResetPasswordFragment : Fragment() {

    private var _binding: ResetPasswordFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ResetPasswordFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.goLogin.setOnClickListener {
            navigateToLogin()
        }
        binding.submit.setOnClickListener {
            val email = binding.email.editText!!.text.toString().trim()
            navigateVerifyCode(email)
        }
    }
    private fun navigateVerifyCode(email:String){
        val action = ResetPasswordFragmentDirections.toVerifyCodeFragment(email)
        findNavController().navigate(action)
    }
    private fun navigateToLogin(){
        val action = ResetPasswordFragmentDirections.globalLoginFragment()
        findNavController().navigate(action)
    }


}