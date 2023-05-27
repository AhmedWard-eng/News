package com.example.news.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.news.R
import com.example.news.databinding.FragmentFavoriteBinding
import com.example.news.domin.model.News
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteFragment : Fragment() {
    // TODO: Rename and change types of parameters
   lateinit var binding : FragmentFavoriteBinding
   private val viewModel : FavoritesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val favAdapter = FavAdapter(FavAdapter.OnClickListener(::deleteFromFav,::navigateToDetailsScreen))
        binding.recyclerView.adapter = favAdapter
        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.news.collectLatest {
                    favAdapter.submitList(it)
                }
            }
        }
    }

    private fun navigateToDetailsScreen(news: News) {
        TODO("Not yet implemented")
    }

    private fun deleteFromFav(news: News) {
        AlertDialog.Builder(requireContext()).setTitle(getString(R.string.warning))
            .setCancelable(false).setMessage(getString(R.string.are_you_sure_that_you_want_to_delete_that_news_from_favorites))
            .setPositiveButton(
                getString(android.R.string.yes)
            ) { _, _ ->
                viewModel.deleteFromFav(news)
            }.setNegativeButton(
                getString(android.R.string.no)
            ) { _, _ ->
            }.show()
    }


}