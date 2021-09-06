package com.hhs.musicwiki.genreDetailScreen.dataModels.AlbumModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.dataModels.TagAttr
import java.util.*

class TopAlbums {
    @SerializedName("album")
    val albumList: ArrayList<Album>? = null

    @SerializedName("@attr")
    val tagAttr: TagAttr? = null
}