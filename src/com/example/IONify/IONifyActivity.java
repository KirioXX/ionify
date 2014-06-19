package com.example.IONify;
import android.widget.Toast;
import com.example.IONify.adapter.NavDrawerListAdapter;

import com.example.IONify.model.NavDrawerItem;
import java.util.ArrayList;
import android.app.Activity;

import android.app.Fragment;

import android.app.FragmentManager;

import android.content.res.Configuration;

import android.content.res.TypedArray;

import android.os.Bundle;

import android.support.v4.app.ActionBarDrawerToggle;

import android.support.v4.widget.DrawerLayout;

import android.util.Log;

import android.view.Menu;

import android.view.MenuItem;

import android.view.View;

import android.widget.AdapterView;

import android.widget.ListView;
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

    private String[] navMenuTitles;

    private TypedArray navMenuIcons;



    private ArrayList<NavDrawerItem> navDrawerItems;

    private NavDrawerListAdapter adapter;

    private db data;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mTitle = mDrawerTitle = getTitle();

        // create db Objekt
        data = new db(this);
        // TODO ersten Test Eitrag erstellen

        // load slide menu items
        // TODO Element Namen als StringArray aus Datenbank ziehen
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

        // nav drawer icons from resources
        // TODO Icons durch Nummern ersetzen
        // TODO Nummern aus Datenbank holen
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

        navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer items to array
        // TODO Menue punkte mit schleife erzeugen

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));


        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

        // Sunshine

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));

        // Rainy, Will add a counter here

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));

        //Snow

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));

        // What's hot, We  will add a counter here

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1), true, "50+"));


        // Recycle the typed array

        navMenuIcons.recycle();

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
        menu.findItem(R.id.action_search).setVisible(drawerOpen);

        return super.onPrepareOptionsMenu(menu);

    }



    /**

     * Diplaying fragment view for selected nav drawer list item

     * */

    private void displayView(int position) {

        // update the main content by replacing fragments
        // TODO wenn Menue fertig hier ContentView automatisieren

        Fragment fragment = null;

        switch (position) {

            case 0:
                Log.e("MainActivity","First");
                break;

            case 1:

               //fragment = new WinterFragment();

                break;

            case 2:

                ///fragment = new RainyFragment();

                break;

            case 3:

                //fragment = new NightatFragment();

                break;

            case 4:

                //fragment = new SunshineFragment();

                break;

            case 5:

                //fragment = new WhatsHotFragment();

                break;



            default:

                break;

        }



        if (fragment != null) {

            setContent(fragment);

            // update selected item and title, then close the drawer

            mDrawerList.setItemChecked(position, true);

            mDrawerList.setSelection(position);

            setTitle(navMenuTitles[position]);

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

    public void setDataDB(){
        int version = getResources().getInteger(R.integer.db_version);
        if( version > data.getDBVersion()){
            int[] ordn = getResources().getIntArray(R.array.ordn);
            String[] name = getResources().getStringArray(R.array.name);
            String[] symbol = getResources().getStringArray(R.array.symbol);
            String[] elekk = getResources().getStringArray(R.array.elekk);
            String[] atomm = getResources().getStringArray(R.array.atomm);
            String[] schmp = getResources().getStringArray(R.array.schmp);
            String[] siedp = getResources().getStringArray(R.array.siedp);
            String[] dichte = getResources().getStringArray(R.array.dichte);
            String[] schmw = getResources().getStringArray(R.array.schmw);
            String[] spezw = getResources().getStringArray(R.array.spezw);
            for(int o : ordn){
                //TODO doppeltes eintragen abfangen!!!!
                data.insertData(ordn[o], name[o], symbol[o], elekk[o], Double.parseDouble(atomm[o]), Double.parseDouble(schmp[o]), Double.parseDouble(siedp[o]), Double.parseDouble(dichte[o]), Double.parseDouble(schmw[o]), Double.parseDouble(spezw[o]));
            }
            Toast.makeText(getBaseContext(), "Data inserted", Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getBaseContext(), "Data letzte", Toast.LENGTH_LONG).show();
        }
    }
}
