package com.duse.android.dsmsocialclub.model;


import java.util.ArrayList;

public class InterestsModel {
    //@author: @Henry

    public ArrayList<String> interests = new ArrayList<String>(15);

    public InterestsModel(){ //Constructor
        interests.set(0,"circus");
        interests.set(1,"comedy");
        interests.set(2,"culinary");
        interests.set(3,"dance movement");
        interests.set(4,"education");
        interests.set(5,"featured");
        interests.set(6,"film");
        interests.set(7,"kids");
        interests.set(8,"lifestyle");
        interests.set(9,"literacy");
        interests.set(10,"music");
        interests.set(11,"social");
        interests.set(12,"technology");
        interests.set(13,"theatre");
        interests.set(14,"visual art");
    }

}
