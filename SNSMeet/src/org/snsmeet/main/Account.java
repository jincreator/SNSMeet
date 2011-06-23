package org.snsmeet.main;

import org.snsmeet.R;
import org.snsmeet.R.id;
import org.snsmeet.R.layout;
import org.snsmeet.R.string;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Account extends Activity {
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        TextView twitter_account=(TextView)findViewById(R.id.twitter_account);
        twitter_account.setText(R.string.account_empty);
    }
}