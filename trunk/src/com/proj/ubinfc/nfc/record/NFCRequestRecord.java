package com.proj.ubinfc.nfc.record;

public class NFCRequestRecord {

	private String requestType = ""; //twitter, linkedin, contact, coupon, etc.
	private String specificRequest;
	/**
	 * @return the requestType
	 */
	public String getRequestType() {
		return requestType;
	}
	/**
	 * @param requestType the requestType to set
	 */
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	/**
	 * @return the specificRequest
	 */
	public String getSpecificRequest() {
		return specificRequest;
	}
	/**
	 * @param specificRequest the specificRequest to set
	 */
	public void setSpecificRequest(String specificRequest) {
		this.specificRequest = specificRequest;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NFCRequestRecord [requestType=" + requestType
				+ ", specificRequest=" + specificRequest + "]";
	}
	
}
