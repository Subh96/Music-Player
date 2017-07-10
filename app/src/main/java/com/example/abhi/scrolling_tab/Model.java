package com.example.abhi.scrolling_tab;

import android.media.Image;
import android.widget.ImageView;

/**
 * Created by abhi on 2/7/17.
 */

public class Model {
    private String Name;
    private String Duration;
    private String Singer;
    private String Path;

    public String getPath() {
        return Path;
    }

    public void setPath(String path) {
        Path = path;
    }


    public void setName(String name) {
        Name = name;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }


    public Model(){
    }

    public String getName() {
        return Name;
    }



    public String getDuration() {
        return Duration;
    }



    public String getSinger() {
        return Singer;
    }

}
