package com.duse.android.dsmsocialclub.activity;


import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import com.duse.android.dsmsocialclub.R;
import com.duse.android.dsmsocialclub.fragment.ExploreFragment;
import com.duse.android.dsmsocialclub.fragment.FavoritesFragment;
import com.duse.android.dsmsocialclub.fragment.HomeFragment;

import java.util.ArrayList;
import java.util.List;

//All the code here will go to SocialActivity
//This was just a test
//MainActivity will deal with user profile setup
public class MainActivity extends AppCompatActivity {

    //menu toolbar (the one with the title)
    private Toolbar mToolbar;
    //tabs toolbar (the one with Home, Explore, Favorites
    private TabLayout mTabLayout;
    //contains the fragment home, explore, favorites
    private ViewPager mViewPager;
    //icons for tabs
    private int[] tabIcons = {
            R.drawable.ic_home,
            R.drawable.ic_event,
            R.drawable.ic_star
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the UI for this activity
        setContentView(R.layout.activity_social);

        //creates the toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        //handles the tabs and fragments
        mViewPager = (ViewPager)findViewById(R.id.viewpager);
        setupViewPager(mViewPager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();

    }

    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //gets the id of the option selected
        int id = item.getItemId();

        //handles the logic for the options selected
        if (id == R.id.action_settings){
            //opens settings activity
            Intent settingsIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingsIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //just shows the icons/menu/3-dots on the UI
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }

    private void setupViewPager(ViewPager viewPager){
        //handles the fragments
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        //opens home fragment
        viewPagerAdapter.addFragment(new HomeFragment(), getResources().getString(R.string.home_fragment_title));
        //opens explore fragment
        viewPagerAdapter.addFragment(new ExploreFragment(), getResources().getString(R.string.explore_fragment_title));
        //opens favorites fragment
        viewPagerAdapter.addFragment(new FavoritesFragment(), getResources().getString(R.string.favorites_fragment_title));
        viewPager.setAdapter(viewPagerAdapter);

    }
    class ViewPagerAdapter extends FragmentPagerAdapter{
        /*
        * adapter for view pager
        * this is where all the tab magic occurs
         */
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        //constructor
        public ViewPagerAdapter(FragmentManager manager){
            super(manager);
        }

        //gets fragment from list
        @Override
        public Fragment getItem(int position){
            return  mFragmentList.get(position);
        }

        //gets size of the fragment list
        @Override
        public int getCount(){
            return mFragmentList.size();
        }

        //add a new fragment to the list of fragments with title
        public void addFragment(Fragment fragment, String title){
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        //gets the title of the fragment
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
