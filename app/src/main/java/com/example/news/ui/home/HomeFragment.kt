package com.example.news.ui.home

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.databinding.FragmentLoginBinding
import com.example.news.domin.model.News
import com.example.news.ui.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

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
        binding.shimmerFrameLayout.startShimmerAnimation()
        homeAdapter =
            HomeAdapter((context as AppCompatActivity).applicationContext, this)

        lifecycleScope.launch {
            homeViewModel.news.collect {
                hideRecycler()
                delay(1000)
               if(it.isNotEmpty()) {
                   showRecycler()
                   homeAdapter.setList(it)
               }
            }
        }
        binding.recycleNewsHome.adapter = homeAdapter

    }

    fun showRecycler() {
        binding.recycleNewsHome.visibility = View.VISIBLE
        binding.shimmerFrameLayout.stopShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.GONE
    }
    fun hideRecycler()
    {
        binding.recycleNewsHome.visibility = View.GONE
        binding.shimmerFrameLayout.startShimmerAnimation()
        binding.shimmerFrameLayout.visibility = View.VISIBLE
    }

    override fun newsClicked(News: News) {

    }
}