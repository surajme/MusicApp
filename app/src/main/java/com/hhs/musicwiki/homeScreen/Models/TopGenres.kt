package com.hhs.musicwiki.homeScreen.Models

import com.google.gson.annotations.SerializedName
import java.util.*

class TopGenres {
    @SerializedName("@attr")
    private val tagAttr: TagAttr? = null

    @SerializedName("tag")
    val genres: ArrayList<Genre>? = null
}