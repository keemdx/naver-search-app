package com.dani.naversearch.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dani.naversearch.data.Item
import com.dani.naversearch.databinding.ListItemResultImageBinding
import com.dani.naversearch.ui.view.WebViewActivity


class ImageListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(ImageDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageViewHolder(
            ListItemResultImageBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val image = getItem(position)
        if (image != null) {
            (holder as ImageViewHolder).bind(image)
        }
    }

    inner class ImageViewHolder(
        private val binding: ListItemResultImageBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.photo?.let { photo ->
                    val intent = Intent(view.context, WebViewActivity::class.java)
                    intent.putExtra("url", photo.link)
                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: Item) {
            binding.apply {
                photo = item
                executePendingBindings()
            }
        }
    }

    private class ImageDiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}