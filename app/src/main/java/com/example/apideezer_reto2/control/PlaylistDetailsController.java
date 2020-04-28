package com.example.apideezer_reto2.control;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.bumptech.glide.Glide;
import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.adapters.TrackAdapter;
import com.example.apideezer_reto2.model.Playlist;
import com.example.apideezer_reto2.model.Track;
import com.example.apideezer_reto2.util.Constants;
import com.example.apideezer_reto2.util.HTTPSWebUtilDomi;
import com.example.apideezer_reto2.view.MainActivity;
import com.example.apideezer_reto2.view.PlaylistDetailsActivity;
import com.example.apideezer_reto2.view.TrackDetailsActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class PlaylistDetailsController implements HTTPSWebUtilDomi.OnResponseListener , View.OnClickListener, AdapterView.OnItemClickListener{

    private PlaylistDetailsActivity activity;
    private HTTPSWebUtilDomi utilDomi;
    private TrackAdapter trackAdapter;
    private Playlist playlist;

    public PlaylistDetailsController(PlaylistDetailsActivity activity)
    {
        this.activity = activity;
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        activity.getBackIV().setOnClickListener(this);
        trackAdapter = new TrackAdapter();
        activity.getTracksLV().setAdapter(trackAdapter);
        activity.getTracksLV().setOnItemClickListener(this);
        loadPlaylistData();
    }


    public void loadPlaylistData()
    {
        long playlistId=(long)activity.getIntent().getExtras().get("playlistId");
        String url="https://api.deezer.com/playlist/"+playlistId;
        new Thread(
                () -> {
                    utilDomi.GETrequest(Constants.PLDETAILS_CALLBACK,url);
                }
        ).start();
    }
    @Override
    public void onResponse(int callbackID, String response) throws IOException {
        switch(callbackID)
        {
            case Constants.PLDETAILS_CALLBACK:
            {
                Gson g = new Gson();

                playlist = g.fromJson(response, Playlist.class);
                activity.runOnUiThread(()->{
                    activity.getPlaylistNameTV().setText(playlist.getTitle());
                    activity.getPlaylistDescriptionTV().setText(playlist.getDescription());
                    if(playlist.getDescription().equals(("")))
                    {
                        activity.getPlaylistDescriptionTV().setText("No hay descripción para esta lista");
                    }
                    activity.getPlaylistSongsTV().setText(" Número de tracks: "+playlist.getNb_tracks()+"");
                    Glide.with(activity).load(playlist.getPicture()).centerCrop().into(activity.getPlaylistIV());
                });
                loadTracks();
                break;
            }


            default:
                throw new IllegalStateException("Unexpected value: " + callbackID);
        }

    }


    public void loadTracks() throws IOException {
        Gson g = new Gson();

        ArrayList<Track> tracks = playlist.getTracks().getData();
        String url="https://api.deezer.com/track/";
        ArrayList<Track> tracks1 = new ArrayList<>();
        for(int i = 0; i<tracks.size();i++)
        {
            String json =  utilDomi.GETrequest( url+""+tracks.get(i).getId());
            Track track = g.fromJson(json, Track.class);
            activity.runOnUiThread(
                    () ->
                    {
                        trackAdapter.addTrack(track);

                    }
            );
        }


    }
    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.backIV:
                Intent i = new Intent(activity, MainActivity.class);
                activity.startActivity(i);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Track track = (Track) trackAdapter.getItem(position);
            Intent i = new Intent(activity, TrackDetailsActivity.class);
            i.putExtra("trackId", track.getId());
            activity.startActivity(i);
    }

}

