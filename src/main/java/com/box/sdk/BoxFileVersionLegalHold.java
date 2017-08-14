package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Representing all holds on a file version.
 * Note that every file version can have a maximum of one file version legal hold.
 */
@BoxResourceType("file_version_legal_hold")
public class BoxFileVersionLegalHold extends BoxResource {

    /**
     * The URL template used for operation with file version legal hold with given ID.
     * @see #getInfo(String...)
     */
    public static final URLTemplate FILE_VERSION_HOLD_URL_TEMPLATE = new URLTemplate("file_version_legal_holds/%s");

    /**
     * Constructs a file version legal hold with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the resource.
     */
    public BoxFileVersionLegalHold(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * @param fields the fields to retrieve.
     * @return information about this file version legal hold.
     */
    public BoxFileVersionLegalHold.Info getInfo(String ... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = FILE_VERSION_HOLD_URL_TEMPLATE.buildWithQuery(
                this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Contains information about the file version legal hold.
     */
    public class Info extends BoxResource.Info {

        /**
         * Used for file version in case it was retrieved separately from file.
         */
        private static final String DEFAULT_FILE_ID = "0";

        /**
         * @see #getFileVersion()
         */
        private BoxFileVersion fileVersion;

        /**
         * @see #getFile()
         */
        private BoxFile.Info file;

        /**
         * @see #getAssignments()
         */
        private List<BoxLegalHoldAssignment.Info> assignments;

        /**
         * @see #getDeletedAt()
         */
        private Date deletedAt;

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
         * {@inheritDoc}
         */
        @Override
        public BoxResource getResource() {
            return BoxFileVersionLegalHold.this;
        }

        /**
         * @return the file version that is held.
         */
        public BoxFileVersion getFileVersion() {
            return this.fileVersion;
        }

        /**
         * @return the parent file of the file version that is held.
         * Note that there is no guarantee that the current version of this file is held.
         */
        public BoxFile.Info getFile() {
            return this.file;
        }

        /**
         * @return iterable with the assignments contributing to this file version legal hold.
         */
        public Iterable<BoxLegalHoldAssignment.Info> getAssignments() {
            return this.assignments;
        }

        /**
         * @return time that this file version legal hold was deleted.
         */
        public Date getDeletedAt() {
            return this.deletedAt;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);
            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("file")) {
                    JsonObject fileJSON = value.asObject();
                    if (this.file == null) {
                        String fileID = fileJSON.get("id").asString();
                        BoxFile file = new BoxFile(getAPI(), fileID);
                        this.file = file.new Info(fileJSON);
                    } else {
                        this.file.update(fileJSON);
                    }
                    if (this.fileVersion != null) {
                        this.fileVersion.setFileID(this.file.getID());
                    }
                } else if (memberName.equals("file_version")) {
                    JsonObject versionJSON = value.asObject();
                    String fileID = this.file != null ? this.file.getID() : DEFAULT_FILE_ID;
                    this.fileVersion = new BoxFileVersion(getAPI(), versionJSON, fileID);
                } else if (memberName.equals("legal_hold_policy_assignments")) {
                    JsonArray array = value.asArray();
                    this.assignments = new ArrayList<BoxLegalHoldAssignment.Info>();
                    for (JsonValue assignmentJSON : array) {
                        String assignmentID = ((JsonObject) assignmentJSON).get("id").asString();
                        BoxLegalHoldAssignment assignment = new BoxLegalHoldAssignment(getAPI(), assignmentID);
                        this.assignments.add(assignment.new Info((JsonObject) assignmentJSON));
                    }
                } else if (memberName.equals("deleted_at")) {
                    this.deletedAt = BoxDateFormat.parse(value.asString());
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }

    }
}
