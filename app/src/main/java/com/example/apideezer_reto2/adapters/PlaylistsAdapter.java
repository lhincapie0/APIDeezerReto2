package com.example.apideezer_reto2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.model.Playlist;

import java.util.ArrayList;

public class PlaylistsAdapter extends BaseAdapter {

    private ArrayList<Playlist> playlists;

    public PlaylistsAdapter()
    {
        playlists = new ArrayList<>();
    }
    @Override
    public int getCount() {
        return playlists.size();
    }

    @Override
    public Object getItem(int position) {
        return playlists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.playlist_adapter, null);
        ImageView playListImage = view.findViewById(R.id.playlistIV);
        TextView playlistNameTV = view.findViewById(R.id.playlistNameTV);
        TextView userCreatorTV = view.findViewById(R.id.playlistUserTV);
        TextView no_itemsTV = view.findViewById(R.id.playlistNbTracksTV);

        playlistNameTV.setText(playlists.get(position).getTitle());
        userCreatorTV.setText(playlists.get(position).getUser().getName());
        no_itemsTV.setText(playlists.get(position).getNb_tracks()+"");
        Glide.with(view).load(playlists.get(position).getPicture()).centerCrop().into(playListImage);
        return view;
    }


    public void setPlaylists(ArrayList<Playlist> playlists){
        this.playlists=playlists;
        notifyDataSetChanged();
    }


    public void addPlaylist(Playlist playlist)
    {
        playlists.add(playlist);
        this.notifyDataSetChanged();

    }
}
