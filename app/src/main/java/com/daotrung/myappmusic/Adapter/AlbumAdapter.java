package com.daotrung.myappmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.daotrung.myappmusic.Activity.DanhSachBaiHatActivity;
import com.daotrung.myappmusic.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daotrung.myappmusic.Model.Album;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder>{
    Context context;
    ArrayList<Album> albumArrayList;

    public AlbumAdapter(Context context, ArrayList<Album> albumArrayList) {
        this.context = context;
        this.albumArrayList = albumArrayList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<Album> getAlbumArrayList() {
        return albumArrayList;
    }

    public void setAlbumArrayList(ArrayList<Album> albumArrayList) {
        this.albumArrayList = albumArrayList;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_album, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Album album = albumArrayList.get(position);
        holder.txtTenCaSiAlbum.setText(album.getTenCaSiAlbum());
        holder.txtTenAlbum.setText(album.getTenAlbum());
        Picasso.with(context).load(album.getHinhAlbum()).into(holder.imgHinhAlbum);
    }

    @Override
    public int getItemCount() {
        return albumArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhAlbum;
        TextView txtTenAlbum, txtTenCaSiAlbum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhAlbum = itemView.findViewById(R.id.imagealbum);
            txtTenAlbum = itemView.findViewById(R.id.textviewtenalbum);
            txtTenCaSiAlbum = itemView.findViewById(R.id.textviewtencasialbum);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent= new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("album",albumArrayList.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}
