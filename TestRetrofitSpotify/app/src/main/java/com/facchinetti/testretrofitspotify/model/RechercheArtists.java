package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechercheArtists {

    @SerializedName("artists")
    @Expose
    private Artists artists;

    public Artists getArtists() {
        return artists;
    }

    public void setArtists(Artists artists) {
        this.artists = artists;
    }

    public class Artists{
        @SerializedName("items")
        @Expose
        private List<Artist> items = null;

        public List<Artist> getItems() {
            return items;
        }

        public void setItems(List<Artist> items) {
            this.items = items;
        }
    }

}
