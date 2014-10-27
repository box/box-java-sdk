package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

public class BoxCollaboration extends BoxResource {
    private static final URLTemplate COLLABORATIONS_URL_TEMPLATE = new URLTemplate("collaborations");
    private static final URLTemplate PENDING_COLLABORATIONS_URL = new URLTemplate("collaborations?status=pending");
    private static final URLTemplate COLLABORATION_URL_TEMPLATE = new URLTemplate("collaborations/%s");

    public BoxCollaboration(BoxAPIConnection api, String id) {
        super(api, id);
    }

    public static Collection<Info> getPendingCollaborations(BoxAPIConnection api) {
        URL url = PENDING_COLLABORATIONS_URL.build(api.getBaseURL());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int entriesCount = responseJSON.get("total_count").asInt();
        Collection<BoxCollaboration.Info> collaborations = new ArrayList<BoxCollaboration.Info>(entriesCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue entry : entries) {
            JsonObject entryObject = entry.asObject();
            BoxCollaboration collaboration = new BoxCollaboration(api, entryObject.get("id").asString());
            BoxCollaboration.Info info = collaboration.new Info(entryObject);
            collaborations.add(info);
        }

        return collaborations;
    }

    public Info getInfo() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATIONS_URL_TEMPLATE.build(api.getBaseURL());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        return new Info(jsonObject);
    }

    public void updateInfo(Info info) {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    public void delete() {
        BoxAPIConnection api = this.getAPI();
        URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

        BoxAPIRequest request = new BoxAPIRequest(api, url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    public class Info extends BoxResource.Info {
        private BoxUser.Info createdBy;
        private Date createdAt;
        private Date modifiedAt;
        private Date expiresAt;
        private Status status;
        private BoxCollaborator.Info accessibleBy;
        private Role role;
        private Date acknowledgedAt;
        private BoxFolder.Info item;

        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        public Date getCreatedAt() {
            return this.createdAt;
        }

        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        public Date getExpiresAt() {
            return this.expiresAt;
        }

        public Status getStatus() {
            return this.status;
        }

        public void setStatus(Status status) {
            this.status = status;
            this.addPendingChange("status", status.name().toLowerCase());
        }

        public BoxCollaborator.Info getAccessibleBy() {
            return this.accessibleBy;
        }

        public Role getRole() {
            return this.role;
        }

        public void setRole(Role role) {
            this.role = role;
            this.addPendingChange("role", role.toJSONString());
        }

        public Date getAcknowledgedAt() {
            return this.acknowledgedAt;
        }

        public BoxFolder.Info getItem() {
            return this.item;
        }

        @Override
        public BoxCollaboration getResource() {
            return BoxCollaboration.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                switch (memberName) {
                    case "created_by":
                        JsonObject userJSON = value.asObject();
                        if (this.createdBy == null) {
                            String userID = userJSON.get("id").asString();
                            BoxUser user = new BoxUser(getAPI(), userID);
                            this.createdBy = user.new Info(userJSON);
                        } else {
                            this.createdBy.update(userJSON);
                        }
                        break;
                    case "created_at":
                        this.createdAt = BoxDateParser.parse(value.asString());
                        break;
                    case "modified_at":
                        this.modifiedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "expires_at":
                        this.expiresAt = BoxDateParser.parse(value.asString());
                        break;
                    case "status":
                        String statusString = value.asString().toUpperCase();
                        this.status = Status.valueOf(statusString);
                        break;
                    case "accessible_by":
                        userJSON = value.asObject();
                        if (this.accessibleBy == null) {
                            String userID = userJSON.get("id").asString();
                            BoxUser user = new BoxUser(getAPI(), userID);
                            BoxUser.Info userInfo = user.new Info(userJSON);
                            this.accessibleBy = userInfo;
                        } else {
                            this.accessibleBy.update(userJSON);
                        }
                        break;
                    case "role":
                        this.role = Role.fromJSONString(value.asString());
                        break;
                    case "acknowledged_at":
                        this.acknowledgedAt = BoxDateParser.parse(value.asString());
                        break;
                    case "item":
                        JsonObject folderJSON = value.asObject();
                        if (this.item == null) {
                            String folderID = folderJSON.get("id").asString();
                            BoxFolder folder = new BoxFolder(getAPI(), folderID);
                            this.item = folder.new Info(folderJSON);
                        } else {
                            this.item.update(folderJSON);
                        }
                        break;
                    default:
                        break;
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    public enum Status {
        ACCEPTED,
        PENDING,
        REJECTED;
    }

    public enum Role {
        EDITOR ("editor"),
        VIEWER ("viewer"),
        PREVIEWER ("previewer"),
        UPLOADER ("uploader"),
        PREVIEWER_UPLOADER ("previewer uploader"),
        VIEWER_UPLOADER ("viewer uploader"),
        CO_OWNER ("co-owner"),
        OWNER ("owner");

        private final String jsonValue;

        private Role(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public static Role fromJSONString(String jsonValue) {
            switch (jsonValue) {
                case "editor":
                    return EDITOR;
                case "viewer":
                    return VIEWER;
                case "previewer":
                    return PREVIEWER;
                case "uploader":
                    return UPLOADER;
                case "previewer uploader":
                    return PREVIEWER_UPLOADER;
                case "viewer uploader":
                    return VIEWER_UPLOADER;
                case "co-owner":
                    return CO_OWNER;
                case "owner":
                    return OWNER;
                default:
                    throw new IllegalArgumentException("The provided JSON value isn't a valid Role.");
            }
        }

        public String toJSONString() {
            return this.jsonValue;
        }
    }
}
