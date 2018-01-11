package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;
import java.util.regex.Pattern;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a comment on a file. Comments can be added directly to a file or they can be created as replies to other
 * comments.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("comment")
public class BoxComment extends BoxResource {

    /**
     * Add Comment URL Template.
     */
    public static final URLTemplate ADD_COMMENT_URL_TEMPLATE = new URLTemplate("comments");
    /**
     * Comment URL Template.
     */
    public static final URLTemplate COMMENT_URL_TEMPLATE = new URLTemplate("comments/%s");

    private static final Pattern MENTION_REGEX = Pattern.compile("@\\[.+:.+\\]");

    /**
     * Constructs a BoxComment for a comment with a given ID.
     * @param  api the API connection to be used with the comment.
     * @param  id  the ID of the comment.
     */
    public BoxComment(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Determines if a comment message contains an @mention.
     * @param  message the comment message.
     * @return true if the message contains an @mention; otherwise false.
     */
    static boolean messageContainsMention(String message) {
        return MENTION_REGEX.matcher(message).find();
    }

    /**
     * Gets information about this comment.
     * @return info about this comment.
     */
    public Info getInfo() {
        URL url = COMMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonResponse = JsonObject.readFrom(response.getJSON());

        return new Info(jsonResponse);
    }

    /**
     * Changes the message of this comment.
     * @param  newMessage the new message for this comment.
     * @return updated info about this comment.
     */
    public Info changeMessage(String newMessage) {
        Info newInfo = new Info();
        newInfo.setMessage(newMessage);

        URL url = COMMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(newInfo.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonResponse = JsonObject.readFrom(response.getJSON());

        return new Info(jsonResponse);
    }

    /**
     * Replies to this comment with another message.
     * @param  message the message for the reply.
     * @return info about the newly created reply comment.
     */
    public BoxComment.Info reply(String message) {
        JsonObject itemJSON = new JsonObject();
        itemJSON.add("type", "comment");
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
     * Deletes this comment.
     */
    public void delete() {
        URL url = COMMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxComment.
     */
    public class Info extends BoxResource.Info {
        private boolean isReplyComment;
        private String message;
        private String taggedMessage;
        private BoxUser.Info createdBy;
        private Date createdAt;
        private BoxResource.Info item;
        private BoxUser.Info modifiedBy;
        private Date modifiedAt;

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
         * Gets whether or not the comment is a reply to another comment.
         * @return true if this comment is a reply to another comment; otherwise false.
         */
        public boolean getIsReplyComment() {
            return this.isReplyComment;
        }

        /**
         * Gets the comment's message.
         * @return the comment's message.
         */
        public String getMessage() {
            if (this.taggedMessage != null) {
                return this.taggedMessage;
            }

            return this.message;
        }

        /**
         * Sets the comment's message. The message can contain @mentions by using the string @[userid:username] anywhere
         * within the message, where userid and username are the ID and username of the person being mentioned.
         * @param message the comment's new message.
         */
        public void setMessage(String message) {
            if (messageContainsMention(message)) {
                this.taggedMessage = message;
                this.addPendingChange("tagged_message", message);
                this.removePendingChange("message");
            } else {
                this.message = message;
                this.addPendingChange("message", message);
                this.removePendingChange("tagged_message");
            }
        }

        /**
         * Gets info about the user who created the comment.
         * @return info about the user who created the comment.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets the time the comment was created.
         * @return the time the comment was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets info about the item this comment is attached to. If the comment is a reply, then the item will be
         * another BoxComment. Otherwise, the item will be a {@link BoxFile}.
         * @return the item this comment is attached to.
         */
        public BoxResource.Info getItem() {
            return this.item;
        }

        /**
         * Gets info about the user who last modified the comment.
         * @return info about the user who last modified the comment.
         */
        public BoxUser.Info getModifiedBy() {
            return this.modifiedBy;
        }

        /**
         * Gets the time the comment was last modified.
         * @return the time the comment was last modified.
         */
        public Date getModifiedAt() {
            return this.modifiedAt;
        }

        @Override
        public BoxComment getResource() {
            return BoxComment.this;
        }

        @Override
        protected void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            try {
                String memberName = member.getName();
                JsonValue value = member.getValue();
                if (memberName.equals("is_reply_comment")) {
                    this.isReplyComment = value.asBoolean();

                } else if (memberName.equals("message")) {
                    this.message = value.asString();

                } else if (memberName.equals("tagged_message")) {
                    this.taggedMessage = value.asString();

                } else if (memberName.equals("created_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.createdBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.createdBy = user.new Info(userJSON);
                    } else {
                        this.createdBy.update(userJSON);
                    }

                } else if (memberName.equals("created_at")) {
                    this.createdAt = BoxDateFormat.parse(value.asString());

                } else if (memberName.equals("item")) {
                    this.parseItem(value);

                } else if (memberName.equals("modified_by")) {
                    JsonObject userJSON = value.asObject();
                    if (this.modifiedBy == null) {
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.modifiedBy = user.new Info(userJSON);
                    } else {
                        this.modifiedBy.update(userJSON);
                    }
                } else if (memberName.equals("modified_at")) {
                    this.modifiedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }

        private void parseItem(JsonValue value) {
            JsonObject itemJSON = value.asObject();
            String itemType = itemJSON.get("type").asString();
            if (itemType.equals("file")) {
                this.updateItemAsFile(itemJSON);
            } else if (itemType.equals("comment")) {
                this.updateItemAsComment(itemJSON);
            }
        }

        private void updateItemAsFile(JsonObject itemJSON) {
            String itemID = itemJSON.get("id").asString();
            if (this.item != null && this.item instanceof BoxFile.Info && this.item.getID().equals(itemID)) {
                this.item.update(itemJSON);
            } else {
                BoxFile file = new BoxFile(getAPI(), itemID);
                this.item = file.new Info(itemJSON);
            }
        }

        private void updateItemAsComment(JsonObject itemJSON) {
            String itemType = itemJSON.get("type").asString();
            String itemID = itemJSON.get("id").asString();
            if (this.item != null && this.item instanceof BoxComment.Info && this.item.getID().equals(itemID)) {
                this.item.update(itemJSON);
            } else {
                BoxComment comment = new BoxComment(getAPI(), itemID);
                this.item = comment.new Info(itemJSON);
            }
        }
    }
}
