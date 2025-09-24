package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * Represents a collaboration between a user and another user or group. Collaborations are Box's
 * equivalent of access control lists. They can be used to set and apply permissions for users or
 * groups to folders.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link
 * BoxAPIException} (unchecked meaning that the compiler won't force you to handle it) if an error
 * occurs. If you wish to implement custom error handling for errors related to the Box REST API,
 * you should capture this exception explicitly.
 */
@BoxResourceType("collaboration")
public class BoxCollaboration extends BoxResource {

  /** All possible fields on a collaboration object. */
  public static final String[] ALL_FIELDS = {
    "type",
    "id",
    "item",
    "accessible_by",
    "role",
    "expires_at",
    "can_view_path",
    "status",
    "acknowledged_at",
    "created_by",
    "created_at",
    "modified_at",
    "is_access_only"
  };

  /** Collaborations URL Template. */
  public static final URLTemplate COLLABORATIONS_URL_TEMPLATE = new URLTemplate("collaborations");
  /** Pending Collaborations URL. */
  public static final URLTemplate PENDING_COLLABORATIONS_URL =
      new URLTemplate("collaborations?status=pending");
  /** Collaboration URL Template. */
  public static final URLTemplate COLLABORATION_URL_TEMPLATE = new URLTemplate("collaborations/%s");
  /** Get All File Collaboations URL. */
  public static final URLTemplate GET_ALL_FILE_COLLABORATIONS_URL =
      new URLTemplate("files/%s/collaborations");

  /**
   * Constructs a BoxCollaboration for a collaboration with a given ID.
   *
   * @param api the API connection to be used by the collaboration.
   * @param id the ID of the collaboration.
   */
  public BoxCollaboration(BoxAPIConnection api, String id) {
    super(api, id);
  }

  /**
   * Create a new collaboration object.
   *
   * @param api the API connection used to make the request.
   * @param accessibleBy the JSON object describing who should be collaborated.
   * @param item the JSON object describing which item to collaborate.
   * @param role the role to give the collaborators.
   * @param notify the user/group should receive email notification of the collaboration or not.
   * @param canViewPath the view path collaboration feature is enabled or not.
   * @return info about the new collaboration.
   */
  protected static BoxCollaboration.Info create(
      BoxAPIConnection api,
      JsonObject accessibleBy,
      JsonObject item,
      BoxCollaboration.Role role,
      Boolean notify,
      Boolean canViewPath) {
    return create(api, accessibleBy, item, role, notify, canViewPath, null, null);
  }

  /**
   * Create a new collaboration object.
   *
   * @param api the API connection used to make the request.
   * @param accessibleBy the JSON object describing who should be collaborated.
   * @param item the JSON object describing which item to collaborate.
   * @param role the role to give the collaborators.
   * @param notify the user/group should receive email notification of the collaboration or not.
   * @param canViewPath the view path collaboration feature is enabled or not.
   * @param expiresAt the date the collaboration expires
   * @return info about the new collaboration.
   */
  protected static BoxCollaboration.Info create(
      BoxAPIConnection api,
      JsonObject accessibleBy,
      JsonObject item,
      BoxCollaboration.Role role,
      Boolean notify,
      Boolean canViewPath,
      Date expiresAt) {
    return create(api, accessibleBy, item, role, notify, canViewPath, expiresAt, null);
  }

  /**
   * Create a new collaboration object.
   *
   * @param api the API connection used to make the request.
   * @param accessibleBy the JSON object describing who should be collaborated.
   * @param item the JSON object describing which item to collaborate.
   * @param role the role to give the collaborators.
   * @param notify the user/group should receive email notification of the collaboration or not.
   * @param canViewPath the view path collaboration feature is enabled or not.
   * @param expiresAt the date the collaboration expires
   * @param isAccessOnly the collaboration is an access only collaboration or not.
   * @return info about the new collaboration.
   */
  protected static BoxCollaboration.Info create(
      BoxAPIConnection api,
      JsonObject accessibleBy,
      JsonObject item,
      BoxCollaboration.Role role,
      Boolean notify,
      Boolean canViewPath,
      Date expiresAt,
      Boolean isAccessOnly) {

    String queryString = "";
    if (notify != null) {
      queryString = new QueryStringBuilder().appendParam("notify", notify.toString()).toString();
    }
    URL url;
    if (queryString.length() > 0) {
      url = COLLABORATIONS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), queryString);
    } else {
      url = COLLABORATIONS_URL_TEMPLATE.build(api.getBaseURL());
    }

    JsonObject requestJSON = new JsonObject();
    requestJSON.add("item", item);
    requestJSON.add("accessible_by", accessibleBy);
    requestJSON.add("role", role.toJSONString());
    if (canViewPath != null) {
      requestJSON.add("can_view_path", canViewPath);
    }
    if (expiresAt != null) {
      requestJSON.add("expires_at", BoxDateFormat.format(expiresAt));
    }
    if (isAccessOnly != null) {
      requestJSON.add("is_access_only", isAccessOnly);
    }

    BoxJSONRequest request = new BoxJSONRequest(api, url, "POST");

    request.setBody(requestJSON.toString());
    try (BoxJSONResponse response = request.send()) {
      JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
      BoxCollaboration newCollaboration =
          new BoxCollaboration(api, responseJSON.get("id").asString());
      return newCollaboration.new Info(responseJSON);
    }
  }

  /**
   * Gets all pending collaboration invites for the current user.
   *
   * @param api the API connection to use.
   * @return a collection of pending collaboration infos.
   */
  public static Collection<Info> getPendingCollaborations(BoxAPIConnection api, String... fields) {
    QueryStringBuilder queryBuilder = new QueryStringBuilder();
    queryBuilder.appendParam("status", "pending");
    if (fields.length > 0) {
      queryBuilder.appendParam("fields", fields);
    }
    URL url = COLLABORATIONS_URL_TEMPLATE.buildWithQuery(api.getBaseURL(), queryBuilder.toString());

    BoxJSONRequest request = new BoxJSONRequest(api, url, "GET");
    try (BoxJSONResponse response = request.send()) {
      JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

      int entriesCount = responseJSON.get("total_count").asInt();
      Collection<BoxCollaboration.Info> collaborations = new ArrayList<>(entriesCount);
      JsonArray entries = responseJSON.get("entries").asArray();
      for (JsonValue entry : entries) {
        JsonObject entryObject = entry.asObject();
        BoxCollaboration collaboration =
            new BoxCollaboration(api, entryObject.get("id").asString());
        BoxCollaboration.Info info = collaboration.new Info(entryObject);
        collaborations.add(info);
      }

      return collaborations;
    }
  }

  /**
   * Used to retrieve all collaborations associated with the item.
   *
   * @param api BoxAPIConnection from the associated file.
   * @param fileID FileID of the associated file
   * @param pageSize page size for server pages of the Iterable
   * @param fields the optional fields to retrieve.
   * @return An iterable of BoxCollaboration.Info instances associated with the item.
   */
  public static BoxResourceIterable<Info> getAllFileCollaborations(
      final BoxAPIConnection api, String fileID, int pageSize, String... fields) {
    QueryStringBuilder builder = new QueryStringBuilder();
    if (fields.length > 0) {
      builder.appendParam("fields", fields);
    }
    return new BoxResourceIterable<BoxCollaboration.Info>(
        api,
        GET_ALL_FILE_COLLABORATIONS_URL.buildWithQuery(
            api.getBaseURL(), builder.toString(), fileID),
        pageSize) {

      @Override
      protected BoxCollaboration.Info factory(JsonObject jsonObject) {
        String id = jsonObject.get("id").asString();
        return new BoxCollaboration(api, id).new Info(jsonObject);
      }
    };
  }

  /**
   * Gets information about this collection with a custom set of fields.
   *
   * @param fields the fields to retrieve.
   * @return info about the collaboration.
   */
  public Info getInfo(String... fields) {
    URL url = COLLABORATION_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
    if (fields.length > 0) {
      String queryString = new QueryStringBuilder().appendParam("fields", fields).toString();
      url =
          COLLABORATION_URL_TEMPLATE.buildWithQuery(
              this.getAPI().getBaseURL(), queryString, this.getID());
    }

    BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
    try (BoxJSONResponse response = request.send()) {
      return new Info(response.getJSON());
    }
  }

  /**
   * Updates the information about this collaboration with any info fields that have been modified
   * locally.
   *
   * @param info the updated info.
   */
  public void updateInfo(Info info) {
    BoxAPIConnection api = this.getAPI();
    URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

    BoxJSONRequest request = new BoxJSONRequest(api, url, "PUT");
    request.setBody(info.getPendingChanges());
    try (BoxJSONResponse response = request.send()) {
      JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
      info.update(jsonObject);
    }
  }

  /** Deletes this collaboration. */
  public void delete() {
    BoxAPIConnection api = this.getAPI();
    URL url = COLLABORATION_URL_TEMPLATE.build(api.getBaseURL(), this.getID());

    BoxAPIRequest request = new BoxAPIRequest(api, url, "DELETE");
    request.send().close();
  }

  /** Enumerates the possible statuses that a collaboration can have. */
  public enum Status {
    /** The collaboration has been accepted. */
    ACCEPTED,

    /** The collaboration is waiting to be accepted or rejected. */
    PENDING,

    /** The collaboration has been rejected. */
    REJECTED
  }

  /** Enumerates the possible access levels that a collaborator can have. */
  public enum Role {
    /**
     * An Editor has full read/write access to a folder. Once invited to a folder, they will be able
     * to view, download, upload, edit, delete, copy, move, rename, generate shared links, make
     * comments, assign tasks, create tags, and invite/remove collaborators. They will not be able
     * to delete or move root level folders.
     */
    EDITOR("editor"),

    /**
     * The viewer role has full read access to a folder. Once invited to a folder, they will be able
     * to preview, download, make comments, and generate shared links. They will not be able to add
     * tags, invite new collaborators, upload, edit, or delete items in the folder.
     */
    VIEWER("viewer"),

    /**
     * The previewer role has limited read access to a folder. They will only be able to preview the
     * items in the folder using the integrated content viewer. They will not be able to share,
     * upload, edit, or delete any content. This role is only available to enterprise accounts.
     */
    PREVIEWER("previewer"),

    /**
     * The uploader has limited write access to a folder. They will only be able to upload and see
     * the names of the items in a folder. They will not able to download or view any content. This
     * role is only available to enterprise accounts.
     */
    UPLOADER("uploader"),

    /**
     * The previewer-uploader role is a combination of previewer and uploader. A user with this
     * access level will be able to preview files using the integrated content viewer as well as
     * upload items into the folder. They will not be able to download, edit, or share, items in the
     * folder. This role is only available to enterprise accounts.
     */
    PREVIEWER_UPLOADER("previewer uploader"),

    /**
     * The viewer-uploader role is a combination of viewer and uploader. A viewer-uploader has full
     * read access to a folder and limited write access. They are able to preview, download, add
     * comments, generate shared links, and upload content to the folder. They will not be able to
     * add tags, invite new collaborators, edit, or delete items in the folder. This role is only
     * available to enterprise accounts.
     */
    VIEWER_UPLOADER("viewer uploader"),

    /**
     * The co-owner role has all of the functional read/write access that an editor does. This
     * permission level has the added ability of being able to manage users in the folder. A
     * co-owner can add new collaborators, change access levels of existing collaborators, and
     * remove collaborators. However, they will not be able to manipulate the owner of the folder or
     * transfer ownership to another user. This role is only available to enterprise accounts.
     */
    CO_OWNER("co-owner"),

    /**
     * The owner role has all of the functional capabilities of a co-owner. However, they will be
     * able to manipulate the owner of the folder or transfer ownership to another user. This role
     * is only available to enterprise accounts.
     */
    OWNER("owner");

    private final String jsonValue;

    Role(String jsonValue) {
      this.jsonValue = jsonValue;
    }

    static Role fromJSONString(String jsonValue) {
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

    String toJSONString() {
      return this.jsonValue;
    }
  }

  /** Contains information about a BoxCollaboration. */
  public class Info extends BoxResource.Info {
    private BoxUser.Info createdBy;
    private Date createdAt;
    private Date modifiedAt;
    private Date expiresAt;
    private Status status;
    private BoxCollaborator.Info accessibleBy;
    private Role role;
    private Date acknowledgedAt;
    private BoxItem.Info item;
    private String inviteEmail;
    private boolean canViewPath;
    private boolean isAccessOnly;

    /** Constructs an empty Info object. */
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

    Info(JsonObject jsonObject) {
      super(jsonObject);
    }

    /**
     * Gets the user who created the collaboration.
     *
     * @return the user who created the collaboration.
     */
    public BoxUser.Info getCreatedBy() {
      return this.createdBy;
    }

    /**
     * Gets the time the collaboration was created.
     *
     * @return the time the collaboration was created.
     */
    public Date getCreatedAt() {
      return this.createdAt;
    }

    /**
     * Gets the time the collaboration was last modified.
     *
     * @return the time the collaboration was last modified.
     */
    public Date getModifiedAt() {
      return this.modifiedAt;
    }

    /**
     * Gets the time the collaboration will expire.
     *
     * @return the time the collaboration will expire.
     */
    public Date getExpiresAt() {
      return this.expiresAt;
    }

    /**
     * Set the time the collaboration will expire.
     *
     * @param expiresAt the expiration date of the collaboration.
     */
    public void setExpiresAt(Date expiresAt) {
      this.expiresAt = expiresAt;
      this.addPendingChange("expires_at", BoxDateFormat.format(expiresAt));
    }

    /**
     * Gets a boolean indicator whether "view path collaboration" feature is enabled or not. When
     * set to true this allows the invitee to see the entire parent path to the item. It is
     * important to note that this does not grant privileges in any parent folder.
     *
     * @return the Boolean value indicating if "view path collaboration" is enabled or not
     */
    public boolean getCanViewPath() {
      return this.canViewPath;
    }

    /**
     * Sets the permission for "view path collaboration" feature. When set to true this allows the
     * invitee to to see the entire parent path to the item
     *
     * @param canViewState the boolean value indicating whether the invitee can see the parent
     *     folder.
     */
    public void setCanViewPath(boolean canViewState) {
      this.canViewPath = canViewState;
      this.addPendingChange("can_view_path", canViewState);
    }

    /**
     * Gets a boolean indicator weather "is access only" feature is enabled or not. This field is
     * read only. It is used to indicate whether a collaboration is an Access Only Collaboration
     * (AOC). When set to true, it separates access from interest by hiding collaborated items from
     * the All Files page and the ALF stream. This means that users who have been granted access
     * through AOCs will not see these items in their regular file view.
     */
    public boolean getIsAccessOnly() {
      return this.isAccessOnly;
    }

    /**
     * The email address used to invite an un-registered collaborator, if they are not a registered
     * user.
     *
     * @return the email for the un-registed collaborator.
     */
    public String getInviteEmail() {
      return this.inviteEmail;
    }

    /**
     * Gets the status of the collaboration.
     *
     * @return the status of the collaboration.
     */
    public Status getStatus() {
      return this.status;
    }

    /**
     * Sets the status of the collaboration in order to accept or reject the collaboration if it's
     * pending.
     *
     * @param status the new status of the collaboration.
     */
    public void setStatus(Status status) {
      this.status = status;
      this.addPendingChange("status", status.name().toLowerCase(java.util.Locale.ROOT));
    }

    /**
     * Gets the collaborator who this collaboration applies to.
     *
     * @return the collaborator who this collaboration applies to.
     */
    public BoxCollaborator.Info getAccessibleBy() {
      return this.accessibleBy;
    }

    /**
     * Gets the level of access the collaborator has.
     *
     * @return the level of access the collaborator has.
     */
    public Role getRole() {
      return this.role;
    }

    /**
     * Sets the level of access the collaborator has.
     *
     * @param role the new level of access to give the collaborator.
     */
    public void setRole(Role role) {
      this.role = role;
      this.addPendingChange("role", role.toJSONString());
    }

    /**
     * Gets the time the collaboration's status was changed.
     *
     * @return the time the collaboration's status was changed.
     */
    public Date getAcknowledgedAt() {
      return this.acknowledgedAt;
    }

    /**
     * Gets the folder the collaboration is related to.
     *
     * @return the folder the collaboration is related to.
     */
    public BoxItem.Info getItem() {
      return this.item;
    }

    @Override
    public BoxCollaboration getResource() {
      return BoxCollaboration.this;
    }

    @SuppressWarnings("checkstyle:MissingSwitchDefault")
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
            this.createdAt = BoxDateFormat.parse(value.asString());

            break;
          case "modified_at":
            this.modifiedAt = BoxDateFormat.parse(value.asString());
            break;
          case "expires_at":
            this.expiresAt = BoxDateFormat.parse(value.asString());
            break;
          case "status":
            String statusString = value.asString().toUpperCase(java.util.Locale.ROOT);
            this.status = Status.valueOf(statusString);

            break;
          case "accessible_by":
            JsonObject accessibleByJSON = value.asObject();
            if (this.accessibleBy == null) {
              this.accessibleBy = this.parseAccessibleBy(accessibleByJSON);
            } else {
              this.updateAccessibleBy(accessibleByJSON);
            }
            break;
          case "role":
            this.role = Role.fromJSONString(value.asString());
            break;
          case "acknowledged_at":
            this.acknowledgedAt = BoxDateFormat.parse(value.asString());
            break;
          case "can_view_path":
            this.canViewPath = value.asBoolean();
            break;
          case "is_access_only":
            this.isAccessOnly = value.asBoolean();
            break;
          case "invite_email":
            this.inviteEmail = value.asString();
            break;
          case "item":
            JsonObject itemJson = value.asObject();
            if (this.item == null) {
              this.item = selectCollaborationItem(itemJson);
            } else {
              this.item.update(itemJson);
            }
            break;
        }
      } catch (Exception e) {
        throw new BoxDeserializationException(memberName, value.toString(), e);
      }
    }

    private BoxItem.Info selectCollaborationItem(JsonObject itemJson) {
      String itemId = itemJson.get("id").asString();
      String itemType = itemJson.get("type").asString();
      switch (itemType) {
        case BoxFile.TYPE:
          return new BoxFile(getAPI(), itemId).new Info(itemJson);
        case BoxFolder.TYPE:
          return new BoxFolder(getAPI(), itemId).new Info(itemJson);
        default:
          throw new IllegalStateException(
              String.format(
                  "Unsupported collaboration item type '%s': JSON %n%s", itemType, itemJson));
      }
    }

    private void updateAccessibleBy(JsonObject json) {
      String type = json.get("type").asString();
      if ((type.equals("user") && this.accessibleBy instanceof BoxUser.Info)
          || (type.equals("group") && this.accessibleBy instanceof BoxGroup.Info)) {

        this.accessibleBy.update(json);
      } else {
        this.accessibleBy = this.parseAccessibleBy(json);
      }
    }

    private BoxCollaborator.Info parseAccessibleBy(JsonObject json) {
      String id = json.get("id").asString();
      String type = json.get("type").asString();
      BoxCollaborator.Info parsedInfo = null;
      if (type.equals("user")) {
        BoxUser user = new BoxUser(getAPI(), id);
        parsedInfo = user.new Info(json);
      } else if (type.equals("group")) {
        BoxGroup group = new BoxGroup(getAPI(), id);
        parsedInfo = group.new Info(json);
      }

      return parsedInfo;
    }
  }
}
