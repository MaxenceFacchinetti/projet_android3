package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TopTracks {
    public List<Track> getItems() {
        return items;
    }

    public void setItems(List<Track> items) {
        this.items = items;
    }

    @SerializedName("items")
    @Expose
    private List<Track> items = null;
}