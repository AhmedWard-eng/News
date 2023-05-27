package com.example.news.ui.newsdetails

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.news.R
import com.example.news.data.repo.auth.AuthRepo
import com.example.news.data.repo.auth.AuthRepoImp
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import com.example.news.databinding.FragmentNewsDetailsBinding
import com.example.news.domin.model.News
import com.example.news.ui.login.LoginViewModel
import com.example.news.ui.registration.RegistViewModelFactory
import com.example.news.ui.registration.RegistrationViewModel
import kotlinx.coroutines.launch


class NewsDetailsFragment : Fragment() {

    lateinit var binding: FragmentNewsDetailsBinding
    val args: NewsDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var news = args.currentNew


        binding.titleTextView.text = news.title
        binding.authorTextView.text = news.author
        binding.dateTextView.text = news.publishedAt
        binding.contentTextView.text = news.content
//        Glide.with(this)
//            .load(news.urlToImage)
//            .into(binding.newsImageView)

        val viewModel = ViewModelProvider(this)[NewsDetailsViewModel::class.java]
        var isFav = viewModel.isFavData.value

        lifecycleScope.launch {
            viewModel.getNewByTitle(news)
        }
        lifecycleScope.launch {
            viewModel.isFavData.collect { isFavorite ->
                binding.newsFabButton.setImageResource(if (isFav) R.drawable.baseline_fill_favorite_24 else R.drawable.baseline_favorite_border_24)
                if (isFav) {
                    viewModel.addToFav(news)
                } else {
                    AlertDialog.Builder(requireContext())
                        .setTitle("Delete From Favorite")
                        .setCancelable(false)
                        .setMessage("Are you sure you want to delete new from favorite")
                        .setPositiveButton(
                            getString(android.R.string.ok)
                        ) { _, _ ->
                            viewModel.deleteFromFav(news)
                        }.show()
                }
            }
        }

        binding.newsFabButton.setOnClickListener {
            viewModel.changeFavIcon()
        }

    }

}