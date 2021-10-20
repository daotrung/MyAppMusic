package com.daotrung.myappmusic.Fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.daotrung.myappmusic.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Fragment_Dia_Nhac extends Fragment {
    View view ;
    CircleImageView circleImageView ;

    ObjectAnimator objectAnimator ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dia_nhac,container,false);
        circleImageView = view.findViewById(R.id.imageviewcircle);
        objectAnimator = ObjectAnimator.ofFloat(circleImageView,"rotation",0f,360f);
        objectAnimator.setDuration(10000); // thời gian chạy 10 s
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE); // chay xong 10 lan phát đi phát lại
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator()); // tranh loi : sau 1 dong thi se dung chay 1 ti
        objectAnimator.start();
        return view ;

    }
    public void playNhac(String hinhAnh){
        Picasso.with(getContext()).load(hinhAnh).into(circleImageView);

    }


 }
