package com.duse.android.dsmsocialclub.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.duse.android.dsmsocialclub.model.EventModel;

import java.util.Arrays;

/**
 * Created by Mahesh Gaya on 9/22/16.
 */
public class EventAdapter extends ArrayAdapter<EventModel>{

    public EventAdapter(Context context, EventModel[] events){
        super(context, 0, Arrays.asList(events));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    private static class ViewHolder{

    }
}
