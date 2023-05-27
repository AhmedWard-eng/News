package com.example.news.ui.favorites

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
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
    var list : List<News>? = null
    var filteredList : List<News>? = null

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

        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filteredList = list?.filter { it.title.contains(s.toString()) }
                if(filteredList.isNullOrEmpty()){
                        binding.groupNoMatchingResult.visibility = VISIBLE
                        binding.recyclerView.visibility = GONE
                }else{

                    binding.groupNoMatchingResult.visibility = GONE
                    binding.recyclerView.visibility = VISIBLE
                }
                favAdapter.submitList(filteredList)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed.
            }
        }

        binding.searchTextField.addTextChangedListener(textWatcher)



        lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                viewModel.news.collectLatest {
                    list = it
                    if(it.isEmpty()){

                        binding.groupNoMatchingResult.visibility = VISIBLE
                        binding.recyclerView.visibility = GONE
                    }else{
                        binding.groupNoMatchingResult.visibility = GONE
                        binding.recyclerView.visibility = VISIBLE
                    }
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