package com.hhs.musicwiki.genreDetailScreen.DataModels.AlbumModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.ArtistInfo
import com.hhs.musicwiki.genreDetailScreen.DataModels.ImageInfo
import com.hhs.musicwiki.genreDetailScreen.DataModels.RankAttr
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