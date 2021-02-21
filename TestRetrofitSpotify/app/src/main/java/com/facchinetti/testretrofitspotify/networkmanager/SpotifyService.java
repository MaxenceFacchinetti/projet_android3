package com.facchinetti.testretrofitspotify.networkmanager;

import com.facchinetti.testretrofitspotify.model.AlbumTracks;
import com.facchinetti.testretrofitspotify.model.ArtistTopTracks;
import com.facchinetti.testretrofitspotify.model.ListePlaylists;
import com.facchinetti.testretrofitspotify.model.Playlist;
import com.facchinetti.testretrofitspotify.model.PlaylistWithTrack;
import com.facchinetti.testretrofitspotify.model.RechercheAlbums;
import com.facchinetti.testretrofitspotify.model.RechercheArtists;
import com.facchinetti.testretrofitspotify.model.RechercheTracks;
import com.facchinetti.testretrofitspotify.model.TopArtists;
import com.facchinetti.testretrofitspotify.model.TopTracks;
import com.facchinetti.testretrofitspotify.model.Track;
import com.facchinetti.testretrofitspotify.model.User;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SpotifyService {

    @GET("/v1/tracks/{id}")
    Call<Track> getTrack(@Path("id") String id, @Header("Authorization") String authorization);

    @GET("v1/me")
    Call<User> getMe(@Header("Authorization") String authorization);

    @GET("v1/me/playlists?limit=10&offset=0")
    Call<ListePlaylists> getListePlaylists(@Header("Authorization") String authorization);

    @POST("v1/users/{userId}/playlists")
    Call<Void> addPlaylist(@Path("userId") String userId, @Body RequestBody body, @Header("Authorization") String authorization);

    @GET("v1/playlists/{playlist_id}")
    Call <Playlist> getPlaylist(@Path("playlist_id") String playlistID, @Header("Authorization") String authorization);

    @GET("v1/playlists/{playlist_id}/tracks?")
    Call <PlaylistWithTrack> getTracksPlaylist(@Path("playlist_id") String playlistID, @Header("Authorization") String authorization);

    @GET("v1/search")
    Call <RechercheTracks> getRechercheTracks(@Query("q") String recherche, @Header("Authorization") String authorization, @Query("type") String type, @Query("limit") String limit, @Query("offset") String offset);

    @GET("v1/search")
    Call <RechercheAlbums> getRechercheAlbums(@Query("q") String recherche, @Header("Authorization") String authorization, @Query("type") String type, @Query("limit") String limit, @Query("offset") String offset);

    @POST("v1/playlists/{playlist_id}/tracks")
    Call<Void> addTracksToPlaylist(@Path("playlist_id") String playlistID, @Query("uris") String uris, @Header("Authorization") String authorization);

    @GET("v1/albums/{album_id}/tracks")
    Call <AlbumTracks> getAlbumTracks(@Path("album_id") String albumID, @Header("Authorization") String authorization, @Query("limit") String limit, @Query("offset") String offset);

    @GET("v1/artists/{id}/top-tracks")
    Call <ArtistTopTracks> getArtistTopTracks(@Path("id") String id, @Header("Authorization") String authorization, @Query("market") String market);

    @GET("v1/search")
    Call <RechercheArtists> getRechercheArtists(@Query("q") String recherche, @Header("Authorization") String authorization, @Query("type") String type, @Query("limit") String limit, @Query("offset") String offset);

    @GET("v1/me/top/{type}")
    Call <TopArtists> getTopArtists(@Path("type") String type, @Header("Authorization") String authorization, @Query("limit") String limit, @Query("offset") String offset);

    @GET("v1/me/top/{type}")
    Call <TopTracks> getTopTracks(@Path("type") String type, @Header("Authorization") String authorization, @Query("limit") String limit, @Query("offset") String offset);
}
