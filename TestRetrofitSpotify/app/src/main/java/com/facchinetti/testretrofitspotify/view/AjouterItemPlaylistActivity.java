package com.facchinetti.testretrofitspotify.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.TypeRecherche;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;

public class AjouterItemPlaylistActivity extends AppCompatActivity {

    private TypeRecherche typeRecherche;
    private EditText editTextRechercheItem;
    private APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();
    private ListView listViewItem;
    private String idPlaylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_item_playlist);

        if(getIntent().getStringExtra("typeRecherche").equals("ALBUM")){
            typeRecherche = TypeRecherche.ALBUM;
        }
        else if(getIntent().getStringExtra("typeRecherche").equals("ARTISTE")){
            typeRecherche = TypeRecherche.ARTISTE;
        }
        else{
            typeRecherche = TypeRecherche.TITRE;
        }

        editTextRechercheItem = findViewById(R.id.editTextRechercheItem);

        switch(typeRecherche){
            case ALBUM:
                editTextRechercheItem.setHint("Rechercher un album");
                break;
            case TITRE:
                editTextRechercheItem.setHint("Rechercher un titre");
                break;
            case ARTISTE:
                editTextRechercheItem.setHint("Rechercher un artiste");
                break;
            default:
                editTextRechercheItem.setHint("Rechercher un titre");
                typeRecherche = TypeRecherche.TITRE;
                break;
        }

        listViewItem = findViewById(R.id.listViewItem);
        idPlaylist = getIntent().getStringExtra("idPlaylist");

    }

    public void onClickRecherche(View v){
        switch(typeRecherche){
            case ALBUM:
                apiSpotifyManager.callGetRechercheAlbums(this,editTextRechercheItem.getText().toString());
                break;
            case TITRE:
                apiSpotifyManager.callGetRechercheTracks(this,editTextRechercheItem.getText().toString());
                break;
            case ARTISTE:
                apiSpotifyManager.callGetRechercheArtists(this,editTextRechercheItem.getText().toString());
                break;
        }
    }

    public void rafraichirTracks(){
        listViewItem.setAdapter(new RechercheTrackAdapter(this,idPlaylist,apiSpotifyManager.getRechercheTracks().getTracks().getItems()));
    }

    public void rafraichirAlbums(){
        listViewItem.setAdapter(new RechercheAlbumAdapter(this,idPlaylist,apiSpotifyManager.getRechercheAlbums().getAlbums().getItems()));
    }

    public void rafraichirArtists(){
        listViewItem.setAdapter(new RechercheArtistsAdapter(this,idPlaylist,apiSpotifyManager.getRechercheArtists().getArtists().getItems()));
    }

}