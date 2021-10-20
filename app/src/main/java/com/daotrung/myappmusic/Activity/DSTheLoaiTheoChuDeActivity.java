package com.daotrung.myappmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.daotrung.myappmusic.Adapter.DSTheLoaiTheoChuDeAdapter;
import com.daotrung.myappmusic.Model.ChuDe;
import com.daotrung.myappmusic.Model.TheLoai;
import com.daotrung.myappmusic.R;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DSTheLoaiTheoChuDeActivity extends AppCompatActivity {

    ChuDe chuDe ;
    RecyclerView recyclerViewtheotlcd ;
    Toolbar toolbartheotlcd ;
    DSTheLoaiTheoChuDeAdapter dsTheLoaiTheoChuDeAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dsthe_loai_theo_chu_de);
        GetIntent();
        anhXa();
        GetData();


    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<TheLoai>> callback = dataservice.GetTheLoaitheochude(chuDe.getIdChuDe());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                dsTheLoaiTheoChuDeAdapter = new DSTheLoaiTheoChuDeAdapter(DSTheLoaiTheoChuDeActivity.this,mangtheloai);
                recyclerViewtheotlcd.setLayoutManager(new GridLayoutManager(DSTheLoaiTheoChuDeActivity.this,2));
                recyclerViewtheotlcd.setAdapter(dsTheLoaiTheoChuDeAdapter);

            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });

    }

    private void anhXa() {
        recyclerViewtheotlcd = findViewById(R.id.recycleviewtheloaitheochude);
        toolbartheotlcd = findViewById(R.id.toolbartheloaitheochude);

        setSupportActionBar(toolbartheotlcd);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        toolbartheotlcd.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void GetIntent() {
        Intent intent = getIntent();
        if(intent.hasExtra("chude")){
            chuDe = (ChuDe) intent.getSerializableExtra("chude");
            Toast.makeText(this, chuDe.getTenChuDe(), Toast.LENGTH_SHORT).show();
        }
    }
}