package com.example.apideezer_reto2.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.control.TrackDetailsController;

public class TrackDetailsActivity extends AppCompatActivity {

    private TextView trackNameTV, trackArtistTV, trackAlbumTV, trackLengthTV;
    private ImageView trackIV;
    private Button escucharBtn;

    private TrackDetailsController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_details);

        trackNameTV = findViewById(R.id.nameTrackTV);
        trackArtistTV = findViewById(R.id.artistTrackIV);
        trackAlbumTV = findViewById(R.id.albumTrackTV);
        trackLengthTV = findViewById(R.id.trackLengthTV);
        trackIV = findViewById(R.id.imageTrackIV);
        escucharBtn = findViewById(R.id.escucharBtn);
        controller = new TrackDetailsController(this);
    }

    public TextView getTrackNameTV() {
        return trackNameTV;
    }

    public TextView getTrackArtistTV() {
        return trackArtistTV;
    }

    public TextView getTrackAlbumTV() {
        return trackAlbumTV;
    }

    public TextView getTrackLengthTV() {
        return trackLengthTV;
    }

    public ImageView getTrackIV() {
        return trackIV;
    }

    public Button getEscucharBtn() {
        return escucharBtn;
    }
}
