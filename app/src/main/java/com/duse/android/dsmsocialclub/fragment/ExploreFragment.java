package com.duse.android.dsmsocialclub.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.adapter.EventAdapter;
import com.duse.android.dsmsocialclub.model.EventModel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class ExploreFragment extends Fragment{
    //TODO: add logic to this class to view list of events
    private EventModel[] mEvents;
    private EventAdapter mEventAdapter;
    private ListView mListView;


    public ExploreFragment(){
        //empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_explore, container, false);
            return rootView;
    }

}
