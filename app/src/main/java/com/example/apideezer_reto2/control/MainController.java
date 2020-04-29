package com.example.apideezer_reto2.control;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.adapters.PlaylistsAdapter;
import com.example.apideezer_reto2.jsonStructures.PlaylistsStructure;
import com.example.apideezer_reto2.model.Playlist;
import com.example.apideezer_reto2.util.Constants;
import com.example.apideezer_reto2.util.HTTPSWebUtilDomi;
import com.example.apideezer_reto2.view.MainActivity;
import com.example.apideezer_reto2.view.PlaylistDetailsActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class MainController implements View.OnClickListener , HTTPSWebUtilDomi.OnResponseListener {

    private MainActivity activity;
    private PlaylistsAdapter playlistsAdapter;
    private HTTPSWebUtilDomi utilDomi;
    private ArrayList<Playlist> playlists;

    public MainController(MainActivity activity)
    {
        this.activity = activity;
        activity.getSearchBut().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        activity.getPlaylistRV().setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activity.getPlaylistRV().setLayoutManager(linearLayoutManager);
        playlists = new ArrayList<>();
        playlistsAdapter = new PlaylistsAdapter( playlists);
        activity.getPlaylistRV().setAdapter(playlistsAdapter);
        activity.getPlaylistRV().addItemDecoration(new DividerItemDecoration(activity.getPlaylistRV().getContext()
                , DividerItemDecoration.VERTICAL));

        playlistsAdapter.setClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // int pos = activity.getPlaylistsRV().indexOfChild(v);
                int pos =activity.getPlaylistRV().getChildAdapterPosition(v);
                Log.e(">>>>>>>>>", pos + "");
                long playlistId  = ((Playlist)(playlistsAdapter.getItem(pos))).getId();
                Intent i = new Intent(activity, PlaylistDetailsActivity.class);
                i.putExtra("playlistId", playlistId);
                activity.startActivity(i);

            }
        });
    }



    @Override
    public void onClick(View v) {
        switch (v.getId())
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
    public void onResponse(int callbackID, String response) throws IOException {

        switch (callbackID) {
            case Constants.SEARCH_CALLBACK:
                Gson g = new Gson();
                PlaylistsStructure playlistsG = g.fromJson(response, PlaylistsStructure.class);
                playlists = playlistsG.getData();
                activity.runOnUiThread(
                        ()->
                        {
                            playlistsAdapter.setData(playlists);
                            playlistsAdapter.notifyDataSetChanged();
                        }
                );

        }

    }

}
