package com.hhs.musicwiki.genreDetailScreen.DataModels.AlbumModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.TagAttr
import java.util.*

class TopAlbums {
    @SerializedName("album")
    val albumList: ArrayList<Album>? = null

    @SerializedName("@attr")
    val tagAttr: TagAttr? = null
}