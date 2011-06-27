package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ToggleButton;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.database.Cursor;
import android.widget.CursorAdapter;
import android.widget.TabHost;
import android.widget.Toast;
import android.content.Context;
import org.snsmeet.main.AccountDB;

public class Account extends Activity implements OnClickListener{
    /** Called when the activity is first created. */
	private AccountDB accountdb;
	private SQLiteDatabase db;
	private Cursor cursor_twitter;
	private Cursor cursor_facebook;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        accountdb=new AccountDB(this);
        db=accountdb.getWritableDatabase();
        cursor_twitter=db.rawQuery("SELECT * FROM twitter",null);
        startManagingCursor(cursor_twitter);
        cursor_facebook=db.rawQuery("SELECT * FROM facebook",null);
        startManagingCursor(cursor_facebook);
        setContentView(R.layout.account);
        TabHost tabhost=(TabHost)findViewById(R.id.tabhost);
        tabhost.setup();
        TabHost.TabSpec spec;
        spec=tabhost.newTabSpec("twitter_tab");
        spec.setIndicator(getString(R.string.twitter),getResources().getDrawable(R.drawable.twitter_newbird_blue_tab));
        spec.setContent(R.id.twitter_frame);
        tabhost.addTab(spec);
        spec=tabhost.newTabSpec("facebook_tab");
        spec.setIndicator(getString(R.string.facebook),getResources().getDrawable(R.drawable.f_logo_tab));
        spec.setContent(R.id.facebook_frame);
        tabhost.addTab(spec);
        ListView twitter_list=(ListView)findViewById(R.id.twitter_listview);
        AccountAdapter twitterAdapter=new AccountAdapter(this,cursor_twitter,"twitter");
        twitter_list.setAdapter(twitterAdapter);
        AccountAdapter facebookAdapter=new AccountAdapter(this,cursor_facebook,"facebook");
        ListView facebook_list=(ListView)findViewById(R.id.facebook_listview);
        facebook_list.setAdapter(facebookAdapter);
        tabhost.setCurrentTab(0);
        View account_add_twitter=findViewById(R.id.account_add_twitter);
        account_add_twitter.setOnClickListener(this);
        View account_add_facebook=findViewById(R.id.account_add_facebook);
        account_add_facebook.setOnClickListener(this);
    }
	class AccountAdapter extends CursorAdapter{
		private String service;
		private Cursor cursor;
		public AccountAdapter(Context context, Cursor cur, String service_input) {
            super(context,cur);
            service=service_input;
            cursor=cur;
        }

        @Override
        public View newView(Context context, Cursor cur, ViewGroup parent) {
            LayoutInflater li = LayoutInflater.from(context);
            return li.inflate(R.layout.account_list, parent, false);
        }

        @Override
        public void bindView(View view, Context context, Cursor cur) {
        	final int cs=cur.getInt(cur.getColumnIndex("_id"));
        	TextView nick = (TextView)view.findViewById(R.id.account_nick);
        	final ToggleButton use = (ToggleButton)view.findViewById(R.id.account_toggle);
        	nick.setText(cur.getString(cur.getColumnIndex("nick")));
        	use.setChecked(cur.getLong(cur.getColumnIndex("use"))==1);
        	use.setOnClickListener(new OnClickListener(){
        		public void onClick(View v){
        			db.execSQL("UPDATE "+service+" SET use="+(int)(use.isChecked()?1:0)+" WHERE _id="+cs+";");
        		}
        	});
            ImageView delete = (ImageView)view.findViewById(R.id.account_delete);
            delete.setOnClickListener(new OnClickListener() {
                
                public void onClick(View v) {
    				db.execSQL("DELETE FROM "+service+" WHERE _id='"+cs+"';");
    				cursor.requery();
                }
            });
        }
    }
		
	public void onClick(View v){
		switch(v.getId()){
		case R.id.account_add_twitter:
			final Intent twitter=new Intent(this,Twitter_Add.class);
			startActivity(twitter);
			accountdb.insert_twitter(db,"abcd","efgh","higk");
			cursor_twitter.requery();
			break;
		case R.id.account_add_facebook:
			accountdb.insert_facebook(db,"dcba","efgh","higk");
			cursor_facebook.requery();
			break;
		}
	}
}