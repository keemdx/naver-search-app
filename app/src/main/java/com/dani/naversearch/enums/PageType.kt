package com.dani.naversearch.enums

enum class PageType(val title: String, val key: String, val tag: String) {
    BLOG("Blog", "blog", "tag_blog"),
    CAFE("Cafe", "cafearticle", "tag_cafe"),
    IMAGE("Image", "image", "tag_image")
}