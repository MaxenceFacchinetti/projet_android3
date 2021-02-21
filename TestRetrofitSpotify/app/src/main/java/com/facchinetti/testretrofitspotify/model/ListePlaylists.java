package com.facchinetti.testretrofitspotify.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListePlaylists {

    @SerializedName("items")
    @Expose
    private List<Playlist> listePlaylists = null;
    @SerializedName("limit")
    @Expose
    private Integer limit;
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("total")
    @Expose
    private Integer total;

    public List<Playlist> getListePlaylists() {
        return listePlaylists;
    }

    public void setListePlaylists(List<Playlist> listePlaylists) {
        this.listePlaylists = listePlaylists;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String toString(){
        String msg = "";
        for(Playlist p : listePlaylists){
            msg += p.getName() + "-" + p.getDescription() + " ";
        }
        return msg;
    }

}