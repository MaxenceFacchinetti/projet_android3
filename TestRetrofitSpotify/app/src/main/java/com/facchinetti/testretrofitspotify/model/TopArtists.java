package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopArtists {
    public List<Artist> getItems() {
        return items;
    }

    public void setItems(List<Artist> items) {
        this.items = items;
    }

    @SerializedName("items")
    @Expose
    private List<Artist> items = null;
}
