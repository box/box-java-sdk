package com.box.sdk;

import java.net.URL;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a Box user account.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
public class BoxUser extends BoxCollaborator {
    private static final URLTemplate GET_USER_URL = new URLTemplate("users/%s");
    private static final URLTemplate GET_ME_URL = new URLTemplate("users/me");

    /**
     * Constructs a BoxUser for a user with a given ID.
     * @param  api the API connection to be used by the user.
     * @param  id  the ID of the user.
     */
    public BoxUser(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets the current user.
     * @param  api the API connection of the current user.
     * @return     the current user.
     */
    public static BoxUser getCurrentUser(BoxAPIConnection api) {
        URL url = GET_ME_URL.build(api.getBaseURL());
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new BoxUser(api, jsonObject.get("id").asString());
    }

    /**
     * Gets information about this user.
     * @return info about this user.
     */
    public BoxUser.Info getInfo() {
        URL url = GET_USER_URL.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Enumerates the possible roles that a user can have within an enterprise.
     */
    public enum Role {
        /**
         * The user is an administrator of their enterprise.
         */
        ADMIN,

        /**
         * The user is a co-administrator of their enterprise.
         */
        COADMIN,

        /**
         * The user is a regular user within their enterprise.
         */
        USER
    }

    /**
     * Enumerates the possible statuses that a user's account can have.
     */
    public enum Status {
        /**
         * The user's account is active.
         */
        ACTIVE,

        /**
         * The user's account is inactive.
         */
        INACTIVE,

        /**
         * The user's account cannot delete or edit content.
         */
        CANNOT_DELETE_EDIT,

        /**
         * The user's account cannot delete, edit, or upload content.
         */
        CANNOT_DELETE_EDIT_UPLOAD
    }

    /**
     * Contains information about a BoxUser.
     */
    public class Info extends BoxCollaborator.Info {
        private String login;
        private Role role;
        private String language;
        private String timezone;
        private long spaceAmount;
        private long spaceUsed;
        private long maxUploadSize;
        private Status status;
        private String jobTitle;
        private String phone;
        private String address;
        private String avatarURL;

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxUser getResource() {
            return BoxUser.this;
        }

        /**
         * Gets the email address the user uses to login.
         * @return the email address the user uses to login.
         */
        public String getLogin() {
            return this.login;
        }

        /**
         * Gets the user's enterprise role.
         * @return the user's enterprise role.
         */
        public Role getRole() {
            return this.role;
        }

        /**
         * Gets the language of the user.
         * @return the language of the user.
         */
        public String getLanguage() {
            return this.language;
        }

        /**
         * Gets the timezone of the user.
         * @return the timezone of the user.
         */
        public String getTimezone() {
            return this.timezone;
        }

        /**
         * Gets the user's total available space in bytes.
         * @return the user's total available space in bytes.
         */
        public long getSpaceAmount() {
            return this.spaceAmount;
        }

        /**
         * Gets the amount of space the user has used in bytes.
         * @return the amount of space the user has used in bytes.
         */
        public long getSpaceUsed() {
            return this.spaceUsed;
        }

        /**
         * Gets the maximum individual file size in bytes the user can have.
         * @return the maximum individual file size in bytes the user can have.
         */
        public long getMaxUploadSize() {
            return this.maxUploadSize;
        }

        /**
         * Gets the user's current account status.
         * @return the user's current account status.
         */
        public Status getStatus() {
            return this.status;
        }

        /**
         * Gets the job title of the user.
         * @return the job title of the user.
         */
        public String getJobTitle() {
            return this.jobTitle;
        }

        /**
         * Gets the phone number of the user.
         * @return the phone number of the user.
         */
        public String getPhone() {
            return this.phone;
        }

        /**
         * Gets the address of the user.
         * @return the address of the user.
         */
        public String getAddress() {
            return this.address;
        }

        /**
         * Gets the URL of the user's avatar.
         * @return the URL of the user's avatar.
         */
        public String getAvatarURL() {
            return this.avatarURL;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            JsonValue value = member.getValue();
            String memberName = member.getName();
            if (memberName.equals("login")) {
                this.login = value.asString();
            } else if (memberName.equals("role")) {
                this.role = this.parseRole(value);
            } else if (memberName.equals("language")) {
                this.language = value.asString();
            } else if (memberName.equals("timezone")) {
                this.timezone = value.asString();
            } else if (memberName.equals("space_amount")) {
                this.spaceAmount = Double.valueOf(value.toString()).longValue();
            } else if (memberName.equals("space_used")) {
                this.spaceUsed = Double.valueOf(value.toString()).longValue();
            } else if (memberName.equals("max_upload_size")) {
                this.maxUploadSize = Double.valueOf(value.toString()).longValue();
            } else if (memberName.equals("status")) {
                this.status = this.parseStatus(value);
            } else if (memberName.equals("job_title")) {
                this.jobTitle = value.asString();
            } else if (memberName.equals("phone")) {
                this.phone = value.asString();
            } else if (memberName.equals("address")) {
                this.address = value.asString();
            } else if (memberName.equals("avatar_url")) {
                this.avatarURL = value.asString();
            }
        }

        private Role parseRole(JsonValue value) {
            String roleString = value.asString().toUpperCase();
            return Role.valueOf(roleString);
        }

        private Status parseStatus(JsonValue value) {
            String statusString = value.asString().toUpperCase();
            return Status.valueOf(statusString);
        }
    }
}
