package com.duse.android.dsmsocialclub.activity;


import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.MultiSelectListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.duse.android.dsmsocialclub.R;

public class SettingsActivity extends AppCompatPreferenceActivity {

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            if (!super.onOptionsItemSelected(item)) {
                NavUtils.navigateUpFromSameTask(this);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getResources().getString(R.string.action_settings));
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment())
                .commit();

    }


    public static class SettingsFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener{
        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.general_settings);
            bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_interests_category_key)));
        }
        private void bindPreferenceSummaryToValue(Preference preference) {
            // Set the listener to watch for value changes.
            preference.setOnPreferenceChangeListener(this);

            // Trigger the listener immediately with the preference's
            // current value.
            onPreferenceChange(preference,
                    PreferenceManager
                            .getDefaultSharedPreferences(preference.getContext())
                            .getString(preference.getKey(), ""));
        }
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();
            if (preference instanceof ListPreference) {

                // For list preferences, look up the correct display value in
                // the preference's 'entries' list (since they have separate labels/values).
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    preference.setSummary(listPreference.getEntries()[prefIndex]);
                }
            } else {
                // For other preferences, set the summary to the value's simple string representation.
                preference.setSummary(stringValue);
            }
            return true;
        }
    }
}
