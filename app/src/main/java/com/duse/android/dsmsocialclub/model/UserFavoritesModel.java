package com.duse.android.dsmsocialclub.model;


import android.os.Parcel;
import android.os.Parcelable;

public class UserFavoritesModel implements Parcelable{
    //@author: @Henry

    private Integer eventID;//initialize Array

    public UserFavoritesModel(int eventID) {    //Constructor
        this.eventID = eventID;
    }

    private UserFavoritesModel(Parcel in){
        eventID = in.readInt();
    }


    public String toString(){
        return eventID.toString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(eventID);
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
