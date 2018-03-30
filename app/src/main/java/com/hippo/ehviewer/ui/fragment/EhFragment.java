/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer.ui.fragment;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDelegate;
import android.util.Log;

import com.hippo.ehviewer.EhApplication;
import com.hippo.ehviewer.R;
import com.hippo.ehviewer.Settings;

public class EhFragment extends PreferenceFragment
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.eh_settings);

        Preference gallerySite = findPreference(Settings.KEY_GALLERY_SITE);
        Preference listMode = findPreference(Settings.KEY_LIST_MODE);
        Preference detailSize = findPreference(Settings.KEY_DETAIL_SIZE);
        Preference thumbSize = findPreference(Settings.KEY_THUMB_SIZE);
        Preference nightMode = findPreference(Settings.KEY_NIGHT_MODE);

        gallerySite.setOnPreferenceChangeListener(this);
        listMode.setOnPreferenceChangeListener(this);
        detailSize.setOnPreferenceChangeListener(this);
        thumbSize.setOnPreferenceChangeListener(this);
        nightMode.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplication());

        sharedPreferences.registerOnSharedPreferenceChangeListener(new SharedPreferences.OnSharedPreferenceChangeListener() {

            @Override
            public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
                if (Settings.KEY_NIGHT_MODE.equals(key)){

                    int nightmode = Integer.parseInt(sharedPreferences.getString(Settings.KEY_NIGHT_MODE,
                            String.valueOf(AppCompatDelegate.getDefaultNightMode())));

                    if (nightmode != AppCompatDelegate.getDefaultNightMode()){
                        AppCompatDelegate.setDefaultNightMode(nightmode);
                        ((EhApplication) getActivity().getApplication()).recreate();
                    }

                }

            }
        });
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        String key = preference.getKey();
        if (Settings.KEY_GALLERY_SITE.equals(key)) {
            getActivity().setResult(Activity.RESULT_OK);
            return true;
        } else if (Settings.KEY_LIST_MODE.equals(key)) {
            getActivity().setResult(Activity.RESULT_OK);
            return true;
        } else if (Settings.KEY_DETAIL_SIZE.equals(key)) {
            getActivity().setResult(Activity.RESULT_OK);
        } else if (Settings.KEY_THUMB_SIZE.equals(key)) {
            getActivity().setResult(Activity.RESULT_OK);
        } else if (Settings.KEY_NIGHT_MODE.equals(key)) {
            getActivity().setResult(Activity.RESULT_OK);
        }
        return true;
    }
}
