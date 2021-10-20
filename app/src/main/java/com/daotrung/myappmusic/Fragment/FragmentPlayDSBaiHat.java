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

import com.daotrung.myappmusic.Activity.PlayNhacActivity;
import com.daotrung.myappmusic.Adapter.PlayNhacAdapter;
import com.daotrung.myappmusic.R;

public class FragmentPlayDSBaiHat extends Fragment {
    View view ;
    RecyclerView rvPlayNhac ;
    PlayNhacAdapter playNhacAdapter ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_play_ds_bh,container,false);
         rvPlayNhac = view.findViewById(R.id.recycleviewplaybaihat);
         if(PlayNhacActivity.mangbaihat.size()>0) {
             playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayNhacActivity.mangbaihat);
             rvPlayNhac.setLayoutManager(new LinearLayoutManager(getActivity()));
             rvPlayNhac.setAdapter(playNhacAdapter);
         }
         return view ;
    }
}
