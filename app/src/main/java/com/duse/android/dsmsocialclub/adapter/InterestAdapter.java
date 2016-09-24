package com.duse.android.dsmsocialclub.adapter;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.model.InterestModel;
import com.squareup.picasso.Picasso;

import java.util.Arrays;

public class InterestAdapter extends ArrayAdapter<InterestModel>{
    private final String TAG = InterestAdapter.class.getSimpleName();

    public InterestAdapter(Context context, InterestModel[] interests){
        super(context, 0, Arrays.asList(interests));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        InterestModel interest = getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.interest_grid_item, parent, false);
            holder = new ViewHolder();
            holder.interestImageView = (ImageView)convertView.findViewById(R.id.imageview_interest);
            holder.interestTextView = (TextView)convertView.findViewById(R.id.textview_interest);
            holder.interestCheckBox = (CheckBox)convertView.findViewById(R.id.checkbox_interest);

            convertView.setTag(holder);
        } else{
            holder= (ViewHolder) convertView.getTag();
        }

        int imageUrlInt = 0;

        if (interest.getInterest().equals(R.string.interest_circus)){
            imageUrlInt = R.drawable.interests_circus;
        } else  if (interest.getInterest().equals(R.string.interest_comedy)){
            imageUrlInt = R.drawable.interests_comedy;
        } else if (interest.getInterest().equals(R.string.interest_culinary)){
            imageUrlInt = R.drawable.interests_culinary;
        } else if (interest.getInterest().equals(R.string.interest_dance_movement)){
            imageUrlInt = R.drawable.interests_dance;
        } else if (interest.getInterest().equals(R.string.interest_education)){
            imageUrlInt = R.drawable.interests_education;
        } else if (interest.getInterest().equals(R.string.interest_featured_event)){
            imageUrlInt = R.drawable.splashscreen;
        } else if (interest.getInterest().equals(R.string.interest_film)){
            imageUrlInt = R.drawable.interests_film;
        } else if (interest.getInterest().equals(R.string.interest_kids)){
            imageUrlInt = R.drawable.interests_kids;
        } else if (interest.getInterest().equals(R.string.interest_lifestyle)){
            imageUrlInt = R.drawable.interests_figure_drawing;
        } else if (interest.getInterest().equals(R.string.interest_literary)){
            imageUrlInt = R.drawable.interests_literary;
        } else if (interest.getInterest().equals(R.string.interest_music)){
            imageUrlInt = R.drawable.interests_music;
        } else if (interest.getInterest().equals(R.string.interest_social)){
            imageUrlInt = R.drawable.interests_social;
        } else if (interest.getInterest().equals(R.string.interest_technology)){
            imageUrlInt = R.drawable.interests_technology;
        } else if (interest.getInterest().equals(R.string.interest_theater)){
            imageUrlInt = R.drawable.interests_theater;
        } else if (interest.getInterest().equals(R.string.interest_visual_art)){
            imageUrlInt = R.drawable.interests_visual_arts;
        }


        try {
            Picasso
                    .with(getContext())
                    .load(imageUrlInt)
                    .error(R.drawable.ic_photo_placeholder)
                    .into(holder.interestImageView);
        }catch (java.lang.IllegalArgumentException e){
            Log.e(TAG, "getView: " + e.getMessage(), e );
            e.printStackTrace();
        }
        return convertView;
    }


    public static class ViewHolder{
        ImageView interestImageView;
        TextView interestTextView;
        CheckBox interestCheckBox;

    }
}
