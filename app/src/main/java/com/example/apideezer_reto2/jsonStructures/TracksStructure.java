package com.example.apideezer_reto2.jsonStructures;

import com.example.apideezer_reto2.model.Track;

import java.util.ArrayList;

public class TracksStructure {

    private ArrayList<Track> data;

    public TracksStructure(ArrayList<Track> data) {
        this.data = data;
    }

    public TracksStructure() {
    }

    public ArrayList<Track> getData() {
        return data;
    }

    public void setData(ArrayList<Track> data) {
        this.data = data;
    }
}
