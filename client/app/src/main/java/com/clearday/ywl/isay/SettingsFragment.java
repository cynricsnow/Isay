package com.clearday.ywl.isay;

import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by clearday on 15/11/20.
 */
public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}
