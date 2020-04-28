package com.example.apideezer_reto2.model;

import com.example.apideezer_reto2.jsonStructures.TracksStructure;
import com.google.gson.annotations.SerializedName;

public class Playlist {

    private long id;
    private String title;
    private int nb_tracks;//Nb tracks in the playlist
    private String picture;
    private String description;
    private User user;// user	user object containing : id, name	object
    private TracksStructure tracks;

    public Playlist(long id, String title, int nb_tracks,  String picture, User user) {
        this.id = id;
        this.title = title;
        this.nb_tracks = nb_tracks;
        this.picture = picture;
        this.user = user;
    }
    public Playlist(long id, String title, int nb_tracks,  String picture, User user, String description, TracksStructure tracks) {
        this.id = id;
        this.title = title;
        this.nb_tracks = nb_tracks;
        this.picture = picture;
        this.user = user;
        this.tracks = tracks;
    }

    public TracksStructure getTracks() {
        return tracks;
    }

    public void setTracks(TracksStructure tracks) {
        this.tracks = tracks;
    }

    public Playlist() {
    }


    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNb_tracks() {
        return nb_tracks;
    }

    public void setNb_tracks(int nb_tracks) {
        this.nb_tracks = nb_tracks;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
