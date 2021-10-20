package com.daotrung.myappmusic.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.daotrung.myappmusic.Adapter.DanhSachPlaylistAdapter;
import com.daotrung.myappmusic.Model.PlayList;
import com.daotrung.myappmusic.R;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachcacplaylistActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerViewDanhSachPlaylist;
    DanhSachPlaylistAdapter danhSachPlaylistAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhXa();
        init();
        getData();

    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<PlayList>> callBack = dataService.GetDanhsachcacPlaylist();
        callBack.enqueue(new Callback<List<PlayList>>() {
            @Override
            public void onResponse(Call<List<PlayList>> call, Response<List<PlayList>> response) {
                ArrayList<PlayList> mangPlaylist = (ArrayList<PlayList>) response.body();
                danhSachPlaylistAdapter = new DanhSachPlaylistAdapter(DanhsachcacplaylistActivity.this,mangPlaylist);
                recyclerViewDanhSachPlaylist.setLayoutManager(new LinearLayoutManager(DanhsachcacplaylistActivity.this));
                recyclerViewDanhSachPlaylist.setAdapter(danhSachPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<PlayList>> call, Throwable t) {

            }
        });
    }

    private void init() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("PlayList");
        toolbar.setTitleTextColor(getResources().getColor(R.color.mautim));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void anhXa() {
        toolbar = findViewById(R.id.toolbardanhsachplaylist);
        recyclerViewDanhSachPlaylist = findViewById(R.id.recycleviewdanhsachplaylist);

    }
}