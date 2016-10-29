package com.example.acer.app_liga_inggris;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ACER on 10/22/2016.
 */

public class adapter_klub  extends RecyclerView.Adapter<adapter_klub.ViewHolder> {
    private Context context;
    List<Item> lokasi;


    public adapter_klub(List<Item> lokasi, Context context){
        super();
        //Getting all the superheroes
        this.lokasi = lokasi;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Item iten_klasemen =  lokasi.get(position);
        System.out.println("ini adapter lognya " + iten_klasemen);
        Log.d("ini adapter log ",iten_klasemen.toString());
        holder.txtposisi.setText(String.valueOf(iten_klasemen.getPosisi()));
        holder.txtklub.setText(iten_klasemen.getKlub());
        holder.txtmain.setText(iten_klasemen.getMain());
        holder.txtmenang.setText(iten_klasemen.getMenang());
        holder.txtseri.setText(iten_klasemen.getSeri());
        holder.txtkalah.setText(iten_klasemen.getKalah());
        holder.txtpoin.setText(iten_klasemen.getPoin());
        holder.item = iten_klasemen;
    }

    @Override
    public int getItemCount() {
        return lokasi.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView txtposisi,txtklub;
        public TextView txtmain,txtmenang,txtseri, txtkalah, txtpoin;
        Item item;
        public ViewHolder(View itemView) {
            super(itemView);
            txtposisi = (TextView) itemView.findViewById(R.id.posisi);
            txtklub = (TextView) itemView.findViewById(R.id.klub);
            txtmain= (TextView) itemView.findViewById(R.id.main);
            txtmenang= (TextView) itemView.findViewById(R.id.menang);
            txtseri = (TextView) itemView.findViewById(R.id.seri);
            txtkalah= (TextView) itemView.findViewById(R.id.kalah);
            txtpoin = (TextView) itemView.findViewById(R.id.poin);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }
}
