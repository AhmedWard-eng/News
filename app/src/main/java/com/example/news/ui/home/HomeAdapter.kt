package com.example.news.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.R
import com.example.news.databinding.NewsListRowBinding
import com.example.news.domin.model.News

class HomeAdapter(
    var context: Context,
    var myListener: OnItemNewsClicked
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var binding: NewsListRowBinding
    private  var newsList: MutableList<News> = ArrayList()

    fun setList(list: List<News>) {
        newsList.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = NewsListRowBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = newsList[position]
        holder.binding.txtTitleHome.text = current.title
        Glide.with(context.applicationContext)
            .load(current.urlToImage)
            .placeholder(R.drawable.image_not_found)
            .error(R.drawable.images)
            .override(200, 200)
            .centerCrop()
            .into(holder.binding.imageNewsHome)
        holder.binding.rowNewsHome.setOnClickListener {
            myListener.newsClicked(current)

        }

    }

    inner class ViewHolder(var binding: NewsListRowBinding) : RecyclerView.ViewHolder(binding.root)
}

interface OnItemNewsClicked {
    fun newsClicked(News: News)
}