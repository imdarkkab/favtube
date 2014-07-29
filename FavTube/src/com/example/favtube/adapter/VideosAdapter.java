package com.example.favtube.adapter;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.favtube.R;
import com.example.favtube.activity.PlayerActivity;
import com.example.favtube.model.Video;
import com.example.favtube.util.Log;
import com.example.favtube.view.UrlImageView;

public class VideosAdapter extends BaseAdapter {
    // The list of videos to display

	Video video ;
    List<Video> videos;
    // An inflator to use when creating rows
    private LayoutInflater mInflater;
     
    /**
     * @param context this is the context that the list will be shown in - used to create new list rows
     * @param videos this is a list of videos to display
     */
    public VideosAdapter(Context context, List<Video> videos) {
        this.videos = videos;
        this.mInflater = LayoutInflater.from(context);
    }
 
    @Override
    public int getCount() {
        return videos.size();
    }
 
    @Override
    public Object getItem(int position) {
        return videos.get(position);
    }
 
    @Override
    public long getItemId(int position) {
        return position;
    }
 
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // If convertView wasn't null it means we have already set it to our list_item_user_video so no need to do it again
        if(convertView == null){
            // This is the layout we are using for each row in our list
            // anything you declare in this layout can then be referenced below
            convertView = mInflater.inflate(R.layout.list_item_user_video, null);
        }
        convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Log.d("OnClick!! List View");
				// TODO Auto-generated method stub
//				PlayerFragment plyerFragment = new PlayerFragment();
				String videoId = video.getId() ;
				String title = video.getTitle() ;
				String description = video.getDescription();
				
				Bundle b = new Bundle();
				b.putString("video_id", videoId);
				b.putString("title",title);
				b.putString("description", description);

				
				Activity activity = (Activity) v.getContext();
				Intent intent = new Intent(v.getContext(), PlayerActivity.class) ;
				intent.putExtras(b);
				activity.startActivity(intent);
//				activity.finish();
			}
		}) ;
//        convertView.setOnClickListener(this);
        // We are using a custom imageview so that we can load images using urls
        // For further explanation see: http://blog.blundell-apps.com/imageview-with-loading-spinner/
        UrlImageView thumb = (UrlImageView) convertView.findViewById(R.id.userVideoThumbImageView);
         
        TextView title = (TextView) convertView.findViewById(R.id.userVideoTitleTextView); 
        // Get a single video from our list
        video = videos.get(position);
        // Set the image for the list item
        thumb.setImageDrawable(video.getThumbUrl());
        // Set the title for the list item
        title.setText(video.getTitle());
         
        return convertView;
    }


}
