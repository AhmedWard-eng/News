package com.example.news.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var loginViewModel: LoginViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val email = binding.emailInputLogin.text.toString()
        val password = binding.passwordInputLogin.text.toString()
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        lifecycleScope.launch {
            loginViewModel.loading.collect {
                when (it) {
                    true -> {
                        Toast.makeText(
                            context,
                            "Loading",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    else -> {
                        Toast.makeText(
                            context,
                            "Loading is Stopped",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }


        }
        lifecycleScope.launch {
            loginViewModel.loginResponse.collect {
                when (it) {
                    false -> {
                        Toast.makeText(
                            context,
                            loginViewModel.error,
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                    else -> {

                        Toast.makeText(
                            context,
                            "Login Successfully",
                            Toast.LENGTH_LONG
                        )
                            .show()
                    }
                }
            }
        }
        binding.btnLogin.setOnClickListener {
            loginViewModel.login(email, password)


        }
    }
}