package com.clearday.ywl.isay.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.clearday.ywl.isay.Tab1Fragment;
import com.clearday.ywl.isay.Tab2Fragment;
import com.clearday.ywl.isay.Tab3Fragment;
/**
 * Created by Ywl on 2015/10/29.
 */

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private final String[] TITLES={"周边", "发现", "我的"};
    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Tab1Fragment();
            case 1:
                return new Tab2Fragment();
            case 2:
                return new Tab3Fragment();
            default:
                return new Tab1Fragment();
        }

    }

    @Override
    public int getCount() {
        // return TITLES.length;
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }
}
