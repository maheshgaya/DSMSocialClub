package com.duse.android.dsmsocialclub.activity;


import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.fragment.ExploreFragment;
import com.duse.android.dsmsocialclub.fragment.FavoritesFragment;
import com.duse.android.dsmsocialclub.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;

//TODO: This activity will contain the splashing screen with a timer
public class MainActivity extends AppCompatActivity {
    private final String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final android.os.Handler handler = new android.os.Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do nothing for 5s
                //intent to start SocialActivity
                Intent socialIntent = new Intent(getApplicationContext(), SocialActivity.class);
                //start SocialActivity (should be trigger after user clicks on Save/Done for user profile)
                MainActivity.this.startActivity(socialIntent);

                //remove activity from history stack
                MainActivity.this.finish();
            }
        }, 5000);



    }
}
