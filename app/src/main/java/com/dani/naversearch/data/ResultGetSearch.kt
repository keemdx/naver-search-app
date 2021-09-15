package com.dani.naversearch.data

import android.graphics.drawable.Drawable
import android.os.Build
import android.text.Html
import android.widget.ImageView
import com.google.gson.annotations.SerializedName
import com.bumptech.glide.Glide

import androidx.databinding.BindingAdapter
import com.bumptech.glide.request.target.Target


data class ResultGetSearch(
    @SerializedName("total")
    val total: Int = 0,
    @SerializedName("lastBuildDate")
    val lastBuildDate: String = "",
    @SerializedName("start")
    val start: Int = 0,
    @SerializedName("display")
    val display: Int = 0,
    @SerializedName("items")
    val items: List<Item>
)

data class Item(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("link")
    val link: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("thumbnail")
    val thumbnail: String = "",
    @SerializedName("pubDate")
    val pubDate: String = ""
)

object ImageViewBind {
    @JvmStatic
    @BindingAdapter("setImage")
    fun setImageUrl(view: ImageView, thumbnail: String?) {
        Glide.with(view.context)
            .load(thumbnail)
            .override(350, 345)
            .centerCrop()
            .into(view)
    }
}
