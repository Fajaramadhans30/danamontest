package com.test.danamontest.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class DataModel : Serializable {
    @SerializedName("albumId")
    @Expose
    var albumId: String? = null

    @SerializedName("id")
    @Expose
    var id: String? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("thumbnailUrl")
    @Expose
    var thumbnailUrl: String? = null
}