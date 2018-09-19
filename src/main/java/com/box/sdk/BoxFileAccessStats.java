package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxFileAccessStats extends BoxFile{

    public static final URLTemplate FILE_ACCESS_STATS_URL_TEMPLATE = new URLTemplate("/file_access_stats/%s");

	public BoxFileAccessStats(BoxAPIConnection api, String id) {
		super(api, id);
	}
	
    public BoxFileAccessStats.Info getInfo() {
    	URL url = FILE_ACCESS_STATS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    	BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
    	BoxJSONResponse response = (BoxJSONResponse) request.send();
    	return (new Info(response.getJSON()));
    }
    
    public class Info extends BoxFile.Info {

    	private long previewCount;
    	private long editCount;
    	private long downloadCount;
    	private long commentCount;

        public Info() {
            super();
        }
        
        public Info(String json) {
            super(json);
        }
        
        public Info(JsonObject jsonObject) {
        	super(jsonObject);
        }
        
	    public long getPreviewCount(){
	    	return previewCount;
	    }
	    
	    public long getEditCount(){
	    	return editCount;
	    }
	    
	    public long getDownloadCount(){
	    	return downloadCount;
	    }
	    
	    public long getCommentCount(){
	    	return commentCount;
	    }
	    
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            if (memberName.equals("preview_count")) {
                this.previewCount = value.asLong();
            } else if (memberName.equals("edit_count")) {
                this.editCount = value.asLong();
            } else if (memberName.equals("download_count")) {
                this.downloadCount = value.asLong();
            } else if (memberName.equals("comment_count")) {
                this.commentCount = value.asLong();
            }
        }
    }
}
