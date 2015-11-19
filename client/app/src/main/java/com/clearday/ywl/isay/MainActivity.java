package com.clearday.ywl.isay;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.SharedElementCallback;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.clearday.ywl.isay.adapter.OrderSpinnerAdapter;
import com.clearday.ywl.isay.adapter.SectionsPagerAdapter;
import com.clearday.ywl.isay.map.BMapActivity;
import com.clearday.ywl.isay.map.TestMapActivity;

import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,Tab1Fragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BMapActivity.class);
                // mode 0: my location mode 1: watching location
                //intent.putExtra("mode",0);
                intent.putExtra("mode", 1);
                intent.putExtra("lat", 31.2332);
                intent.putExtra("lng", 87.2134);
                MainActivity.this.startActivity(intent);
            }
        });
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
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        spinner.setVisibility(View.VISIBLE);
                        textView.setVisibility(View.GONE);
                        if(fab.getVisibility()==View.GONE) {
                            fab.show();
                        }
                        break;
                    case 1:
                        spinner.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        if(fab.getVisibility()==View.VISIBLE){
                            fab.hide();
                        }
                        break;
                    case 2:
                        spinner.setVisibility(View.GONE);
                        textView.setVisibility(View.VISIBLE);
                        if(fab.getVisibility()==View.VISIBLE){
                            fab.hide();
                        }
                        break;
                }
                Log.i("...", "Selected" + position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
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
        //View headerView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        View headerView = LayoutInflater.from(this).inflate(R.layout.nav_header_main, navigationView, true);
        CircleImageView circleImageView = (CircleImageView)headerView.findViewById(R.id.circleImageView);
        if(circleImageView!=null){
            circleImageView.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    MainActivity.this.startActivity(intent);
                    Log.i("...", "Selected");
                }
            });
        }
        else{
            Log.i("...", "not find");
        }
        TextView textView1 = (TextView)headerView.findViewById(R.id.ywl);
        textView1.setText("asdaasafdf");
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
            Intent intent = new Intent(MainActivity.this, TestMapActivity.class);
            MainActivity.this.startActivity(intent);
            return true;
        }
        else if(id == R.id.action_new) {
            Intent intent = new Intent(MainActivity.this, BMapActivity.class);
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

        if (id == R.id.nav_theme) {
            Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
            MainActivity.this.startActivity(intent);
        } else if (id == R.id.nav_settings) {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            MainActivity.this.startActivity(intent);
        }else if (id == R.id.nav_feedback) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:610014042@qq.com"));
            //emailIntent.setType("text/email");
            //emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"610014042@qq.com"});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "意见反馈");
            //Email.putExtra(Intent.EXTRA_TEXT, "Dear ...," + "");
            startActivity(Intent.createChooser(emailIntent, "发送反馈"));
        }else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            //shareIntent.setAction(Intent.ACTION_SEND);
            shareIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            shareIntent.setType("text/plain");
            startActivity(shareIntent);
        } else if (id == R.id.nav_update) {

        } else if (id == R.id.nav_aboout) {
            Intent intent = new Intent(MainActivity.this, AboutActivity.class);
            MainActivity.this.startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
