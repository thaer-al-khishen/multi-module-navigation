package com.thaer.core.data.models

import com.google.gson.annotations.SerializedName

data class Hero(
    @SerializedName("name")
    val superName: String?,
    val realName: String?,
    val team: String?
)
