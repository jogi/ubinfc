package com.proj.ubinfc.twitter;

import oauth.signpost.OAuth;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

public class TwitterUtils {

	public static boolean isAuthenticated(SharedPreferences prefs) {

		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		System.out.println(token);
		System.out.println(secret);
		
		if(token==null || token.equals(""))
			return false;
		else
		{
			AccessToken a = new AccessToken(token,secret);
			Twitter twitter = new TwitterFactory().getInstance();
			twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
			twitter.setOAuthAccessToken(a);
			
			try {
				twitter.getAccountSettings();
				return true;
			} catch (TwitterException e) {
				return false;
			}
		}
	}
	
	public static void sendTweet(SharedPreferences prefs,String msg) throws Exception {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		System.out.println(token);
		System.out.println(secret);
		AccessToken a = new AccessToken(token,secret);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
        twitter.updateStatus(msg);
	}
	
	public static void sendFollowRequest(Context context, String friend) throws Exception {
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
		User user = getCurrentUser(prefs);
		
		sendFollowRequest(prefs, user.getScreenName(), friend);
	}
	
	public static void sendFollowRequest(SharedPreferences prefs, String friendA, String friendB) throws Exception {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		AccessToken a = new AccessToken(token,secret);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
        
		if(twitter.existsFriendship(friendA, friendB)){
			//then do nothing
			Log.i("Twitter Follow", "Already following : "+friendB);
		} else {
			User user = twitter.createFriendship(friendB);			
			Log.i("Befriended user", user.toString());		
		}			
	}
	
	public static User getCurrentUser(SharedPreferences prefs) throws Exception {
		String token = prefs.getString(OAuth.OAUTH_TOKEN, "");
		String secret = prefs.getString(OAuth.OAUTH_TOKEN_SECRET, "");
		
		AccessToken a = new AccessToken(token,secret);
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer(Constants.CONSUMER_KEY, Constants.CONSUMER_SECRET);
		twitter.setOAuthAccessToken(a);
		
		return twitter.showUser(twitter.getId());
	}
}
