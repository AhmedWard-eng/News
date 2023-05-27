package com.example.news.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.data.local.preferences.PreferencesData
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SplashFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SplashFragment : Fragment() {


        val viewModel : SplashViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.CREATED) {

                viewModel.navigate.collect{
                    when(it){
                        Destination.LOGIN -> Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_loginFragment)
                        Destination.HOME -> Navigation.findNavController(requireView()).navigate(R.id.action_splashFragment_to_homeFragment)
                        else -> {

                        }
                    }
                }
            }
        }

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

}