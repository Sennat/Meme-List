package com.project.memelist.model

import com.google.gson.annotations.SerializedName

data class MemeResponse(
    val data: List<MemeItem>,
    val next: String
)

data class MemeItem (
    @SerializedName("ID") val id: Int,
    val bottomText: String,
    val image: String,
    val name: String,
    val rank: Int,
    val tags: String,
    val topText: String,
)
