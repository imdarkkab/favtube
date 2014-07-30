package com.example.favtube.util;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.favtube.model.Mp3;

public class VideoUtilities {

	private static final String BASE_URL = "http://demo.tigermob.com/youtube2mp3/index.jsp?v=";

	public static Mp3 getConvertFile(String videoId) {
		Mp3 mp3 = new Mp3(-1, "") ;
		Log.d("videoId=" + videoId);

		try {

			// String requestUrl =
			// "https://gdata.yoututestbe.com/feeds/api/videos?author="+username+"&v=2&alt=jsonc"
			// ;
			String requestUrl = BASE_URL + videoId;
			Log.d("requestUrl=" + requestUrl);

			// Get a httpclient to talk to the internet
			HttpClient client = new DefaultHttpClient();
			// Perform a GET request to YouTube for a JSON list of all the
			// videos by a specific user
			HttpUriRequest request = new HttpGet(requestUrl);
			// Get the response that YouTube sends back
			HttpResponse response = client.execute(request);
			// Convert this response into a readable string
			String jsonString = StreamUtils.convertToString(response
					.getEntity().getContent());
			Log.d("jsonString=" + jsonString);
			// Create a JSON object that we can use from the String
			JSONObject json = new JSONObject(jsonString);

			// For further information about the syntax of this request and
			// JSON-C
			// see the documentation on YouTube
			// http://code.google.com/apis/youtube/2.0/developers_guide_jsonc.html
			String downloadUrl = "";
			// Get are search result items
			int status = json.getInt("status");
			Log.d("status=" + status);
			if (status == 1) {
				downloadUrl = json.getString("url");
				Log.d("downloadUrl=" + downloadUrl);
				return new Mp3(status, downloadUrl) ;
			} else {
				return new Mp3(status, "") ;
			}

		} catch (ClientProtocolException e) {
			Log.e("Feck", e);
		} catch (IOException e) {
			Log.e("Feck", e);
		} catch (JSONException e) {
			Log.e("Feck", e);
		}
		
		return mp3 ;
	}
}
