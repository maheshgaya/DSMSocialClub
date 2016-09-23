package com.duse.android.dsmsocialclub.model;

import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class EventModel {

    //add JSON parsing and JSON array
    //Tutorial: <a>http://www.tutorialspoint.com/android/android_json_parser.htm</a>
    //Better Tutorial: <a>https://developer.android.com/reference/android/util/JsonReader.html</a>
    //@author: @Henry
    String title; //the title of event
    String description; //the description of the event
    String date; //the date of the event
    String time; //the time of the event
    String location; //the location of the event
    String imageURL; //the url of the event's image
    String interests; //the interests that event concerns

    public EventModel(String ptitle, String pdescription, String pdate, String ptime, String plocation, String pimageURL, String ptags){
        title = ptitle; description = pdescription; date = pdate; time = ptime; location = plocation; imageURL = pimageURL; interests = ptags;

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
        return interests;
    }

    public void setTags(String tags) {
        this.interests = tags;
    }
}


