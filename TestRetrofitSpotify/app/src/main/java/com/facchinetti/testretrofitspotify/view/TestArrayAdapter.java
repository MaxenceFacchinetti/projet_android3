package com.facchinetti.testretrofitspotify.view;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.facchinetti.testretrofitspotify.model.Track;

public class TestArrayAdapter extends ArrayAdapter<Track> {

    public TestArrayAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
