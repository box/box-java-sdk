package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a lock associated with a File on Box.
 */
public class BoxLock extends BoxJSONObject {
    private String type;
    private Date expiresAt;
    private Boolean isDownloadPrevented;
    private BoxUser.Info createdBy;
    private Date createdAt;
    private String id;
    private BoxAPIConnection api;

    /**
     * Constructs a base BoxLock object.
     * @param type lock type, "lock" or "unlock".
     * @param expiresAt lock expiration date.
     */
    public BoxLock(String type, Date expiresAt) {
        super();
        this.type = type;
        this.expiresAt = expiresAt;
        this.isDownloadPrevented = false;
    }

    /**
     * Constructs a BoxLock object.
     * @param type lock type, "lock" or "unlock".
     * @param expiresAt lock expiration date.
     * @param isDownloadPrevented if true, download is prevented while locked.
     */
    public BoxLock(String type, Date expiresAt, Boolean isDownloadPrevented) {
        super();
        this.type = type;
        this.expiresAt = expiresAt;
        this.isDownloadPrevented = isDownloadPrevented;
    }

    /**
     * Constructs an BoxLock object using an already parsed JSON object.
     * @param  jsonObject the parsed JSON object.
     */
    BoxLock(JsonObject jsonObject, BoxAPIConnection api) {
        super(jsonObject);
        this.api = api;
    }

    /**
     * Gets the lock type.
     * @return the type of a lock.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Gets a locks expiration date.
     * @return the locks expiration date.
     */
    public Date getExpiresAt() {
        return this.expiresAt;
    }

    /**
     * Does the lock prevent downloads.
     * @return true if lock prevents downloads.
     */
    public Boolean getIsDownloadPrevented() {
        return this.isDownloadPrevented;
    }

    /**
     * User who created the lock.
     * @return Lock creator.
     */
    public BoxUser.Info getCreatedBy() {
        return this.createdBy;
    }

    /**
     * @return Lock's creation date.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * @return Lock's ID.
     */
    public String getId() {
        return this.id;
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        super.parseJSONMember(member);

        String memberName = member.getName();
        JsonValue value = member.getValue();

        try {
            if (memberName.equals("type")) {
                this.type = value.asString();
            } else if (memberName.equals("expires_at")) {
                this.expiresAt = BoxDateFormat.parse(value.asString());
            } else if (memberName.equals("is_download_prevented")) {
                this.isDownloadPrevented = value.asBoolean();
            } else if (memberName.equals("created_by")) {
                JsonObject userJSON = value.asObject();
                if (this.createdBy == null) {
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(this.api, userID);
                    this.createdBy = user.new Info(userJSON);
                } else {
                    this.createdBy.update(userJSON);
                }
            } else if (memberName.equals("created_at")) {
                this.createdAt = BoxDateFormat.parse(value.asString());
            } else if (memberName.equals("id")) {
                this.id = value.toString();
            }
        } catch (ParseException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        }
    }
}
