package com.hhs.musicwiki.genreDetailScreen.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistModel.AllArtists;
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistModel.Artist;
import com.hhs.musicwiki.genreDetailScreen.fragmentAdapters.ArtistRecyclerAdapter;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtistsFragment extends Fragment {
    private RecyclerView recyclerView;
    private ArtistRecyclerAdapter artistRecyclerAdapter;
    private ArrayList<Artist> artistArrayList = new ArrayList<>();

    private String genreName;
    private ShimmerFrameLayout mShimmerViewContainer;

    private String TAG = "ArtistsFragment";

    public ArtistsFragment(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<AllArtists> allArtistsCall = apiService.getArtists(getString(R.string.tag_gettopartists), genreName, getString(R.string.api_key), getString(R.string.json));

        getAllArtists(allArtistsCall);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        recyclerView = view.findViewById(R.id.recycler_view);


        return view;
    }

    void getAllArtists (Call<AllArtists> call) {
        call.enqueue(new Callback<AllArtists>() {
            @Override
            public void onResponse(Call<AllArtists> call, Response<AllArtists> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                artistArrayList = new ArrayList<Artist>(response.body().getTopArtists().getArtistList());
                artistRecyclerAdapter = new ArtistRecyclerAdapter(getContext(), artistArrayList);
                RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(artistRecyclerAdapter);

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AllArtists> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }
}