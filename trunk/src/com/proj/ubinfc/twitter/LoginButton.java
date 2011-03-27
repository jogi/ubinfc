package com.proj.ubinfc.twitter;

import java.util.Date;

import oauth.signpost.OAuth;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.facebook.android.R;

public class LoginButton extends ImageButton {
    
    private Activity mActivity;
    private SharedPreferences prefs;
    
    public LoginButton(Context context) {
        super(context);
    }
    
    public LoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    public LoginButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
        
    public void init(final Activity activity) {
        mActivity = activity;        
        prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        
        setBackgroundColor(Color.TRANSPARENT);
        setAdjustViewBounds(true);
        setImageResource(TwitterUtils.isAuthenticated(prefs) ? R.drawable.logout_button : R.drawable.login_button);
        drawableStateChanged();      
        
        setOnClickListener(new ButtonOnClickListener());
    }
    
    private final class ButtonOnClickListener implements OnClickListener {
        
        public void onClick(View arg0) {
            if (TwitterUtils.isAuthenticated(prefs)) {
                //logout aka clear credentials
            	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        		final Editor edit = prefs.edit();
        		edit.remove(OAuth.OAUTH_TOKEN);
        		edit.remove(OAuth.OAUTH_TOKEN_SECRET);
        		edit.commit();
        		
            	//setImageResource(R.drawable.login_button);
        		updateButton();
            } else {
            	Log.i("Twitter login", "Twitter login initiated!");
            	Intent i = new Intent(mActivity.getApplicationContext(), PrepareRequestTokenActivity.class);
            	i.putExtra("tweet_msg", "Test message from UbiNFC at : "+new Date().toString());				
            	mActivity.startActivity(i);

            	//setImageResource(R.drawable.logout_button);
            	updateButton();
            }
        }
    }    
    
    public void updateButton() {
    	setImageResource(TwitterUtils.isAuthenticated(prefs) ? R.drawable.logout_button : R.drawable.login_button);
    }
}
