package com.moizest89.mobile_gl_latam.domain
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DataModelItem(
    @SerializedName("description")
    var description: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("title")
    var title: String = ""
) : Parcelable