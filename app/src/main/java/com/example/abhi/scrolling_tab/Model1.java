package com.example.abhi.scrolling_tab;

/**
 * Created by abhi on 3/7/17.
 */

public class Model1 {
    private String Name;
    private String Path;
    private String Album;

    public String getPath() {
        return Path;
    }

    public String getAlbum() {
        return Album;
    }

    public String getArtist() {
        return Artist;
    }

    private String Artist;

    public Model1() {

    }

    public String getName() {
        return Name;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setPath(String path) {
        Path = path;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }
}