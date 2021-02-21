package com.facchinetti.testretrofitspotify.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RechercheTracks{

    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    public class Tracks {
        public List<Track> getItems() {
            return items;
        }

        public String getNext() {
            return next;
        }

        public String getPrevious() {
            return previous;
        }

        public void setItems(List<Track> items) {
            this.items = items;
        }

        public void setNext(String next) {
            this.next = next;
        }

        public void setPrevious(String previous) {
            this.previous = previous;
        }

        @SerializedName("items")
        @Expose
        private List<Track> items = null;
        @SerializedName("next")
        @Expose
        private String next;
        @SerializedName("previous")
        @Expose
        private String previous;

        @Override
        public String toString() {
            return "RechercheTracks{" +
                    "items=" + items.toString() +
                    '}';
        }
    }
}


