package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxUser extends BoxResource {
    private static final URLTemplate GET_USER_URL = new URLTemplate("users/%s");
    private static final URLTemplate GET_ME_URL = new URLTemplate("users/me");

    public BoxUser(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static BoxUser getCurrentUser(BoxAPIConnection api) {
        URL url = GET_ME_URL.build(api.getBaseURL());
        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new BoxUser(api, jsonObject.get("id").asString());
    }

    public BoxUser.Info getInfo() {
        URL url = GET_USER_URL.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    public enum Role {
        ADMIN,
        COADMIN,
        USER
    }

    public enum Status {
        ACTIVE,
        INACTIVE,
        CANNOT_DELETE_EDIT,
        CANNOT_DELETE_EDIT_UPLOAD
    }

    public class Info extends BoxResource.Info<BoxUser> {
        private String name;
        private String login;
        private Date createdAt;
        private Date modifiedAt;
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

        public Info(JsonObject jsonObject) {
            for (JsonObject.Member member : jsonObject) {
                if (member.getValue().isNull()) {
                    continue;
                }

                this.parseJsonMember(member);
            }
        }

        @Override
        public BoxUser getResource() {
            return BoxUser.this;
        }

        public String getName() {
            return this.name;
        }

        public String getLogin() {
            return this.login;
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }

        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        public Role getRole() {
            return this.role;
        }

        public String getLanguage() {
            return this.language;
        }

        public String getTimezone() {
            return this.timezone;
        }

        public long getSpaceAmount() {
            return this.spaceAmount;
        }

        public long getSpaceUsed() {
            return this.spaceUsed;
        }

        public long getMaxUploadSize() {
            return this.maxUploadSize;
        }

        public Status getStatus() {
            return this.status;
        }

        public String getJobTitle() {
            return this.jobTitle;
        }

        public String getPhone() {
            return this.phone;
        }

        public String getAddress() {
            return this.address;
        }

        public String getAvatarURL() {
            return this.avatarURL;
        }

        protected void parseJsonMember(JsonObject.Member member) {
            try {
                JsonValue value = member.getValue();
                switch (member.getName()) {
                    case "name":
                        this.name = value.asString();
                        break;
                    case "login":
                        this.login = value.asString();
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "role":
                        this.role = this.parseRole(value);
                        break;
                    case "language":
                        this.language = value.asString();
                        break;
                    case "timezone":
                        this.timezone = value.asString();
                        break;
                    case "space_amount":
                        this.spaceAmount = value.asLong();
                        break;
                    case "space_used":
                        this.spaceUsed = value.asLong();
                        break;
                    case "max_upload_size":
                        this.maxUploadSize = value.asLong();
                        break;
                    case "status":
                        this.status = this.parseStatus(value);
                        break;
                    case "job_title":
                        this.jobTitle = value.asString();
                        break;
                    case "phone":
                        this.jobTitle = value.asString();
                        break;
                    case "address":
                        this.address = value.asString();
                        break;
                    case "avatar_url":
                        this.avatarURL = value.asString();
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
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
