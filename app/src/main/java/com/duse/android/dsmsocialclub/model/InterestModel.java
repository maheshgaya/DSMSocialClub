package com.duse.android.dsmsocialclub.model;



import android.os.Parcel;
import android.os.Parcelable;



public class InterestModel implements Parcelable{

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    //@author: @Henry
    private String interest;

    public InterestModel(String interest){ //Constructor
        this.interest = interest;
    }
    private InterestModel(Parcel in){
        interest = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(interest);
    }

    public static final Parcelable.Creator<InterestModel> CREATOR = new Parcelable.Creator<InterestModel>(){
        @Override
        public InterestModel createFromParcel(Parcel parcel) {
            return new InterestModel(parcel);
        }

        @Override
        public InterestModel[] newArray(int i) {
            return new InterestModel[i];
        }



    };





}
