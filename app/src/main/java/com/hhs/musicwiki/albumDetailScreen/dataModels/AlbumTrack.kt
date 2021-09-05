package com.hhs.musicwiki.albumDetailScreen.dataModels

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.ArtistInfo
import com.hhs.musicwiki.genreDetailScreen.DataModels.RankAttr
import com.hhs.musicwiki.genreDetailScreen.DataModels.TrackModel.Streamable

class AlbumTrack {
    private val name: String? = null
    private val url: String? = null
    private val duration: Long = 0

    @SerializedName("@attr")
    private val rankAttr: RankAttr? = null
    private val streamable: Streamable? = null

    @SerializedName("artist")
    private val artistInfo: ArtistInfo? = null
}