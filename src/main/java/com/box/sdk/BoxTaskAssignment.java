package com.box.sdk;

import java.net.URL;
import java.text.ParseException;
import java.util.Date;

import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 * Represents a task assignment on Box, which can be used to assign a task to a single user. There can be multiple
 * assignments on a single task.
 */
@BoxResourceType("task_assignment")
public class BoxTaskAssignment extends BoxResource {

    /**
     * Task Assignment URL Template.
     */
    public static final URLTemplate TASK_ASSIGNMENT_URL_TEMPLATE = new URLTemplate("task_assignments/%s");

    /**
     * Constructs a BoxTaskAssignment for a task assignment with a given ID.
     *
     * @param api the API connection to be used by the resource.
     * @param id  the ID of the task assignment.
     */
    public BoxTaskAssignment(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Deletes this task assignment.
     */
    public void delete() {
        URL url = TASK_ASSIGNMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        BoxAPIResponse response = request.send();
        response.disconnect();
    }

    /**
     * Gets information about this task assignment.
     * @return info about this task assignment.
     */
    public Info getInfo() {
        URL url = TASK_ASSIGNMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Gets information about this task assignment.
     * @param fields the fields to retrieve.
     * @return info about this task assignment.
     */
    public Info getInfo(String... fields) {
        QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        URL url = TASK_ASSIGNMENT_URL_TEMPLATE.buildWithQuery(
                this.getAPI().getBaseURL(), builder.toString(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "GET");
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());
        return new Info(responseJSON);
    }

    /**
     * Updates the information about this task assignment with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxTaskAssignment taskAssignment = new BoxTaskAssignment(api, id);
     *BoxTaskAssignment.Info info = taskAssignment.getInfo();
     *taskAssignment.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxTaskAssignment.Info info) {
        URL url = TASK_ASSIGNMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject jsonObject = JsonObject.readFrom(response.getJSON());
        info.update(jsonObject);
    }

    /**
     * Contains information about a task assignment.
     */
    public class Info extends BoxResource.Info {
        private BoxItem.Info item;
        private BoxUser.Info assignedTo;
        private String message;
        private Date completedAt;
        private Date assignedAt;
        private Date remindedAt;
        private ResolutionState resolutionState;
        private String status;
        private BoxUser.Info assignedBy;

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

        @Override
        public BoxResource getResource() {
            return BoxTaskAssignment.this;
        }

        /**
         * Gets the item associated with this task.
         * @return the item associated with this task.
         */
        public BoxItem.Info getItem() {
            return this.item;
        }

        /**
         * Gets the user the assignment is assigned to.
         * @return the user assigned to this assignment.
         */
        public BoxUser.Info getAssignedTo() {
            return this.assignedTo;
        }

        /**
         * Gets the message that will be included with this task assignment.
         * @return the message that will be included with this task assignment.
         */
        public String getMessage() {
            return this.message;
        }

        /**
         * Gets the date the assignment is to be completed at.
         * @return the date the assignment is to be completed at.
         */
        public Date getCompletedAt() {
            return this.completedAt;
        }

        /**
         * Gets the date the assignment was assigned at.
         * @return the date the assignment was assigned at.
         */
        public Date getAssignedAt() {
            return this.assignedAt;
        }

        /**
         * Gets the date the assignee is to be reminded at.
         * @return the date the assignee is to be reminded at.
         */
        public Date getRemindedAt() {
            return this.remindedAt;
        }

        /**
         * Gets the current resolution state of the assignment.
         * @return the current resolution state of the assignment.
         */
        public ResolutionState getResolutionState() {
            return this.resolutionState;
        }

        /**
         * Gets the current status of the assignment.
         * @return the current status of the assignment.
         */
        public String getStatus() {
            return this.status;
        }

        /**
         * Sets the status for the assignment.
         * @param status the status to be set on the assignment.
         */
        public void setStatus(String status) {
            this.status = status;
            this.addPendingChange("status", status);
        }

        /**
         * Gets the user that assigned the assignment.
         * @return the user that assigned the assignment.
         */
        public BoxUser.Info getAssignedBy() {
            return this.assignedBy;
        }

        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                if (memberName.equals("item")) {
                    JsonObject itemJSON = value.asObject();
                    String itemID = itemJSON.get("id").asString();
                    BoxFile file = new BoxFile(getAPI(), itemID);
                    this.item = file.new Info(itemJSON);
                } else if (memberName.equals("assigned_to")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.assignedTo = user.new Info(userJSON);
                } else if (memberName.equals("message")) {
                    this.message = value.asString();
                } else if (memberName.equals("completed_at")) {
                    this.completedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("assigned_at")) {
                    this.assignedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("reminded_at")) {
                    this.remindedAt = BoxDateFormat.parse(value.asString());
                } else if (memberName.equals("resolution_state")) {
                    this.resolutionState = ResolutionState.fromJSONString(value.asString());
                } else if (memberName.equals("status")) {
                    this.status = value.asString();
                } else if (memberName.equals("assigned_by")) {
                    JsonObject userJSON = value.asObject();
                    String userID = userJSON.get("id").asString();
                    BoxUser user = new BoxUser(getAPI(), userID);
                    this.assignedBy = user.new Info(userJSON);
                }
            } catch (ParseException e) {
                assert false : "A ParseException indicates a bug in the SDK.";
            }
        }
    }

    /**
     * Enumerates the possible resolution states that a task assignment can have.
     */
    public enum ResolutionState {
        /**
         * The task assignment has been completed.
         */
        COMPLETED ("completed"),

        /**
         * The task assignment is incomplete.
         */
        INCOMPLETE ("incomplete"),

        /**
         * The task assignment has been approved.
         */
        APPROVED ("approved"),

        /**
         * The task assignment has been rejected.
         */
        REJECTED ("rejected");

        private final String jsonValue;

        private ResolutionState(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static ResolutionState fromJSONString(String jsonValue) {
            if (jsonValue.equals("completed")) {
                return COMPLETED;
            } else if (jsonValue.equals("incomplete")) {
                return INCOMPLETE;
            } else if (jsonValue.equals("approved")) {
                return APPROVED;
            } else if (jsonValue.equals("rejected")) {
                return REJECTED;
            } else {
                throw new IllegalArgumentException("The provided JSON value isn't a valid ResolutionState.");
            }
        }

        String toJSONString() {
            return this.jsonValue;
        }
    }
}
