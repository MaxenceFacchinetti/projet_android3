package com.facchinetti.testretrofitspotify.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.ImageSpotify;
import com.facchinetti.testretrofitspotify.model.PlaylistWithTrack;
import com.facchinetti.testretrofitspotify.model.Track;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    public APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();
    private TextView textViewNomPlaylistActPlaylist;
    private ImageView imageViewPlaylistActPlaylist;
    private ListView listTracksPlaylist;
    private String idPlaylist;
    private TrackAdapter adapter;
    public static final int REQUEST_CODE_PLAYLIST_ACTIVITY = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        genererPlaylistActivity();

    }

    @Override
    protected void onResume() {
        super.onResume();
        apiSpotifyManager.callGetPlaylist(this,idPlaylist);
        apiSpotifyManager.callGetTracksPlaylist(this,idPlaylist);
    }

    private void genererPlaylistActivity(){
        textViewNomPlaylistActPlaylist = findViewById(R.id.textViewNomPlaylistActPlaylist);
        imageViewPlaylistActPlaylist = findViewById(R.id.imageViewPlaylistActPlaylist);
        listTracksPlaylist = findViewById(R.id.listTracksPlaylist);
        idPlaylist = getIntent().getStringExtra("id");
        apiSpotifyManager.callGetPlaylist(this,idPlaylist);
        apiSpotifyManager.callGetTracksPlaylist(this,idPlaylist);
    }

    public void modifyNomAndImage(String nom, ImageSpotify img){
        textViewNomPlaylistActPlaylist.setText(nom);
        if(img != null)
        Picasso.get().load(img.getUrl()).into(imageViewPlaylistActPlaylist);
    }

    public void setListeTrack(PlaylistWithTrack playlistWithTrack){
        List<Track> listTracks = new ArrayList<Track>();
        for(PlaylistWithTrack.Tracks tracks : playlistWithTrack.getItems()){
            listTracks.add(tracks.getTrack());
        }
        adapter = new TrackAdapter(this,listTracks);
        listTracksPlaylist.setAdapter(adapter);
    }

    public void onClickRetour(View v){
        Intent returnIntent = new Intent();
        setResult(0);
        finish();
    }

    public void onClickAddTrack(View v){
        Intent intentAddTrack = new Intent(this,AjouterItemPlaylistActivity.class);
        intentAddTrack.putExtra("typeRecherche","TITRE");
        intentAddTrack.putExtra("idPlaylist",idPlaylist);
        startActivityForResult(intentAddTrack,REQUEST_CODE_PLAYLIST_ACTIVITY);
    }

    public void onClickAddAlbum(View v){
        Intent intentAddAlbum = new Intent(this,AjouterItemPlaylistActivity.class);
        intentAddAlbum.putExtra("typeRecherche","ALBUM");
        intentAddAlbum.putExtra("idPlaylist",idPlaylist);
        startActivityForResult(intentAddAlbum,REQUEST_CODE_PLAYLIST_ACTIVITY);
    }

    public void onClickAddArtist(View v){
        Intent intentAddArtist = new Intent(this,AjouterItemPlaylistActivity.class);
        intentAddArtist.putExtra("typeRecherche","ARTISTE");
        intentAddArtist.putExtra("idPlaylist",idPlaylist);
        startActivityForResult(intentAddArtist,REQUEST_CODE_PLAYLIST_ACTIVITY);
    }

}