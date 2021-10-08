package com.example.musicplayer;

import java.io.Serializable;

public class Music implements Serializable {
    private int imgMusic;
    private int mp3;
    private String textSong;
    private String textArtist;

    public Music(int imgMusic, String textSong, String textArtist) {
        this.imgMusic = imgMusic;
        this.textSong = textSong;
        this.textArtist = textArtist;
    }

    public Music(int imgMusic, String textSong, String textArtist, int mp3) {
        this.imgMusic = imgMusic;
        this.mp3 = mp3;
        this.textSong = textSong;
        this.textArtist = textArtist;
    }

    public Music() {
    }

    public int getImgMusic() {
        return imgMusic;
    }

    public void setImgMusic(int imgMusic) {
        this.imgMusic = imgMusic;
    }

    public String getTextSong() {
        return textSong;
    }

    public void setTextSong(String textSong) {
        this.textSong = textSong;
    }

    public String getTextArtist() {
        return textArtist;
    }

    public void setTextArtist(String textArtist) {
        this.textArtist = textArtist;
    }

    public int getMp3() {
        return mp3;
    }

    public void setMp3(int mp3) {
        this.mp3 = mp3;
    }
}
