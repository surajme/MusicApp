package com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopAlbumModels;

import com.google.gson.annotations.SerializedName;

public class ArtistTopAlbums {

    @SerializedName("topalbums")
    private ArtistTopAlbum artistTopAlbum;

    public ArtistTopAlbum getArtistTopAlbum() {
        return artistTopAlbum;
    }
}
