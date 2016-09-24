package com.duse.android.dsmsocialclub.fragment;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.adapter.EventAdapter;
import com.duse.android.dsmsocialclub.model.EventJsonGetter;
import com.duse.android.dsmsocialclub.model.EventModel;

import java.util.List;


public class ExploreFragment extends Fragment{
    private EventModel[] mEvents = {};
    private EventAdapter mEventAdapter;
    private RecyclerView mRecycleView;

    @Override
    public void onStart() {
        super.onStart();
        if (mEvents.length == 0) {
            updateEvents();
        }
    }

    public void updateEvents(){
        FetchEventTask fetchEventTask = new FetchEventTask();
        //TODO: Add params for sorting, shared preferences
        fetchEventTask.execute();
    }

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
        mEventAdapter = new EventAdapter(getActivity(), mEvents);
        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recycleview_events_explore);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mEventAdapter);
        return rootView;
    }


    public class FetchEventTask extends AsyncTask<Void, Void, EventModel[]> {
        private final String LOG_TAG = FetchEventTask.class.getSimpleName();
        @Override
        protected void onPostExecute(EventModel[] result) {
            if (result != null){
                mEvents = result;
                try {
                    mEventAdapter = new EventAdapter(getContext(), mEvents);
                    mEventAdapter.notifyDataSetChanged();
                    mRecycleView.setAdapter(mEventAdapter);

                } catch (UnsupportedOperationException e){
                    Log.e(LOG_TAG, "onPostExecute: ", e);
                    e.printStackTrace();
                }
            }



        }

        @Override
        protected EventModel[] doInBackground(Void... voids) {

            List<EventModel> eventList = new EventJsonGetter(getActivity()).getEventsList();
            EventModel[] events = eventList.toArray(new EventModel[eventList.size()]);
            return events;
        }
    }

}
