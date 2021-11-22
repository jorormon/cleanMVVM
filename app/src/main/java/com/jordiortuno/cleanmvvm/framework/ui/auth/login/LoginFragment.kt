package com.jordiortuno.cleanmvvm.framework.ui.auth.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.jordiortuno.cleanmvvm.databinding.LoginFragmentBinding
import com.jordiortuno.cleanmvvm.domain.ResultWrapper
import com.jordiortuno.cleanmvvm.framework.ui.auth.AuthActivity
import com.jordiortuno.cleanmvvm.framework.ui.common.setTextError


class LoginFragment : Fragment() {
    private val viewModel by viewModels<LoginViewModel>()
    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.resultLogin.observe(viewLifecycleOwner, Observer {
            when (it) {
                ResultWrapper.Loading -> {

                }
                is ResultWrapper.Success -> {
                    navigateToMainActivity()
                }
            }

        })
        setup()
    }

    private fun setup() {
        binding.submitLogin.setOnClickListener {
            viewModel.onSubmitLogin("","")
            //navigateToMainActivity()
            /*val email = binding.email.editText!!.text.toString().trim()
            val password = binding.password.editText!!.text.toString().trim()

            val emailValid = viewModel.checkEmailCorrect(email)
            val passwordValid = viewModel.checkPasswordValid(password)

            if(emailValid && passwordValid)
                viewModel.onSubmitLogin(email,password)
            else{
                if(!emailValid){
                    displayErrorEmail()
                }
                if(!passwordValid){
                    displayErrorPassword()
                }
            }*/
        }


        binding.register.setOnClickListener {
            navigateToRegister()
        }
        binding.resetPassword.setOnClickListener {
            navigateResetPassword()
        }
    }

    private fun displayErrorEmail(){
        binding.email.setTextError("Email Incorrecto")
    }
    private fun displayErrorPassword(){
        binding.password.setTextError("La contraseña no es correcta")
    }

    private fun navigateToRegister(){
        val action = LoginFragmentDirections.toRegisterFragment()
        findNavController().navigate(action)
    }

    private fun navigateResetPassword(){
       val action = LoginFragmentDirections.toResetPasswordFragment()
       findNavController().navigate(action)
    }

    private fun navigateToMainActivity(){
        activity?.let{
            (activity as AuthActivity).startSession()
        }
    }


}