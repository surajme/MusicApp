package com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopTracksModels;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTagAttr;

import java.util.ArrayList;

public class ArtistTopTrack {

    @SerializedName("track")
    private ArrayList<TopTrack> topTracksArrayList;

    @SerializedName("@attr")
    private ArtistTagAttr artistTagAttr;

    public ArrayList<TopTrack> getTopTracksArrayList() {
        return topTracksArrayList;
    }

    public ArtistTagAttr getArtistTagAttr() {
        return artistTagAttr;
    }
}
