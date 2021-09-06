package com.hhs.musicwiki.genreDetailScreen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;
import com.hhs.musicwiki.genreDetailScreen.dataModels.AlbumModel.Album;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistModel.Artist;
import com.hhs.musicwiki.genreDetailScreen.fragmentAdapters.FragmentAdapter;
import com.hhs.musicwiki.genreDetailScreen.models.GenreInfo;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GenreDetailActivity extends AppCompatActivity {

    private TextView genreInfo;

    private ArrayList<Album> albumArrayList;
    private ArrayList<Artist> artistArrayList;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private FragmentAdapter adapter;

    private String TAG = "MainActivity";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genre_detail);

        genreInfo = findViewById(R.id.genre_info);
        tabLayout= findViewById(R.id.htab_tabs);
        viewPager= findViewById(R.id.htab_viewpager);

        String genreName = getIntent().getStringExtra("GENRE_NAME");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        if(getSupportActionBar() != null)
        {
            assert genreName != null;
            getSupportActionBar().setTitle(genreName.toUpperCase());
            getSupportActionBar().setElevation(0);
        }

        Call<GenreInfo> genreInfoCall = apiService.getGenreInfo(getString(R.string.tag_getinfo), genreName, getString(R.string.api_key), getString(R.string.json));

        getGenreInfo(genreInfoCall);

        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.albums)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.artists)));
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.tracks)));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        adapter = new FragmentAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), genreName);
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    void getGenreInfo (Call<GenreInfo> call) {
        call.enqueue(new Callback<GenreInfo>() {
            @Override
            public void onResponse(Call<GenreInfo> call, Response<GenreInfo> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                String name = response.body().getTag().getName().toUpperCase();
                String summaryTemp = response.body().getTag().getWiki().getSummary();
                String regex = "<a href=(\"[^\"]*\")[^<]*</a>";
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(summaryTemp);
                genreInfo.setText(m.replaceAll("$1"));
            }

            @Override
            public void onFailure(Call<GenreInfo> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }
}
