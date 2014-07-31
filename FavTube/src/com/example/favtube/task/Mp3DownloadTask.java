package com.example.favtube.task;
 
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.favtube.util.Log;
import com.example.favtube.util.StreamUtils;
 
 
/**
 * This is the task that will ask YouTube for a list of videos for a specified user</br>
 * This class implements Runnable meaning it will be ran on its own Thread</br>
 * Because it runs on it's own thread we need to pass in an object that is notified when it has finished
 *
 * @author paul.blundell
 */
public class Mp3DownloadTask implements Runnable {
	
	private static final String BASE_URL = "http://demo.tigermob.com/youtube2mp3/index.jsp?v=";

	private String videoId = "" ;
 
    /**
     * Don't forget to call run(); to start this task
     * @param replyTo - the handler you want to receive the response when this task has finished
     * @param username - the username of who on YouTube you are browsing
     */
    public Mp3DownloadTask( String videoId) {
        this.videoId = videoId;
    }
    



	@Override
    public void run() {
        try {
        	int status = 0 ;
        	int requestCount = 0 ;
        	
        	while ( status != 1 && requestCount < 10) {
				// String requestUrl =
				// "https://gdata.yoututestbe.com/feeds/api/videos?author="+username+"&v=2&alt=jsonc"
				// ;
				String requestUrl = BASE_URL + videoId;
				Log.d("requestUrl=" + requestUrl);
	
				HttpParams httpParameters = new BasicHttpParams();
				// Set the timeout in milliseconds until a connection is established.
				// The default value is zero, that means the timeout is not used. 
				int timeoutConnection = 30000;
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				// Set the default socket timeout (SO_TIMEOUT) 
				// in milliseconds which is the timeout for waiting for data.
				int timeoutSocket = 50000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
				
				// Get a httpclient to talk to the internet
				HttpClient client = new DefaultHttpClient(httpParameters);
		
				// Perform a GET request to YouTube for a JSON list of all the
				// videos by a specific user
				HttpUriRequest request = new HttpGet(requestUrl);
				request.setHeader("User-Agent","Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1)");
				// Get the response that YouTube sends back
				
				HttpResponse response = client.execute(request);
				
				StatusLine statusLine = response.getStatusLine();
				int statusCode = statusLine.getStatusCode() ;
				String reason = statusLine.getReasonPhrase() ;
				Log.d("statusCode=" + statusCode+",reason="+reason);
				// Convert this response into a readable string
				String jsonString = StreamUtils.convertToString(response.getEntity().getContent());
				
				Log.d("jsonString=" + jsonString);
				// Create a JSON object that we can use from the String
				JSONObject json = new JSONObject(jsonString);
	
				String downloadUrl = "";
				// Get are search result items
				status = json.getInt("status");
				Log.d("status=" + status);
				if(status == 1){
					downloadUrl = json.getString("url") ;
					Log.d("downloadUrl=" + downloadUrl);
					break ;
				}
				
				requestCount++;
			
				try {
					Thread.sleep(1000) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		} catch (ClientProtocolException e) {
            Log.e("Feck", e);
        } catch (IOException e) {
            Log.e("Feck", e);
        } catch (JSONException e) {
            Log.e("Feck", e);
        }
    }
}