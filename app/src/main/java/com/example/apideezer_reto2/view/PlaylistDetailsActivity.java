package com.example.apideezer_reto2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.control.PlaylistDetailsController;

public class PlaylistDetailsActivity extends AppCompatActivity {

    private TextView playlistNameTV, playlistDescriptionTV, playlistSongsTV;
    private ImageView playlistIV;
    private ListView tracksLV;
    private PlaylistDetailsController controller;
    private ImageView backIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_details);
        playlistNameTV = findViewById(R.id.trackNameTV);
        playlistDescriptionTV = findViewById(R.id.playlistDescriptionTV);
        playlistSongsTV = findViewById(R.id.playlistSongsTV);
        playlistIV = findViewById(R.id.playlistIV);
        tracksLV = findViewById(R.id.tracksLV);
        backIV = findViewById(R.id.backIV);
        controller = new PlaylistDetailsController(this);
    }

    public TextView getPlaylistNameTV() {
        return playlistNameTV;
    }

    public TextView getPlaylistDescriptionTV() {
        return playlistDescriptionTV;
    }

    public TextView getPlaylistSongsTV() {
        return playlistSongsTV;
    }

    public ImageView getBackIV() {
        return backIV;
    }


    public ImageView getPlaylistIV() {
        return playlistIV;
    }

    public ListView getTracksLV() {
        return tracksLV;
    }
}
