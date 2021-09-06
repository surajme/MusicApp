package com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopAlbumModels;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTagAttr;

import java.util.ArrayList;

public class ArtistTopAlbum {

    @SerializedName("album")
    private ArrayList<TopAlbum> topAlbumArrayList;

    @SerializedName("@attr")
    private ArtistTagAttr artistTagAttr;

    public ArrayList<TopAlbum> getTopAlbumArrayList() {
        return topAlbumArrayList;
    }

    public ArtistTagAttr getArtistTagAttr() {
        return artistTagAttr;
    }
}
