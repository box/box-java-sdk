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
    /**
     * An array of all possible file fields that can be requested when calling {@link #getInfo()}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "name", "login", "created_at", "modified_at", "role",
        "language", "timezone", "space_amount", "space_used", "max_upload_size", "tracking_codes",
        "can_see_managed_users", "is_sync_enabled", "is_external_collab_restricted", "status", "job_title", "phone",
        "address", "avatar_url", "is_exempt_from_device_limits", "is_exempt_from_login_verification", "enterprise",
        "my_tags", "hostname"};

    private static final URLTemplate USER_URL_TEMPLATE = new URLTemplate("users/%s");
    private static final URLTemplate GET_ME_URL = new URLTemplate("users/me");
    private static final URLTemplate USERS_URL_TEMPLATE = new URLTemplate("users");

    /**
     * Constructs a BoxUser for a user with a given ID.
     * @param  api the API connection to be used by the user.
     * @param  id  the ID of the user.
     */
    public BoxUser(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Provisions a new user in an enterprise.
     * @param  api   the API connection to be used by the created user.
     * @param  login the email address the user will use to login.
     * @param  name  the name of the user.
     * @return       the created user's info.
     */
    public static BoxUser.Info createEnterpriseUser(BoxAPIConnection api, String login, String name) {
        return createEnterpriseUser(api, login, name, null);
    }

    /**
     * Provisions a new user in an enterprise with additional user information.
     * @param  api    the API connection to be used by the created user.
     * @param  login  the email address the user will use to login.
     * @param  name   the name of the user.
     * @param  params additional user information.
     * @return        the created user's info.
     */
    public static BoxUser.Info createEnterpriseUser(BoxAPIConnection api, String login, String name,
        CreateUserParams params) {

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("login", login);
        requestJSON.add("name", name);

        if (params != null) {
            if (params.getRole() != null) {
                requestJSON.add("role", params.getRole().toJSONValue());
            }

            if (params.getStatus() != null) {
                requestJSON.add("status", params.getStatus().toJSONValue());
            }

            requestJSON.add("language", params.getLanguage());
            requestJSON.add("is_sync_enabled", params.getIsSyncEnabled());
            requestJSON.add("job_title", params.getJobTitle());
            requestJSON.add("phone", params.getPhone());
            requestJSON.add("address", params.getAddress());
            requestJSON.add("space_amount", params.getSpaceAmount());
            requestJSON.add("can_see_managed_users", params.getCanSeeManagedUsers());
            requestJSON.add("timezone", params.getTimezone());
            requestJSON.add("is_exempt_from_device_limits", params.getIsExemptFromDeviceLimits());
            requestJSON.add("is_exempt_from_login_verification", params.getIsExemptFromLoginVerification());
        }

        URL url = USERS_URL_TEMPLATE.build(api.getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxUser createdUser = new BoxUser(api, responseJSON.get("id").asString());
        return createdUser.new Info(responseJSON);
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
        URL url = USER_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    /**
     * Deletes a user from an enterprise account.
     * @param notifyUser whether or not to send an email notification to the user that their account has been deleted.
     * @param force      whether or not this user should be deleted even if they still own files.
     */
    public void delete(boolean notifyUser, boolean force) {
        String queryString = new QueryStringBuilder()
            .appendParam("notify", String.valueOf(notifyUser))
            .appendParam("force", String.valueOf(force))
            .toString();

        URL url = USER_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Enumerates the possible roles that a user can have within an enterprise.
     */
    public enum Role {
        /**
         * The user is an administrator of their enterprise.
         */
        ADMIN ("admin"),

        /**
         * The user is a co-administrator of their enterprise.
         */
        COADMIN ("coadmin"),

        /**
         * The user is a regular user within their enterprise.
         */
        USER ("user");

        private final String jsonValue;

        private Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Role fromJSONValue(String jsonValue) {
            return Role.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible statuses that a user's account can have.
     */
    public enum Status {
        /**
         * The user's account is active.
         */
        ACTIVE ("active"),

        /**
         * The user's account is inactive.
         */
        INACTIVE ("inactive"),

        /**
         * The user's account cannot delete or edit content.
         */
        CANNOT_DELETE_EDIT ("cannot_delete_edit"),

        /**
         * The user's account cannot delete, edit, or upload content.
         */
        CANNOT_DELETE_EDIT_UPLOAD ("cannot_delete_edit_upload");

        private final String jsonValue;

        private Status(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Status fromJSONValue(String jsonValue) {
            return Status.valueOf(jsonValue.toUpperCase());
        }

        String toJSONValue() {
            return this.jsonValue;
        }
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
                this.role = Role.fromJSONValue(value.asString());
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
                this.status = Status.fromJSONValue(value.asString());
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
    }
}
