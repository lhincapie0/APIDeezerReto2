package com.example.apideezer_reto2.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.model.Track;

import java.util.ArrayList;

public class TrackAdapter extends BaseAdapter {

    private ArrayList<Track> tracks;

    public TrackAdapter(){
        tracks = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return tracks.size();
    }

    @Override
    public Object getItem(int position) {
        return tracks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.track_adapter, null);
        ImageView trackIV = view.findViewById(R.id.trackIV);
        TextView trackNameTV = view.findViewById(R.id.trackNameTV);
        TextView trackArtistTV = view.findViewById(R.id.trackArtistTV);
        TextView trackReleaseDateTV = view.findViewById(R.id.trackReleaseDateTV);

        trackNameTV.setText(tracks.get(position).getTitle());
        trackArtistTV.setText(tracks.get(position).getArtist().getName());
        trackReleaseDateTV.setText(tracks.get(position).getRelease_date().toString().split(" ")[5]);
        Glide.with(view).load(tracks.get(position).getAlbum().getCover()).centerCrop().into(trackIV);

        return view;
    }


    public void setTracks(ArrayList<Track> tracks){
        this.tracks=tracks;
        Log.e(">>>>>>>>>>>", "ENTRO");

        notifyDataSetChanged();
    }


    public void addTrack(Track track)
    {
        tracks.add(track);
        this.notifyDataSetChanged();

    }
}
