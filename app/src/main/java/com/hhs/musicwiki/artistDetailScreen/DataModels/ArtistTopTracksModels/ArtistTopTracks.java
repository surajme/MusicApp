package com.hhs.musicwiki.artistDetailScreen.DataModels.ArtistTopTracksModels;

import com.google.gson.annotations.SerializedName;

public class ArtistTopTracks {

    @SerializedName("toptracks")
    private ArtistTopTrack artistTopTrack;

    public ArtistTopTrack getArtistTopTrack() {
        return artistTopTrack;
    }
}
