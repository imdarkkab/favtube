package com.example.favtube.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.favtube.R;
import com.example.favtube.model.Library;
import com.example.favtube.task.GetYouTubeUserVideosTask;
import com.example.favtube.util.Log;
import com.example.favtube.view.VideosListView;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 * 
 */
public class SearchFragment extends Fragment {

	
	private VideosListView listView;
	
	GetYouTubeUserVideosTask getYouTubeUserVideosTask ;
	
	public SearchFragment() {
		// Required empty public constructor
		getYouTubeUserVideosTask = new GetYouTubeUserVideosTask(responseHandler, keyword);
	}
	
	private String keyword ="";
	
	

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		super.onCreateView(inflater, container, savedInstanceState);
		View view = inflater.inflate(R.layout.fragment_search, container, false);
		Log.d("view="+view) ;
		listView = (VideosListView)view.findViewById(R.id.videosListView);
		Log.d("listView="+listView) ;
		
//		listView.setOnClickListener(new View.OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Log.d("onClick listView") ;
//			}
//		});
//		
		Button myButton = (Button) view.findViewById(R.id.searchButton);
		
		EditText searchTextBox = (EditText)view.findViewById(R.id.searchTextBox) ;
		searchTextBox.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				Log.d("onTextChanged==>"+s.toString());
				setKeyword(s.toString()) ;
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
//		myButton.setOnClickListener(this);   
		Log.d("myButton="+myButton) ;
		myButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Log.d(">>>>>>>>>>>>") ;
				getUserYouTubeFeed(v) ;
			}
		});
		
		
		return view;
	}
	
	 // This is the XML onClick listener to retreive a users video feed
    public void getUserYouTubeFeed(View v){
    	
        // We start a new task that does its work on its own thread
        // We pass in a handler that will be called when the task has finished
        // We also pass in the name of the user we are searching YouTube for
    	getYouTubeUserVideosTask.setKeyword(keyword);
        new Thread(getYouTubeUserVideosTask).start();
    }
    
 // This is the handler that receives the response when the YouTube task has finished
    Handler responseHandler = new Handler() {
        public void handleMessage(Message msg) {
            populateListWithVideos(msg);
        };
    };
 
    /**
     * This method retrieves the Library of videos from the task and passes them to our ListView
     * @param msg
     */
    private void populateListWithVideos(Message msg) {
        // Retreive the videos are task found from the data bundle sent back
        Library lib = (Library) msg.getData().get(GetYouTubeUserVideosTask.LIBRARY);
        // Because we have created a custom ListView we don't have to worry about setting the adapter in the activity
        // we can just call our custom method with the list of items we want to display
        listView.setVideos(lib.getVideos());
    }
     
    @Override
	public void onStop() {
        // Make sure we null our handler when the activity has stopped
        // because who cares if we get a callback once the activity has stopped? not me!
        responseHandler = null;
        super.onStop();
    }




}
