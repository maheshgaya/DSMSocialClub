package com.duse.android.dsmsocialclub.model;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahesh Gaya on 9/14/16.
 */
public class EventsModel {
    //TODO: add functions to this class
    //add JSON parsing and JSON array
    //Tutorial: <a>http://www.tutorialspoint.com/android/android_json_parser.htm</a>
    //Better Tutorial: <a>https://developer.android.com/reference/android/util/JsonReader.html</a>
    //@assignee: @Henry
    String title;
    String description;
    String date;
    String time;
    String location;
    String imageURL;
    String tags;

    public EventsModel(String ptitle, String pdescription, String pdate, String ptime, String plocation, String pimageURL, String ptags){
        title = ptitle; description = pdescription; date = pdate; time = ptime; location = plocation; imageURL = pimageURL; tags = ptags;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}

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
            else if (name.equals("Interests")) tags = reader.nextString();
            else if (name.equals("Description")) description = reader.nextString();
            else if (name.equals("ImageURL")) imageURL = reader.nextString();
            else if (name.equals("Location")) location = reader.nextString();
            else reader.skipValue();
        }
        reader.endObject();
        return new EventsModel(title,description,date,time,location,imageURL,tags);
    }
