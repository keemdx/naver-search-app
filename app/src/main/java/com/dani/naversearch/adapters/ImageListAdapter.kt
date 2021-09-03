package com.dani.naversearch.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dani.naversearch.data.Items
import com.dani.naversearch.databinding.ListItemResultImageBinding

class ImageListAdapter(private var items: List<Items>) :
    RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    private lateinit var imageBinding: ListItemResultImageBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        imageBinding =
            ListItemResultImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(imageBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ListItemResultImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Items) {
            Glide.with(binding.root)
                .load(item.thumbnail)
                .override(item.sizewidth.toInt(), item.sizeheight.toInt())
                .into(binding.ivImage)
        }
    }

    override fun getItemCount(): Int = items.size

}