package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {

    @SerializedName("display_name")
    @Expose
    private String displayName;
    @SerializedName("images")
    @Expose
    private List<ImageSpotify> listeImages;
    @SerializedName("id")
    @Expose
    private String id;

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public List<ImageSpotify> getListeImages() {
        return listeImages;
    }

    public void setListeImages(List<ImageSpotify> listeImages) {
        this.listeImages = listeImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}