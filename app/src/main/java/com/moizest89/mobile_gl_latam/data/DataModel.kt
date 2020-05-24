package com.moizest89.mobile_gl_latam.data
import com.google.gson.annotations.SerializedName


data class DataModelItem(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("title")
    var title: String = ""
)