package com.dani.naversearch.data

public data class ResultGetSearch(
    var lastBuildDate: String = "",
    var total: Int = 0,
    var start: Int = 0,
    var display: Int = 0,
    var items: List<Items>
)

data class Items (
    var title: String = "",
    var link: String = "",
    var description: String = "",
    var thumbnail: String = "",
    var sizewidth: String = "",
    var sizeheight: String = "",
    var pubDate: String = ""
)
