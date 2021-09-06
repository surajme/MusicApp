package com.hhs.musicwiki.artistDetailScreen.dataModels;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ArtistsSimilar {

    @SerializedName("artist")
    private ArrayList<ArtistInfo2> artistInfo2ArrayList;

    public ArrayList<ArtistInfo2> getArtistInfo2ArrayList() {
        return artistInfo2ArrayList;
    }
}
