package com.example.cirestechnologiesmobilechallenge.presentation.news.discover

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cirestechnologiesmobilechallenge.core.util.loadImageWithGlide
import com.example.cirestechnologiesmobilechallenge.databinding.DiscoverItemBinding
import com.example.cirestechnologiesmobilechallenge.domain.model.Data

class DiscoverAdapter() : ListAdapter<Data, DiscoverAdapter.DiscoverViewHolder>(DiffUtilCallBacks()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        val binding =
            DiscoverItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DiscoverViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class DiscoverViewHolder(private val binding: DiscoverItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(newsData: Data) {
            binding.apply {
                newsTitleTextView.text = newsData.title
                newsImageView.loadImageWithGlide(newsData.imageUrl)
                newsHoursTextView.text = newsData.dateTime
            }
        }
    }

    class DiffUtilCallBacks : DiffUtil.ItemCallback<Data>() {
        override fun areItemsTheSame(oldItem: Data, newItem: Data) =
            oldItem.title == newItem.title && oldItem.dateTime == newItem.dateTime && oldItem.imageUrl == newItem.imageUrl

        override fun areContentsTheSame(oldItem: Data, newItem: Data) =
            oldItem == newItem
    }
}