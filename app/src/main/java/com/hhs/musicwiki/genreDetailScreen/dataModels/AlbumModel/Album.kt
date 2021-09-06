package com.hhs.musicwiki.genreDetailScreen.dataModels.AlbumModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistInfo
import com.hhs.musicwiki.genreDetailScreen.dataModels.ImageInfo
import com.hhs.musicwiki.genreDetailScreen.dataModels.RankAttr
import java.util.*

class Album {
    val name: String? = null
    val mbid: String? = null
    val url: String? = null

    @SerializedName("artist")
    val albumArtist: ArtistInfo? = null

    @SerializedName("image")
    val image: ArrayList<ImageInfo>? = null

    @SerializedName("@attr")
    val rankAttr: RankAttr? = null
}