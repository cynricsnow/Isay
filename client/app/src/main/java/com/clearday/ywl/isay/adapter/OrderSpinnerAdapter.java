package com.clearday.ywl.isay.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by Ywl on 2015/10/29.
 */
public class OrderSpinnerAdapter extends ArrayAdapter<String> implements ThemedSpinnerAdapter {
    private final ThemedSpinnerAdapter.Helper mDropDownHelper;

    public OrderSpinnerAdapter(Context context, String[] objects) {
        super(context, android.R.layout.simple_list_item_1, objects);
        mDropDownHelper = new ThemedSpinnerAdapter.Helper(context);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            // Inflate the drop down using the helper's LayoutInflater
            LayoutInflater inflater = mDropDownHelper.getDropDownViewInflater();
            view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
        } else {
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(getItem(position));

        return view;
    }

    @Override
    public void setDropDownViewTheme(Resources.Theme theme) {
        mDropDownHelper.setDropDownViewTheme(theme);
    }

    @Override
    public Resources.Theme getDropDownViewTheme() {
        return mDropDownHelper.getDropDownViewTheme();
    }
}
