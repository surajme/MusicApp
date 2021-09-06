package com.hhs.musicwiki.artistDetailScreen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;
import com.hhs.musicwiki.albumDetailScreen.dataModels.AlbumTag;
import com.hhs.musicwiki.artistDetailScreen.artistRecyclerAdapter.TopAlbumsRecyclerAdapter;
import com.hhs.musicwiki.artistDetailScreen.artistRecyclerAdapter.TopTracksRecyclerAdapter;
import com.hhs.musicwiki.artistDetailScreen.dataModels.Artist;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopAlbumModels.ArtistTopAlbums;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopAlbumModels.TopAlbum;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopTracksModels.ArtistTopTracks;
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopTracksModels.TopTrack;
import com.hhs.musicwiki.genreDetailScreen.GenreDetailActivity;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistDetailActivity extends AppCompatActivity {

    String artistName;
    String artistCover;

    TextView artistInfo;
    TextView playCount;
    TextView listeners;
    ImageView expandedImage;

    LinearLayout tagsLayout;

    ArrayList<String> genreList = new ArrayList<String>();

    private RecyclerView topTracksRecyclerView;
    private TopTracksRecyclerAdapter topTracksRecyclerAdapter;
    private ArrayList<TopTrack> topTrackArrayList = new ArrayList<>();

    private RecyclerView topAlbumsRecyclerView;
    private TopAlbumsRecyclerAdapter topAlbumsRecyclerAdapter;
    private ArrayList<TopAlbum> topAlbumArrayList = new ArrayList<>();

    private String TAG = "ArtistDetailActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_detail);

        artistName = getIntent().getStringExtra("ARTIST_NAME");
        artistCover = getIntent().getStringExtra("ARTIST_COVER");

        tagsLayout = findViewById(R.id.tags_layout);

        expandedImage = findViewById(R.id.expandedImage);
        artistInfo = findViewById(R.id.item_info);

        playCount = findViewById(R.id.playcount);
        listeners = findViewById(R.id.listeners);

        topTracksRecyclerView = findViewById(R.id.top_tracks_recycler_view);
        topAlbumsRecyclerView = findViewById(R.id.top_albums_recycler_view);


        Glide.with(this)
                .load(artistCover)
                .into(expandedImage);

        if(getSupportActionBar() != null)
        {
            assert artistName != null;
            getSupportActionBar().setTitle(artistName.toUpperCase());
            getSupportActionBar().setElevation(0);
        }

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(artistName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Artist> artistInfoCall = apiService.getArtistInfo(getString(R.string.artist_getinfo), artistName, getString(R.string.api_key), getString(R.string.json));
        getArtistInfo(artistInfoCall);

        Call<ArtistTopTracks> topTracksCall = apiService.getArtistTopTracks(getString(R.string.artist_gettoptracks), artistName, getString(R.string.api_key), getString(R.string.json));
        getArtistTopTracks(topTracksCall);

        Call<ArtistTopAlbums> topAlbumsCall = apiService.getArtistTopAlbums(getString(R.string.artist_gettopalbums), artistName, getString(R.string.api_key), getString(R.string.json));
        getArtistTopAlbums(topAlbumsCall);

    }

    void getArtistTopTracks(Call<ArtistTopTracks> call) {
        call.enqueue(new Callback<ArtistTopTracks>() {
            @Override
            public void onResponse(Call<ArtistTopTracks> call, Response<ArtistTopTracks> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                topTrackArrayList = new ArrayList<TopTrack>(response.body().getArtistTopTrack().getTopTracksArrayList());
                topTracksRecyclerAdapter = new TopTracksRecyclerAdapter(ArtistDetailActivity.this, topTrackArrayList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(ArtistDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);

                topTracksRecyclerView.setLayoutManager(manager);
                topTracksRecyclerView.setAdapter(topTracksRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<ArtistTopTracks> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }

    void getArtistTopAlbums(Call<ArtistTopAlbums> call) {
        call.enqueue(new Callback<ArtistTopAlbums>() {
            @Override
            public void onResponse(Call<ArtistTopAlbums> call, Response<ArtistTopAlbums> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                topAlbumArrayList = new ArrayList<TopAlbum>(response.body().getArtistTopAlbum().getTopAlbumArrayList());
                topAlbumsRecyclerAdapter = new TopAlbumsRecyclerAdapter(ArtistDetailActivity.this, topAlbumArrayList);
                RecyclerView.LayoutManager manager = new LinearLayoutManager(ArtistDetailActivity.this, LinearLayoutManager.HORIZONTAL, false);

                topAlbumsRecyclerView.setLayoutManager(manager);
                topAlbumsRecyclerView.setAdapter(topAlbumsRecyclerAdapter);
            }

            @Override
            public void onFailure(Call<ArtistTopAlbums> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }

    void getArtistInfo(Call<Artist> call) {
        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                String playcount = String.valueOf(response.body().getArtistInfo().getArtistStats().getPlaycount());
                String listenersCount = String.valueOf(response.body().getArtistInfo().getArtistStats().getListeners());
                String artistSummary = String.valueOf(response.body().getArtistInfo().getArtistBio().getSummary());

                artistInfo.setText(artistSummary);
                playCount.setText(playcount);
                listeners.setText(listenersCount);
                for (AlbumTag albumTag : response.body().getArtistInfo().getAlbumTags().getAlbumTagArrayList()) {
                    genreList.add(albumTag.getName());
                }
                buildGenreTag(genreList);
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }

    void buildGenreTag(ArrayList<String> genreList) {
        tagsLayout.removeAllViews();
        for (String genre : genreList) {
            MaterialButton materialButton = new MaterialButton(ArtistDetailActivity.this, null, R.attr.chipStyle);
            materialButton.setText(genre);
            materialButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            materialButton.setTextColor(getResources().getColor(R.color.colorAccent));
            materialButton.setCornerRadius(20);
            materialButton.setPadding(16, 18,16,18);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(16, 16, 16, 8);
            materialButton.setLayoutParams(params);
            tagsLayout.addView(materialButton);
            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ArtistDetailActivity.this, GenreDetailActivity.class);
                    i.putExtra("GENRE_NAME", materialButton.getText());
                    startActivity(i);
                }
            });
        }
    }
}