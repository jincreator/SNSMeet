package org.snsmeet;

import android.preference.PreferenceActivity;
import android.os.Bundle;

public class Setting extends  PreferenceActivity{
    /** Called when the activity is first created. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting);
    }
}