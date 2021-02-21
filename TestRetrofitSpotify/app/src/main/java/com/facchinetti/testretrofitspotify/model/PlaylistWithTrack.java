package com.facchinetti.testretrofitspotify.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlaylistWithTrack {

    @Override
    public String toString() {
        return "PlaylistWithTrack{" +
                "items=" + items +
                '}';
    }

    @SerializedName("items")
    @Expose
    private List<Tracks> items = null;

    public List<Tracks> getItems() {
        return items;
    }

    public class Tracks{

        @Override
        public String toString() {
            return "Tracks{" +
                    "track=" + track +
                    '}';
        }

        @SerializedName("track")
        @Expose
        private Track track;

        public Track getTrack() {
            return track;
        }
    }

}
