package com.hhs.musicwiki.genreDetailScreen.DataModels.ArtistModel

import com.google.gson.annotations.SerializedName
import com.hhs.musicwiki.genreDetailScreen.DataModels.TagAttr
import java.util.*

class TopArtists {
    @SerializedName("artist")
    val artistList: ArrayList<Artist>? = null

    @SerializedName("@attr")
    val tagAttr: TagAttr? = null
}