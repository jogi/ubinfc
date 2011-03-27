package com.proj.ubinfc.twitter;

public class Constants {

	public static final String CONSUMER_KEY = "npJWXXS9SSIQRu5WDj3g3w";
	public static final String CONSUMER_SECRET= "QXCgmIX8hEpthvSAirS8wwG8i8HbgIlMjp54MyyQBU";
	
	public static final String REQUEST_URL = "https://api.twitter.com/oauth/request_token";
	public static final String ACCESS_URL = "https://api.twitter.com/oauth/access_token";
	public static final String AUTHORIZE_URL = "https://api.twitter.com/oauth/authorize";
	
	public static final String	OAUTH_CALLBACK_SCHEME	= "x-oauthflow-twitter";
	public static final String	OAUTH_CALLBACK_HOST		= "callback";
	public static final String	OAUTH_CALLBACK_URL		= OAUTH_CALLBACK_SCHEME + "://" + OAUTH_CALLBACK_HOST;

}

