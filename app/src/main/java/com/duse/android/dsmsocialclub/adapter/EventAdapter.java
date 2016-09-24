package com.duse.android.dsmsocialclub.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.Constant;
import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.activity.DetailActivity;
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

    private final String TAG = EventAdapter.class.getSimpleName();
    public EventAdapter(Context context, EventModel[] events){
        eventList = Arrays.asList(events);
        mContext = context;
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
        //TODO: check for starred or unstarred
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
                    } else {
                        holder.eventStarButton.setImageResource(R.drawable.ic_star);
                        holder.eventStarButton.setTag("2");
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
