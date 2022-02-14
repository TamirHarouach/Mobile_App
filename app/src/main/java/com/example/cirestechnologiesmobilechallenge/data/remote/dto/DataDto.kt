package com.example.cirestechnologiesmobilechallenge.data.remote.dto


import com.example.cirestechnologiesmobilechallenge.domain.model.Data
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataDto(
    @SerialName("author")
    val author: String?,
    @SerialName("content")
    val content: String?,
    @SerialName("date")
    val date: String?,
    @SerialName("imageUrl")
    val imageUrl: String?,
    @SerialName("readMoreUrl")
    val readMoreUrl: String?,
    @SerialName("time")
    val time: String?,
    @SerialName("title")
    val title: String?,
    @SerialName("url")
    val url: String?
) {
    fun toData() : Data {
        return Data(
            title = title!!,
            imageUrl = imageUrl!!,
            dateTime = date!!
        )
    }
}