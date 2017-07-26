package com.box.sdk;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an individual recent item.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.*
 */
public class BoxRecentItem extends BoxJSONObject {
    private String type;
    private String interactionType;
    private BoxItem.Info item;
    private Date interactedAt;
    private URL interactionSharedLink;
    private BoxAPIConnection api;

    /**
     * Construct a BoxRecentItem.
     * @param jsonObject the parsed JSON object.
     * @param api the API connection to be used to fetch interacted item
     */
    public BoxRecentItem(JsonObject jsonObject, BoxAPIConnection api) {
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
            } else if (memberName.equals("interaction_type")) {
                this.interactionType = value.asString();
            } else if (memberName.equals("item")) {
                String id = value.asObject().get("id").asString();
                this.item = new BoxFile(this.api, id).new Info(value.asObject());
            } else if (memberName.equals("interacted_at")) {
                this.interactedAt = BoxDateFormat.parse(value.asString());
            } else if (memberName.equals("interaction_shared_link")) {
                this.interactionSharedLink = new URL(value.asString());
            }
        } catch (ParseException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        } catch (MalformedURLException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        }
    }

    /**
     * Get item type.
     * @return type of item
     */
    public String getType() {
        return this.type;
    }

    /**
     * Get interaction type.
     * @return interaction type
     */
    public String getInteractionType() {
        return this.interactionType;
    }

    /**
     * Get the item which was interacted with.
     * @return box item
     */
    public BoxItem.Info getItem() {
        return this.item;
    }

    /**
     * Get the interaction date.
     * @return interaction date
     */
    public Date getInteractedAt() {
        return this.interactedAt;
    }

    /**
     * Get the shared link, if the item was accessed through a shared link.
     * @return shared link
     */
    public URL getInteractionSharedLink() {
        return this.interactionSharedLink;
    }

}
