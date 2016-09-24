package com.duse.android.dsmsocialclub.model;


import java.util.ArrayList;

public class UserFavoritesModel {
    //@author: @Henry

    protected String user; //String for referencing the user
    protected ArrayList<Integer> eventsArray = new ArrayList<Integer>(1); //an array to hold the numbers of the favorited events

    public UserFavoritesModel() {    //Constructor
        this.eventsArray.set(0, -1); // initialize the array to -1 to ensure that event 0 is not contained
    }
    public void addEvent(Integer eventID){    //function to add an event
        this.eventsArray.ensureCapacity(this.eventsArray.size()+1); //increase array size by 1
        this.eventsArray.add(eventID);   // append the event to the end
    }

    public void removeEvent(Integer eventID){    // function to remove an event
        if (this.eventsArray.contains(eventID)){
            this.eventsArray.remove(eventID); //removing the event
        }
    }

    public boolean contains(Integer eventID){  // function to test if an event is contained
        return this.eventsArray.contains(eventID);
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
