package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an invitation for a user to join an enterprise.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("invite")
public class BoxInvite extends BoxResource {

    /**
     * The URL template for invite creation requests.
     */
    public static final URLTemplate INVITE_CREATION_URL_TEMPLATE = new URLTemplate("invites");

    /**
     * The URL template for invite retrieval requests.
     * @see #getInfo()
     */
    public static final URLTemplate INVITE_URL_TEMPLATE = new URLTemplate("invites/%s");

    /**
     * Constructs a BoxInvitee for an invite with a given ID.
     * @param  api the API connection to be used by the invite.
     * @param  id  the ID of the invite.
     */
    public BoxInvite(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information about this group membership.
     * @return info about this group membership.
     */
    public Info getInfo() {
        BoxAPIConnection api = this.getAPI();
        URL url = INVITE_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Invite a user to an enterprise.
     * @param api the API connection to use for the request.
     * @param userLogin the login of the user to invite.
     * @param enterpriseID the ID of the enterprise to invite the user to.
     * @return the invite info.
     */
    public static Info inviteUserToEnterprise(BoxAPIConnection api, String userLogin, String enterpriseID) {

        URL url = INVITE_CREATION_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

        JsonObject body = new JsonObject();

        JsonObject enterprise = new JsonObject();
        enterprise.add("id", enterpriseID);
        body.add("enterprise", enterprise);

        JsonObject actionableBy = new JsonObject();
        actionableBy.add("login", userLogin);
        body.add("actionable_by", actionableBy);

        request.setBody(body);
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxInvite invite = new BoxInvite(api, responseJSON.get("id").asString());
        return invite.new Info(responseJSON);
    }

    /**
     * Contains information about a BoxInvite.
     */
    public class Info extends BoxResource.Info {

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
         * @see #getStatus()
         */
        private String status;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         * @param  json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         * @param  jsonObject the parsed JSON object.
         */
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        /**
         * Gets the enterprise the user was invited to.
         *
         * @return the enterprise the user was invited to.
         */
        public BoxEnterprise getInvitedTo() {
            return this.invitedTo;
        }

        /**
         * Gets the user that was invited to the enterprise.
         *
         * <p>Note: the BoxUser.Info returned by this method will only have the ID, name, and login fields
         * populated.</p>
         *
         * @return the invited user.
         */
        public BoxUser.Info getActionableBy() {
            return this.actionableBy;
        }

        /**
         * Gets the user that made the invitation.
         *
         * <p>Note: the BoxUser.Info returned by this method will only have the ID, name, and login fields
         * populated.</p>
         *
         * @return the user that created the invitation.
         */
        public BoxUser.Info getInvitedBy() {
            return this.invitedBy;
        }

        /**
         * Gets the status of the invitation.
         *
         * @return the invite status.
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * Gets the time the invite was created.
         * @return the time the invite was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the time the invite was last modified.
         * @return the time the invite was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public BoxInvite getResource() {
            return BoxInvite.this;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();

            try {
                if (memberName.equals("invited_to")) {
                    JsonObject enterpriseJSON = value.asObject();
                    BoxEnterprise enterprise = new BoxEnterprise(enterpriseJSON);
                    this.invitedTo = enterprise;
                } else if (memberName.equals("actionable_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.actionableBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.actionableBy = user.new Info(userJSON);
                    } else {
                        this.actionableBy.update(userJSON);
                    }
                } else if (memberName.equals("invited_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.invitedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.invitedBy = user.new Info(userJSON);
                    } else {
                        this.invitedBy.update(userJSON);
                    }
                } else if (memberName.equals("status")) {
                    this.status = value.asString();
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

}
