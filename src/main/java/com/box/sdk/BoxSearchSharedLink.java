package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a search item when searching shared links.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.*
 */
public class BoxSearchSharedLink extends BoxJSONObject {
    private String type;
    private BoxItem.Info item;
    private URL accessibleViaSharedLink;
    private BoxAPIConnection api;

    /**
     * Construct a BoxRecentItem.
     *
     * @param jsonObject the parsed JSON object.
     * @param api        the API connection to be used to fetch interacted item
     */
    public BoxSearchSharedLink(JsonObject jsonObject, BoxAPIConnection api) {
        super(jsonObject);
        this.api = api;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);

        String memberName = member.getName();
        JsonValue value = member.getValue();
        try {
            if (memberName.equals("type")) {
                this.type = value.asString();
            } else if (memberName.equals("item")) {
                JsonObject jsonObject = value.asObject();
                String type = jsonObject.get("type").asString();
                String id = jsonObject.get("id").asString();

                if (type.equals("folder")) {
                    BoxFolder folder = new BoxFolder(this.api, id);
                    this.item = folder.new Info(jsonObject);
                } else if (type.equals("file")) {
                    BoxFile file = new BoxFile(this.api, id);
                    this.item = file.new Info(jsonObject);
                } else if (type.equals("web_link")) {
                    BoxWebLink link = new BoxWebLink(this.api, id);
                    this.item = link.new Info(jsonObject);
                } else {
                    assert false : "Unsupported item type: " + type;
                    throw new BoxAPIException("Unsupported item type: " + type);
                }
            } else if (memberName.equals("accessible_via_shared_link")) {
                this.accessibleViaSharedLink = new URL(value.asString());
            }
        } catch (MalformedURLException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        }
    }

    /**
     * Get item type.
     *
     * @return type of item
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get the item which was interacted with.
     *
     * @return box item
     */
    public BoxItem.Info getItem() {
        return this.item;
    }

    /**
     * Get the optional shared link through which the user has access to this item.
     *
     * @return shared link
     */
    public URL getAccessibleViaSharedLink() {
        return this.accessibleViaSharedLink;
    }

}

