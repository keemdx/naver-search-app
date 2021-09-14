package com.dani.naversearch.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dani.naversearch.data.Item
import com.dani.naversearch.databinding.ListItemResultImageBinding


class ImageListAdapter : RecyclerView.Adapter<ImageListAdapter.ViewHolder>() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val imageBinding =
            ListItemResultImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(imageBinding).apply {
            itemView.setOnClickListener {
                itemClick?.onClick(items[adapterPosition])
            }
        }
    }

    interface ItemClick {
        fun onClick(item: Item)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ListItemResultImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {

            Glide.with(binding.root)
                .load(item.thumbnail)
                .override(350, 345)
                .centerCrop()
                .into(binding.ivImage)
        }
    }

    override fun getItemCount(): Int = items.size

    fun setItems(newItems: List<Item>) {
        val diffCallback = ResultDiffCallback(this.items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items = newItems.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}