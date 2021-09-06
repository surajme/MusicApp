package com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.dataModels.ImageInfo
import com.hhs.musicwiki.genreDetailScreen.dataModels.RankAttr
import java.util.*

class Artist {
    val name: String? = null
    val mbid: String? = null
    val url: String? = null
    val streamable = 0

    @SerializedName("image")
    val image: ArrayList<ImageInfo>? = null

    @SerializedName("@attr")
    val rankAttr: RankAttr? = null
}