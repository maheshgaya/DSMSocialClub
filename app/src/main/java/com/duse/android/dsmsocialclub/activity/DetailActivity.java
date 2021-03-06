package com.duse.android.dsmsocialclub.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import com.duse.android.dsmsocialclub.Constant;
import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.database.FavoriteContract;
import com.duse.android.dsmsocialclub.database.FavoriteDBHelper;
import com.duse.android.dsmsocialclub.model.EventModel;
import com.squareup.picasso.Picasso;



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

        private FavoriteDBHelper favoriteDBHelper;
        private SQLiteDatabase dbWrite;
        private SQLiteDatabase dbRead;

        //the views
        ImageView detailImageView;
        TextView detailTitleTextView;
        TextView detailInterestTextView;
        TextView detailDateTextView;
        TextView detailTimeLocationPriceTextView;
        ImageButton detailStarButton;
        TextView detailDescriptionTextView;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);
            Intent intent = getActivity().getIntent();

            //get link to database
            favoriteDBHelper = new FavoriteDBHelper(getContext());
            //get the ability to write to database
            dbWrite = favoriteDBHelper.getWritableDatabase();
            //get the ability to write to database
            dbRead = favoriteDBHelper.getReadableDatabase();

            //initialize the views -- get the reference
            detailImageView = (ImageView)rootView.findViewById(R.id.imageview_detail);
            detailTitleTextView = (TextView)rootView.findViewById(R.id.textview_detail_title);
            detailInterestTextView = (TextView)rootView.findViewById(R.id.textview_detail_interest);
            detailDateTextView = (TextView)rootView.findViewById(R.id.textview_detail_date);
            detailTimeLocationPriceTextView = (TextView)rootView.findViewById(R.id.textview_detail_time_location_price);
            detailStarButton = (ImageButton)rootView.findViewById(R.id.button_detail_star);
            detailDescriptionTextView = (TextView)rootView.findViewById(R.id.textview_detail_description);

            if (intent != null){
                if (intent.hasExtra(Constant.EXTRA_EVENT_PARCELABLE)){
                    final EventModel event = (EventModel)intent.getParcelableExtra(Constant.EXTRA_EVENT_PARCELABLE);
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
                    detailInterestTextView.setText(event.getInterests());
                    detailDateTextView.setText(event.getDate());
                    detailTimeLocationPriceTextView.setText(event.getTime() + " | " +
                            event.getLocation()); // + " | " + event.getPrice());
                    detailDescriptionTextView.setText(event.getDescription());


                    //TODO: Check if user has already favorite this event
                    //Create a new map of values, where column names are the keys
                    final ContentValues values = new ContentValues();
                    //read from sqlite
                    String[] projection = {
                            FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID,
                            FavoriteContract.FeedEntry.FAVORITE_COLUMN_BOOLEAN
                    };
                    final String selection = FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID + " =?";
                    final String[] selectionArgs = {Integer.toString(event.getId())};

                    Cursor cursor = dbRead.query(
                            FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME, //Table name
                            projection, //columns to return
                            selection, //columns for WHERE Clause
                            selectionArgs, //Values for WHERE Clause
                            null, //don't group the rows
                            null, //don't filter by row groups
                            null //don't sort(since value will be unique)
                    );


                    if (cursor != null){
                        cursor.moveToFirst();
                    }

                    //initialize
                    int favoriteEventId = -1;
                    int favoriteEventBoolean = 0;
                    try {
                        favoriteEventId = cursor.getInt(0);
                        favoriteEventBoolean =  cursor.getInt(1);
                    }catch (android.database.CursorIndexOutOfBoundsException e){
                        //reset boolean
                        favoriteEventBoolean = 0;
                        //Log.e(TAG, "onBindViewHolder: ", e );
                        //e.printStackTrace();
                    }finally {
                        cursor.close();
                    }


                    //check database to see if event is favorite or not
                    if (favoriteEventBoolean == 0){
                        detailStarButton.setImageResource(R.drawable.ic_star_border);
                        detailStarButton.setTag("1");
                    } else if (favoriteEventBoolean == 1){
                        detailStarButton.setImageResource(R.drawable.ic_star);
                        detailStarButton.setTag("2");
                    };

                    detailStarButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (view == detailStarButton){
                                if (detailStarButton.getTag().equals("2")){
                                    detailStarButton.setImageResource(R.drawable.ic_star_border);
                                    detailStarButton.setTag("1");
                                    //remove from database
                                    dbWrite.delete(FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME,
                                            selection,
                                            selectionArgs);
                                    Toast.makeText(getContext(), getResources().getString(R.string.event_removed), Toast.LENGTH_SHORT).show();
                                } else {
                                    detailStarButton.setImageResource(R.drawable.ic_star);
                                    detailStarButton.setTag("2");
                                    //add to database
                                    values.put(FavoriteContract.FeedEntry.FAVORITE_COLUMN_EVENT_ID, event.getId());
                                    values.put(FavoriteContract.FeedEntry.FAVORITE_COLUMN_BOOLEAN, Constant.FAVORITE_BOOLEAN_TRUE);
                                    long rowId = dbWrite.insert(FavoriteContract.FeedEntry.FAVORITE_TABLE_NAME, null, values);
                                    Log.d(LOG_TAG, "onClick: " + rowId);
                                    Toast.makeText(getContext(), getResources().getString(R.string.event_added), Toast.LENGTH_SHORT).show();
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
