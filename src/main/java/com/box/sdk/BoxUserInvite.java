package com.box.sdk;

import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an invite object for {@link BoxUser#inviteUser(String, String)}.
 */
public class BoxUserInvite extends BoxJSONObject {

    /**
     * The API connection to be used by the resource.
     */
    private BoxAPIConnection api;

    /**
     * @see #getStatus()
     */
    private String status;

    /**
     * @see #getID()
     */
    private String id;

    /**
     * @see #getInvitedTo()
     */
    private BoxEnterprise invitedTo;

    /**
     * @see #getActionableBy()
     */
    private BoxUser.Info actionableBy;

    /**
     * @see #getInvitedBy()
     */
    private BoxUser.Info invitedBy;

    /**
     * @see #getCreatedAt()
     */
    private Date createdAt;

    /**
     * @see #getModifiedAt()
     */
    private Date modifiedAt;

    /**
     * Constructs an empty Invite.
     *
     * @param api the API connection to be used by the resource.
     */
    public BoxUserInvite(BoxAPIConnection api) {
        super();
        this.api = api;
    }

    /**
     * Constructs an Invite from a JSON string.
     *
     * @param api  the API connection to be used by the resource.
     * @param json the json encoded user invite.
     */
    public BoxUserInvite(BoxAPIConnection api, String json) {
        super(json);
        this.api = api;
    }

    /**
     * Constructs an Invite from a JSON object.
     *
     * @param api the API connection to be used by the resource.
     * @param jsonObject the JSON encoded user invite.
     */
    BoxUserInvite(BoxAPIConnection api, JsonObject jsonObject) {
        super(jsonObject);
        this.api = api;
    }

    /**
     * Gets the ID of this invite.
     *
     * @return the ID of this invite.
     */
    public String getID() {
        return this.id;
    }

    /**
     * Gets status of this invite.
     *
     * @return the status of this invite
     */
    public String getStatus() {
        return this.status;
    }

    /**
     * Gets info about the enterprise invited to.
     *
     * @return info about the enterprise invited to.
     */
    public BoxEnterprise getInvitedTo() {
        return this.invitedTo;
    }

    /**
     * Gest info about user invited by that invitation.
     *
     * @return info about user invited by that invitation.
     */
    public BoxUser.Info getActionableBy() {
        return this.actionableBy;
    }

    /**
     * Gets info about user that sent this invitation.
     *
     * @return info about user that sent this invitation.
     */
    public BoxUser.Info getInvitedBy() {
        return this.invitedBy;
    }

    /**
     * Gets date of creation of this invitation.
     *
     * @return date of creation of this invitation.
     */
    public Date getCreatedAt() {
        return this.createdAt;
    }

    /**
     * date of the last modification of this invitation.
     *
     * @return date of the last modification of this invitation.
     */
    public Date getModifiedAt() {
        return this.modifiedAt;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    void parseJSONMember(JsonObject.Member member) {
        JsonValue value = member.getValue();
        String memberName = member.getName();
        try {
            if (memberName.equals("id")) {
                this.id = value.asString();
            } else if (memberName.equals("status")) {
                this.status = value.asString();
            } else if (memberName.equals("invited_to")) {
                this.invitedTo = new BoxEnterprise(value.toString());
            } else if (memberName.equals("actionable_by")) {
                JsonObject actionableByUser = value.asObject();
                String userID = actionableByUser.get("id").asString();
                BoxUser user = new BoxUser(this.api, userID);
                this.actionableBy = user.new Info(actionableByUser);
            } else if (memberName.equals("invited_by")) {
                JsonObject invitedByUser = value.asObject();
                String userID = invitedByUser.get("id").asString();
                BoxUser user = new BoxUser(this.api, userID);
                this.invitedBy = user.new Info(invitedByUser);
            } else if (memberName.equals("created_at")) {
                this.createdAt = BoxDateFormat.parse(value.asString());
            } else if (memberName.equals("modified_at")) {
                this.modifiedAt = BoxDateFormat.parse(value.asString());
            }
        } catch (ParseException e) {
            assert false : "A ParseException indicates a bug in the SDK.";
        }

    }

}
