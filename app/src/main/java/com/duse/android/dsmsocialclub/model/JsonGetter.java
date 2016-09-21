package com.duse.android.dsmsocialclub.model;

import android.util.JsonReader;

import org.json.JSONArray;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class JsonGetter {
    private ArrayList<EventsModel> eventsList;

    public ArrayList<EventsModel> getEventsList(JSONArray[] eventsArrayInJson){
        return eventsList;
    }

    private JSONArray[] getEventsArrayFromJson(String[] eventObjStr){
        JSONArray[] jsonEventArray = {};
        return jsonEventArray;
    }

    private String[] readFromJsonFile(){
        String[] jsonEventObjStr = {};
        return jsonEventObjStr;
    }

    /*
    public List<EventsModel> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readEventsArray(reader);
        } finally {
            reader.close();
        }
    }
    public List<EventsModel> readEventsArray(JsonReader reader) throws IOException {
        List<EventsModel> events = new ArrayList<EventsModel>();

        reader.beginArray();
        while (reader.hasNext()) {
            events.add(readEvent(reader));
        }
        reader.endArray();
        return events;
    }
    public EventsModel readEvent(JsonReader reader) throws IOException {

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("Title")) title = reader.nextString();
            else if (name.equals("Date")) date = reader.nextString();
            else if (name.equals("Time")) time = reader.nextString();
            else if (name.equals("Interests")) interests = reader.nextString();
            else if (name.equals("Description")) description = reader.nextString();
            else if (name.equals("ImageURL")) imageURL = reader.nextString();
            else if (name.equals("Location")) location = reader.nextString();
            else reader.skipValue();
        }
        reader.endObject();
        return new EventsModel(title,description,date,time,location,imageURL,interests);
    }
    */
}
