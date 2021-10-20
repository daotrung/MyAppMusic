package com.daotrung.myappmusic;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.daotrung.myappmusic.Activity.DanhSachBaiHatActivity;
import com.daotrung.myappmusic.Model.QuangCao;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BannerAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> arrayListBanner = new ArrayList<QuangCao>() ;

    public BannerAdapter(Context context, ArrayList<QuangCao> arrayListBanner) {
        this.context = context;
        this.arrayListBanner = arrayListBanner;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<QuangCao> getArrayListBanner() {
        return arrayListBanner;
    }

    public void setArrayListBanner(ArrayList<QuangCao> arrayListBanner) {
        this.arrayListBanner = arrayListBanner;
    }

    @Override
    public int getCount() {
        return arrayListBanner.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dong_banner,null);
        ImageView imgBackgroundBanner = view.findViewById(R.id.imagebackgroundbanner);
        ImageView imgSongBanner = view.findViewById(R.id.imageviewbanner);
        TextView txtTitleSongBanner = view.findViewById(R.id.textviewtitlebannerbaihat);
        TextView txtNoiDung = view.findViewById(R.id.textviewnoidung);

        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgBackgroundBanner);
        Picasso.with(context).load(arrayListBanner.get(position).getHinhAnh()).into(imgSongBanner);
        txtTitleSongBanner.setText(arrayListBanner.get(position).getTenBaiHat());
        txtNoiDung.setText(arrayListBanner.get(position).getNoiDung());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DanhSachBaiHatActivity.class);
                intent.putExtra("banner",arrayListBanner.get(position));
                context.startActivity(intent);
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
