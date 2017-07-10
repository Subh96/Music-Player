package com.example.abhi.scrolling_tab;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Fragment_two extends Fragment {

    private RecyclerView recyclerView2;
    private List<Model1> modelList;
    private myAdapter2 adapter;
    private MenuItem menu;
    private MediaPlayer mediaPlayer;


    public Fragment_two() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_two, container, false);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.recycle2);
        int No_of_coloumns = 3;
        recyclerView2.setLayoutManager(new GridLayoutManager(getContext(), No_of_coloumns));
        modelList = getAllaudiofromdevice();
        adapter = new myAdapter2(modelList, (ViewGroup) view);
        recyclerView2.setAdapter(adapter);
        return view;
    }

    public List<Model1> getAllaudiofromdevice() {
        List<Model1> files = new ArrayList<>();
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            String selection = MediaStore.Audio.Media.IS_MUSIC;
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.ARTIST};
            Cursor c = contentResolver.query(uri, projection, selection, null, null);
            if (c != null) {
                while (c.moveToNext()) {
                    Model1 model1 = new Model1();
                    String path = c.getString(0);
                    String album = c.getString(1);
                    String artist = c.getString(2);
                    String name = path.substring(path.lastIndexOf('/') + 1);
                    model1.setName(name);
                    model1.setArtist(artist);
                    model1.setAlbum(album);
                    model1.setPath(path);
                    files.add(model1);
                }
                c.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        check();
        return files;
    }

    public void check() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 123);
                return;
            }
        } else {
            getAllaudiofromdevice();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestcode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestcode) {
            case 123:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    getAllaudiofromdevice();
                } else {
                    Toast.makeText(getContext(), "you have to set permissions", Toast.LENGTH_SHORT).show();
                    check();
                }
            default:
                super.onRequestPermissionsResult(requestcode, permissions, grantResults);
        }

    }


}


