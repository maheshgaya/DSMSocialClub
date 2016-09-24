package com.duse.android.dsmsocialclub.model;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class UserFavoritesModel implements Parcelable{
    //@author: @Henry, @Mahesh

    private ArrayList<Integer> favoriteEvents = new ArrayList<Integer>(){} ;//initialize Array

    public UserFavoritesModel(Context context, int eventID) {    //Constructor
        this.favoriteEvents.add(eventID);
    }

    private UserFavoritesModel(Parcel in){
        favoriteEvents = (ArrayList<Integer>) in.readSerializable();
    }

    public void addEvent(int eventID){    //function to add an event
        favoriteEvents.add(eventID);
    }

    public void removeEvent(int eventID){    // function to remove an event
        if (favoriteEvents.contains(eventID)){
            favoriteEvents.remove(eventID); //removing the event
        }
    }

    public String toString(){
        return favoriteEvents.toString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(favoriteEvents);
    }
    public static final Parcelable.Creator<UserFavoritesModel> CREATOR = new Parcelable.Creator<UserFavoritesModel>(){
        @Override
        public UserFavoritesModel createFromParcel(Parcel parcel) {
            return new UserFavoritesModel(parcel);
        }

        @Override
        public UserFavoritesModel[] newArray(int i) {
            return new UserFavoritesModel[i];
        }



    };
}
