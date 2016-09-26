package com.duse.android.dsmsocialclub.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.fragment.AboutFragment;

//@assignee: Henry
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle(getResources().getString(R.string.about_label));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container_about, new AboutFragment())
                    .commit();
        }
    }

}
