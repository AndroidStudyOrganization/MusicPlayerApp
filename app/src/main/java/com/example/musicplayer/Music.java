package com.example.musicplayer;

public class Music {
    int imgMusic;
    String textSong;
    String textArtist;

    public Music(int imgMusic, String textSong, String textArtist) {
        this.imgMusic = imgMusic;
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
}
