package com.hhs.musicwiki.genreDetailScreen.dataModels.TrackModel;

import com.google.gson.annotations.SerializedName;
import com.hhs.musicwiki.genreDetailScreen.dataModels.TagAttr;

import java.util.ArrayList;

public class TopTracks {
    @SerializedName("track")
    private ArrayList<Track> trackList;

    @SerializedName("@attr")
    private TagAttr tagAttr;

    public ArrayList<Track> getTrackList() {
        return trackList;
    }

    public TagAttr getTagAttr() {
        return tagAttr;
    }
}
