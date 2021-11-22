package com.jordiortuno.cleanmvvm.framework.ui.auth.resetPassword

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.jordiortuno.cleanmvvm.databinding.VerifyCodeFragmentBinding
import com.jordiortuno.cleanmvvm.framework.ui.common.showToast

class VerifyCodeFragment : Fragment() {
    val args:VerifyCodeFragmentArgs by navArgs()
    private lateinit var email:String
    private var _binding: VerifyCodeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = VerifyCodeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        email = args.email
        binding.goLogin.setOnClickListener {
            navigateToLogin()
        }
        binding.resendCode.setOnClickListener {
            resendCode()
        }
        binding.submitCode.setOnClickListener {
            val recoveryCode = binding.recoveryCode.editText!!.text.toString()
            submitCode(recoveryCode)
        }
    }

    private fun resendCode(){
        context?.showToast("Reenviar codigo",Toast.LENGTH_SHORT)
    }

    private fun navigateToLogin() {
        val action = VerifyCodeFragmentDirections.globalLoginFragment()
        findNavController().navigate(action)
    }

    private fun submitCode(recoveryCode:String){
        context?.showToast("Codigo enviado: $recoveryCode",Toast.LENGTH_SHORT)
        val action = VerifyCodeFragmentDirections.toChangePasswordFragment(email,"token")
        findNavController().navigate(action)
    }

}