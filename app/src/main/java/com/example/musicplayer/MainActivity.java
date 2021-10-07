package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
    ArrayList<Music> music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recyclermusic);
        music= new ArrayList<>();
        createMusicList();
        musicAdapter= new MusicAdapter(this, music);
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void createMusicList() {
        music.add(new Music(R.drawable.imgmusic,"Bad Habits","숀 (SHAUN)"));
        music.add(new Music(R.drawable.imgmusic2,"THATS WHAT I WANT","Lil Nas X"));
        music.add(new Music(R.drawable.imgmusic3,"Pupmp it","The Black Eyed Peas"));
        music.add(new Music(R.drawable.imgmusic4,"Alone","Marshmello"));
        music.add(new Music(R.drawable.imgmusic5,"Venom","Eminem"));
    }
}