package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    MusicAdapter musicAdapter;
//    ArrayList<Music> music;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recyclermusic);
//        music= new ArrayList<>();
//        createMusicList();
        musicAdapter= new MusicAdapter(this);
        musicAdapter.setData(createMusicList());
        recyclerView.setAdapter(musicAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private List<Music> createMusicList() {
        List<Music> listMusic= new ArrayList<>();
        listMusic.add(new Music(R.drawable.imgmusic,"Bad Habits","숀 (SHAUN)",R.raw.badhabits));
        listMusic.add(new Music(R.drawable.imgmusic2,"THATS WHAT I WANT","Lil Nas X",R.raw.thatswhatiwant));
        listMusic.add(new Music(R.drawable.imgmusic3,"Pupmp it","The Black Eyed Peas",R.raw.pumpit));
        listMusic.add(new Music(R.drawable.imgmusic4,"Alone","Marshmello",R.raw.alone));
        listMusic.add(new Music(R.drawable.imgmusic5,"Venom","Eminem",R.raw.venom));
        return listMusic;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (musicAdapter != null){
            musicAdapter.release();
        }
    }
}