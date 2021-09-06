package com.hhs.musicwiki.artistDetailScreen.artistRecyclerAdapter;

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
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopTracksModels.TopTrack;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

public class TopTracksRecyclerAdapter extends RecyclerView.Adapter<TopTracksRecyclerAdapter.MyViewHolder> {
    ArrayList<TopTrack> topTrackArrayList;
    Context context = null;

    public TopTracksRecyclerAdapter(Context context, ArrayList<TopTrack> topTrackArrayList) {
        this.context = context;
        this.topTrackArrayList = topTrackArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_artist_top_recycler_item_row, viewGroup, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int i) {
        TopTrack topTrack = topTrackArrayList.get(i);
        String trackName = topTrack.getName();
        String artistName = topTrack.getArtistInfo().getName();
        viewHolder.album_name.setText(trackName);
        viewHolder.album_artist.setText(artistName);

        String cover = topTrack.getImageInfoArrayList().get(3).getText();
        Glide.with(context)
                .load(cover)
                .into(viewHolder.album_cover);
    }

    @Override
    public int getItemCount() {
        return topTrackArrayList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView album_name;
        TextView album_artist;
        ImageView album_cover;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            album_cover = itemView.findViewById(R.id.cover);
            album_name = itemView.findViewById(R.id.name);
            album_artist = itemView.findViewById(R.id.artist_name);
        }
    }
}