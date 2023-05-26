package com.example.news.ui.registration

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.news.R
import com.example.news.databinding.RegistrationUiBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegistrationFragment : Fragment() {

    lateinit var binding: RegistrationUiBinding
    lateinit var myFactory: RegistViewModelFactory
    lateinit var viewModel: RegistrationViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.registration_ui, container, false)
        binding = RegistrationUiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.registButton.setOnClickListener {
            val email = binding.emailTextField.text
            val userName = binding.userNameTextField.text
            val password = binding.passwordTextField.text

            if (viewModel.validateEmail(email.toString()) && viewModel.validatePassword(password.toString()) && viewModel.validateUserName(userName.toString())) {
                viewModel.postSignUp(email.toString(), userName.toString(), password.toString())
            }
        }


        lifecycleScope.launch {
            viewModel.signUpLoading.collectLatest {
                Log.i("TAG", "Loading")
            }
            viewModel.signUpSuccess.collectLatest {
                Log.i("TAG", "Success")
            }
            viewModel.signUpError.collectLatest {
                Log.i("TAG", "Error")
            }
        }


    }

}