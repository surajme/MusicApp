package com.hhs.musicwiki.artistDetailScreen.dataModels;

import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("artist")
    private ArtistInfo artistInfo;

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }
}
