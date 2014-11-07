package com.box.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents an individual file on Box. This class can be used to download a file's contents, upload new versions, and
 * perform other common file operations (move, copy, delete, etc.).
 */
public class BoxFile extends BoxItem {
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "sha1", "name", "description",
        "size", "path_collection", "created_at", "modified_at", "trashed_at", "purged_at", "content_created_at",
        "content_modified_at", "created_by", "modified_by", "owned_by", "shared_link", "parent", "item_status",
        "version_number", "comment_count", "permissions", "tags", "lock", "extension", "is_package"};

    private static final URLTemplate FILE_URL_TEMPLATE = new URLTemplate("files/%s");
    private static final URLTemplate CONTENT_URL_TEMPLATE = new URLTemplate("files/%s/content");
    private static final URLTemplate VERSIONS_URL_TEMPLATE = new URLTemplate("files/%s/versions");
    private static final URLTemplate COPY_URL_TEMPLATE = new URLTemplate("files/%s/copy");
    private static final URLTemplate ADD_COMMENT_URL_TEMPLATE = new URLTemplate("comments");
    private static final URLTemplate GET_COMMENTS_URL_TEMPLATE = new URLTemplate("files/%s/comments");
    private static final int BUFFER_SIZE = 8192;

    private final URL fileURL;
    private final URL contentURL;

    /**
     * Constructs a BoxFile for a file with a given ID.
     * @param  api the API connection to be used by the file.
     * @param  id  the ID of the file.
     */
    public BoxFile(BoxAPIConnection api, String id) {
        super(api, id);

        this.fileURL = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        this.contentURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    }

    @Override
    public BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
        BoxSharedLink.Permissions permissions) {

        BoxSharedLink sharedLink = new BoxSharedLink(access, unshareDate, permissions);
        Info info = new Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }

    /**
     * Adds a comment to this file. The message can contain @mentions by using the string @[userid:username] anywhere
     * within the message, where userid and username are the ID and username of the person being mentioned.
     * @see    <a href="https://developers.box.com/docs/#comments-add-a-comment-to-an-item">the tagged_message field
     *         for including @mentions.</a>
     * @param  message the comment's message.
     * @return information about the newly added comment.
     */
    public BoxComment.Info addComment(String message) {
        JsonObject itemJSON = new JsonObject();
        itemJSON.add("type", "file");
        itemJSON.add("id", this.getID());

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("item", itemJSON);
        if (BoxComment.messageContainsMention(message)) {
            requestJSON.add("tagged_message", message);
        } else {
            requestJSON.add("message", message);
        }

        URL url = ADD_COMMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        BoxComment addedComment = new BoxComment(this.getAPI(), responseJSON.get("id").asString());
        return addedComment.new Info(responseJSON);
    }

    /**
     * Downloads the contents of this file to a given OutputStream.
     * @param output the stream to where the file will be written.
     */
    public void download(OutputStream output) {
        this.download(output, null);
    }

    /**
     * Downloads the contents of this file to a given OutputStream while reporting the progress to a ProgressListener.
     * @param output   the stream to where the file will be written.
     * @param listener a listener for monitoring the download's progress.
     */
    public void download(OutputStream output, ProgressListener listener) {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.contentURL, "GET");
        BoxAPIResponse response = request.send();
        InputStream input = response.getBody(listener);

        byte[] buffer = new byte[BUFFER_SIZE];
        try {
            int n = input.read(buffer);
            while (n != -1) {
                output.write(buffer, 0, n);
                n = input.read(buffer);
            }
        } catch (IOException e) {
            throw new BoxAPIException("Couldn't connect to the Box API due to a network error.", e);
        }

        response.disconnect();
    }

    @Override
    public BoxFile.Info copy(BoxFolder destination) {
        return this.copy(destination, null);
    }

    @Override
    public BoxFile.Info copy(BoxFolder destination, String newName) {
        URL url = COPY_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject copyInfo = new JsonObject();
        copyInfo.add("parent", parent);
        if (newName != null) {
            copyInfo.add("name", newName);
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(copyInfo.toString());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        BoxFile copiedFile = new BoxFile(this.getAPI(), responseJSON.get("id").asString());
        return copiedFile.new Info(responseJSON);
    }

    /**
     * Deletes this file by moving it to the trash.
     */
    public void delete() {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.fileURL, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    @Override
    public BoxFile.Info getInfo() {
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), this.fileURL, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    @Override
    public BoxFile.Info getInfo(String... fields) {
        String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
        URL url = FILE_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());

        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        return new Info(response.getJSON());
    }

    /**
     * Updates the information about this file with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxFile file = new File(api, id);
     *BoxFile.Info info = file.getInfo();
     *file.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxFile.Info info) {
        URL url = FILE_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Gets any previous versions of this file. Note that only users with premium accounts will be able to retrieve
     * previous versions of their files.
     * @return a list of previous file versions.
     */
    public Collection<BoxFileVersion> getVersions() {
        URL url = VERSIONS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();

        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        JsonArray entries = jsonObject.get("entries").asArray();
        Collection<BoxFileVersion> versions = new ArrayList<BoxFileVersion>();
        for (JsonValue entry : entries) {
            versions.add(new BoxFileVersion(this.getAPI(), entry.asObject(), this.getID()));
        }

        return versions;
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     * @param fileContent a stream containing the new file contents.
     */
    public void uploadVersion(InputStream fileContent) {
        this.uploadVersion(fileContent, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version. Note that only users with premium accounts
     * will be able to view and recover previous versions of the file.
     * @param fileContent a stream containing the new file contents.
     * @param modified    the date that the new version was modified.
     */
    public void uploadVersion(InputStream fileContent, Date modified) {
        this.uploadVersion(fileContent, modified, 0, null);
    }

    /**
     * Uploads a new version of this file, replacing the current version, while reporting the progress to a
     * ProgressListener. Note that only users with premium accounts will be able to view and recover previous versions
     * of the file.
     * @param fileContent a stream containing the new file contents.
     * @param modified    the date that the new version was modified.
     * @param fileSize    the size of the file used for determining the progress of the upload.
     * @param listener    a listener for monitoring the upload's progress.
     */
    public void uploadVersion(InputStream fileContent, Date modified, long fileSize, ProgressListener listener) {
        URL uploadURL = CONTENT_URL_TEMPLATE.build(this.getAPI().getBaseUploadURL(), this.getID());
        BoxMultipartRequest request = new BoxMultipartRequest(getAPI(), uploadURL);
        if (fileSize > 0) {
            request.setFile(fileContent, "", fileSize);
        } else {
            request.setFile(fileContent, "");
        }

        if (modified != null) {
            request.putField("content_modified_at", modified);
        }

        BoxAPIResponse response;
        if (listener == null) {
            response = request.send();
        } else {
            response = request.send(listener);
        }
        response.disconnect();
    }

    /**
     * Gets a list of any comments on this file.
     * @return a list of comments on this file.
     */
    public List<BoxComment.Info> getComments() {
        URL url = GET_COMMENTS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        int totalCount = responseJSON.get("total_count").asInt();
        List<BoxComment.Info> comments = new ArrayList<BoxComment.Info>(totalCount);
        JsonArray entries = responseJSON.get("entries").asArray();
        for (JsonValue value : entries) {
            JsonObject commentJSON = value.asObject();
            BoxComment comment = new BoxComment(this.getAPI(), commentJSON.get("id").asString());
            BoxComment.Info info = comment.new Info(commentJSON);
            comments.add(info);
        }

        return comments;
    }

    /**
     * Contains additional information about a BoxFile.
     */
    public class Info extends BoxItem.Info {
        private String sha1;
        private String versionNumber;
        private long commentCount;
        private EnumSet<Permission> permissions;
        private String extension;
        private boolean isPackage;

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
        protected Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxFile getResource() {
            return BoxFile.this;
        }

        /**
         * Gets the SHA1 hash of the file.
         * @return the SHA1 hash of the file.
         */
        public String getSha1() {
            return this.sha1;
        }

        public String getVersionNumber() {
            return this.versionNumber;
        }

        public long getCommentCount() {
            return this.commentCount;
        }

        public EnumSet<Permission> getPermissions() {
            return this.permissions;
        }

        public String getExtension() {
            return this.extension;
        }

        public boolean getIsPackage() {
            return this.isPackage;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            switch (memberName) {
                case "sha1":
                    this.sha1 = value.asString();
                    break;
                case "version_number":
                    this.versionNumber = value.asString();
                    break;
                case "comment_count":
                    this.commentCount = value.asLong();
                    break;
                case "permissions":
                    this.permissions = this.parsePermissions(value.asObject());
                    break;
                case "extension":
                    this.extension = value.asString();
                    break;
                case "is_package":
                    this.isPackage = value.asBoolean();
                    break;
                default:
                    break;
            }
        }

        private EnumSet<Permission> parsePermissions(JsonObject jsonObject) {
            EnumSet<Permission> permissions = EnumSet.noneOf(Permission.class);
            for (JsonObject.Member member : jsonObject) {
                JsonValue value = member.getValue();
                if (value.isNull() || !value.asBoolean()) {
                    continue;
                }

                String memberName = member.getName();
                switch (memberName) {
                    case "can_download":
                        permissions.add(Permission.CAN_DOWNLOAD);
                        break;
                    case "can_upload":
                        permissions.add(Permission.CAN_UPLOAD);
                        break;
                    case "can_rename":
                        permissions.add(Permission.CAN_RENAME);
                        break;
                    case "can_delete":
                        permissions.add(Permission.CAN_DELETE);
                        break;
                    case "can_share":
                        permissions.add(Permission.CAN_SHARE);
                        break;
                    case "can_set_share_access":
                        permissions.add(Permission.CAN_SET_SHARE_ACCESS);
                        break;
                    case "can_preview":
                        permissions.add(Permission.CAN_PREVIEW);
                        break;
                    case "can_comment":
                        permissions.add(Permission.CAN_COMMENT);
                        break;
                    default:
                        break;
                }
            }

            return permissions;
        }
    }

    public enum Permission {
        CAN_DOWNLOAD ("can_download"),
        CAN_UPLOAD ("can_upload"),
        CAN_RENAME ("can_rename"),
        CAN_DELETE ("can_delete"),
        CAN_SHARE ("can_share"),
        CAN_SET_SHARE_ACCESS ("can_set_share_access"),
        CAN_PREVIEW ("can_preview"),
        CAN_COMMENT ("can_comment");

        private final String jsonValue;

        private Permission(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        public static Permission fromJSONValue(String jsonValue) {
            return Permission.valueOf(jsonValue.toUpperCase());
        }

        public String toJSONValue() {
            return this.jsonValue;
        }
    }
}
