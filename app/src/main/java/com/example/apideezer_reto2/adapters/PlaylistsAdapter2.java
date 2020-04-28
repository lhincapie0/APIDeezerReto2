package com.example.apideezer_reto2.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.model.Playlist;

import java.util.ArrayList;

public class PlaylistsAdapter2 extends RecyclerView.Adapter<PlaylistsAdapter2.ViewHolder> {

    private ArrayList<Playlist> playlists;
    private Context context;

    public PlaylistsAdapter2(Context context, ArrayList<Playlist> playlists)
    {
        this.playlists = playlists;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_adapter, parent, false);
        return new ViewHolder(v);}

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Playlist playlist = playlists.get(position);
        holder.custom(playlist);
    }

    @Override
    public int getItemCount() {

        return playlists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView playlistNameTV, playlistUserTV, playlistNbTracksTV;
        private ImageView playlistIV;
        private View view;

        public ViewHolder(View view) {
            super(view);
            this. view = view;
            playlistNameTV = view.findViewById(R.id.playlistNameTV);
            playlistUserTV = view.findViewById(R.id.playlistUserTV);
            playlistNbTracksTV = view.findViewById(R.id.playlistNbTracksTV);
            playlistIV = view.findViewById(R.id.playlistIV);

        }
        // Personalizamos un ViewHolder a partir de un lugar
        public void custom(Playlist playlist) {
            playlistNameTV.setText(playlist.getTitle());
            playlistUserTV.setText(playlist.getUser().getName());
            playlistNbTracksTV.setText(playlist.getNb_tracks()+"");
            Glide.with(view).load(playlist.getPicture()).centerCrop().into(playlistIV);

        }
    }
}
