package com.example.musicplayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import service.MyService;

public class Music_Player extends AppCompatActivity {
    private ImageView imgBadHabits,imgClear,imgPlayOrPause,imgBack;
    private TextView tvNameSong,tvNameArtist;

    private Music mSong;
    private boolean isPlaying;

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if( bundle == null){
                return;
            }

            mSong = (Music) bundle.get("object_song");
            isPlaying = bundle.getBoolean("status_player");
            int actionMusic = bundle.getInt("action_music");

            handleLayoutMusic(actionMusic);
        }
    };

    private void handleLayoutMusic(int action) {

        switch (action){
            case MyService.ACTION_START:
                showInforSong();
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_PAUSE:
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_RESUME:
                setStatusButtonPlayOrPause();
                break;

            case MyService.ACTION_CLEAR:
                break;
        }
    }

    private void setStatusButtonPlayOrPause() {
        if (isPlaying){
            imgPlayOrPause.setImageResource(R.drawable.pause);
        } else{
            imgPlayOrPause.setImageResource(R.drawable.play);
        }
    }

    private void showInforSong() {
        if (mSong == null){
            return;
        }

        imgPlayOrPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPlaying) {
                    sendActionToService(MyService.ACTION_PAUSE);
                } else{
                    sendActionToService(MyService.ACTION_RESUME);
                }
            }
        });

//        imgClear.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendActionToService(MyService.ACTION_CLEAR);
//            }
//        });
    }

    private void sendActionToService(int action) {
        Intent intent = new Intent(this, MyService.class);
        intent.putExtra("action_music_service" , action);

        startService(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("send_data_to_activity"));

        Bundle bundle = getIntent().getExtras();
        if(bundle == null){
            return;
        }

        Music song = (Music) bundle.get("object_song");
        Intent intent = new Intent(this, MyService.class);
        intent.putExtras(bundle);

        imgBadHabits = findViewById(R.id.img_BadHabit);
        tvNameSong = findViewById(R.id.tv_nameSong);
        tvNameArtist =  findViewById(R.id.tv_nameArtist);
        imgBadHabits.setImageResource(song.getImgMusic());
        tvNameSong.setText(song.getTextSong());
        tvNameArtist.setText(song.getTextArtist());
//        imgClear = findViewById(R.id.img_clear);
        imgPlayOrPause = findViewById(R.id.imagePlayPause);
        if(isPlaying){
            sendActionToService(MyService.ACTION_CLEAR);
        } else{
            startService(intent);
        }
        imgBack = findViewById(R.id.imageBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendActionToService(MyService.ACTION_CLEAR);
                Intent it= new Intent(Music_Player.this, MainActivity.class);
                startActivity(it);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        sendActionToService(MyService.ACTION_CLEAR);
        finish();
    }
}