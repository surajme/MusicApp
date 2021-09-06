package com.hhs.musicwiki.homeScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.button.MaterialButton;
import com.hhs.musicwiki.genreDetailScreen.GenreDetailActivity;
import com.hhs.musicwiki.homeScreen.Models.AllGenres;
import com.hhs.musicwiki.homeScreen.Models.Genre;
import com.hhs.musicwiki.apiService.ApiService;
import com.hhs.musicwiki.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    boolean isClicked = false;
    FlexboxLayout flexboxLayout;
    ArrayList<String> genreList = new ArrayList<String>();

    private String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView arrowButton = findViewById(R.id.arrow_button);
        flexboxLayout = findViewById(R.id.buttonLayout);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        Call<AllGenres> call = apiService.getGenres(getString(R.string.tag_getTopTags), getString(R.string.api_key), getString(R.string.json));

        getAllGenres(call);

        arrowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClicked) {
                    arrowButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_expand_less_24));
                    isClicked = true;
                    buildGenreTag(genreList.size());
                } else {
                    arrowButton.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_expand_more_24));
                    isClicked = false;
                    buildGenreTag(10);
                }

            }
        });
    }

    void getAllGenres(Call<AllGenres> call) {
        call.enqueue(new Callback<AllGenres>() {
            @Override
            public void onResponse(Call<AllGenres> call, Response<AllGenres> response) {
                if(!response.isSuccessful()) {
                    Log.d(TAG, "onResponse: error retrieving data");
                    return;
                }
                assert response.body() != null;
                assert response.body().getTopGenres() != null;
                for (Genre tag : response.body().getTopGenres().getGenres()) {
                    genreList.add(tag.getName());
                }
                buildGenreTag(10);
            }

            @Override
            public void onFailure(Call<AllGenres> call, Throwable t) {
                Log.d(TAG, "onResponse: retrofit error");
            }
        });
    }

    void buildGenreTag(int count) {
        flexboxLayout.removeAllViews();
        for (int i = 0; i < count; i++) {
            MaterialButton materialButton = new MaterialButton(MainActivity.this, null, R.attr.chipStyle);
            materialButton.setText(genreList.get(i));
            materialButton.setBackgroundResource(R.drawable.layout_stroke_black);
            materialButton.setTextColor(getResources().getColor(R.color.black));
            materialButton.setPadding(16, 18,16,18);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(16, 12, 16, 12);
            materialButton.setLayoutParams(params);
            flexboxLayout.setFlexDirection(FlexDirection.ROW);
            flexboxLayout.addView(materialButton);
            materialButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, GenreDetailActivity.class);
                    i.putExtra("GENRE_NAME", materialButton.getText());
                    startActivity(i);
                }
            });
        }
    }
}