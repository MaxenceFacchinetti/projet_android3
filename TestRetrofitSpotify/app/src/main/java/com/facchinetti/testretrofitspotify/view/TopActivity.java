package com.facchinetti.testretrofitspotify.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.TypeRecherche;
import com.facchinetti.testretrofitspotify.model.TypeTop;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;
import com.facchinetti.testretrofitspotify.view.ArtistAdapter;
import com.facchinetti.testretrofitspotify.view.TrackAdapter;

public class TopActivity extends AppCompatActivity {

    private ListView listViewTop;
    private APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();
    private TypeTop typeTop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        listViewTop = findViewById(R.id.listViewTop);

        String type = getIntent().getStringExtra("type");

        switch(type){
            case "TRACKS":
                typeTop = TypeTop.TRACKS;
                break;
            case "ARTISTS":
                typeTop = TypeTop.ARTISTS;
                break;
        }

        switch(typeTop){
            case TRACKS:
                apiSpotifyManager.callGetTopTracks(this);
                break;
            case ARTISTS:
                apiSpotifyManager.callGetTopArtists(this);
                break;
        }


    }

    public void rafraichirArtists(){
        listViewTop.setAdapter(new ArtistAdapter(this,apiSpotifyManager.getTopArtists().getItems()));
    }

    public void rafraichirTracks(){
        listViewTop.setAdapter(new TrackAdapter(this,apiSpotifyManager.getTopTracks().getItems()));
    }
}