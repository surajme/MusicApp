package com.hhs.musicwiki.apiService

import com.hhs.musicwiki.albumDetailScreen.dataModels.Album
import com.hhs.musicwiki.artistDetailScreen.dataModels.Artist
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopAlbumModels.ArtistTopAlbums
import com.hhs.musicwiki.artistDetailScreen.dataModels.ArtistTopTracksModels.ArtistTopTracks
import com.hhs.musicwiki.genreDetailScreen.dataModels.AlbumModel.AllAlbums
import com.hhs.musicwiki.genreDetailScreen.dataModels.ArtistModel.AllArtists
import com.hhs.musicwiki.genreDetailScreen.dataModels.TrackModel.AllTracks
import com.hhs.musicwiki.genreDetailScreen.models.GenreInfo
import com.hhs.musicwiki.homeScreen.Models.AllGenres
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("2.0/")
    fun getGenres(
        @Query("method") allGenres: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<AllGenres?>?

    @GET("2.0/")
    fun getGenreInfo(
        @Query("method") genreInfo: String?,
        @Query("tag") genre: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<GenreInfo?>?

    @GET("2.0/")
    fun getAlbums(
        @Query("method") allAlbums: String?,
        @Query("tag") genre: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<AllAlbums?>?

    @GET("2.0/")
    fun getArtists(
        @Query("method") allArtists: String?,
        @Query("tag") genre: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<AllArtists?>?

    @GET("2.0/")
    fun getTracks(
        @Query("method") allTracks: String?,
        @Query("tag") genre: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<AllTracks?>?

    @GET("2.0/")
    fun getAlbumInfo(
        @Query("method") albumInfo: String?,
        @Query("api_key") apiKey: String?,
        @Query("artist") artist: String?,
        @Query("album") album: String?,
        @Query("format") json: String?
    ): Call<Album?>?

    @GET("2.0/")
    fun getArtistInfo(
        @Query("method") artistInfo: String?,
        @Query("artist") artist: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<Artist?>?

    @GET("2.0/")
    fun getArtistTopTracks(
        @Query("method") artistTopTracks: String?,
        @Query("artist") artist: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<ArtistTopTracks?>?

    @GET("2.0/")
    fun getArtistTopAlbums(
        @Query("method") artistTopAlbums: String?,
        @Query("artist") artist: String?,
        @Query("api_key") apiKey: String?,
        @Query("format") json: String?
    ): Call<ArtistTopAlbums?>?
}