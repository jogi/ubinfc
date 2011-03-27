package com.proj.ubinfc;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.facebook.android.R;
import com.proj.ubinfc.twitter.LoginButton;
import com.proj.ubinfc.twitter.TwitterUtils;

public class SocialActivity extends Activity {
    private LoginButton twtLoginButton;

    private SharedPreferences prefs;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.proj.ubinfc.R.layout.social);
        
        prefs = PreferenceManager.getDefaultSharedPreferences(this);
        twtLoginButton = (LoginButton) findViewById(com.facebook.android.R.id.loginTwitter);
        
        twtLoginButton.init(this);
        
                
        /*
        twtLoginButton.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {            	
            	Log.i("Twitter login", "Twitter login initiated!");
            	Intent i = new Intent(getApplicationContext(), PrepareRequestTokenActivity.class);
            	i.putExtra("tweet_msg", "Test message from UbiNFC at : "+new Date().toString());
				startActivity(i);                
            }
        });*/
    }
    
    @Override
	protected void onResume() {
		super.onResume();
		updateLoginStatus();
	}

	public void updateLoginStatus() {
		twtLoginButton.setImageResource(TwitterUtils.isAuthenticated(prefs) ? R.drawable.logout_button : R.drawable.login_button);
	}

}