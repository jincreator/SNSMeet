package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;
import android.widget.BaseAdapter;
import android.widget.TabHost;
import android.content.Context;

public class Account extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private AccountDB accountdb;
	private SQLiteDatabase db;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountdb=new AccountDB(this);
        db=accountdb.getWritableDatabase();
        Cursor cursor_twitter=db.rawQuery("SELECT * FROM twitter",null);
        startManagingCursor(cursor_twitter);
        setContentView(R.layout.account);
        TabHost tabhost=(TabHost)findViewById(R.id.tabhost);
        tabhost.setup();
        TabHost.TabSpec spec;
        spec=tabhost.newTabSpec("Twitter1");
        spec.setIndicator("Twitter",getResources().getDrawable(R.drawable.twitter_newbird_blue_tab));
        spec.setContent(R.id.twitter_frame);
        tabhost.addTab(spec);
        spec=tabhost.newTabSpec("Facebook2");
        spec.setIndicator("Facebook",getResources().getDrawable(R.drawable.f_logo_tab));
        spec.setContent(R.id.facebook_frame);
        tabhost.addTab(spec);
        SimpleCursorAdapter adapter_twitter=new SimpleCursorAdapter(this,R.layout.account_list,cursor_twitter,new String[]{"nick"},new int[]{R.id.account_nick});
        ListView twitter_list=(ListView)findViewById(R.id.twitter_listview);
        twitter_list.setAdapter(adapter_twitter);
        Cursor cursor_facebook=db.rawQuery("SELECT * FROM facebook",null);
        startManagingCursor(cursor_facebook);
        SimpleCursorAdapter adapter_facebook=new SimpleCursorAdapter(this,R.layout.account_list,cursor_facebook,new String[]{"nick"},new int[]{R.id.account_nick});
        ListView facebook_list=(ListView)findViewById(R.id.facebook_listview);
        facebook_list.setAdapter(adapter_facebook);
        tabhost.setCurrentTab(0);
        View account_add_twitter=findViewById(R.id.account_add_twitter);
        account_add_twitter.setOnClickListener(this);
        View account_add_facebook=findViewById(R.id.account_add_facebook);
        account_add_facebook.setOnClickListener(this);
    }
	
	public void onClick(View v){
		switch(v.getId()){
		case R.id.account_add_twitter:
			final Intent twitter=new Intent(this,Twitter_Add.class);
			startActivity(twitter);
			db.execSQL("INSERT INTO twitter VALUES (null,'abcd','efgh','higk');");
				break;
		case R.id.account_add_facebook:
			db.execSQL("INSERT INTO facebook VALUES (null,'dcba','efgh','higk');");
			break;
		}
	}
}