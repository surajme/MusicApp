package com.hhs.musicwiki.albumDetailScreen;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.button.MaterialButton;

import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hhs.musicwiki.albumDetailScreen.dataModels.Album;
import com.hhs.musicwiki.albumDetailScreen.dataModels.AlbumTag;
import com.hhs.musicwiki.genreDetailScreen.GenreDetailActivity;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlbumDetailActivity extends AppCompatActivity {

    String albumCover;
    String albumName;
    String artistName;

    TextView albumInfo;
    ImageView expandedImage;

    LinearLayout tagsLayout;

    ArrayList<String> genreList = new ArrayList<String>();

    private String TAG = "AlbumDetailActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);

        albumName = getIntent().getStringExtra("ALBUM_NAME");
        albumCover = getIntent().getStringExtra("ALBUM_COVER");
        artistName = getIntent().getStringExtra("ARTIST_NAME");

        tagsLayout = findViewById(R.id.tags_layout);
        expandedImage = findViewById(R.id.expandedImage);
        albumInfo = findViewById(R.id.item_info);

        Glide.with(this)
                .load(albumCover)
                .into(expandedImage);

        if(getSupportActionBar() != null)
        {
            assert albumName != null;
            getSupportActionBar().setTitle(albumName.toUpperCase());
            getSupportActionBar().setElevation(0);
        }

        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(artistName);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<Album> call = apiService.getAlbumInfo(getString(R.string.album_getinfo), getString(R.string.api_key), artistName, albumName,getString(R.string.json));

        getAlbumInfo(call);

    }

    void getAlbumInfo(Call<Album> call) {
        call.enqueue(new Callback<Album>() {
            @Override
            public void onResponse(Call<Album> call, Response<Album> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                String albumSummary = response.body().getAlbumInfo().getAlbumWiki().getContent();
                albumInfo.setText(albumSummary);
                for (AlbumTag albumTag : response.body().getAlbumInfo().getTags().getAlbumTagArrayList()) {
                    genreList.add(albumTag.getName());
                }
                buildGenreTag(genreList);
            }

            @Override
            public void onFailure(Call<Album> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }

    void buildGenreTag(ArrayList<String> genreList) {
        tagsLayout.removeAllViews();
        for (String genre : genreList) {
            MaterialButton materialButton = new MaterialButton(AlbumDetailActivity.this, null, R.attr.chipStyle);
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
                    Intent i = new Intent(AlbumDetailActivity.this, GenreDetailActivity.class);
                    i.putExtra("GENRE_NAME", materialButton.getText());
                    startActivity(i);
                }
            });
        }
    }
}