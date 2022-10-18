package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Represents a file request on Box.
 */
@BoxResourceType("file_request")
public class BoxFileRequest extends BoxResource {

    /**
     * File Request URL Template.
     */
    public static final URLTemplate FILE_REQUEST_URL_TEMPLATE = new URLTemplate("file_requests/%s");
    /**
     * Copy File Request URL Template.
     */
    public static final URLTemplate COPY_FILE_REQUEST_URL_TEMPLATE = new URLTemplate("file_requests/%s/copy");

    /**
     * Constructs a BoxFileRequest for a file request with a given ID.
     *
     * @param api the API connection to be used by the file request.
     * @param id  the ID of the file request.
     */
    public BoxFileRequest(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Gets information about this file request.
     *
     * @return info about this file request.
     */
    public BoxFileRequest.Info getInfo() {
        URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return new Info(responseJSON, this.getAPI().getBaseAppUrl());
        }
    }

    /**
     * Copies this file request that is already present on one folder, and applies it to another folder.
     *
     * @param folderId the ID of the folder for the file request.
     * @return info about the newly copied file request.
     */
    public BoxFileRequest.Info copyInfo(String folderId) {
        URL url = COPY_FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        JsonObject body = new JsonObject();
        JsonObject folderBody = new JsonObject();
        folderBody.add("id", folderId);
        folderBody.add("type", "folder");
        body.add("folder", folderBody);
        request.setBody(body.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new Info(jsonObject, this.getAPI().getBaseAppUrl());
        }
    }

    /**
     * Copies this file request that is already present on one folder, and applies it to another folder.
     *
     * <p>Info fields that have been modified locally will overwrite the values in the original file request.
     *
     * @param info     the info.
     * @param folderId the ID of the folder for the file request.
     * @return info about the newly copied file request.
     */
    public BoxFileRequest.Info copyInfo(BoxFileRequest.Info info, String folderId) {
        URL url = COPY_FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        JsonObject body = new JsonObject();
        JsonObject pendingChanges = info.getPendingChangesAsJsonObject();
        if (pendingChanges != null) {
            body = pendingChanges;
        }
        JsonObject folderBody = new JsonObject();
        folderBody.add("id", folderId);
        folderBody.add("type", "folder");
        body.add("folder", folderBody);
        request.setBody(body.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
            return new Info(jsonObject, this.getAPI().getBaseAppUrl());
        }
    }

    /**
     * Updates the information about this file request with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxFileRequest fileRequest = new BoxFileRequest(api, id);
     * BoxFileRequest.Info info = fileRequest.getInfo();
     * info.updateInfo(info);</pre>
     *
     * @param info the updated info.
     * @return info about the updated file request.
     */
    public BoxFileRequest.Info updateInfo(BoxFileRequest.Info info) {
        URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
            return info;
        }
    }

    /**
     * Delete this file request.
     */
    public void delete() {
        URL url = FILE_REQUEST_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * The status of the file request.
     */
    public enum Status {
        /**
         * The file request can accept new submissions.
         */
        ACTIVE("active"),

        /**
         * The file request can't accept new submissions.
         */
        INACTIVE("inactive");

        private final String jsonValue;

        Status(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Status fromJSONString(String jsonValue) {
            return Status.valueOf(jsonValue.toUpperCase());
        }

        String toJSONString() {
            return this.jsonValue;
        }
    }

    /**
     * Contains information about a BoxFileRequest.
     */
    public class Info extends BoxResource.Info {
        private String type;
        private Date createdAt;
        private BoxUser.Info createdBy;
        private String description;
        private String etag;
        private Date expiresAt;
        private BoxFolder.Info folder;
        private boolean isDescriptionRequired;
        private boolean isEmailRequired;
        private Status status;
        private String title;
        private Date updatedAt;
        private BoxUser.Info updatedBy;
        private URL url;
        private String path;
        private String baseUrl;

        /**
         * Constructs an empty Info object.
         */
        public Info() {
            super();
        }

        /**
         * Constructs an Info object by parsing information from a JSON string.
         *
         * @param json the JSON string to parse.
         */
        public Info(String json) {
            super(json);
        }

        /**
         * Constructs an Info object using an already parsed JSON object.
         *
         * @param jsonObject the parsed JSON object.
         * @param fileRequestBaseUrl Request base URL
         */
        Info(JsonObject jsonObject, String fileRequestBaseUrl) {
            super(jsonObject);
            try {
                this.baseUrl = fileRequestBaseUrl;
                this.url = new URL(this.baseUrl + this.path);
            } catch (MalformedURLException e) {
                throw new BoxAPIException("Couldn't construct url for file request", e);
            }
        }

        @Override
        public BoxFileRequest getResource() {
            return BoxFileRequest.this;
        }

        /**
         * Gets the file request type.
         *
         * @return the file request type.
         */
        public String getType() {
            return this.type;
        }

        /**
         * Gets the date when the file request was created.
         *
         * @return the date when the file request was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the user who created this file request.
         *
         * @return the user who created this file request.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets the description of this file request.
         *
         * @return the description of this file request.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Sets the description of this file request.
         *
         * @param description the file request's new description.
         */
        public void setDescription(String description) {
            this.description = description;
            this.addPendingChange("description", description);
        }

        /**
         * Gets a unique string identifying the version of the item.
         *
         * @return a unique string identifying the version of the item.
         */
        public String getEtag() {
            return this.etag;
        }

        /**
         * Gets the date after which a file request will no longer accept new submissions.
         *
         * @return the date after which a file request will no longer accept new submissions.
         */
        public Date getExpiresAt() {
            return this.expiresAt;
        }


        /**
         * Sets the date after which a file request will no longer accept new submissions.
         *
         * @param expiresAt the date after which a file request will no longer accept new submissions.
         */
        public void setExpiresAt(Date expiresAt) {
            this.expiresAt = expiresAt;
            this.addPendingChange("expires_at", BoxDateFormat.format(expiresAt));
        }

        /**
         * Gets the folder that this file request is associated with.
         *
         * @return the folder that this file request is associated with.
         */
        public BoxFolder.Info getFolder() {
            return this.folder;
        }

        /**
         * Gets whether a file request submitter is required to provide a description of the files they are submitting.
         *
         * @return whether a file request submitter is required to provide a description of the files they
         * are submitting.
         */
        public Boolean getIsDescriptionRequired() {
            return this.isDescriptionRequired;
        }

        /**
         * Sets whether a file request submitter is required to provide a description of the files they are submitting.
         *
         * @param isDescriptionRequired whether a file request submitter is required to provide a description of the
         *                              files they are submitting.
         */
        public void setIsDescriptionRequired(Boolean isDescriptionRequired) {
            this.isDescriptionRequired = isDescriptionRequired;
            this.addPendingChange("is_description_required", isDescriptionRequired);
        }

        /**
         * Gets whether a file request submitter is required to provide their email address.
         *
         * @return whether a file request submitter is required to provide their email address.
         */
        public Boolean getIsEmailRequired() {
            return this.isEmailRequired;
        }

        /**
         * Sets whether a file request submitter is required to provide their email address.
         *
         * @param isEmailRequired whether a file request submitter is required to provide their email address.
         */
        public void setIsEmailRequired(Boolean isEmailRequired) {
            this.isEmailRequired = isEmailRequired;
            this.addPendingChange("is_email_required", isEmailRequired);
        }

        /**
         * Gets the status of the file request.
         *
         * @return the status of the file request
         */
        public Status getStatus() {
            return this.status;
        }


        /**
         * Sets the status of the file request.
         *
         * @param status the status of the file request
         */
        public void setStatus(Status status) {
            this.status = status;
            this.addPendingChange("status", status.toJSONString());
        }

        /**
         * Gets the title of file request.
         *
         * @return the title of file request.
         */
        public String getTitle() {
            return this.title;
        }

        /**
         * Sets the title of file request.
         *
         * @param title the title of file request.
         */
        public void setTitle(String title) {
            this.title = title;
            this.addPendingChange("title", title);
        }

        /**
         * Gets the date when the file request was last updated.
         *
         * @return the date when the file request was last updated.
         */
        public Date getUpdatedAt() {
            return this.updatedAt;
        }

        /**
         * Gets the user who last modified this file request.
         *
         * @return the user who last modified this file request.
         */
        public BoxUser.Info getUpdatedBy() {
            return this.updatedBy;
        }

        /**
         * Gets the URL can be shared with users to let them upload files to the associated folder.
         *
         * @return the URL for files upload.
         */
        public URL getUrl() {
            return this.url;
        }

        /**
         * Gets the base URL for the upload files link.
         *
         * @return the base url including protocol and hostname.
         */
        public String getBaseUrl() {
            return this.baseUrl;
        }

        /**
         * Sets the base URL for the upload files link. Can throw an exception if format of the URL is invalid.
         *
         * @param baseUrl the base url including protocol and hostname.
         * @throws MalformedURLException when baseUrl format is invalid.
         */
        public void setBaseUrl(String baseUrl) throws MalformedURLException {
            this.baseUrl = baseUrl;
            this.url = new URL(this.baseUrl + this.path);
        }

        /**
         * Gets the URL containing only the path (e.g. "/f/123456789") shared with users to let
         * them upload files to the associated folder.
         *
         * @return the path of the URL for files upload.
         */
        public String getPath() {
            return this.path;
        }

        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("type")) {
                    this.type = value.asString();
                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("created_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.createdBy = user.new Info(userJSON);
                } else if (memberName.equals("description")) {
                    this.description = value.asString();
                } else if (memberName.equals("etag")) {
                    this.etag = value.asString();
                } else if (memberName.equals("expires_at")) {
                    this.expiresAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("folder")) {
                    JsonObject folderJSON = value.asObject();
                    String folderID = folderJSON.get("id").asString();
                    BoxFolder folder = new BoxFolder(getAPI(), folderID);
                    this.folder = folder.new Info(folderJSON);
                } else if (memberName.equals("is_description_required")) {
                    this.isDescriptionRequired = value.asBoolean();
                } else if (memberName.equals("is_email_required")) {
                    this.isEmailRequired = value.asBoolean();
                } else if (memberName.equals("status")) {
                    this.status = Status.fromJSONString(value.asString());
                } else if (memberName.equals("title")) {
                    this.title = value.asString();
                } else if (memberName.equals("updated_at")) {
                    this.updatedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("updated_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.createdBy = user.new Info(userJSON);
                } else if (memberName.equals("url")) {
                    this.path = value.asString();
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}
