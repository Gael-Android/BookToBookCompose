package com.eggtart.booktobookcompose.data

data class BookData(
    val meta: Meta,
    val documents: List<Document>
)

data class Document(
    val authors: List<String>,
    val contents: String,
    val datetime: String,
    val isbn: String,
    val price: Int,
    val publisher: String,
    val sale_price: Int,
    val status: String,
    val thumbnail: String,
    val title: String,
    val translators: List<String>,
    val url: String
)

data class Meta(
    val is_end: Boolean,
    val pageable_count: Int,
    val total_count: Int
)
