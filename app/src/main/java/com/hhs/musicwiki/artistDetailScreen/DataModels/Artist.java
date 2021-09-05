package com.hhs.musicwiki.artistDetailScreen.DataModels;

import com.google.gson.annotations.SerializedName;

public class Artist {
    @SerializedName("artist")
    private ArtistInfo artistInfo;

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }
}
