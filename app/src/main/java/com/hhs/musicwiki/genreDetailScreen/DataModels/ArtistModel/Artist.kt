package com.hhs.musicwiki.genreDetailScreen.DataModels.ArtistModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.ImageInfo
import com.hhs.musicwiki.genreDetailScreen.DataModels.RankAttr
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