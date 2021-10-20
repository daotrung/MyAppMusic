package com.daotrung.myappmusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.daotrung.myappmusic.Activity.DanhSachBaiHatActivity;
import com.daotrung.myappmusic.Activity.DanhsachcacplaylistActivity;
import com.daotrung.myappmusic.Model.PlayList;
import com.daotrung.myappmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DanhSachPlaylistAdapter extends RecyclerView.Adapter<DanhSachPlaylistAdapter.ViewHolder> {

    Context context;
    ArrayList<PlayList> mangPlaylist = new ArrayList<>();


    public DanhSachPlaylistAdapter(Context context, ArrayList<PlayList> mangPlaylist) {
        this.context = context;
        this.mangPlaylist = mangPlaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_danh_sach_playlist,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PlayList playList = mangPlaylist.get(position);
        Picasso.with(context).load(playList.getHinhPlayList()).into(holder.imgHinhNen);
        holder.txtTenPlaylist.setText(playList.getTen());
    }

    @Override
    public int getItemCount() {
        return mangPlaylist.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhNen;
        TextView txtTenPlaylist;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imageviewdanhsachplaylist);
            txtTenPlaylist = itemView.findViewById(R.id.textviewdanhsachplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("itemplaylist", mangPlaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}


