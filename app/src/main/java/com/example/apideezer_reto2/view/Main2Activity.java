package com.example.apideezer_reto2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.control.Main2Controller;

public class Main2Activity extends AppCompatActivity {

    private EditText playlistNameET;
    private ImageView searchBut;
    private RecyclerView playlistsRV;
    private Main2Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        this.playlistNameET = findViewById(R.id.playlistNameET);
        this.searchBut = findViewById(R.id.searchBut);
        this.playlistsRV = findViewById(R.id.playlistsRV);
        controller = new Main2Controller(this);
    }

    public EditText getPlaylistNameET() {
        return playlistNameET;
    }

    public ImageView getSearchBut() {
        return searchBut;
    }

    public RecyclerView getPlaylistsRV() {
        return playlistsRV;
    }
}
