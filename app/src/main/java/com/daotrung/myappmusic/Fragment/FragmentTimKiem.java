package com.daotrung.myappmusic.Fragment;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;


import com.daotrung.myappmusic.Adapter.SearchBaiHatAdapter;
import com.daotrung.myappmusic.Model.BaiHat;
import com.daotrung.myappmusic.R;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentTimKiem extends Fragment {
    View view ;

    Toolbar toolbar ;
    RecyclerView recyclerViewtimkiem ;
    TextView txtkdl ;

    SearchBaiHatAdapter adapter ;
    public FragmentTimKiem() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_tim_kiem,container,false);
        toolbar = view.findViewById(R.id.toolbarsearchbaihat);
        txtkdl = view.findViewById(R.id.textviewkhongcodulieu);
        recyclerViewtimkiem = view.findViewById(R.id.recycleviewsearchbaihat);
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        toolbar.setTitle("");
         setHasOptionsMenu(true);



        return view ;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_view,menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchBaiHat(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    private void searchBaiHat(String query){
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getSearchBaiHat(query);
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> mangBaiHat = (ArrayList<BaiHat>) response.body();
                if(mangBaiHat.size() > 0){
                    adapter = new SearchBaiHatAdapter(getActivity(),mangBaiHat);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerViewtimkiem.setLayoutManager(linearLayoutManager);
                    recyclerViewtimkiem.setAdapter(adapter);
                    txtkdl.setVisibility(View.GONE);
                    recyclerViewtimkiem.setVisibility(View.VISIBLE);
                }else{
                    recyclerViewtimkiem.setVisibility(View.GONE);
                    txtkdl.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}