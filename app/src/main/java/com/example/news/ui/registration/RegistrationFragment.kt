package com.example.news.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.data.repo.auth.AuthRepo
import com.example.news.data.repo.auth.AuthRepoImp

import com.example.news.databinding.RegistrationUiBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    lateinit var binding: RegistrationUiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = RegistrationUiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val authRepo: AuthRepo = AuthRepoImp()

        val viewModel : RegistrationViewModel by viewModels {
            RegistViewModelFactory(authRepo)
        }

        binding.registButton.setOnClickListener {
            val email = binding.emailTextField.text
            val userName = binding.userNameTextField.text
            val password = binding.passwordTextField.text

            if(!viewModel.validateUserName(userName = userName.toString())){
                binding.userNameTextField.error = viewModel.signUpUserName.value
            }
            if(!viewModel.validateEmail(email = email.toString())){
                binding.emailTextField.error = viewModel.signUpEmail.value
            }
            if(!viewModel.validatePassword(password= password.toString())){
                binding.passwordTextField.error = viewModel.signUpPassword.value
            }

            if (viewModel.validateEmail(email.toString()) && viewModel.validatePassword(password.toString()) && viewModel.validateUserName(userName.toString())) {
                viewModel.postSignUp(email.toString(), userName.toString(), password.toString())
            }else{
                Toast.makeText(context, "Invalid Registration, try again", Toast.LENGTH_SHORT).show()
            }
        }

        lifecycleScope.launch {
            viewModel.signUpSuccess.collectLatest {
                when(it){
                    false->{

                    }else -> {
                        Toast.makeText(context, "Registration successfully", Toast.LENGTH_SHORT).show()
                        Navigation.findNavController(requireView())
                            .navigate(R.id.action_registrationFragment_to_loginFragment2)
                    }
                }

            }

        }

        lifecycleScope.launch {
            viewModel.signUpLoading.collectLatest {
            }
        }

        lifecycleScope.launch {
            viewModel.signUpError.collectLatest {
                Toast.makeText(context, it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}