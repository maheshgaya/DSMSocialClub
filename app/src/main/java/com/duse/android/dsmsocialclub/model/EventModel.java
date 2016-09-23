package com.duse.android.dsmsocialclub.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.JsonReader;
import android.util.JsonToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class EventModel implements Parcelable{

    //add JSON parsing and JSON array
    //Tutorial: <a>http://www.tutorialspoint.com/android/android_json_parser.htm</a>
    //Tutorial: <a>https://developer.android.com/reference/android/util/JsonReader.html</a>
    //@assignee: @Henry
    String title; //the title of event
    String description; //the description of the event
    String date; //the date of the event
    String time; //the time of the event
    String location; //the location of the event
    String imageURL; //the url of the event's image
    String interests; //the interests that event concerns

    public EventModel(String title, String description, String date, String time, String location,
                      String imageURL, String interests){
        this.title = title;
        this.description = description;
        this.date = date;
        this.time = time;
        this.location = location;
        this.imageURL = imageURL;
        this.interests = interests;

    }

    private EventModel(Parcel in){
        title = in.readString();
        description = in.readString();
        date = in.readString();
        time = in.readString();
        location = in.readString();
        imageURL = in.readString();
        interests = in.readString();
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

    public String getInterests() {
        return interests;
    }

    public void setTags(String tags) {
        this.interests = tags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(location);
        parcel.writeString(imageURL);
        parcel.writeString(interests);

    }
    public static final Parcelable.Creator<EventModel> CREATOR = new Parcelable.Creator<EventModel>(){
        @Override
        public EventModel createFromParcel(Parcel parcel) {
            return new EventModel(parcel);
        }

        @Override
        public EventModel[] newArray(int i) {
            return new EventModel[i];
        }



    };
}


