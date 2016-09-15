package com.duse.android.dsmsocialclub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duse.android.dsmsocialclub.R;

/**
 * Created by Mahesh Gaya on 9/14/16.
 */
public class HomeFragment extends Fragment{
    //TODO: add logic to this class to view list of events
    public HomeFragment(){
        //empty constructor required
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }
}
