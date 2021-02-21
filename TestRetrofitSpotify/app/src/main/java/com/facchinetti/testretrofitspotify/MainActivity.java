package com.facchinetti.testretrofitspotify;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;
import com.facchinetti.testretrofitspotify.view.AjouterPlaylistActivity;
import com.facchinetti.testretrofitspotify.view.TopActivity;
import com.spotify.sdk.android.auth.AuthorizationClient;
import com.spotify.sdk.android.auth.AuthorizationRequest;
import com.spotify.sdk.android.auth.AuthorizationResponse;

public class MainActivity extends AppCompatActivity {


    private String log;
    public static final int REQUEST_CODE_SPOTIFY = 999;
    public static final int REQUEST_CODE_AJOUTER_PLAYLIST = 1;
    public static final int REQUEST_CODE_CONSULTER_PLAYLIST = 2;
    private static final String REDIRECT_URI = "https://example.com/callback";
    private static final String CLIENT_ID = "5b7a4efa27d548e3bf4d7f706bb90722";
    private APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();
    public TextView textViewMe;
    public ImageView imageViewMe;
    public ListView listViewListePlaylists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewMe = findViewById(R.id.textViewMe);
        imageViewMe = findViewById(R.id.imageViewMe);
        listViewListePlaylists = findViewById(R.id.listViewListePlaylists);

        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN,REDIRECT_URI);
        builder.setScopes(new String[]{"streaming","playlist-modify-public","playlist-modify-private","user-top-read"});
        AuthorizationRequest request = builder.build();
        AuthorizationClient.openLoginActivity(this,REQUEST_CODE_SPOTIFY,request);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE_SPOTIFY){
            AuthorizationResponse response = AuthorizationClient.getResponse(resultCode,data);

            switch(response.getType()){
                case TOKEN:
                    apiSpotifyManager.setAuthorizationCode("Bearer " + response.getAccessToken());
                    apiSpotifyManager.callGetMe(this);
                    genererActivity();
                    break;
            }

        }

        if(requestCode == REQUEST_CODE_AJOUTER_PLAYLIST){
            genererActivity();
        }

        if(requestCode == REQUEST_CODE_CONSULTER_PLAYLIST && resultCode == 0){
            genererActivity();
        }
    }

    public void onClickButtonDeconnexion(View v){
        AuthorizationClient.clearCookies(this);

        AuthorizationRequest.Builder builder = new AuthorizationRequest.Builder(CLIENT_ID, AuthorizationResponse.Type.TOKEN,REDIRECT_URI);

        builder.setScopes(new String[]{"streaming"});
        AuthorizationRequest request = builder.build();

        AuthorizationClient.openLoginActivity(this,REQUEST_CODE_SPOTIFY,request);
    }

    public void onClickButtonAjouterPlaylist(View v){
        Intent ajouterPlaylistIntent = new Intent(this, AjouterPlaylistActivity.class);
        startActivityForResult(ajouterPlaylistIntent,REQUEST_CODE_AJOUTER_PLAYLIST);
    }

    public void genererActivity(){
        apiSpotifyManager.callGetListePlaylists(this);
    }

    public void onClickTopTracks(View v){
        Intent topTracks = new Intent(this, TopActivity.class);
        topTracks.putExtra("type","TRACKS");
        startActivity(topTracks);
    }

    public void onClickTopArtists(View v){
        Intent topTracks = new Intent(this,TopActivity.class);
        topTracks.putExtra("type","ARTISTS");
        startActivity(topTracks);
    }

}
