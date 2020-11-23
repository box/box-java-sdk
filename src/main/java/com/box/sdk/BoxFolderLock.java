package com.box.sdk;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a lock on a folder.
 *
 * <p>Unless otherwise noted, the methods in this class can throw an unchecked {@link BoxAPIException} (unchecked
 * meaning that the compiler won't force you to handle it) if an error occurs. If you wish to implement custom error
 * handling for errors related to the Box REST API, you should capture this exception explicitly.</p>
 */
@BoxResourceType("folder_lock")
public class BoxFolderLock extends BoxResource {
    /**
     * Delete Folder Locks URL Template.
     */
    public static final URLTemplate DELETE_FOLDER_LOCK_URL_TEMPLATE = new URLTemplate("folder_locks/%s");

    /**
     * Constructs a BoxFolderLock with a given ID.
     *
     * @param api the API connection to be used by the folder lock.
     * @param id  the ID of the folder lock.
     */
    public BoxFolderLock(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Delete the lock on this folder.
     */
    public void delete() {
        URL url = DELETE_FOLDER_LOCK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Contains information about a BoxFolderLock.
     */
    public class Info extends BoxResource.Info {
        private BoxFolder.Info folder;
        private BoxUser.Info createdBy;
        private Date createdAt;
        private String lockType;
        private Map<String, Boolean> lockedOperations;

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
        Info(JsonObject jsonObject) {
            super(jsonObject);
        }

        @Override
        public BoxResource getResource() {
            return BoxFolderLock.this;
        }

        /**
         * Gets the folder that the lock applies to.
         *
         * @return The folder that the lock applies to.
         */
        public BoxFolder.Info getFolder() {
            return this.folder;
        }

        /**
         * Gets the user or group that created the lock.
         *
         * @return the user or group that created the lock.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets the date the folder lock object was created.
         *
         * @return the date the folder lock object was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        /**
         * Gets the lock type, always freeze.
         *
         * @return the lock type, always freeze.
         */
        public String getLockType() {
            return this.lockType;
        }

        /**
         * Gets the operations that have been locked.
         *
         * @return the operations that have been locked.
         */
        public Map<String, Boolean> getLockedOperations() {
            return this.lockedOperations;
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
                if (memberName.equals("folder")) {
                    JsonObject folderJSON = value.asObject();
                    String folderID = folderJSON.get("id").asString();
                    BoxFolder folder = new BoxFolder(getAPI(), folderID);
                    this.folder = folder.new Info(folderJSON);
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

                } else if (memberName.equals("lock_type")) {
                    this.lockType = value.asString();

                } else if (memberName.equals("locked_operations")) {
                    JsonObject lockedOperationsJSON = value.asObject();
                    Map<String, Boolean> operationsMap = new HashMap<String, Boolean>();
                    for (JsonObject.Member operationMember : lockedOperationsJSON) {
                        String operation = operationMember.getName();
                        Boolean operationBoolean = operationMember.getValue().asBoolean();
                        operationsMap.put(operation, operationBoolean);
                    }
                    this.lockedOperations = operationsMap;
                }
            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }
    }
}
