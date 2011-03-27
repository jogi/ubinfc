/**
 * 
 */
package com.proj.ubinfc.nfc.record;

/**
 * @author Jogi
 *
 */
public class SocialRequestRecord {

	private String requestType;
	private long id;
	private String twitterScreenName;
	
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTwitterScreenName() {
		return twitterScreenName;
	}
	public void setTwitterScreenName(String twitterScreenName) {
		this.twitterScreenName = twitterScreenName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SocialRequestRecord [requestType=" + requestType + ", id=" + id
				+ ", twitterScreenName=" + twitterScreenName + "]";
	}	
}
