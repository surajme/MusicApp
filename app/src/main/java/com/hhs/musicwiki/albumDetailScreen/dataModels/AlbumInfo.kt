package com.hhs.musicwiki.albumDetailScreen.dataModels

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.ImageInfo
import java.util.*

class AlbumInfo {
    val name: String? = null
    val artist: String? = null
    val url: String? = null

    @SerializedName("image")
    val imageInfo: ArrayList<ImageInfo>? = null
    val listeners: Long = 0
    val playCount: Long = 0
    val tracks: AlbumTracks? = null
    val tags: AlbumTags? = null

    @SerializedName("wiki")
    val albumWiki: AlbumWiki? = null
}