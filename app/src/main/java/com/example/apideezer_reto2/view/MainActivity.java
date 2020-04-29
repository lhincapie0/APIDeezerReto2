package com.example.apideezer_reto2.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.apideezer_reto2.R;
import com.example.apideezer_reto2.control.MainController;

public class MainActivity extends AppCompatActivity {

    private EditText playlistNameET;
    private ImageView searchBut;
    private RecyclerView playlistRV;
    private MainController controller;

    public EditText getPlaylistNameET() {
        return playlistNameET;
    }

    public ImageView getSearchBut() {
        return searchBut;
    }

    public RecyclerView getPlaylistRV() {
        return playlistRV;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.playlistNameET = findViewById(R.id.playlistNameET);
        this.searchBut = findViewById(R.id.searchBut);
        this.playlistRV = findViewById(R.id.playlistRV);
        this.controller = new MainController(this);
    }
}
