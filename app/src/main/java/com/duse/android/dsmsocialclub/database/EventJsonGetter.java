package com.duse.android.dsmsocialclub.database;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.duse.android.dsmsocialclub.Constant;
import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.model.EventModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * EventJsonGetter
 * This class gets the events data from the event.json file
 * It then returns that to an ArrayList
 */
public class EventJsonGetter {
    private ArrayList<EventModel> eventsList;
    private Context mContext;
    private final String TAG = EventJsonGetter.class.getSimpleName();

    /**
     * Construct for EventJsonGetter
     * @param context gets the context of the activity
     */
    public EventJsonGetter(Activity context){
        mContext = context;
    }

    /**
     * getEventsList
     * @return ArrayList of EventModel
     * This will read the data from Json and return an arraylist of the events
     */
    public ArrayList<EventModel> getEventsList(){
        //initializing
        eventsList = new ArrayList<>();
        String eventJson;

        //read from file, and put to a buffer
        try{
            InputStream inputStream = mContext.getAssets().open("events.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();
            eventJson = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        //read the json from the buffer
        try {
            JSONObject eventsJsonObject = new JSONObject(eventJson);
            JSONArray eventJsonArray = eventsJsonObject.getJSONArray("events");

            for (int i = 0; i < eventJsonArray.length(); i++){
                JSONObject eventObject = eventJsonArray.getJSONObject(i);
                //In real case, id should be a unique number
                int id = eventObject.getInt(Constant.EVENT_ID);
                String title = eventObject.getString(Constant.EVENT_TITLE);
                String date = eventObject.getString(Constant.EVENT_DATE);
                String time = eventObject.getString(Constant.EVENT_TIME);
                String interest = eventObject.getString(Constant.EVENT_INTEREST);
                String description = eventObject.getString(Constant.EVENT_DESCRIPTION);
                String imageUrl = eventObject.getString(Constant.EVENT_IMAGE_URL);
                String location = eventObject.getString(Constant.EVENT_LOCATION);
                //Log.d(TAG, "getEventsList: " + interest);
                EventModel event = new EventModel(id, title, description, date, time, location, imageUrl, interest);

                eventsList.add(event);
            }

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }


        return eventsList;
    }

    public ArrayList<EventModel> getEventsList(Boolean[] interestsArray){
        //initializing
        eventsList = new ArrayList<>();
        String eventJson;
        List interestsList = Arrays.asList(mContext.getResources().getStringArray(R.array.interests_values));

        //read from file, and put to a buffer
        try{
            InputStream inputStream = mContext.getAssets().open("events.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();
            eventJson = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        int position = 0;
        //read the json from the buffer
        try {
            JSONObject eventsJsonObject = new JSONObject(eventJson);
            JSONArray eventJsonArray = eventsJsonObject.getJSONArray("events");

            for (int i = 0; i < eventJsonArray.length(); i++){
                JSONObject eventObject = eventJsonArray.getJSONObject(i);
                //In real case, id should be a unique number
                int id = eventObject.getInt(Constant.EVENT_ID);
                String title = eventObject.getString(Constant.EVENT_TITLE);
                String date = eventObject.getString(Constant.EVENT_DATE);
                String time = eventObject.getString(Constant.EVENT_TIME);
                String interest = eventObject.getString(Constant.EVENT_INTEREST);
                String description = eventObject.getString(Constant.EVENT_DESCRIPTION);
                String imageUrl = eventObject.getString(Constant.EVENT_IMAGE_URL);
                String location = eventObject.getString(Constant.EVENT_LOCATION);

                //check if the interest exists, get the position
                if (interestsList.contains(interest)){
                    position = interestsList.indexOf(interest);
                }

                //check if the interest in preferences is checked by user, then add to list
                if (interestsArray[position]){
                    EventModel event = new EventModel(id, title, description, date, time, location, imageUrl, interest);
                    eventsList.add(event);
                }

            }

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }


        return eventsList;
    }

    public ArrayList<EventModel> getEventsList(Integer[] eventIds){
        //initializing
        eventsList = new ArrayList<>();
        String eventJson;
        List eventIdList = Arrays.asList(eventIds);

        //read from file, and put to a buffer
        try{
            InputStream inputStream = mContext.getAssets().open("events.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];

            inputStream.read(buffer);
            inputStream.close();
            eventJson = new String(buffer, "UTF-8");

        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

        //read the json from the buffer
        try {
            JSONObject eventsJsonObject = new JSONObject(eventJson);
            JSONArray eventJsonArray = eventsJsonObject.getJSONArray("events");

            for (int i = 0; i < eventJsonArray.length(); i++){
                Boolean ifExists = false;
                JSONObject eventObject = eventJsonArray.getJSONObject(i);
                //In real case, id should be a unique number
                int id = eventObject.getInt(Constant.EVENT_ID);
                String title = eventObject.getString(Constant.EVENT_TITLE);
                String date = eventObject.getString(Constant.EVENT_DATE);
                String time = eventObject.getString(Constant.EVENT_TIME);
                String interest = eventObject.getString(Constant.EVENT_INTEREST);
                String description = eventObject.getString(Constant.EVENT_DESCRIPTION);
                String imageUrl = eventObject.getString(Constant.EVENT_IMAGE_URL);
                String location = eventObject.getString(Constant.EVENT_LOCATION);

                //Log.d(TAG, "getEventsList: " + id);
                //check if the id exists, get the position
                if (eventIdList.contains(id)){
                    ifExists = true;
                }
                //Log.d(TAG, "getEventsList: " + ifExists);

                //if event id exists in the parameter array then put to returning list
                if (ifExists){
                    EventModel event = new EventModel(id, title, description, date, time, location, imageUrl, interest);
                    eventsList.add(event);
                }
            }

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }


        return eventsList;
    }


}
