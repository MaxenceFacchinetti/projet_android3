package com.facchinetti.testretrofitspotify.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;

public class AjouterPlaylistActivity extends AppCompatActivity {

    public EditText editTextNomPlaylist;
    public Button buttonAjouter;
    public APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_playlist);

        editTextNomPlaylist = findViewById(R.id.editTextNomPlaylist);
        buttonAjouter = findViewById(R.id.buttonAjouter);

    }

    public void onClickButtonAjouter(View v){
        apiSpotifyManager.callAddPlaylist(this);
    }

}