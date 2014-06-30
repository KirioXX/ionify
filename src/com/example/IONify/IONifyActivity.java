package com.example.IONify;
import com.example.IONify.adapter.IONifyDataSource;
import com.example.IONify.adapter.NavDrawerListAdapter;

import com.example.IONify.model.Element;
import com.example.IONify.model.NavDrawerItem;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;

import android.app.Fragment;

import android.app.FragmentManager;

import android.content.res.Configuration;

import android.os.Bundle;

import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;

import android.util.Log;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;
import com.example.IONify.view.ContentFragment;
import com.example.IONify.view.HomeFragment;


public class IONifyActivity extends Activity {

    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;



    // nav drawer title

    private CharSequence mDrawerTitle;



    // used to store app title

    private CharSequence mTitle;



    // slide menu items

    private List<String> navMenuTitles;

    private ArrayList<NavDrawerItem> navDrawerItems;

    private NavDrawerListAdapter adapter;

    private IONifyDataSource datasource;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // Create DB adapter
        datasource = new IONifyDataSource(this);
        datasource.open();

        // load slide menu items from db
        navMenuTitles = datasource.getMenuTitleList();

        datasource.close();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer items to array
        for (String temp : navMenuTitles) {
            navDrawerItems.add(new NavDrawerItem(temp));
        }

        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

        // setting the nav drawer list adapter

        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);

        mDrawerList.setAdapter(adapter);



        // enabling action bar app icon and behaving it as toggle button

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_navigation_drawer, //nav menu toggle icon

                R.string.app_name, // nav drawer open - description for accessibility

                R.string.app_name // nav drawer close - description for accessibility

        ) {

            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);

                // calling onPrepareOptionsMenu() to show action bar icons

                invalidateOptionsMenu();

            }



            public void onDrawerOpened(View drawerView) {

                getActionBar().setTitle(mDrawerTitle);

                // calling onPrepareOptionsMenu() to hide action bar icons

                invalidateOptionsMenu();

            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);



        if (savedInstanceState == null) {

            // on first time display view for first nav item

            Fragment fragment=new HomeFragment();
            setContent(fragment);
            setTitle("IONify");

        }


    }



    /**

     * Slide menu item click listener

     * */

    private class SlideMenuClickListener implements

            ListView.OnItemClickListener {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            // display view for selected nav drawer item

            displayView(position);

        }

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return true;

    }



    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        // toggle nav drawer on selecting action bar app icon/title

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;

        }

        // Handle action bar actions click

        switch (item.getItemId()) {

            case R.id.action_help:
                Fragment fragment=new HomeFragment();
                setContent(fragment);
                setTitle(item.getTitleCondensed());
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }

    }



    /***

     * Called when invalidateOptionsMenu() is triggered

     */

    @Override

    public boolean onPrepareOptionsMenu(Menu menu) {

        // if nav drawer is opened, hide the action items

        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        menu.findItem(R.id.action_help).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);

    }



    /**

     * Diplaying fragment view for selected nav drawer list item

     * */

    private void displayView(int position) {

        // update the main content by replacing fragments

        Fragment fragment = null;
        Element element = null;
        datasource.open();
            element = datasource.getElementContent(position+1);
        datasource.close();
        fragment = new ContentFragment(element);


        if (fragment != null) {

            setContent(fragment);

            // update selected item and title, then close the drawer

            mDrawerList.setItemChecked(position, true);

            mDrawerList.setSelection(position);

            setTitle(navMenuTitles.get(position));

            mDrawerLayout.closeDrawer(mDrawerList);

        } else {

            // error in creating fragment

            Log.e("MainActivity", "Error in creating fragment");

        }

    }



    @Override

    public void setTitle(CharSequence title) {

        mTitle = title;

        getActionBar().setTitle(mTitle);

    }

    /**

     * When using the ActionBarDrawerToggle, you must call it during

     * onPostCreate() and onConfigurationChanged()...

     */



    @Override

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        // Sync the toggle state after onRestoreInstanceState has occurred.

        mDrawerToggle.syncState();

    }

    @Override

    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        // Pass any configuration change to the drawer toggls

        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    public void setContent(Fragment fragment){
        FragmentManager fragmentManager = getFragmentManager();

        fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
    }

    public void openDrawer(){
        mDrawerLayout.openDrawer(mDrawerList);
    }
}
