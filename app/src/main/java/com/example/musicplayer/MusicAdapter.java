package com.example.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.musicplayer.R;
import com.example.musicplayer.Music_Player;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder>{
//    public class ViewHolder extends RecyclerView.ViewHolder {
//        private ImageView imgMusic;
//        private TextView viewSong;
//        private TextView viewArtist;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            imgMusic= itemView.findViewById(R.id.imageMusic);
//            viewSong= itemView.findViewById(R.id.textSong);
//            viewArtist= itemView.findViewById(R.id.textArtist);
//        }
//    }
//
//    private Context context;
//    private ArrayList<Music> musics;
//
//    public MusicAdapter(Context context, ArrayList<Music> musics) {
//        this.context= context;
//        this.musics= musics;
//    }
//
//    @NonNull
//    @Override
//    public MusicAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//        LayoutInflater inflater= LayoutInflater.from(context);
//        View musicView= inflater.inflate(R.layout.customrecycleview, viewGroup, false);
//        ViewHolder viewHolder= new ViewHolder(musicView);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MusicAdapter.ViewHolder viewHolder, int i) {
//        Music music= musics.get(i);
//        viewHolder.imgMusic.setImageResource(music.getImgMusic());
//        viewHolder.viewSong.setText(music.getTextSong());
//        viewHolder.viewArtist.setText(music.getTextArtist());
//    }
//
//    @Override
//    public int getItemCount() {
//        return musics.size();
//    }
    private Context mContext;
    private List<Music> mSongs;

    public MusicAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<Music> list){
        this.mSongs = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customrecycleview,parent,false);
        return new MusicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        Music music = mSongs.get(position);
        if(music == null){
            return;
        }
        holder.imgSong.setImageResource(music.getImgMusic());
        holder.tvNameSong.setText(music.getTextSong());
        holder.tvArtist.setText(music.getTextArtist());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToSongPlay(music);
            }
        });
    }

    private void onClickGoToSongPlay(Music music){
        Intent intent = new Intent(mContext, Music_Player.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_song", music);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    public void release(){
        mContext = null;
    }

    @Override
    public int getItemCount() {
        if(mSongs != null){
            return mSongs.size();
        }
        return 0;
    }

    public class MusicViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout relativeLayout;

        private ImageView imgSong;
        private TextView tvNameSong;
        private TextView tvArtist;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            relativeLayout = itemView.findViewById(R.id.layout_song);
            imgSong = itemView.findViewById(R.id.imageMusic);
            tvNameSong = itemView.findViewById(R.id.textSong);
            tvArtist = itemView.findViewById(R.id.textArtist);

        }
    }
}
