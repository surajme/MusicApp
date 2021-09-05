package com.hhs.musicwiki.artistDetailScreen.DataModels.ArtistTopTracksModels;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.artistDetailScreen.DataModels.ArtistTagAttr;

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
