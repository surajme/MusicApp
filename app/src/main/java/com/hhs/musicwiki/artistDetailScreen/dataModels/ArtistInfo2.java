package com.hhs.musicwiki.artistDetailScreen.dataModels;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ImageInfo;

import java.util.ArrayList;

public class ArtistInfo2 {
    private String name;
    private String url;

    @SerializedName("image")
    private ArrayList<ImageInfo> imageInfo;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ArrayList<ImageInfo> getImageInfo() {
        return imageInfo;
    }
}
