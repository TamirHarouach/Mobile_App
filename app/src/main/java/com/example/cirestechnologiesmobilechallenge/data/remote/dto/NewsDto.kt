package com.example.cirestechnologiesmobilechallenge.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsDto(
    @SerialName("category")
    val category: String,
    @SerialName("data")
    val dataDto: List<DataDto>,
    @SerialName("success")
    val success: Boolean
)