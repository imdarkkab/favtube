package com.example.favtube.model;

import java.io.Serializable;

public class Mp3 implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int status ;
	private String url ;
	
	
	
	public Mp3() {
		super();
	}
	
	

	public Mp3(int status, String url) {
		super();
		this.status = status;
		this.url = url;
	}



	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}
