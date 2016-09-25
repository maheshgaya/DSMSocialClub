package com.duse.android.dsmsocialclub.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.adapter.EventAdapter;
import com.duse.android.dsmsocialclub.database.EventJsonGetter;
import com.duse.android.dsmsocialclub.database.FavoriteContract;
import com.duse.android.dsmsocialclub.database.FavoriteDBHelper;
import com.duse.android.dsmsocialclub.model.EventModel;

import java.util.ArrayList;
import java.util.List;


public class FavoritesFragment extends Fragment {
    private static final String TAG = FavoritesFragment.class.getSimpleName();
    private EventModel[] mEvents = {}; //store events
    private EventAdapter mEventAdapter; //use for recycleview/cardview
    private RecyclerView mRecycleView; //use for UI
    private FavoriteDBHelper favoriteDBHelper;
    private SQLiteDatabase dbRead;

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
        FetchEventFavoriteTask fetchEventFavoriteTask = new FetchEventFavoriteTask();
        //read from database and add to array, then send that array
        //read from sqlite
        Cursor cursor = dbRead.rawQuery("SELECT * FROM " + FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME, null);
        Integer[] eventIds = new Integer[]{};
        int size = cursor.getCount();
        int rowId = 0;

        try {
            if (size != 0) {
                eventIds = new Integer[size];

                if (cursor != null) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        int eventId = cursor.getInt(cursor.getColumnIndex(FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID));
                        //Log.d(TAG, "updateEvents: " + eventId + " @row: " + rowId);
                        eventIds[rowId] = eventId;
                        cursor.moveToNext();
                        rowId = rowId + 1;
                    }
                }
            }
        }catch (java.lang.ArrayIndexOutOfBoundsException e){
            Log.e(TAG, "updateEvents: " + rowId ,e );
        } finally {
            cursor.close();
        }

        fetchEventFavoriteTask.execute(eventIds);
    }

    public FavoritesFragment(){
        //empty constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //saves instance state for fragment
        setRetainInstance(true);
        //get link to database
        favoriteDBHelper = new FavoriteDBHelper(getContext());
        //get the ability to write to database
        dbRead = favoriteDBHelper.getReadableDatabase();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Setup Cardview
        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        mEventAdapter = new EventAdapter(getActivity(), mEvents);
        mRecycleView = (RecyclerView) rootView.findViewById(R.id.recycleview_events_favorite);
        mRecycleView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycleView.setLayoutManager(linearLayoutManager);
        mRecycleView.setAdapter(mEventAdapter);

        return rootView;
    }


    private class FetchEventFavoriteTask extends AsyncTask<Integer, Void, EventModel[]> {
        private final String LOG_TAG = FetchEventFavoriteTask.class.getSimpleName();
        @Override
        protected void onPostExecute(EventModel[] result) {
            //if result is not empty, update adapter and the UI
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
        protected EventModel[] doInBackground(Integer... params) {
            if (params.length == 0){
                return null;
            }
            //get events from json
            List<EventModel> eventList = new EventJsonGetter(getActivity()).getEventsList(params);
            EventModel[] events = eventList.toArray(new EventModel[eventList.size()]);
            return events;
        }
    }

}
