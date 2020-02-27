package com.box.sdk;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an individual item returned by a metadata query item.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.*
 */
public class BoxMetadataQueryItem extends BoxJSONObject {
    private BoxItem.Info item;
    private JsonObject metadata;
    private BoxAPIConnection api;

    /**
     * Construct a BoxMetadataQueryItem.
     * @param jsonObject the parsed JSON object.
     * @param api the API connection to be used to fetch interacted item
     */
    public BoxMetadataQueryItem(JsonObject jsonObject, BoxAPIConnection api) {
        super(jsonObject);
        this.api = api;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);

        String memberName = member.getName();
        JsonValue value = member.getValue();
		if (memberName.equals("item")) {
			String id = value.asObject().get("id").asString();
			String type = value.asObject().get("type").asString();
			this.item = new BoxFile(this.api, id).new Info(value.asObject());
			if (type.equals("folder")) {
				BoxFolder folder = new BoxFolder(this.api, id);
				this.item = folder.new Info(value.asObject());
			} else if (type.equals("file")) {
				BoxFile file = new BoxFile(this.api, id);
				this.item  = file.new Info(value.asObject());
			} else if (type.equals("web_link")) {
				BoxWebLink link = new BoxWebLink(this.api, id);
				this.item  = link.new Info(value.asObject());
			} else {
				assert false : "Unsupported item type: " + type;
				throw new BoxAPIException("Unsupported item type: " + type);
			}
		} else if (memberName.equals("metadata")) {
			this.metadata = value.asObject();
		}
    }

    /**
     * Get the item which was interacted with.
     * @return box item
     */
    public BoxItem.Info getItem() {
        return this.item;
    }

    /**
     * Get the metadata on the item.
     * @return JsonObject of metadata
     */
    public JsonObject getMetadata() {
        return this.metadata;
    }

}
