package com.example.mycontact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Introslider_Fragment extends Fragment {

    public Introslider_Fragment initFrag(int backgroundId){
        Introslider_Fragment fragment = new Introslider_Fragment();
        Bundle args = new Bundle();
        args.putInt("backgroundId",backgroundId);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_introslider,container,false);
        view.findViewById(R.id.layout_fragment_intoslider).setBackgroundColor(getArguments().getInt("backgroundId"));
        return view;
    }
}
