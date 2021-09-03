package com.dani.naversearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dani.naversearch.data.Items
import com.dani.naversearch.databinding.ListItemResultPostBinding

class PostListAdapter(private var items: List<Items>) :
    RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private lateinit var postBinding: ListItemResultPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        postBinding = ListItemResultPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(postBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ListItemResultPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            binding.tvTitle.text = item.title
            binding.tvContents.text = item.description
        }
    }

    override fun getItemCount(): Int = items.size

}