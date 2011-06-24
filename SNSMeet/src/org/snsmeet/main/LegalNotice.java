package org.snsmeet.main;

import org.snsmeet.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class LegalNotice extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.legal_notice);
        InputStream i=getResources().openRawResource(R.raw.legal_notice);
        byte[] b;
		try {
			b = new byte[i.available()];
			while(i.read(b)!=-1){;}
			i.close();
	        TextView view=(TextView)findViewById(R.id.legal_notice_view);
	        view.setText(new String(b));
		} catch (IOException e) {;}

        
    }
}