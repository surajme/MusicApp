package com.hhs.musicwiki.genreDetailScreen.FragmentAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hhs.musicwiki.artistDetailScreen.ArtistDetailActivity;
import com.hhs.musicwiki.genreDetailScreen.DataModels.ArtistModel.Artist;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

public class ArtistRecyclerAdapter extends RecyclerView.Adapter<ArtistRecyclerAdapter.MyViewHolder> {
    ArrayList<Artist> artistArrayList;
    Context context = null;
    public ArtistRecyclerAdapter(Context context, ArrayList<Artist> artistArrayList) {
        this.context = context;
        this.artistArrayList = artistArrayList;
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
        Artist artist = artistArrayList.get(i);

        viewHolder.artistName.setText(String.valueOf(artist.getName()));
        String cover = artist.getImage().get(2).getText();
        String artistName = artist.getName();

        Glide.with(context)
                .load(cover)
                .into(viewHolder.artistCover);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, ArtistDetailActivity.class);
                i.putExtra("ARTIST_NAME", artistName);
                i.putExtra("ARTIST_COVER", cover);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return artistArrayList.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView artistName;
        CardView cardView;
        ImageView artistCover;
        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.card_view);
            artistCover = itemView.findViewById(R.id.cover);
            artistName = itemView.findViewById(R.id.name);
        }
    }
}