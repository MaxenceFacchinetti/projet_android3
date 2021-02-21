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
import com.facchinetti.testretrofitspotify.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends BaseAdapter {

    private List<Playlist> listePlaylist;
    private LayoutInflater layoutInflater;
    private Context context;
    private MainActivity activity;

    public PlaylistAdapter(MainActivity activity,List<Playlist> listePlaylist) {
        this.activity = activity;
        this.context = activity.getApplicationContext();
        this.layoutInflater = LayoutInflater.from(context);
        this.listePlaylist = listePlaylist;
    }

    @Override
    public int getCount() {
        return listePlaylist.size();
    }

    @Override
    public Playlist getItem(int position) {
        return listePlaylist.get(position);
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

            viewHolder.nomPlaylist = convertView.findViewById(R.id.textViewNomPlaylist);
            viewHolder.iconePlaylist = convertView.findViewById(R.id.imageViewIconePlaylist);
            viewHolder.addButton = convertView.findViewById(R.id.buttonAddArtist);

            viewHolder.addButton.setVisibility(View.INVISIBLE);

            try{
                Picasso.get().load(listePlaylist.get(position).getImages().get(0).getUrl()).into(viewHolder.iconePlaylist);

            }catch(Exception e){

            }

            try{
                viewHolder.nomPlaylist.setText(listePlaylist.get(position).getName());
            }
            catch(Exception e){

            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intentPlaylist = new Intent(context,PlaylistActivity.class);
                    intentPlaylist.putExtra("id",listePlaylist.get(position).getId());
                    activity.startActivityForResult(intentPlaylist,activity.REQUEST_CODE_CONSULTER_PLAYLIST);

                }
            });

        //}

        return convertView;
    }

    static class ViewHolder{
        ImageView iconePlaylist;
        TextView nomPlaylist;
        Button addButton;
    }

}
