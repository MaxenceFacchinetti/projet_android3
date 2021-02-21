package com.facchinetti.testretrofitspotify.view;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.Album;
import com.facchinetti.testretrofitspotify.model.Track;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RechercheAlbumAdapter extends BaseAdapter {

    private List<Album> listeAlbums;
    private Context context;
    private String idPlaylist;
    private APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();

    public RechercheAlbumAdapter(Context context, String idPlaylist, List<Album> listeAlbums) {
        super();
        this.context = context;
        this.listeAlbums = listeAlbums;
        this.idPlaylist = idPlaylist;
    }

    @Override
    public int getCount() {
        return listeAlbums.size();
    }

    @Override
    public Album getItem(int position) {
        return listeAlbums.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        RechercheAlbumAdapter.ViewHolder viewHolder;

        //if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_track,null);
            viewHolder = new RechercheAlbumAdapter.ViewHolder();

            viewHolder.nomTrack = convertView.findViewById(R.id.textViewNomTrackAdapterTrack);
            viewHolder.iconeTrack = convertView.findViewById(R.id.imageViewIconeTrack);
            viewHolder.artist = convertView.findViewById(R.id.textViewArtisteAdapterTrack);
            viewHolder.addButton = convertView.findViewById(R.id.buttonAddTrackOrAlbum);

            viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String idAlbum = listeAlbums.get(position).getId();
                    apiSpotifyManager.callGetAlbumTracks(idAlbum,idPlaylist);
                    Toast.makeText(context,"Album ajouté !",Toast.LENGTH_SHORT).show();
                }
            });

            try{
                Picasso.get().load(listeAlbums.get(position).getImages().get(0).getUrl()).into(viewHolder.iconeTrack);

            }catch(Exception e){

            }

            viewHolder.nomTrack.setText(listeAlbums.get(position).getName());
            String artistsLabel = listeAlbums.get(position).getArtists().get(0).getName();

            for(int i = 1; i < listeAlbums.get(position).getArtists().size(); i++){
                artistsLabel += ", " + listeAlbums.get(position).getArtists().get(i).getName();
            }

            viewHolder.artist.setText(artistsLabel);

        //}

        return convertView;
    }

    static class ViewHolder{
        ImageView iconeTrack;
        TextView nomTrack;
        TextView artist;
        Button addButton;
    }
}