package com.example.news.ui.logout

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentLogoutBinding
import com.example.news.ui.login.LoginViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LogoutFragment : Fragment() {

    private lateinit var binding: FragmentLogoutBinding
    private lateinit var viewModel: LogoutViewModel

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

        binding.buttonLogout.setOnClickListener {

            AlertDialog.Builder(requireContext()).setTitle(getString(R.string.attention))
                .setCancelable(false).setMessage(getString(R.string.sure_logout))
                .setPositiveButton(
                    getString(android.R.string.yes)
                ) { _, _ ->

                    viewModel.logout()
                    Navigation.findNavController(requireView())
                        .navigate(R.id.action_logoutFragment_to_loginFragment)

                }.setNegativeButton(
                    getString(android.R.string.no)
                ) { _, _ ->
                }.show()

        }

        lifecycleScope.launch {
            viewModel.email.collect {
                it?.let {
                    binding.userNameText.text = it
                }
            }
        }

        lifecycleScope.launch {
            viewModel.userName.collect {
                it?.let {
                    binding.userNameText.text = it
                }
            }
        }

    }
}