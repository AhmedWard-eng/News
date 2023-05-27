package com.example.news

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainActivityNewsModel

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[MainActivityNewsModel::class.java]
        viewModel.checkInternetConnection()


        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.networkStatFlow.collect {
                    when (it) {
                        NetworkStatus.CONNECTED -> {
                            showSnackBar(binding.root, it.stringId)
                        }

                        NetworkStatus.DISCONNECTED -> {
                            showSnackBar(binding.root, it.stringId)
                        }

                        else -> {

                        }
                    }
                }
            }
        }

    }

    private fun showSnackBar(view: View, stringId: Int) {
        val snackBar = Snackbar.make(
            view, getString(stringId), Snackbar.LENGTH_LONG
        )
        snackBar.show()
    }
}