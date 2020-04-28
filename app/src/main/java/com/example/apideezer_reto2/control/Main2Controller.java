package com.example.apideezer_reto2.control;

import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.adapters.PlaylistsAdapter;
import com.example.apideezer_reto2.adapters.PlaylistsAdapter2;
import com.example.apideezer_reto2.jsonStructures.PlaylistsStructure;
import com.example.apideezer_reto2.model.Playlist;
import com.example.apideezer_reto2.util.Constants;
import com.example.apideezer_reto2.util.HTTPSWebUtilDomi;
import com.example.apideezer_reto2.view.Main2Activity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

public class Main2Controller implements View.OnClickListener , HTTPSWebUtilDomi.OnResponseListener{

    private Main2Activity activity;
    private HTTPSWebUtilDomi utilDomi;

    public Main2Controller(Main2Activity activity)
    {
        this.activity = activity;
        activity.getSearchBut().setOnClickListener(this);
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);

        activity.getPlaylistsRV().setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        activity.getPlaylistsRV().setLayoutManager(linearLayoutManager);

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
                ArrayList<Playlist> playlists = playlistsG.getData();

                PlaylistsAdapter2 playlistsAdapter2 = new PlaylistsAdapter2(activity,playlists);
                activity.runOnUiThread( ()-> {
                    activity.getPlaylistsRV().setAdapter(playlistsAdapter2);
                } );

        }

    }
}
