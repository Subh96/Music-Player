package com.example.abhi.scrolling_tab;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Fragment_one extends Fragment {

    public RecyclerView recyclerView1;
    private LinearLayoutManager manager;
    private List<Model> modelList;
    private  myAdapter3 adapter;
    public LinearLayout linearLayout;
    public Fragment_one() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View view = inflater.inflate(R.layout.activity_fragment_one, container, false);
        recyclerView1 = (RecyclerView) view.findViewById(R.id.recycle1);
        manager = new LinearLayoutManager(getActivity());
        recyclerView1.setLayoutManager(manager);
        modelList = getAllaudiofromdevice();
        adapter = new myAdapter3(modelList, (ViewGroup) view);
        recyclerView1.setAdapter(adapter);
        return view;
    }
    public List<Model> getAllaudiofromdevice() {
        List<Model> files = new ArrayList<>();
        try {
            ContentResolver contentResolver = getContext().getContentResolver();
            String selection = MediaStore.Audio.Media.IS_MUSIC;
            Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            String[] projection = {MediaStore.Audio.AudioColumns.DATA, MediaStore.Audio.AudioColumns.ALBUM, MediaStore.Audio.AudioColumns.ARTIST};
            Cursor c = contentResolver.query(uri, null, selection, null, null);
            if (c != null) {
                while (c.moveToNext()) {
                    Model model1 = new Model();
                    //String singer = c.getString(0);
                    String path = c.getString(1);
                    String hello = c.getString(2);
                    String duration=c.getString(3);
                    String name = path.substring(path.lastIndexOf('/') + 1);
                    model1.setName(name);
                    //model1.setSinger(singer);
                    model1.setPath(path);
                    model1.setSinger(hello);
                    model1.setDuration(duration);
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
