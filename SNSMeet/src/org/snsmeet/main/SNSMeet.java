package org.snsmeet.main;

import org.snsmeet.R;
import org.snsmeet.R.id;
import org.snsmeet.R.layout;
import org.snsmeet.R.menu;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.*;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class SNSMeet extends Activity implements OnClickListener
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	super.onCreate(savedInstanceState);
        setContentView(R.layout.snsmeet);
        //Button aboutbutton=(Button)findViewById(R.id.about_button);
        View nfcbutton=findViewById(R.id.nfc_connect);
        nfcbutton.setOnClickListener(this);
        View qrcodebutton=findViewById(R.id.qrcode_connect);
        qrcodebutton.setOnClickListener(this);
    }
    public void onClick(View v)
    {
    	Intent i;
        switch(v.getId())
        {
            case R.id.nfc_connect:
            	i=new Intent(this,NFC.class);
            	startActivity(i);
            	break;
            case R.id.qrcode_connect:
            	i=new Intent(this,QRcode.class);
            	startActivity(i);
            	break;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
    	super.onCreateOptionsMenu(menu);
    	MenuInflater inflater=getMenuInflater();
    	inflater.inflate(R.menu.menu,menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
    	switch(item.getItemId())
    	{
    	case R.id.account:
    		startActivity(new Intent(this,Account.class));
    		return true;
    	case R.id.list:
    		startActivity(new Intent(this,List.class));
    		return true;
    	case R.id.setting:
    		startActivity(new Intent(this,Setting.class));
    		return true;
    	}
    	return false;
    }
}