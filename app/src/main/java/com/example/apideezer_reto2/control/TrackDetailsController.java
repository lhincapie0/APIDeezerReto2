package com.example.apideezer_reto2.control;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.model.Track;
import com.example.apideezer_reto2.util.Constants;
import com.example.apideezer_reto2.util.HTTPSWebUtilDomi;
import com.example.apideezer_reto2.view.MainActivity;
import com.example.apideezer_reto2.view.TrackDetailsActivity;
import com.google.gson.Gson;

import java.io.IOException;

public class TrackDetailsController implements HTTPSWebUtilDomi.OnResponseListener, View.OnClickListener{

    private TrackDetailsActivity activity;
    private HTTPSWebUtilDomi utilDomi;
    private Track track;


    public TrackDetailsController(TrackDetailsActivity activity)
    {
        this.activity = activity;
        utilDomi = new HTTPSWebUtilDomi();
        utilDomi.setListener(this);
        loadTrackData();
        activity.getEscucharBtn().setOnClickListener(this);

    }

    private void loadTrackData() {
        {
            long trackId=(long)activity.getIntent().getExtras().get("trackId");
            String url="https://api.deezer.com/track/"+trackId;
            new Thread(
                    () -> {
                        utilDomi.GETrequest(Constants.TRACKDETAILS_CALLBACK,url);
                    }
            ).start();
        }
    }

    @Override
    public void onResponse(int callbackID, String response) throws IOException {
        switch (callbackID)
        {
            case Constants.TRACKDETAILS_CALLBACK:
            {
                Gson g = new Gson();
                track = g.fromJson(response, Track.class);
                int dur = track.getDuration();
                int mins = dur/60;
                int segs = dur - (mins*60);
                activity.runOnUiThread(
                        ()->{
                            activity.getTrackAlbumTV().setText("Album: "+track.getAlbum().getTitle());
                            activity.getTrackNameTV().setText(track.getTitle());
                            activity.getTrackArtistTV().setText("Artista: "+track.getArtist().getName());

                            activity.getTrackLengthTV().setText(mins+ ":"+ segs+"");
                            Glide.with(activity).load(track.getAlbum().getCover()).centerCrop().into(activity.getTrackIV());
                        }

                );


            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.escucharBtn:
            {
                Intent i = this.activity.getPackageManager().getLaunchIntentForPackage("deezer.android.app");
                Uri link = Uri.parse(track.getLink());
                if(i != null)
                {
                    i.setData(link);
                    activity.startActivity(i);
                }else
                {
                    Intent intent=new Intent(Intent.ACTION_VIEW);
                    intent.setData(link);
                    activity.startActivity(intent);
                }
                break;
            }
            case R.id.backIV:
            {
                    Intent i = new Intent(activity, MainActivity.class);
                    activity.startActivity(i);
                    break;
            }
        }

    }
}
