package com.dani.naversearch.adapter

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dani.naversearch.data.Item
import com.dani.naversearch.databinding.ListItemResultPostBinding

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private var items: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val postBinding =
            ListItemResultPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(postBinding).apply {
            itemView.setOnClickListener {
                itemClick?.onClick(items[adapterPosition])
            }
        }
    }

    interface ItemClick {
        fun onClick(item: Item)
    }

    var itemClick: ItemClick? = null

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) { //스크롤할때마다 기존 뷰홀더를 재사용하기위해 계 속호출
        holder.bind(items[position])
    }

    inner class ViewHolder(private val binding: ListItemResultPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Item) {
            binding.tvTitle.text = htmlToString(item.title)
            binding.tvContents.text = htmlToString(item.description)
        }
    }

    override fun getItemCount(): Int = items.size

    fun htmlToString(htmlItem: String): String {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(htmlItem, Html.FROM_HTML_MODE_LEGACY).toString()
        } else {
            return Html.fromHtml(htmlItem).toString()
        }
    }

    fun setItems(newItems: List<Item>) {
        val diffCallback = ResultDiffCallback(this.items, newItems)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.items = newItems.toMutableList()
        diffResult.dispatchUpdatesTo(this)
    }
}