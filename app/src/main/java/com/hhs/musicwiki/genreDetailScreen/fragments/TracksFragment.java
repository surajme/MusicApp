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
import com.hhs.musicwiki.genreDetailScreen.dataModels.TrackModel.AllTracks;
import com.hhs.musicwiki.genreDetailScreen.dataModels.TrackModel.Track;
import com.hhs.musicwiki.genreDetailScreen.fragmentAdapters.TrackRecyclerAdapter;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TracksFragment extends Fragment {
    private RecyclerView recyclerView;
    private TrackRecyclerAdapter trackRecyclerAdapter;
    private ArrayList<Track> tracksArrayList = new ArrayList<>();

    private String genreName;
    private ShimmerFrameLayout mShimmerViewContainer;

    private String TAG = "TracksFragment";

    public TracksFragment(String genreName) {
        this.genreName = genreName;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tracks, container, false);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<AllTracks> allTracksCall = apiService.getTracks(getString(R.string.tag_gettoptracks), genreName, getString(R.string.api_key), getString(R.string.json));

        getAllTracks(allTracksCall);

        mShimmerViewContainer = view.findViewById(R.id.shimmer_view_container);
        recyclerView = view.findViewById(R.id.recycler_view);

        return view;
    }

    void getAllTracks (Call<AllTracks> call) {
        call.enqueue(new Callback<AllTracks>() {
            @Override
            public void onResponse(Call<AllTracks> call, Response<AllTracks> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                tracksArrayList = new ArrayList<Track>(response.body().getTopTracks().getTrackList());
                trackRecyclerAdapter = new TrackRecyclerAdapter(getContext(), tracksArrayList);
                RecyclerView.LayoutManager manager = new GridLayoutManager(getContext(), 2);
                recyclerView.setLayoutManager(manager);
                recyclerView.setAdapter(trackRecyclerAdapter);

                mShimmerViewContainer.stopShimmerAnimation();
                mShimmerViewContainer.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<AllTracks> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit failure");
            }
        });
    }
}