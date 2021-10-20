package com.daotrung.myappmusic.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.daotrung.myappmusic.Activity.DSTheLoaiTheoChuDeActivity;
import com.daotrung.myappmusic.Activity.DanhSachBaiHatActivity;
import com.daotrung.myappmusic.Activity.DanhSachTatCaChuDeActivity;
import com.daotrung.myappmusic.Model.ChuDe;
import com.daotrung.myappmusic.Model.TheLoai;
import com.daotrung.myappmusic.Model.TheLoaiTrongNgay;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import com.daotrung.myappmusic.R;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentChuDeTheLoai extends Fragment {
    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtXemThemChuDeVaTheLoai;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_chude_theloai,container,false);
        horizontalScrollView = view.findViewById(R.id.horizontalScrollview);
        txtXemThemChuDeVaTheLoai = view.findViewById(R.id.textviewxemthem);

        txtXemThemChuDeVaTheLoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DanhSachTatCaChuDeActivity.class);
                startActivity(intent);
            }
        });

        getData();
        return view;
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<TheLoaiTrongNgay> callBack = dataService.getCategoryMusic();
        callBack.enqueue(new Callback<TheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<TheLoaiTrongNgay> call, Response<TheLoaiTrongNgay> response) {
                TheLoaiTrongNgay theLoaiTrongNgay = response.body();

                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(theLoaiTrongNgay.getChuDe());

                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(theLoaiTrongNgay.getTheLoai());

                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500,500);
                layoutParams.setMargins(10,20,10,30);

                for (int i = 0; i < (chuDeArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(20);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(chuDeArrayList.get(i).getHinhChuDe()!= null){
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                             Intent intent = new Intent(getActivity(), DSTheLoaiTheoChuDeActivity.class);
                             intent.putExtra("chude",chuDeArrayList.get(finalI));
                             startActivity(intent);
                        }
                    });

                }

                for (int j = 0; j < (chuDeArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(theLoaiArrayList.get(j).getHinhTheLoai()!= null){
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(j).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(getActivity(), DanhSachBaiHatActivity.class);
                            intent.putExtra("idtheloai",theLoaiArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });


                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<TheLoaiTrongNgay> call, Throwable t) {

            }
        });

    }
}
