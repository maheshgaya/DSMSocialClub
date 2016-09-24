package com.duse.android.dsmsocialclub.model;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable{
    //@assignee: @Henry
    private String name;
    private String[] interests;


    public UserModel(Context context, String name, String[] interests){
        this.name = name;
        this.interests = interests;
    }

    private UserModel(Parcel in){
        name = in.readString();
        interests = (String[])in.readSerializable();

    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeSerializable(interests);
    }
    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>(){
        @Override
        public UserModel createFromParcel(Parcel parcel) {
            return new UserModel(parcel);
        }

        @Override
        public UserModel[] newArray(int i) {
            return new UserModel[i];
        }



    };
}
