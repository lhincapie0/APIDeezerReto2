package com.example.apideezer_reto2.control;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.adapters.PlaylistsAdapter;
import com.example.apideezer_reto2.model.Playlist;
import com.example.apideezer_reto2.jsonStructures.PlaylistsStructure;
import com.example.apideezer_reto2.util.Constants;
import com.example.apideezer_reto2.util.HTTPSWebUtilDomi;
import com.example.apideezer_reto2.view.MainActivity;
import com.example.apideezer_reto2.view.PlaylistDetailsActivity;
import com.google.gson.Gson;

import java.util.ArrayList;

public class MainController implements View.OnClickListener, AdapterView.OnItemClickListener, HTTPSWebUtilDomi.OnResponseListener{

    private MainActivity activity;
    private PlaylistsAdapter playlistAdapter;
    private HTTPSWebUtilDomi utilDomi;
    public MainController(MainActivity activity)
    {
        this.activity = activity;
        activity.getSearchBut().setOnClickListener(this);
        playlistAdapter = new PlaylistsAdapter();
        activity.getPlaylistLV().setAdapter(playlistAdapter);
        activity.getPlaylistLV().setOnItemClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.searchBut:
                final String playlistName = activity.getPlaylistNameET().getText().toString();
                String url="https://api.deezer.com/search/playlist/?q="+playlistName+"";
                new Thread(
                        () -> {
                           utilDomi.GETrequest(Constants.SEARCH_CALLBACK,url);
                        }
                ).start();
                break;
        }
    }




    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String j = parent.getItemAtPosition(position).toString();
        long playlistId  = ((Playlist)(playlistAdapter.getItem(position))).getId();
        Intent i = new Intent(activity, PlaylistDetailsActivity.class);
        i.putExtra("playlistId", playlistId);
        activity.startActivity(i);
    }

    @Override
    public void onResponse(int callbackID, String response) {
        switch (callbackID) {
            case Constants.SEARCH_CALLBACK:
                Gson g = new Gson();
                PlaylistsStructure playlistsG = g.fromJson(response, PlaylistsStructure.class);
                ArrayList<Playlist> playlists = playlistsG.getData();

                    activity.runOnUiThread( ()-> {
                        playlistAdapter.setPlaylists(playlists);
                    } );

        }

    }
}
