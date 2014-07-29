package com.example.favtube.view;

import java.util.List;

import com.example.favtube.adapter.VideosAdapter;
import com.example.favtube.model.Video;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListAdapter;
import android.widget.ListView;

public class VideosListView extends ListView {
	 
    public VideosListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
 
    public VideosListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
 
    public VideosListView(Context context) {
        super(context);
    }
 
    public void setVideos(List<Video> videos){
        VideosAdapter adapter = new VideosAdapter(getContext(), videos);
        setAdapter(adapter);
    }
     
    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
    }
}
