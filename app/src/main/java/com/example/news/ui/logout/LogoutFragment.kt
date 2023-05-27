package com.example.news.ui.logout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.news.R
import com.example.news.databinding.FragmentLogoutBinding
import com.example.news.ui.login.LoginViewModel

class LogoutFragment : Fragment() {

    private lateinit var binding : FragmentLogoutBinding
    private lateinit var viewModel : LogoutViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLogoutBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[LogoutViewModel::class.java]

        binding.userNameText.text = viewModel.userName
        binding.userEmailText.text = viewModel.email

        binding.buttonLogout.setOnClickListener {



        }

    }
}