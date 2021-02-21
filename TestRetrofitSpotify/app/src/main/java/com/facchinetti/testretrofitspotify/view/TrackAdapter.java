package com.facchinetti.testretrofitspotify.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.PlaylistWithTrack;
import com.facchinetti.testretrofitspotify.model.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrackAdapter extends BaseAdapter {

    private List<Track> listeTracks;
    private Context context;

    public TrackAdapter(Context context, List<Track> listeTracks) {
        super();
        this.context = context;
        this.listeTracks = listeTracks;
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
        TrackAdapter.ViewHolder viewHolder;

        //if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_track,null);
            viewHolder = new TrackAdapter.ViewHolder();

            viewHolder.nomTrack = convertView.findViewById(R.id.textViewNomTrackAdapterTrack);
            viewHolder.iconeTrack = convertView.findViewById(R.id.imageViewIconeTrack);
            viewHolder.artist = convertView.findViewById(R.id.textViewArtisteAdapterTrack);
            viewHolder.buttonAdd = convertView.findViewById(R.id.buttonAddTrackOrAlbum);
            viewHolder.buttonAdd.setVisibility(View.INVISIBLE);
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
        Button buttonAdd;
    }
}