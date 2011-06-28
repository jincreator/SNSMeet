package org.snsmeet.main;

import org.snsmeet.R;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.database.sqlite.SQLiteDatabase;

public class Twitter_Add extends Activity{
	private AccountDB accountdb;
	private SQLiteDatabase db;
	public static String consumerKey = "9rLaK4l7RBn2x4YMgRVkw";
	public static String consumerSecret = "sbkoZ5Q5kCngvPh65e1HM7eQoGLdW5FfudfwkcOPy5Q";
	public static Uri CALLBACK_URL = Uri.parse("http://snsmeet.sourceforge.net/");
	
	private Twitter twitter;
	private AccessToken acToken;
	private RequestToken rqToken;
	private Status status = null;
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.twitter_add);
        
        try {
//        	System.setProperty("twitter4j.http.useSSL", "false");
//        	System.setProperty("twitter4j.oauth.consumerKey", consumerKey);
//        	System.setProperty("twitter4j.oauth.consumerSecret", consumerSecret);
//        	System.setProperty("twitter4j.oauth.requestTokenURL", "http://api.twitter.com/oauth/request_token");
//        	System.setProperty("twitter4j.oauth.accessTokenURL", "http://api.twitter.com/oauth/access_token");
//        	System.setProperty("twitter4j.oauth.authorizationURL", "http://api.twitter.com/oauth/authorize");
        	
        	twitter = new TwitterFactory().getInstance();
        	twitter.setOAuthConsumer(consumerKey, consumerSecret);
			rqToken = twitter.getOAuthRequestToken(CALLBACK_URL.toString());
		} catch (TwitterException e) {
			e.printStackTrace();
		}
		startActivity(new Intent(Intent.ACTION_VIEW,  Uri.parse(rqToken.getAuthorizationURL())));
    }
	 
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Uri uri = intent.getData();
		if(uri != null && CALLBACK_URL.getScheme().equals(uri.getScheme())){
			String oauth_verifier = uri.getQueryParameter("oauth_verifier");
			try {
			             acToken = twitter.getOAuthAccessToken(rqToken, oauth_verifier);
			             accountdb.insert_twitter(db,"nick",acToken.getToken(), acToken.getTokenSecret());
			} catch (TwitterException e) {
			             e.printStackTrace();
			}
	    }
	}
	
	public void OnStart(){
		super.onStart();
	}
	public void OnResume(){
		super.onResume();
	}
}