package com.duse.android.dsmsocialclub.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.duse.android.dsmsocialclub.Constant;
import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.model.EventModel;
import com.squareup.picasso.Picasso;


//TODO: This Activity will show each individual events
    //So when you tap on the event this activity will be triggered
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(getResources().getString(R.string.detail_title));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_detail, new DetailFragment())
                    .commit();
        }
    }

    /**
     * onCreateOptionsMenu
     * @param menu
     * inflate the menu for this activity
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    /**
     * onOptionsItemSelected
     * @param item
     * check if action_settings is tapped
     *      opens settings activity if it is the case
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(this.getApplicationContext(), SettingsActivity.class);
            startActivity(settingsIntent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public static class DetailFragment extends Fragment{
        public static final String LOG_TAG = DetailFragment.class.getSimpleName();

        //the views
        ImageView detailImageView;
        TextView detailTitleTextView;
        TextView detailDateTextView;
        TextView detailTimeLocationPriceTextView;
        ImageButton detailStarButton;
        TextView detailDescriptionTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();

            //initialize the views -- get the reference
            detailImageView = (ImageView)rootView.findViewById(R.id.imageview_detail);
            detailTitleTextView = (TextView)rootView.findViewById(R.id.textview_detail_title);
            detailDateTextView = (TextView)rootView.findViewById(R.id.textview_detail_date);
            detailTimeLocationPriceTextView = (TextView)rootView.findViewById(R.id.textview_detail_time_location_price);
            detailStarButton = (ImageButton)rootView.findViewById(R.id.button_detail_star);
            detailDescriptionTextView = (TextView)rootView.findViewById(R.id.textview_detail_description);

            if (intent != null){
                if (intent.hasExtra(Constant.EXTRA_EVENT_PARCELABLE)){
                    EventModel event = (EventModel)intent.getParcelableExtra(Constant.EXTRA_EVENT_PARCELABLE);
                    try {
                        Picasso
                                .with(getContext())
                                .load(event.getImageURL())
                                .error(R.drawable.ic_photo_placeholder)
                                .into(detailImageView);
                    } catch (java.lang.IllegalArgumentException e){
                        Picasso
                                .with(getContext())
                                .load(R.drawable.ic_photo_placeholder)
                                .into(detailImageView);

                        Log.e(LOG_TAG, "onCreateView: ",e );
                        e.printStackTrace();
                    }
                    detailTitleTextView.setText(event.getTitle());
                    detailDateTextView.setText(event.getDate());
                    detailTimeLocationPriceTextView.setText(event.getTime() + " | " +
                            event.getLocation()); // + " | " + event.getPrice());
                    detailDescriptionTextView.setText(event.getDescription());


                    //TODO: Check if user has already favorite this event
                    //detailStarButton.setImageResource(R.drawable.ic_star);
                    //detailStarButton.setTag("2");
                    detailStarButton.setImageResource(R.drawable.ic_star_border);
                    detailStarButton.setTag("1");

                    detailStarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view == detailStarButton){
                                if (detailStarButton.getTag().equals("2")){
                                    detailStarButton.setImageResource(R.drawable.ic_star_border);
                                    detailStarButton.setTag("1");
                                } else {
                                    detailStarButton.setImageResource(R.drawable.ic_star);
                                    detailStarButton.setTag("2");
                                }
                            }
                        }
                    });

                }
            }

            return rootView;
        }
    }
}
