package com.example.news.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news.databinding.NewsListRowBinding
import com.example.news.domin.model.News

class FavAdapter(val onClickListener: OnClickListener) : ListAdapter<News, FavAdapter.ViewHolder>(DiffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = NewsListRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener{
            onClickListener.onItemClick(item)
        }

        holder.itemView.setOnLongClickListener {
            onClickListener.removeClickListener(item)
            true
        }
        holder.binding.txtTitleHome.text = item.title
        holder.binding.imageResultItemMeal.setImageUsingGlide(item.urlToImage)



    }

    class ViewHolder(val binding: NewsListRowBinding) :RecyclerView.ViewHolder(binding.root)


    object DiffUtils : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

    }

    class OnClickListener(val removeClickListener : (News) -> Unit, val itemClickListener : (News) -> Unit){
        fun onRemoveClick(news: News) = removeClickListener(news)
        fun onItemClick(news: News) = itemClickListener(news)
    }
}

fun ImageView.setImageUsingGlide(url: String){
    Glide.with(this)
        .load(url)
        .into(this)
}