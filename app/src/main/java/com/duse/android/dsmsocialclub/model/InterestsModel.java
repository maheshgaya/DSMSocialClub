package com.duse.android.dsmsocialclub.model;


import java.util.ArrayList;

public class InterestsModel {
    //@author: @Henry

    public ArrayList<String> interests = new ArrayList<String>(15);

    public InterestsModel(){ //Constructor
        this.interests.set(0,"circus");
        this.interests.set(1,"comedy");
        this.interests.set(2,"culinary");
        this.interests.set(3,"dance movement");
        this.interests.set(4,"education");
        this.interests.set(5,"featured");
        this.interests.set(6,"film");
        this.interests.set(7,"kids");
        this.interests.set(8,"lifestyle");
        this.interests.set(9,"literacy");
        this.interests.set(10,"music");
        this.interests.set(11,"social");
        this.interests.set(12,"technology");
        this.interests.set(13,"theatre");
        this.interests.set(14,"visual art");
    }

}
