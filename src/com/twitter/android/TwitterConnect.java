/**
 * 
 */
package com.twitter.android;

import winterwell.jtwitter.OAuthSignpostClient;
import winterwell.jtwitter.Twitter;

/**
 * @author Jogi
 *
 */
public class TwitterConnect {

	/**
	 * 
	 */
	private static final String oauthKey = "NY9gFFwMMHvzTOy77xh2hg";
	private static final String oauthSecret = "TpE3MKsKoJHtASR9QEPHDL5JpkEUOZnC6VmXOYb2g";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		OAuthSignpostClient oClient = new OAuthSignpostClient(oauthKey, 
															oauthSecret, 
															"45574537-B4rIIw3niShOss5QbzyjOCvliofY0bQ1djsv6iJ71", 
															"VrdHzvyJrd0N9vFT7C9j2t2r4OZ23ByMgMo7pdtlRI");
		
		//OAuthSignpostClient oClient = new OAuthSignpostClient(oauthKey, oauthSecret, "oob");
		//URI url = oClient.authorizeUrl();
		//System.out.println(url.toString());
		/*
		//oClient.authorizeDesktop();
		// get the pin
		String v = oClient.askUser("Please enter the verification PIN from Twitter");
		oClient.setAuthorizationCode(v);
		// Store the authorisation token details for future use
		String[] accessToken = oClient.getAccessToken();
		System.out.println(accessToken[0]+" "+accessToken[1]);
		// Next time we can use new OAuthSignpostClient(OAUTH_KEY, OAUTH_SECRET, 
//		      accessToken[0], accessToken[1]) to avoid authenticating again.
		
		*/
		// Make a Twitter object
		Twitter twitter = new Twitter("vashishthajogi", oClient);
		// Print Daniel Winterstein's status
		if(!twitter.isFollower("vashishthajogi", "ubinfc"))
			System.out.println(twitter.follow("ubinfc"));
		else
			System.out.println("Already following ubinfc!");
		
	}

}
