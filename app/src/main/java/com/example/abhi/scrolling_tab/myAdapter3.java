package com.example.abhi.scrolling_tab;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by abhi on 2/7/17.
 */

public class myAdapter3 extends RecyclerView.Adapter<myAdapter3.Viewholder> {



    private List<Model> Music;
    private ViewGroup context;
    public MediaPlayer mediaPlayer3;
    public LinearLayout linearLayout1;
    public myAdapter3(List<Model> music, ViewGroup c){
        this.context=c;
        this.Music=music;
        linearLayout1= (LinearLayout) c.findViewById(R.id.bottom);
    }
    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row,parent,false);

        return new myAdapter3.Viewholder(v);
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        final Model model=Music.get(position);
        holder.text1.setText(model.getName());
        holder.text2.setText(model.getSinger());
        holder.text3.setText(model.getDuration());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),model.getName(),Toast.LENGTH_LONG).show();
            }
        });
        holder.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.btn3.getText().equals("Stop")){
                    mediaPlayer3.stop();
                    mediaPlayer3.reset();
                    mediaPlayer3.release();
                    mediaPlayer3=null;
                    linearLayout1.setVisibility(View.INVISIBLE);
                    holder.btn3.setText("Play");

                }
                else if(holder.btn3.getText().equals("Play")){
                    try {

                        mediaPlayer3 = new MediaPlayer();
                        mediaPlayer3.setDataSource(Music.get(position).getPath());
                        mediaPlayer3.prepareAsync();
                        mediaPlayer3.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mp) {
                                mp.start();
                                linearLayout1.setVisibility(View.VISIBLE);
                                TextView txt= (TextView) context.findViewById(R.id.txt);
                                txt.setText(Music.get(position).getSinger());
                                Button button= (Button) context.findViewById(R.id.bttn);
                                button.setText("Stop");
                                holder.btn3.setText("Stop");
                            }

                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } });
    }

    @Override
    public int getItemCount() {
        return Music.size();
    }


public class Viewholder extends RecyclerView.ViewHolder {

    public TextView text1, text2,text3;
    public LinearLayout linearLayout;
    public ImageView img;
    public Button btn3;

    public Viewholder(View itemView) {
        super(itemView);

        text1 = (TextView) itemView.findViewById(R.id.txt1);
        text2 = (TextView) itemView.findViewById(R.id.txt2);
        text3= (TextView) itemView.findViewById(R.id.txt3);
        linearLayout = (LinearLayout) itemView.findViewById(R.id.linear);
        img= (ImageView) itemView.findViewById(R.id.img);
        btn3= (Button) itemView.findViewById(R.id.play3);


    }
}

}
