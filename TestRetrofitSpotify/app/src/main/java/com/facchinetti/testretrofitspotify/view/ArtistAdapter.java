package com.facchinetti.testretrofitspotify.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facchinetti.testretrofitspotify.MainActivity;
import com.facchinetti.testretrofitspotify.R;
import com.facchinetti.testretrofitspotify.model.Artist;
import com.facchinetti.testretrofitspotify.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtistAdapter extends BaseAdapter {

    private List<Artist> listeArtist;
    private LayoutInflater layoutInflater;
    private Context context;

    public ArtistAdapter(Context context ,List<Artist> listeArtist) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
        this.listeArtist = listeArtist;
    }

    @Override
    public int getCount() {
        return listeArtist.size();
    }

    @Override
    public Artist getItem(int position) {
        return listeArtist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        //if(convertView == null){
        convertView = layoutInflater.inflate(R.layout.adapter_playlist_artiste,null);
        viewHolder = new ViewHolder();

        viewHolder.nomArtist = convertView.findViewById(R.id.textViewNomPlaylist);
        viewHolder.iconeArtist = convertView.findViewById(R.id.imageViewIconePlaylist);
        viewHolder.addButton = convertView.findViewById(R.id.buttonAddArtist);

        viewHolder.addButton.setVisibility(View.INVISIBLE);

        try{
            Picasso.get().load(listeArtist.get(position).getImages().get(0).getUrl()).into(viewHolder.iconeArtist);

        }catch(Exception e){

        }

        try{
            viewHolder.nomArtist.setText(listeArtist.get(position).getName());
        }
        catch(Exception e){

        }

        //}

        return convertView;
    }

    static class ViewHolder{
        ImageView iconeArtist;
        TextView nomArtist;
        Button addButton;
    }

}
