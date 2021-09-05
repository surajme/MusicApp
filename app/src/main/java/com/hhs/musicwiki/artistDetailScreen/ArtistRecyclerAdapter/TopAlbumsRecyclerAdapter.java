package com.hhs.musicwiki.artistDetailScreen.ArtistRecyclerAdapter;

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
import com.hhs.musicwiki.albumDetailScreen.AlbumDetailActivity;
import com.hhs.musicwiki.artistDetailScreen.DataModels.ArtistTopAlbumModels.TopAlbum;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

public class TopAlbumsRecyclerAdapter extends RecyclerView.Adapter<TopAlbumsRecyclerAdapter.MyViewHolder> {
    ArrayList<TopAlbum> topAlbumArrayList;
    Context context = null;

    public TopAlbumsRecyclerAdapter(Context context, ArrayList<TopAlbum> topAlbumArrayList) {
        this.context = context;
        this.topAlbumArrayList = topAlbumArrayList;
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
        TopAlbum topAlbum = topAlbumArrayList.get(i);
        String albumName = topAlbum.getName();
        String artistName = topAlbum.getArtistInfo().getName();
        viewHolder.album_name.setText(albumName);
        viewHolder.album_artist.setText(artistName);

        String cover = topAlbum.getImageInfoArrayList().get(3).getText();
        Glide.with(context)
                .load(cover)
                .into(viewHolder.album_cover);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, AlbumDetailActivity.class);
                i.putExtra("ALBUM_NAME", albumName);
                i.putExtra("ALBUM_COVER", cover);
                i.putExtra("ARTIST_NAME", artistName);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return topAlbumArrayList.size();
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