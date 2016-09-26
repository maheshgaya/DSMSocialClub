package com.duse.android.dsmsocialclub.fragment;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.adapter.EventAdapter;
import com.duse.android.dsmsocialclub.database.EventJsonGetter;
import com.duse.android.dsmsocialclub.model.EventModel;

import java.util.List;

public class HomeFragment extends Fragment {
    private EventModel[] mEvents = {}; //store events
    private EventAdapter mEventAdapter; //use for recycleview/cardview
    private RecyclerView mRecycleView; //use for UI

    public HomeFragment(){
        //empty constructor required
    }
    /**
     * onStart()
     * get updates of events if the array is zero
     */
    @Override
    public void onStart() {
        super.onStart();
        //get data
        updateEvents();

    }

    public void updateEvents(){
        //get event updates
        //This works Woohoo
        FetchEventInterestsTask fetchEventTask = new FetchEventInterestsTask();
        Boolean[] preferences = new Boolean[15];
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());

        Boolean prefCircus = prefs.getBoolean(getString(R.string.interest_circus_value), false);
        preferences[0] = prefCircus;
        Boolean prefComedy = prefs.getBoolean(getString(R.string.interest_comedy_value), false);
        preferences[1] = prefComedy;
        Boolean prefCulinary = prefs.getBoolean(getString(R.string.interest_culinary_value), false);
        preferences[2] = prefCulinary;
        Boolean prefDanceMovement = prefs.getBoolean(getString(R.string.interest_dance_movement_value), false);
        preferences[3] = prefDanceMovement;
        Boolean prefEducation = prefs.getBoolean(getString(R.string.interest_education_value), false);
        preferences[4] = prefEducation;

        Boolean prefFeaturedEvent = prefs.getBoolean(getString(R.string.interest_featured_event_value), false);
        preferences[5] = prefFeaturedEvent;
        Boolean prefFilm = prefs.getBoolean(getString(R.string.interest_film_value), false);
        preferences[6] = prefFilm;
        Boolean prefKids = prefs.getBoolean(getString(R.string.interest_kids_value), false);
        preferences[7] = prefKids;
        Boolean prefLifestyle = prefs.getBoolean(getString(R.string.interest_lifestyle_value), false);
        preferences[8] = prefLifestyle;
        Boolean prefLiterary = prefs.getBoolean(getString(R.string.interest_literary_value), false);
        preferences[9] = prefLiterary;

        Boolean prefMusic = prefs.getBoolean(getString(R.string.interest_music_value), false);
        preferences[10] = prefMusic;
        Boolean prefSocial = prefs.getBoolean(getString(R.string.interest_social_value), false);
        preferences[11] = prefSocial;
        Boolean prefTechnology = prefs.getBoolean(getString(R.string.interest_technology_value), false);
        preferences[12] = prefTechnology;
        Boolean prefTheater = prefs.getBoolean(getString(R.string.interest_theater_value), false);
        preferences[13] = prefTheater;
        Boolean prefVisualArt = prefs.getBoolean(getString(R.string.interest_visual_art_value), false);
        preferences[14] = prefVisualArt;


        fetchEventTask.execute(preferences);
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //saves instance state for fragment
        setRetainInstance(true);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        //Setup Cardview
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        mEventAdapter = new EventAdapter(getActivity(), mEvents);
        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recycleview_events_home);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mEventAdapter);

        return rootView;
    }



    private class FetchEventInterestsTask extends AsyncTask<Boolean, Void, EventModel[]> {
        private final String LOG_TAG = FetchEventInterestsTask.class.getSimpleName();

        @Override
        protected void onPostExecute(EventModel[] result) {
            //if result is not empty, update adapter and the UI
            if (result != null) {
                mEvents = result;
                try {
                    mEventAdapter = new EventAdapter(getContext(), mEvents);
                    mEventAdapter.notifyDataSetChanged();
                    mRecycleView.setAdapter(mEventAdapter);

                } catch (UnsupportedOperationException e) {
                    Log.e(LOG_TAG, "onPostExecute: ", e);
                    e.printStackTrace();
                }
            }

        }

        @Override
        protected EventModel[] doInBackground(Boolean... params) {
            //Log.d(LOG_TAG, "doInBackground: " + params.length);
            if (params.length == 0){
                return null;
            }
            //get events from json
            List<EventModel> eventList = new EventJsonGetter(getActivity()).getEventsList(params);
            EventModel[] events = eventList.toArray(new EventModel[eventList.size()]);
            //Log.d(LOG_TAG, "doInBackground: " + events.toString());
            return events;
        }
    }
}
