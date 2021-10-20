package com.daotrung.myappmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.daotrung.myappmusic.Adapter.DanhSachTatCaChuDeAdapter;
import com.daotrung.myappmusic.Model.ChuDe;
import com.daotrung.myappmusic.R;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhSachTatCaChuDeActivity extends AppCompatActivity {

    RecyclerView recyclerView ;
    Toolbar toolbarTatCaChude ;
    DanhSachTatCaChuDeAdapter danhSachTatCaChuDeAdapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_tat_ca_chu_de);

        recyclerView = findViewById(R.id.recycleviewchude);
        toolbarTatCaChude = findViewById(R.id.toolbarchude);
        setSupportActionBar(toolbarTatCaChude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất cả chủ để ");
        toolbarTatCaChude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Dataservice dataservice = APIService.getService();
        Call<List<ChuDe>> callback= dataservice.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe>  mangchude = (ArrayList<ChuDe>) response.body();
                danhSachTatCaChuDeAdapter= new DanhSachTatCaChuDeAdapter(DanhSachTatCaChuDeActivity.this,mangchude);
                recyclerView.setLayoutManager(new GridLayoutManager(DanhSachTatCaChuDeActivity.this,2));
                recyclerView.setAdapter(danhSachTatCaChuDeAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {

            }
        });


    }
}