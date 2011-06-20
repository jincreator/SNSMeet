package org.snsmeet;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class account extends Activity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        TextView twitter_account=(TextView)findViewById(R.id.twitter_account);
        twitter_account.setText(R.string.account_empty);
    }
}