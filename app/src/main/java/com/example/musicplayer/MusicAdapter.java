package com.example.musicplayer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgMusic;
        private TextView viewSong;
        private TextView viewArtist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgMusic= itemView.findViewById(R.id.imageMusic);
            viewSong= itemView.findViewById(R.id.textSong);
            viewArtist= itemView.findViewById(R.id.textArtist);
        }
    }

    private Context context;
    private ArrayList<Music> musics;

    public MusicAdapter(Context context, ArrayList<Music> musics) {
        this.context= context;
        this.musics= musics;
    }

    @NonNull
    @Override
    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View musicView= inflater.inflate(R.layout.customrecycleview, viewGroup, false);
        ViewHolder viewHolder= new ViewHolder(musicView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder viewHolder, int i) {
        Music music= musics.get(i);
        viewHolder.imgMusic.setImageResource(music.getImgMusic());
        viewHolder.viewSong.setText(music.getTextSong());
        viewHolder.viewArtist.setText(music.getTextArtist());
    }

    @Override
    public int getItemCount() {
        return musics.size();
    }
}
