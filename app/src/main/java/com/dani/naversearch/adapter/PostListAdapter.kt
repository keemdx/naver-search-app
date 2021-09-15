package com.dani.naversearch.adapter

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dani.naversearch.data.Item
import com.dani.naversearch.databinding.ListItemResultPostBinding
import com.dani.naversearch.ui.view.WebViewActivity

class PostListAdapter : ListAdapter<Item, RecyclerView.ViewHolder>(PostDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PostViewHolder(
            ListItemResultPostBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val post = getItem(position)
        (holder as PostViewHolder).bind(post)
    }

    inner class PostViewHolder(
        private val binding: ListItemResultPostBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener { view ->
                binding.post?.let { post ->
                    val intent = Intent(view.context, WebViewActivity::class.java)
                    intent.putExtra("url", post.link)
                    view.context.startActivity(intent)
                }
            }
        }

        fun bind(item: Item) {
            binding.apply {
                post = item
                executePendingBindings()
            }
        }
    }

    private class PostDiffCallback : DiffUtil.ItemCallback<Item>() {

        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return oldItem == newItem
        }
    }
}