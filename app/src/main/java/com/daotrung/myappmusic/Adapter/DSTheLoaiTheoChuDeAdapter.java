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
import com.daotrung.myappmusic.Model.TheLoai;
import com.daotrung.myappmusic.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DSTheLoaiTheoChuDeAdapter extends RecyclerView.Adapter<DSTheLoaiTheoChuDeAdapter.ViewHolder>{

    Context context;
    ArrayList<TheLoai> mangTheLoai;

    public DSTheLoaiTheoChuDeAdapter(Context context, ArrayList<TheLoai> mangTheLoai) {
        this.context = context;
        this.mangTheLoai = mangTheLoai;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_the_loai_theo_chu_de,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TheLoai theLoai = mangTheLoai.get(position);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(holder.imgHinhNen);
        holder.txtTenTheLoai.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return mangTheLoai.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imgHinhNen;
        TextView txtTenTheLoai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHinhNen = itemView.findViewById(R.id.imageviewtheloaitheochude);
            txtTenTheLoai = itemView.findViewById(R.id.textviewtentheloaitheochude);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                    intent.putExtra("idchude",mangTheLoai.get(getPosition()));
                    context.startActivity(intent);
                }
            });

        }
    }
}

