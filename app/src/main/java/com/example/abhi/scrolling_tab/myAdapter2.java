package com.example.abhi.scrolling_tab;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

/**
 * Created by abhi on 2/7/17.
 */

public class myAdapter2 extends RecyclerView.Adapter<myAdapter2.Viewholder> {



    private List<Model1> Music;
    private MediaPlayer mediaPlayer;
    private ViewGroup context;
    public myAdapter2(List<Model1> music, ViewGroup c){
        this.context=c;
        this.Music=music;
    }
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view,parent,false);
        return new myAdapter2.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        final Model1 model=Music.get(position);
        holder.text2.setText(model.getArtist());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),Music.get(position).getPath(),Toast.LENGTH_SHORT).show();
            }
        });
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn.getText().equals("Stop")){
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    mediaPlayer.release();
                    mediaPlayer=null;
                    holder.btn.setText("Play");
                }
                else if(holder.btn.getText().equals("Play")){
                try {

                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setDataSource(Music.get(position).getPath());
                    mediaPlayer.prepareAsync();
                    mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                            holder.btn.setText("Stop");
                        }

                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }}
            } });}




    @Override
    public int getItemCount() {
        return Music.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {

        public TextView text1, text2;
        public ImageView img;
        public Button btn;
        public Viewholder(View itemView) {
            super(itemView);
            text1 = (TextView) itemView.findViewById(R.id.txtview1);
            text2 = (TextView) itemView.findViewById(R.id.txtview2);
            img= (ImageView) itemView.findViewById(R.id.img);
            btn= (Button) itemView.findViewById(R.id.play);
            btn.setText("Play");
        }
    }

}
