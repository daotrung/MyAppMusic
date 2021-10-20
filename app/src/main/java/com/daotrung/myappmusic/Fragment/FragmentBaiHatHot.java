package com.daotrung.myappmusic.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.daotrung.myappmusic.Adapter.BaiHatHotAdapter;
import com.daotrung.myappmusic.Model.BaiHat;
import com.daotrung.myappmusic.Service.APIService;
import com.daotrung.myappmusic.Service.Dataservice;

import java.util.ArrayList;
import java.util.List;
import com.daotrung.myappmusic.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentBaiHatHot extends Fragment {

    View view;
    RecyclerView recyclerViewBaiHatHot;
    BaiHatHotAdapter baiHatHotAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_bai_hat_thich_nhat,container,false);
        recyclerViewBaiHatHot = view.findViewById(R.id.recycleviewbaihathot);
        getData();
        return view;
    }

    private void getData() {
        Dataservice dataService = APIService.getService();
        Call<List<BaiHat>> callBack = dataService.getBaiHatHot();
        callBack.enqueue(new Callback<List<BaiHat>>() {
            @Override
            public void onResponse(Call<List<BaiHat>> call, Response<List<BaiHat>> response) {
                ArrayList<BaiHat> baiHatArrayList = (ArrayList<BaiHat>) response.body();
                baiHatHotAdapter = new BaiHatHotAdapter(getActivity(), baiHatArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewBaiHatHot.setLayoutManager(linearLayoutManager);
                recyclerViewBaiHatHot.setAdapter(baiHatHotAdapter);
            }

            @Override
            public void onFailure(Call<List<BaiHat>> call, Throwable t) {

            }
        });
    }
}
