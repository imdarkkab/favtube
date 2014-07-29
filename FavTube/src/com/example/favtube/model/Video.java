package com.example.favtube.model;

import java.io.Serializable;

public class Video implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5290277473591313778L;
	
	private String id ;
    // The title of the video
    private String title;
    // A link to the video on youtube
    private String url;
    // A link to a still image of the youtube video
    private String thumbUrl;
    
    private String description ;
     
    public Video(String id,String title, String url, String thumbUrl , String description) {
        super();
        this.id = id ;
        this.title = title;
        this.url = url;
        this.thumbUrl = thumbUrl;
        this.description = description ;
    }
    
	public String getId() {
		return id;
	}

 
    /**
     * @return the title of the video
     */
    public String getTitle(){
        return title;
    }
 
    /**
     * @return the url to this video on youtube
     */
    public String getUrl() {
        return url;
    }
 
    /**
     * @return the thumbUrl of a still image representation of this video
     */
    public String getThumbUrl() {
        return thumbUrl;
    }

	public String getDescription() {
		return description;
	}


    
    
}
