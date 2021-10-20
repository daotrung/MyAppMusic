package com.daotrung.myappmusic.Fragment;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.daotrung.myappmusic.R;


public class FragmentTrangChu extends Fragment {

    View view ;

    public FragmentTrangChu() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_trang_chu,container,false);
        return view;
    }
}