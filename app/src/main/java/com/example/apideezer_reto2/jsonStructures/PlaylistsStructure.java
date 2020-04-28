package com.example.apideezer_reto2.jsonStructures;

import com.example.apideezer_reto2.model.Playlist;

import java.util.ArrayList;
/*
The json file for the playlists search doesn´t come alone, so we have to extract it from the data, because it has to more elements that can´t be cast as a "Playlist" object
 */
public class PlaylistsStructure {


    ArrayList<Playlist> data;

    public PlaylistsStructure() {
    }

    public PlaylistsStructure(ArrayList<Playlist> data) {
        this.data = data;
    }

    public ArrayList<Playlist> getData() {
        return data;
    }

    public void setData(ArrayList<Playlist> data) {
        this.data = data;
    }
}
