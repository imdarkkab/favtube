package com.example.favtube.util;

public class Log {

	private static final String TAG = "FVT" ; 
	public static void d(String message){
		android.util.Log.d(TAG,message) ;
	}
	
	public static void e(String message, Throwable e){
		android.util.Log.e(TAG,message, e) ;
	}
}
