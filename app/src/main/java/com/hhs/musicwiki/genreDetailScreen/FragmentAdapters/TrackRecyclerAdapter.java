package com.hhs.musicwiki.genreDetailScreen.FragmentAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hhs.musicwiki.genreDetailScreen.DataModels.TrackModel.Track;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

public class TrackRecyclerAdapter extends RecyclerView.Adapter<TrackRecyclerAdapter.MyViewHolder> {
    ArrayList<Track> trackArrayList;
    Context context = null;
    public TrackRecyclerAdapter(Context context, ArrayList<Track> trackArrayList) {
        this.context = context;
        this.trackArrayList = trackArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_recycler_item_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        Track track = trackArrayList.get(i);
        String artistName = track.getArtistInfo().getName();
        viewHolder.trackArtist.setText(artistName);
        viewHolder.trackName.setText(String.valueOf(track.getName()));
        String cover = track.getImage().get(2).getText();

        Glide.with(context)
                .load(cover)
                .into(viewHolder.trackCover);
    }

    @Override
    public int getItemCount() {
        return trackArrayList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView trackName;
        TextView trackArtist;
        ImageView trackCover;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            trackCover = itemView.findViewById(R.id.cover);
            trackName = itemView.findViewById(R.id.name);
            trackArtist = itemView.findViewById(R.id.artist_name);
        }
    }
}