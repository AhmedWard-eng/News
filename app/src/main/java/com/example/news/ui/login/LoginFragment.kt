package com.example.news.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentLoginBinding
import com.example.news.ui.splash.Destination
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.progressBar.visibility = View.GONE

        lifecycleScope.launch {
            loginViewModel.loading.collect {
                when (it) {
                    true -> {
                        binding.progressBar.visibility = View.VISIBLE                    }
                    else -> {
                        binding.progressBar.visibility = View.GONE
                    }
                }
            }
        }


        lifecycleScope.launch {
            loginViewModel.loginResponse.collect{
                when (it) {
                    false -> {
                        Toast.makeText(
                            context,
                            loginViewModel.error,
                            Toast.LENGTH_LONG
                        )
                            .show()

                    }
                    true -> {
                        Toast.makeText(
                            context,
                            "Login Successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                        Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)

                    }
                    else -> {

                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.emailInputLogin.text.toString()
            val password = binding.passwordInputLogin.text.toString()
            loginViewModel.login(email, password)
        }

        binding.txtSignUp.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registrationFragment2)
        }
    }
}