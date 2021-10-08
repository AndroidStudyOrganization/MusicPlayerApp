package service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.musicplayer.Music;

public class MyService extends Service {
    public static final int ACTION_PAUSE =1;
    public static final int ACTION_RESUME =2;
    public static final int ACTION_CLEAR =3;
    public static final int ACTION_START =4;

    private MediaPlayer mediaPlayer;
    private Boolean isPlaying;
    private Music mSong;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("PHUC","My service onCreate");

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Bundle bundle =  intent.getExtras();
        if(bundle != null){
            Music music = (Music) bundle.get("object_song");
            if(music != null) {
                mSong = music;
                startMusic(music);
//                sendNotification(music);
            }
        }

        int actionMusic = intent.getIntExtra("action_music_service",0);
        handleActionMusic(actionMusic);


        return START_NOT_STICKY;
    }

    private void handleActionMusic(int actionMusic) {
        switch (actionMusic){
            case ACTION_PAUSE:
                pauseMusic();
                break;

            case ACTION_RESUME:
                resumeMusic();
                break;

            case ACTION_CLEAR:
                stopSelf();
                sendActionToActivity(ACTION_CLEAR);
                break;
        }
    }

    private void resumeMusic() {
        if (mediaPlayer != null && !isPlaying){
            mediaPlayer.start();
            isPlaying = true;
//            sendNotification(mSong);
            sendActionToActivity(ACTION_RESUME);
        }
    }

    private void pauseMusic() {
        if (mediaPlayer != null && isPlaying){
            mediaPlayer.pause();
            isPlaying = false;
//            sendNotification(mSong);
            sendActionToActivity(ACTION_PAUSE);
        }
    }

    private void startMusic(Music music) {
        if(mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), music.getMp3());
        }
        mediaPlayer.start();
        isPlaying = true;
        sendActionToActivity(ACTION_START);
    }

    private void sendActionToActivity(int actionStart) {
        Intent intent = new Intent("send_data_to_activity");
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", mSong);
        bundle.putBoolean("status_player", isPlaying);
        bundle.putInt("action_music", actionStart);

        intent.putExtras(bundle);

        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

    }
    private PendingIntent getPendingIntent(Context context, int action){
        Intent intent = new Intent(this, MyReciever.class);
        intent.putExtra("action_music",action);

        return PendingIntent.getBroadcast(context.getApplicationContext(),action,intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("Dat","My service onDestroy");
        if(mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}
