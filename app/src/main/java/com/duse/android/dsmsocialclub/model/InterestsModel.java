package com.duse.android.dsmsocialclub.model;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.duse.android.dsmsocialclub.R;


public class InterestsModel implements Parcelable{

    //@author: @Henry, @Mahesh
    private String[] interests;

    public InterestsModel(Context context){ //Constructor
        interests = context.getResources().getStringArray(R.array.interests_items);
    }
    private InterestsModel(Parcel in){
        interests = (String[])in.readSerializable();
    }

    public String getItem(int position){
        return interests[position];
    }

    public String[] getInterests() {
        return interests;
    }

    public void setInterests(String[] interests) {
        this.interests = interests;
    }

    public String toString(){
        return interests.toString();
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(interests);
    }

    public static final Parcelable.Creator<InterestsModel> CREATOR = new Parcelable.Creator<InterestsModel>(){
        @Override
        public InterestsModel createFromParcel(Parcel parcel) {
            return new InterestsModel(parcel);
        }

        @Override
        public InterestsModel[] newArray(int i) {
            return new InterestsModel[i];
        }



    };





}
