package com.example.news.ui.home

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.domin.model.News
import kotlinx.coroutines.launch


class HomeFragment : Fragment(), OnItemNewsClicked {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        (context as AppCompatActivity).supportActionBar?.title = getString(R.string.home)
        homeAdapter =
            HomeAdapter( (context as AppCompatActivity).applicationContext, this)

        lifecycleScope.launch {
            homeViewModel.news.collect {
                homeAdapter.setList(it)
            }
        }
        binding.recycleNewsHome.adapter=homeAdapter

    }

    override fun newsClicked(News: News) {

    }
}