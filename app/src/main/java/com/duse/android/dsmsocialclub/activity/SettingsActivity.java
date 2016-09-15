package com.duse.android.dsmsocialclub.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.adapter.SettingsAdapter;


/**
 * Created by Mahesh Gaya on 9/14/16.
 */
public class SettingsActivity extends AppCompatActivity{
    private SettingsAdapter mSettingsAdapter;
    private ListView mListView;
    //TAG = ClassName, i.e. SettingsActivity
    private final  String TAG = this.getClass().getSimpleName();

    private Toolbar mToolbar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mToolbar = (Toolbar) findViewById(R.id.toolbar_settings);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        String[] settingsArray;
        settingsArray = getResources().getStringArray(R.array.settings_items);

        mListView = (ListView)findViewById(R.id.listview_settings);
        mSettingsAdapter = new SettingsAdapter(this, settingsArray);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Object obj = mListView.getAdapter().getItem(position);
                Toast.makeText(getApplicationContext(), obj.toString() + " : "+ position, Toast.LENGTH_LONG).show();

                if (obj.toString() == getResources().getString(R.string.settings_item_interests)){
                    //TODO: start activity for interests
                } else if (obj.toString() == getResources().getString(R.string.settings_item_about)){
                    //TODO: start activity for about
                }

            }
        });
        mListView.setAdapter(mSettingsAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionMenu(Menu menu)
    {
        //blank, not needed
        return true;
    }

}
