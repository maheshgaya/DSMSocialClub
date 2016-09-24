package com.duse.android.dsmsocialclub.model;

import android.app.Activity;
import android.content.Context;

import com.duse.android.dsmsocialclub.Constant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * EventJsonGetter
 * This class gets the events data from the event.json file
 * It then returns that to an ArrayList
 */
public class EventJsonGetter {
    private ArrayList<EventModel> eventsList;
    private Context mContext;


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
        //intializing
        eventsList = new ArrayList<>();
        String eventJson = null;

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
                //int id = eventObject.getInt(Constant.EVENT_ID); //not needed, will use ArrayList index
                String title = eventObject.getString(Constant.EVENT_TITLE);
                String date = eventObject.getString(Constant.EVENT_DATE);
                String time = eventObject.getString(Constant.EVENT_TIME);
                String interest = eventObject.getString(Constant.EVENT_INTEREST);
                String description = eventObject.getString(Constant.EVENT_DESCRIPTION);
                String imageUrl = eventObject.getString(Constant.EVENT_IMAGE_URL);
                String location = eventObject.getString(Constant.EVENT_LOCATION);

                EventModel event = new EventModel(title, description, date, time, location, imageUrl, interest);

                eventsList.add(event);
            }

        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }


        return eventsList;
    }
}
