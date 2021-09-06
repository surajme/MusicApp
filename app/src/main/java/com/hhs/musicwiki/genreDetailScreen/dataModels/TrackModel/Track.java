package com.hhs.musicwiki.genreDetailScreen.dataModels.TrackModel;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistInfo;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ImageInfo;
import com.hhs.musicwiki.genreDetailScreen.dataModels.RankAttr;

import java.util.ArrayList;

public class Track {
    private String name;
    private long duration;
    private String mbid;
    private String url;

    @SerializedName("streamable")
    private Streamable streamable;

    @SerializedName("image")
    private ArrayList<ImageInfo> image;

    @SerializedName("artist")
    private ArtistInfo artistInfo;

    @SerializedName("@attr")
    private RankAttr rankAttr;

    public String getName() {
        return name;
    }

    public long getDuration() {
        return duration;
    }

    public String getMbid() {
        return mbid;
    }

    public String getUrl() {
        return url;
    }

    public ArtistInfo getArtistInfo() {
        return artistInfo;
    }

    public Streamable getStreamable() {
        return streamable;
    }

    public ArrayList<ImageInfo> getImage() {
        return image;
    }

    public RankAttr getRankAttr() {
        return rankAttr;
    }
}
