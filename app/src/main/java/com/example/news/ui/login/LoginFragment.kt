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
                Log.i("wwwwwwwwwwwwww",it.toString())
                when (it) {
                    false -> {
                        Toast.makeText(
                            context,
                            loginViewModel.error,
                            Toast.LENGTH_LONG
                        )
                            .show()

                        Log.i("ttttttttttttt",loginViewModel.error)

                    }
                    true -> {

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

        binding.txtSignUp.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_registrationFragment2)
        }
    }
}