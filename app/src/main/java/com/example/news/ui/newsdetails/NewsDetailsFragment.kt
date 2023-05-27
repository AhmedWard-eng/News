package com.example.news.ui.newsdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.news.data.repo.auth.AuthRepo
import com.example.news.data.repo.auth.AuthRepoImp
import com.example.news.data.repo.news.NewsRepo
import com.example.news.data.repo.news.NewsRepoImp
import com.example.news.databinding.FragmentNewsDetailsBinding
import com.example.news.domin.model.News
import com.example.news.ui.registration.RegistViewModelFactory
import com.example.news.ui.registration.RegistrationViewModel


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

        val news: News = args.currentNews

        binding.titleTextView.text = news.title
        binding.authorTextView.text = news.author
        binding.dateTextView.text = news.publishedAt
        binding.contentTextView.text = news.content
//        Glide.with(this)
//            .load(news.urlToImage)
//            .into(binding.newsImageView)


    }

}