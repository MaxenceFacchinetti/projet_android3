package com.facchinetti.testretrofitspotify.networkmanager;

import android.Manifest;
import android.content.Intent;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facchinetti.testretrofitspotify.MainActivity;
import com.facchinetti.testretrofitspotify.model.Album;
import com.facchinetti.testretrofitspotify.model.AlbumTracks;
import com.facchinetti.testretrofitspotify.model.Artist;
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
import com.facchinetti.testretrofitspotify.view.AjouterItemPlaylistActivity;
import com.facchinetti.testretrofitspotify.view.AjouterPlaylistActivity;
import com.facchinetti.testretrofitspotify.view.PlaylistActivity;
import com.facchinetti.testretrofitspotify.view.PlaylistAdapter;
import com.facchinetti.testretrofitspotify.view.TopActivity;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Authenticator;
import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;

public class APISpotifyManager {

    private static APISpotifyManager instance =  null;

    private Retrofit retrofit;
    private SpotifyService service;
    private Track track;
    private static String authorizationCode;
    private static User me;
    private static ListePlaylists listePlaylists;
    private static Playlist playlist;
    private static PlaylistWithTrack tracksPlaylist;
    private static RechercheTracks rechercheTracks;
    private static RechercheAlbums rechercheAlbums;
    private static RechercheArtists rechercheArtists;
    private static AlbumTracks albumTracks;
    private static ArtistTopTracks artistTopTracks;
    private static TopTracks topTracks;
    private static TopArtists topArtists;

    public TopTracks getTopTracks() {
        return topTracks;
    }

    public TopArtists getTopArtists() {
        return topArtists;
    }

    private APISpotifyManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        service = retrofit.create(SpotifyService.class);

    }

    public static ListePlaylists getListePlaylists() {
        return listePlaylists;
    }

    public static Playlist getPlaylist() {
        return playlist;
    }

    public void setMe(User me){this.me = me;}

    public User getMe() {return me;}

    public RechercheTracks getRechercheTracks() {
        return rechercheTracks;
    }

    public RechercheAlbums getRechercheAlbums() {
        return rechercheAlbums;
    }

    public RechercheArtists getRechercheArtists() {
        return rechercheArtists;
    }

    public Track getTrack() {
        return track;
    }

    public void callGetTrack(final AppCompatActivity activity){

        Call<Track> call = service.getTrack("6OQdNlyLiYMmAJ9ZEdUmal",authorizationCode);

        call.enqueue(new Callback<Track>() {
            @Override
            public void onResponse(Call<Track> call, Response<Track> response) {
                track = response.body();
                Log.d("maxe",track.toString());
                //activity.finish();
            }

            @Override
            public void onFailure(Call<Track> call, Throwable throwable) {
                Log.d("maxe","on failure");
            }
        });

    }

    public void callGetMe(final MainActivity activity){

        Call<User> call = service.getMe(authorizationCode);

        call.enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                setMe(response.body());
                activity.textViewMe.setText(me.getDisplayName());
                Picasso.get().load(me.getListeImages().get(0).getUrl()).into(activity.imageViewMe);
            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {
                Log.d("maxe","on failure");
            }
        });

    }

    public void callGetListePlaylists(final MainActivity activity){

        Call<ListePlaylists> call = service.getListePlaylists(authorizationCode);

        call.enqueue(new Callback<ListePlaylists>() {
            @Override
            public void onResponse(Call<ListePlaylists> call, Response<ListePlaylists> response) {
                listePlaylists = response.body();
                activity.listViewListePlaylists.setAdapter(new PlaylistAdapter(activity,listePlaylists.getListePlaylists()));
            }

            @Override
            public void onFailure(Call<ListePlaylists> call, Throwable throwable) {

            }
        });

    }

    public void callGetPlaylist(final PlaylistActivity activity, String id){
        Call<Playlist> call = service.getPlaylist(id,authorizationCode);

        call.enqueue(new Callback<Playlist>() {
            @Override
            public void onResponse(Call<Playlist> call, Response<Playlist> response) {
                playlist = response.body();
                try{
                    activity.modifyNomAndImage(playlist.getName(),playlist.getImages().get(0));
                }catch(Exception e){
                    activity.modifyNomAndImage(playlist.getName(),null);
                }

            }

            @Override
            public void onFailure(Call<Playlist> call, Throwable throwable) {

            }
        });
    }

    public void callGetTracksPlaylist(final PlaylistActivity activity, String id){
        Call<PlaylistWithTrack> call = service.getTracksPlaylist(id,authorizationCode);

        call.enqueue(new Callback<PlaylistWithTrack>() {
            @Override
            public void onResponse(Call<PlaylistWithTrack> call, Response<PlaylistWithTrack> response) {
                tracksPlaylist = response.body();
                activity.setListeTrack(tracksPlaylist);
            }

            @Override
            public void onFailure(Call<PlaylistWithTrack> call, Throwable throwable) {

            }
        });
    }

    public void callAddPlaylist(final AjouterPlaylistActivity activity){

        String bodyJSON = "{\"name\":\"" + activity.editTextNomPlaylist.getText().toString() + "\", \"description\":\"\",\"public\":true}";

        RequestBody body = RequestBody.create(MediaType.parse("application/json"),bodyJSON);

        Call<Void> call2 = service.addPlaylist(getMe().getId(),body,authorizationCode);

        call2.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

                Log.d("maxe",response.code() + "");
                activity.finishActivity(MainActivity.REQUEST_CODE_AJOUTER_PLAYLIST);
                activity.finish();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {
                Log.d("maxe","fail ajout");
            }
        });

    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public static APISpotifyManager getInstance(){
        if(instance == null){
            return new APISpotifyManager();
        }
        return instance;
    }

    public void callGetRechercheTracks(final AjouterItemPlaylistActivity activity, String recherche){
        Call<RechercheTracks> call = service.getRechercheTracks(recherche,authorizationCode,"track","50","0");

        call.enqueue(new Callback<RechercheTracks>() {
            @Override
            public void onResponse(Call<RechercheTracks> call, Response<RechercheTracks> response) {
                rechercheTracks = response.body();
                activity.rafraichirTracks();
            }

            @Override
            public void onFailure(Call<RechercheTracks> call, Throwable throwable) {

            }
        });
    }

    public void callGetRechercheAlbums(final AjouterItemPlaylistActivity activity, String recherche){
        Call<RechercheAlbums> call = service.getRechercheAlbums(recherche,authorizationCode,"album","50","0");

        call.enqueue(new Callback<RechercheAlbums>() {
            @Override
            public void onResponse(Call<RechercheAlbums> call, Response<RechercheAlbums> response) {
                rechercheAlbums = response.body();
                activity.rafraichirAlbums();
            }

            @Override
            public void onFailure(Call<RechercheAlbums> call, Throwable throwable) {

            }
        });
    }

    public void callGetRechercheArtists(final AjouterItemPlaylistActivity activity, String recherche){
        Call<RechercheArtists> call = service.getRechercheArtists(recherche,authorizationCode,"artist","50","0");

        call.enqueue(new Callback<RechercheArtists>() {
            @Override
            public void onResponse(Call<RechercheArtists> call, Response<RechercheArtists> response) {
                rechercheArtists = response.body();
                activity.rafraichirArtists();
            }

            @Override
            public void onFailure(Call<RechercheArtists> call, Throwable throwable) {

            }
        });
    }

    public void callAddTracksToPlaylist(String idPlaylist, String uris){
        Call<Void> call = service.addTracksToPlaylist(idPlaylist,uris,authorizationCode);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable throwable) {

            }
        });
    }

    public void callGetAlbumTracks(String idAlbum, final String idPlaylist){
        Call<AlbumTracks> call = service.getAlbumTracks(idAlbum,authorizationCode,"50","0");

        call.enqueue(new Callback<AlbumTracks>() {
            @Override
            public void onResponse(Call<AlbumTracks> call, Response<AlbumTracks> response) {
                albumTracks = response.body();
                String urisTracks = "";
                for(Track t : albumTracks.getItems()){
                    urisTracks += t.getUri() + ",";
                }

                if(!urisTracks.isEmpty()){
                    callAddTracksToPlaylist(idPlaylist,urisTracks);
                }
            }

            @Override
            public void onFailure(Call<AlbumTracks> call, Throwable throwable) {

            }
        });
    }

    public void callGetArtistTopTracks(String idArtist, final String idPlaylist){
        Call<ArtistTopTracks> call = service.getArtistTopTracks(idArtist,authorizationCode,"FR");

        call.enqueue(new Callback<ArtistTopTracks>() {
            @Override
            public void onResponse(Call<ArtistTopTracks> call, Response<ArtistTopTracks> response) {
                artistTopTracks = response.body();
                String urisTracks = "";
                for(Track t : artistTopTracks.getTracks()){
                    urisTracks += t.getUri() + ",";
                }

                if(!urisTracks.isEmpty()){
                    callAddTracksToPlaylist(idPlaylist,urisTracks);
                }
            }

            @Override
            public void onFailure(Call<ArtistTopTracks> call, Throwable throwable) {

            }
        });
    }

    public void callGetTopTracks(final TopActivity activity){
        Call<TopTracks> call = service.getTopTracks("tracks",authorizationCode,"50","0");

        call.enqueue(new Callback<TopTracks>() {
            @Override
            public void onResponse(Call<TopTracks> call, Response<TopTracks> response) {
                topTracks = response.body();
                activity.rafraichirTracks();
            }

            @Override
            public void onFailure(Call<TopTracks> call, Throwable throwable) {

            }
        });
    }

    public void callGetTopArtists(final TopActivity activity){
        Call<TopArtists> call = service.getTopArtists("artists",authorizationCode,"50","0");

        call.enqueue(new Callback<TopArtists>() {
            @Override
            public void onResponse(Call<TopArtists> call, Response<TopArtists> response) {
                topArtists = response.body();
                activity.rafraichirArtists();
            }

            @Override
            public void onFailure(Call<TopArtists> call, Throwable throwable) {

            }
        });
    }

}
