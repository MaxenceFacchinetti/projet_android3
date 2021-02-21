package com.facchinetti.testretrofitspotify.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.PlaylistWithTrack;
import com.facchinetti.testretrofitspotify.model.RechercheTracks;
import com.facchinetti.testretrofitspotify.model.Track;
import com.facchinetti.testretrofitspotify.networkmanager.APISpotifyManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RechercheTrackAdapter extends BaseAdapter {

    private List<Track> listeTracks;
    private Context context;
    private APISpotifyManager apiSpotifyManager = APISpotifyManager.getInstance();
    private String idPlaylist;

    public RechercheTrackAdapter(Context context, String idPlaylist, List<Track> listeTracks) {
        super();
        this.context = context;
        this.listeTracks = listeTracks;
        this.idPlaylist = idPlaylist;
    }

    @Override
    public int getCount() {
        return listeTracks.size();
    }

    @Override
    public Track getItem(int position) {
        return listeTracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        RechercheTrackAdapter.ViewHolder viewHolder;

        //if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_track,null);
            viewHolder = new RechercheTrackAdapter.ViewHolder();

            viewHolder.nomTrack = convertView.findViewById(R.id.textViewNomTrackAdapterTrack);
            viewHolder.iconeTrack = convertView.findViewById(R.id.imageViewIconeTrack);
            viewHolder.artist = convertView.findViewById(R.id.textViewArtisteAdapterTrack);
            viewHolder.addButton = convertView.findViewById(R.id.buttonAddTrackOrAlbum);

            viewHolder.addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String uriTrack = listeTracks.get(position).getUri();
                    apiSpotifyManager.callAddTracksToPlaylist(idPlaylist,uriTrack);
                    Toast.makeText(context,"Titre ajouté !",Toast.LENGTH_SHORT).show();
                }
            });

            try{
                Picasso.get().load(listeTracks.get(position).getAlbum().getImages().get(0).getUrl()).into(viewHolder.iconeTrack);

            }catch(Exception e){

            }

            viewHolder.nomTrack.setText(listeTracks.get(position).getName());
            String artistsLabel = listeTracks.get(position).getArtists().get(0).getName();

            for(int i = 1; i < listeTracks.get(position).getArtists().size(); i++){
                artistsLabel += ", " + listeTracks.get(position).getArtists().get(i).getName();
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