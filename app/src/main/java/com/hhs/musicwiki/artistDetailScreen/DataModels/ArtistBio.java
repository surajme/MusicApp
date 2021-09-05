package com.hhs.musicwiki.artistDetailScreen.DataModels;

import com.google.gson.annotations.SerializedName;

public class ArtistBio {

    @SerializedName("links")
    private ArtistLinks artistLinks;

    private String published;
    private String summary;
    private String content;

    public ArtistLinks getArtistLinks() {
        return artistLinks;
    }

    public String getPublished() {
        return published;
    }

    public String getSummary() {
        return summary;
    }

    public String getContent() {
        return content;
    }
}
