package com.box.sdk;

import com.box.sdk.sharedlink.BoxSharedLinkWithoutPermissionsRequest;
import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Represents an individual WebLink file on Box. This class can be used to retrieve the link's URL or perform other
 * common file operations (move, copy, delete, etc.).
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("web_link")
public class BoxWebLink extends BoxItem {

    /**
     * An array of all possible weblink fields that can be requested when calling {@link #getInfo(String...)}.
     */
    public static final String[] ALL_FIELDS = {"type", "id", "sequence_id", "etag", "name", "url", "description",
        "path_collection", "created_at", "modified_at", "trashed_at", "purged_at", "created_by", "modified_by",
        "owned_by", "shared_link", "parent", "item_status", "collections"};

    /**
     * Copy URL Template.
     */
    public static final URLTemplate COPY_URL_TEMPLATE = new URLTemplate("web_links/%s/copy");
    /**
     * Web Link URL Template.
     */
    public static final URLTemplate WEB_LINK_URL_TEMPLATE = new URLTemplate("web_links/%s");

    /**
     * Constructs a BoxWebLink for a weblink with a given ID.
     *
     * @param api the API connection to be used by the weblink.
     * @param id  the ID of the weblink.
     */
    public BoxWebLink(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Creates a new shared link for this item.
     *
     * <p>This method is a convenience method for manually creating a new shared link and applying it to this item with
     * {@link BoxItem.Info#setSharedLink}. You may want to create the shared link manually so that it can be updated along with
     * other changes to the item's info in a single network request, giving a boost to performance.</p>
     *
     * @param access      the access level of the shared link.
     * @param unshareDate the date and time at which the link will expire. Can be null to create a non-expiring link.
     * @param permissions the permissions of the shared link. Can be null to use the default permissions.
     * @return the created shared link.
     * @deprecated use {@link BoxWebLink#createSharedLink(BoxSharedLinkWithoutPermissionsRequest)}
     */
    @Override
    @Deprecated
    public BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
                                          BoxSharedLink.Permissions permissions) {

        if (permissions != null) {
            throw new IllegalArgumentException(
                "Cannot set permissions on a shared link to web link. "
                    + "The BoxWebLink#createSharedLink(BoxSharedLinkWithoutPermissionsRequest) is preferred."
            );
        }
        BoxSharedLink sharedLink = new BoxSharedLink(access, unshareDate, permissions);
        return this.createSharedLink(sharedLink);
    }

    /**
     * Creates new SharedLink for a BoxWebLink with a password.
     *
     * @param access      The access level of the shared link.
     * @param unshareDate A specified date to unshare the Box web link.
     * @param permissions The permissions to set on the shared link for the Box web link.
     * @param password    Password set on the shared link to give access to the Box web link.
     * @return information about the newly created shared link.
     * @deprecated Use {@link BoxWebLink#createSharedLink(BoxSharedLinkWithoutPermissionsRequest)}
     */
    @Deprecated
    public BoxSharedLink createSharedLink(BoxSharedLink.Access access, Date unshareDate,
                                          BoxSharedLink.Permissions permissions, String password) {

        if (permissions != null) {
            throw new IllegalArgumentException(
                "Cannot set permissions on a shared link to web link. "
                    + "The BoxWebLink#createSharedLink(BoxSharedLinkWithoutPermissionsRequest) is supported."
            );
        }
        BoxSharedLink sharedLink = new BoxSharedLink(access, unshareDate, permissions, password);
        return this.createSharedLink(sharedLink);
    }

    /**
     * Creates a shared link.
     *
     * @param sharedLinkRequest Shared link to create
     * @return Created shared link.
     */
    public BoxSharedLink createSharedLink(BoxSharedLinkWithoutPermissionsRequest sharedLinkRequest) {
        return createSharedLink(sharedLinkRequest.asSharedLink());
    }

    private BoxSharedLink createSharedLink(BoxSharedLink sharedLink) {
        BoxWebLink.Info info = new BoxWebLink.Info();
        info.setSharedLink(sharedLink);

        this.updateInfo(info);
        return info.getSharedLink();
    }


    @Override
    public BoxWebLink.Info copy(BoxFolder destination) {
        return this.copy(destination, null);
    }

    @Override
    public BoxWebLink.Info copy(BoxFolder destination, String newName) {
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
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            BoxWebLink copiedWebLink = new BoxWebLink(this.getAPI(), responseJSON.get("id").asString());
            return copiedWebLink.new Info(responseJSON);
        }
    }

    /**
     * Deletes this weblink by moving it to the trash.
     */
    public void delete() {
        URL url = WEB_LINK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    @Override
    public BoxItem.Info move(BoxFolder destination) {
        return this.move(destination, null);
    }

    @Override
    public BoxItem.Info move(BoxFolder destination, String newName) {
        URL url = WEB_LINK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject parent = new JsonObject();
        parent.add("id", destination.getID());

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("parent", parent);
        if (newName != null) {
            updateInfo.add("name", newName);
        }

        request.setBody(updateInfo.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            BoxWebLink movedWebLink = new BoxWebLink(this.getAPI(), responseJSON.get("id").asString());
            return movedWebLink.new Info(responseJSON);
        }
    }

    /**
     * Renames this weblink.
     *
     * @param newName the new name of the weblink.
     */
    public void rename(String newName) {
        URL url = WEB_LINK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");

        JsonObject updateInfo = new JsonObject();
        updateInfo.add("name", newName);

        request.setBody(updateInfo.toString());
        request.send().close();
    }

    @Override
    public BoxWebLink.Info getInfo(String... fields) {
        URL url = WEB_LINK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        if (fields.length > 0) {
            String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
            url = WEB_LINK_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        }

        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            return new Info(response.getJSON());
        }
    }

    /**
     * Updates the information about this weblink with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxWebLink webLink = new BoxWebLink(api, id);
     * BoxWebLink.Info info = webLink.getInfo();
     * webLink.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxWebLink.Info info) {
        URL url = WEB_LINK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
        }
    }

    @Override
    public BoxWebLink.Info setCollections(BoxCollection... collections) {
        JsonArray jsonArray = new JsonArray();
        for (BoxCollection collection : collections) {
            JsonObject collectionJSON = new JsonObject();
            collectionJSON.add("id", collection.getID());
            jsonArray.add(collectionJSON);
        }
        JsonObject infoJSON = new JsonObject();
        infoJSON.add("collections", jsonArray);

        String queryString = new QueryStringBuilder().appendParam("fields", ALL_FIELDS).toString();
        URL url = WEB_LINK_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), queryString, this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(infoJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            return new Info(jsonObject);
        }
    }

    /**
     * Contains information about a BoxWebLink.
     */
    public class Info extends BoxItem.Info {
        private URL linkURL;
        private String description;

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
         */
        public Info(JsonObject jsonObject) {
            super(jsonObject.toString());
        }

        @Override
        public BoxWebLink getResource() {
            return BoxWebLink.this;
        }

        /**
         * Gets the description of this weblink.
         *
         * @return the description of this weblink.
         */
        public String getDescription() {
            return this.description;
        }

        /**
         * Gets the URL this weblink points to.
         *
         * @return the URL this weblink points to.
         */
        public URL getLinkURL() {
            return this.linkURL;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("url")) {
                    try {
                        if (value.asString().isEmpty()) {
                            this.linkURL = null;
                        } else {
                            this.linkURL = new URL(value.asString());
                        }
                    } catch (MalformedURLException e) {
                        throw new BoxAPIException("Couldn't parse url for weblink", e);
                    }
                } else if (memberName.equals("description")) {
                    this.description = value.asString();
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}
