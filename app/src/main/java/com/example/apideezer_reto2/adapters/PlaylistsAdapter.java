package com.example.apideezer_reto2.adapters;

import android.util.Log;
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

public class PlaylistsAdapter extends RecyclerView.Adapter<PlaylistsAdapter.ViewHolder> {

    private ArrayList<Playlist> playlists;
    private View.OnClickListener mClickListener;


    public PlaylistsAdapter(ArrayList<Playlist> playlists)
    {
        this.playlists = playlists;
    }

    public void setClickListener(View.OnClickListener callback) {
        mClickListener = (View.OnClickListener) callback;
    }


    public void setData(ArrayList<Playlist> playlists)
    {
        this.playlists = playlists;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist_adapter, parent, false);
        ViewHolder holder = new ViewHolder(v);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onClick(view);
            }
        });
        return holder;
    }

    public Playlist getItem(int position)
    {
        return playlists.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.custom(playlists.get(position));
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
            view.setTag(this);

        }


        // Personalizamos un ViewHolder a partir de un lugar
        public void custom(Playlist playlist) {
            playlistNameTV.setText(playlist.getTitle());
            playlistUserTV.setText(playlist.getUser().getName());
            playlistNbTracksTV.setText(playlist.getNb_tracks()+"");
            Glide.with(view).load(playlist.getPicture()).centerCrop().into(playlistIV);
            Log.e(">>>>>>", "custom");


        }
    }
}
