package com.duse.android.dsmsocialclub.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.Constant;
import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.activity.DetailActivity;
import com.duse.android.dsmsocialclub.database.FavoriteContract;
import com.duse.android.dsmsocialclub.database.FavoriteDBHelper;
import com.duse.android.dsmsocialclub.model.EventModel;
import com.squareup.picasso.Picasso;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Mahesh Gaya on 9/22/16.
 */
public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder>{

    private List<EventModel> eventList;
    private Context mContext;

    private FavoriteDBHelper favoriteDBHelper;
    private SQLiteDatabase dbWrite;
    private SQLiteDatabase dbRead;

    private final String TAG = EventAdapter.class.getSimpleName();
    public EventAdapter(Context context, EventModel[] events){
        eventList = Arrays.asList(events);
        mContext = context;
        //initialize database
        favoriteDBHelper = new FavoriteDBHelper(context);
        //get the ability to write to database
        dbWrite = favoriteDBHelper.getWritableDatabase();
        //get the ability to write to database
        dbRead = favoriteDBHelper.getReadableDatabase();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_event_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final EventModel event = eventList.get(position);
        //Create a new map of values, where column names are the keys
        final ContentValues values = new ContentValues();
        //read from sqlite
        String[] projection = {
                FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID,
                FavoriteContract.FeedEntry.FAVORITE_COLUMN_BOOLEAN
        };
        String selection = FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID + " =?";
        String[] selectionArgs = {Integer.toString(event.getId())};
        Cursor cursor = dbRead.query(
                FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME, //Table name
                projection, //columns to return
                selection, //columns for WHERE Clause
                selectionArgs, //Values for WHERE Clause
                null, //don't group the rows
                null, //don't filter by row groups
                null //don't sort(since value will be unique)
        );


        //holder.eventStarButton.setImageResource(R.drawable.ic_star);
        //holder.eventStarButton.setTag("2");
        holder.eventStarButton.setImageResource(R.drawable.ic_star_border);
        holder.eventStarButton.setTag("1");

        holder.eventStarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == holder.eventStarButton){
                    if (holder.eventStarButton.getTag().equals("2")){
                        holder.eventStarButton.setImageResource(R.drawable.ic_star_border);
                        holder.eventStarButton.setTag("1");
                        //remove from database


                    } else {
                        holder.eventStarButton.setImageResource(R.drawable.ic_star);
                        holder.eventStarButton.setTag("2");
                        //add to database
                        values.put(FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID, event.getId());
                        values.put(FavoriteContract.FeedEntry.FAVORITE_COLUMN_BOOLEAN, Constant.FAVORITE_BOOLEAN_TRUE);
                        dbWrite.insert(FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME, null, values);
                    }
                }
            }
        });
        holder.eventTitleTextView.setText(event.getTitle());
        holder.eventDateTextView.setText(event.getDate());
        holder.eventTimeLocationTextView.setText(event.getTime() + " | " + event.getLocation());

        holder.eventInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra(Constant.EXTRA_EVENT_PARCELABLE, event);
                mContext.startActivity(intent);
            }
        });

        String imageUrl = event.getImageURL();
        if (imageUrl.isEmpty()){
            imageUrl = null;
        }
        try {
            Picasso
                    .with(mContext)
                    .load(imageUrl)
                    .error(R.drawable.ic_photo_placeholder)
                    .into(holder.eventImageView);
            //Log.d(TAG, "getView: Picasso executed");
        }catch (java.lang.IllegalArgumentException e){
            Log.e(TAG, "getView: " + e.getMessage(), e );
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView eventImageView;
        TextView eventTitleTextView;
        TextView eventDateTextView;
        TextView eventTimeLocationTextView;
        ImageButton eventStarButton;
        ImageButton eventInfoButton;

        public ViewHolder(View itemView) {
            super(itemView);
            eventImageView = (ImageView)itemView.findViewById(R.id.imageview_card);
            eventTitleTextView = (TextView)itemView.findViewById(R.id.textview_card_title);
            eventDateTextView = (TextView)itemView.findViewById(R.id.textview_card_date);
            eventTimeLocationTextView = (TextView)itemView.findViewById(R.id.textview_card_time_location);
            eventStarButton = (ImageButton)itemView.findViewById(R.id.button_card_star);
            eventInfoButton = (ImageButton) itemView.findViewById(R.id.button_card_info);
        }
    }
}
