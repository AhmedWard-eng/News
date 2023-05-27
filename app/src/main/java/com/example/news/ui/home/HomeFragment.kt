package com.example.news.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.domin.model.News
import com.example.news.ui.login.LoginViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
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
        binding.shimmerFrameLayout.startShimmerAnimation()
        homeAdapter =
            HomeAdapter((context as AppCompatActivity).applicationContext, this)
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val filteredList = filteredMyListWithSequence(s.toString())
                showNoMatchingResultIfFilteredListIsEmpty(filteredList)
                if (filteredList != null) {
                    homeAdapter.setList(filteredList)
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed.
            }
        }
        binding.searchEdit.addTextChangedListener(textWatcher)
        lifecycleScope.launch {
            homeViewModel.news.collect {

                homeViewModel.list=it
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
    private fun filteredMyListWithSequence(s: String): List<News>? {

        return homeViewModel.list?.filter { it.title.lowercase().contains(s.lowercase()) }
    }
    private fun showNoMatchingResultIfFilteredListIsEmpty(filteredList: List<News>?) {
        if (filteredList.isNullOrEmpty()) {
            binding.txtNoMatching.visibility = View.VISIBLE
            binding.recycleNewsHome.visibility = View.GONE
        } else {

            binding.txtNoMatching.visibility = View.GONE
            binding.recycleNewsHome.visibility = View.VISIBLE
        }
    }
    override fun newsClicked(News: News) {

    }
}