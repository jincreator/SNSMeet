package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class License extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.license);
        InputStream i=getResources().openRawResource(R.raw.lgpl_3);
        byte[] b;
		try {
			b = new byte[i.available()];
			while(i.read(b)!=-1){;}
			i.close();
	        TextView view=(TextView)findViewById(R.id.license_view);
	        view.setText(new String(b));
		} catch (IOException e) {;}

        
    }
}