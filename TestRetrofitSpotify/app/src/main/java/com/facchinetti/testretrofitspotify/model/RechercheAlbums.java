package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechercheAlbums {

    @SerializedName("albums")
    @Expose
    private Albums albums;

    public Albums getAlbums() {
        return albums;
    }

    public void setAlbums(Albums albums) {
        this.albums = albums;
    }

    public class Albums {
        @SerializedName("items")
        @Expose
        private List<Album> items = null;
        @SerializedName("next")
        @Expose
        private String next;
        @SerializedName("previous")
        @Expose
        private String previous;

        public void setItems(List<Album> items) {
            this.items = items;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        public List<Album> getItems() {
            return items;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }
    }


}
