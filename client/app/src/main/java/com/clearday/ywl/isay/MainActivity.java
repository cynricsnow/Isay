package com.clearday.ywl.isay;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.clearday.ywl.isay.adapter.OrderSpinnerAdapter;
import com.clearday.ywl.isay.adapter.SectionsPagerAdapter;

import java.io.IOException;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Tab1Fragment.OnFragmentInteractionListener{
    private int targetPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);

        // Setup spinner
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setAdapter(new OrderSpinnerAdapter(
                toolbar.getContext(),
                new String[]{
                        "离我最近",
                        "最近发布",
                        "最受欢迎",
                }));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //getSupportFragmentManager().beginTransaction()
                //      .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                //    .commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        spinner.setVisibility(View.GONE);
        final TextView textView = (TextView)findViewById(R.id.appname);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);

        viewPager.setAdapter(sectionsPagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(positionOffset < 0.0000001 || positionOffset >0.9999999){
                    switch (position){
                        case 0:
                            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorRed500)));
                            break;
                        case 1:
                            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorIndigo500)));
                            break;
                        case 2:
                            fab.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getApplicationContext(), R.color.colorCyan500)));
                            break;
                    }
                    fab.show();
                }
                else {
                    fab.hide();
                }
                Log.i("...", "scrolled" + position+"offset"+positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        spinner.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        spinner.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                        break;
                    case 2:
                        spinner.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                        break;
                }
                targetPosition = position;
                Log.i("...", "Selected" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                switch (state){
                    case 0:Log.i("...", "state0");break;  //什么都没做
                    case 1:Log.i("...", "state1");break; //正在滑动
                    case 2:Log.i("...", "state2");break; //滑动结束
                }
            }
        });
        tabs.setupWithViewPager(viewPager);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        MenuItem menuItem = menu.findItem(R.id.action_notify);
        int count=0;
        if(count > 0){
            menuItem.setIcon(R.mipmap.ic_notifications_active_white_24dp);
        }
        return true;
    }
    public void onFragmentInteraction(Uri uri){}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_notify) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        else if(id == R.id.action_search) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        else if(id == R.id.action_new) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
