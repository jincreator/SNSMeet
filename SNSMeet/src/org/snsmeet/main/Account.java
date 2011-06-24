package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class Account extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);
        TextView twitter_account=(TextView)findViewById(R.id.twitter_account);
        twitter_account.setText(R.string.account_empty);
        View account_add=findViewById(R.id.account_add);
        account_add.setOnClickListener(this);
    }
	public void onClick(View v){
		switch(v.getId()){
		case R.id.account_add:
			final Intent twitter=new Intent(this,Twitter_Add.class);
			AlertDialog.Builder b=new AlertDialog.Builder(this);
			b.setTitle(R.string.account_add);
			b.setItems(R.array.sns_type,new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int sns_type){
					switch(sns_type){
					case 0:
						startActivity(twitter);
						break;
					}
				}
			});
			b.show();
		}
	}
}