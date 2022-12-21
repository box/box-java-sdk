package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Represents a task on Box. Tasks can have a due date and can be assigned to a specific user.
 */
@BoxResourceType("task")
public class BoxTask extends BoxResource {

    /**
     * Task URL Template.
     */
    public static final URLTemplate TASK_URL_TEMPLATE = new URLTemplate("tasks/%s");
    /**
     * Get Assignments URL Template.
     */
    public static final URLTemplate GET_ASSIGNMENTS_URL_TEMPLATE = new URLTemplate("tasks/%s/assignments");
    /**
     * Add Task Assignment URL Template.
     */
    public static final URLTemplate ADD_TASK_ASSIGNMENT_URL_TEMPLATE = new URLTemplate("task_assignments");

    /**
     * Constructs a BoxTask for a task with a given ID.
     *
     * @param api the API connection to be used by the task.
     * @param id  the ID of the task.
     */
    public BoxTask(BoxAPIConnection api, String id) {
        super(api, id);
    }

    /**
     * Deletes this task.
     */
    public void delete() {
        URL url = TASK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxAPIRequest request = new BoxAPIRequest(this.getAPI(), url, "DELETE");
        request.send().close();
    }

    /**
     * Adds a new assignment to this task.
     *
     * @param assignTo the user to assign the assignment to.
     * @return information about the newly added task assignment.
     */
    public BoxTaskAssignment.Info addAssignment(BoxUser assignTo) {
        JsonObject taskJSON = new JsonObject();
        taskJSON.add("type", "task");
        taskJSON.add("id", this.getID());

        JsonObject assignToJSON = new JsonObject();
        assignToJSON.add("id", assignTo.getID());

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("task", taskJSON);
        requestJSON.add("assign_to", assignToJSON);

        URL url = ADD_TASK_ASSIGNMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            BoxTaskAssignment addedAssignment = new BoxTaskAssignment(this.getAPI(), responseJSON.get("id").asString());
            return addedAssignment.new Info(responseJSON);
        }
    }

    /**
     * Adds a new assignment to this task using user's login as identifier.
     *
     * @param assignToLogin the login of user to assign the task to.
     * @return information about the newly added task assignment.
     */
    public BoxTaskAssignment.Info addAssignmentByLogin(String assignToLogin) {
        JsonObject taskJSON = new JsonObject();
        taskJSON.add("type", "task");
        taskJSON.add("id", this.getID());

        JsonObject assignToJSON = new JsonObject();
        assignToJSON.add("login", assignToLogin);

        JsonObject requestJSON = new JsonObject();
        requestJSON.add("task", taskJSON);
        requestJSON.add("assign_to", assignToJSON);

        URL url = ADD_TASK_ASSIGNMENT_URL_TEMPLATE.build(this.getAPI().getBaseURL());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "POST");
        request.setBody(requestJSON.toString());
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            BoxTaskAssignment addedAssignment = new BoxTaskAssignment(this.getAPI(), responseJSON.get("id").asString());
            return addedAssignment.new Info(responseJSON);
        }
    }

    /**
     * Gets any assignments for this task.
     *
     * @return a list of assignments for this task.
     */
    public List<BoxTaskAssignment.Info> getAssignments() {
        URL url = GET_ASSIGNMENTS_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();

            int totalCount = responseJSON.get("total_count").asInt();
            List<BoxTaskAssignment.Info> assignments = new ArrayList<>(totalCount);
            JsonArray entries = responseJSON.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject assignmentJSON = value.asObject();
                BoxTaskAssignment assignment =
                    new BoxTaskAssignment(this.getAPI(), assignmentJSON.get("id").asString());
                BoxTaskAssignment.Info info = assignment.new Info(assignmentJSON);
                assignments.add(info);
            }

            return assignments;
        }
    }

    /**
     * Gets an iterable of all the assignments of this task.
     *
     * @param fields the fields to retrieve.
     * @return an iterable containing info about all the assignments.
     */
    public Iterable<BoxTaskAssignment.Info> getAllAssignments(String... fields) {
        final QueryStringBuilder builder = new QueryStringBuilder();
        if (fields.length > 0) {
            builder.appendParam("fields", fields);
        }
        return () -> {
            URL url = GET_ASSIGNMENTS_URL_TEMPLATE.buildWithQuery(
                BoxTask.this.getAPI().getBaseURL(), builder.toString(), BoxTask.this.getID());
            return new BoxTaskAssignmentIterator(BoxTask.this.getAPI(), url);
        };
    }


    /**
     * Gets information about this task.
     *
     * @param fields the fields to retrieve.
     * @return info about this task.
     */
    public Info getInfo(String... fields) {
        URL url = TASK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        if (fields.length > 0) {
            QueryStringBuilder builder = new QueryStringBuilder().appendParam("fields", fields);
            url = TASK_URL_TEMPLATE.buildWithQuery(this.getAPI().getBaseURL(), builder.toString(), this.getID());
        }
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "GET");
        try (BoxJSONResponse response = request.send()) {
            JsonObject responseJSON = Json.parse(response.getJSON()).asObject();
            return new Info(responseJSON);
        }
    }

    /**
     * Updates the information about this task with any info fields that have been modified locally.
     *
     * <p>The only fields that will be updated are the ones that have been modified locally. For example, the following
     * code won't update any information (or even send a network request) since none of the info's fields were
     * changed:</p>
     *
     * <pre>BoxTask task = new BoxTask(api, id);
     * BoxTask.Info info = task.getInfo();
     * task.updateInfo(info);</pre>
     *
     * @param info the updated info.
     */
    public void updateInfo(BoxTask.Info info) {
        URL url = TASK_URL_TEMPLATE.build(this.getAPI().getBaseURL(), this.getID());
        BoxJSONRequest request = new BoxJSONRequest(this.getAPI(), url, "PUT");
        request.setBody(info.getPendingChanges());
        try (BoxJSONResponse response = request.send()) {
            JsonObject jsonObject = Json.parse(response.getJSON()).asObject();
            info.update(jsonObject);
        }
    }

    /**
     * Enumerates the possible actions that a task can have.
     */
    public enum Action {
        /**
         * The task must be reviewed.
         */
        REVIEW("review"),

        /**
         * The task must be completed.
         */
        COMPLETE("complete");

        private final String jsonValue;

        Action(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        static Action fromJSONString(String jsonValue) {
            if (jsonValue.equals("review")) {
                return REVIEW;
            } else if (jsonValue.equals("complete")) {
                return COMPLETE;
            } else {
                throw new IllegalArgumentException("The provided JSON value isn't a valid Action.");
            }
        }

        String toJSONString() {
            return this.jsonValue;
        }
    }

    /**
     * Enumerates the possible completion rules for a task.
     */
    public enum CompletionRule {

        /**
         * The task must be completed by all assignees.
         */
        ALL_ASSIGNEES("all_assignees"),

        /**
         * The task must be completed by at least one assignee.
         */
        ANY_ASSIGNEE("any_assignee");

        private final String jsonValue;

        CompletionRule(String jsonValue) {
            this.jsonValue = jsonValue;
        }

        String toJSONString() {
            return this.jsonValue;
        }
    }

    /**
     * Contains information about a BoxTask.
     */
    public class Info extends BoxResource.Info {
        private BoxFile.Info item;
        private Date dueAt;
        private String action;
        private String completionRule;
        private String message;
        private List<BoxTaskAssignment.Info> taskAssignments;
        private boolean completed;
        private BoxUser.Info createdBy;
        private Date createdAt;

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
        public BoxTask getResource() {
            return BoxTask.this;
        }

        /**
         * Gets the file associated with this task.
         *
         * @return the file associated with this task.
         */
        public BoxFile.Info getItem() {
            return this.item;
        }

        /**
         * Gets the date at which this task is due.
         *
         * @return the date at which this task is due.
         */
        public Date getDueAt() {
            return this.dueAt;
        }

        /**
         * Sets the task's due date.
         *
         * @param dueAt the task's due date.
         */
        public void setDueAt(Date dueAt) {
            this.dueAt = dueAt;
            this.addPendingChange("due_at", BoxDateFormat.format(dueAt));
        }

        /**
         * Gets the action the task assignee will be prompted to do.
         *
         * @return the action the task assignee will be prompted to do.
         */
        public String getTaskType() {
            return this.action;
        }

        /**
         * Returns the completion rule for the task.
         *
         * @return the task completion rule.
         */
        public String getCompletionRule() {
            return this.completionRule;
        }

        /**
         * Sets the task's completion rule.
         *
         * @param completionRule the new completion rule.
         */
        public void setCompletionRule(CompletionRule completionRule) {
            this.completionRule = completionRule.toJSONString();
            this.addPendingChange("completion_rule", completionRule.toJSONString());
        }

        /**
         * Gets the message that will be included with this task.
         *
         * @return the message that will be included with this task.
         */
        public String getMessage() {
            return this.message;
        }

        /**
         * Sets the task's message.
         *
         * @param message the task's new message.
         */
        public void setMessage(String message) {
            this.message = message;
            this.addPendingChange("message", message);
        }

        /**
         * Gets the collection of task assignments associated with this task.
         *
         * @return the collection of task assignments associated with this task.
         */
        public List<BoxTaskAssignment.Info> getTaskAssignments() {
            return this.taskAssignments;
        }

        /**
         * Gets whether or not this task has been completed.
         *
         * @return whether or not this task has been completed.
         */
        public boolean isCompleted() {
            return this.completed;
        }

        /**
         * Gets the user who created this task.
         *
         * @return the user who created this task.
         */
        public BoxUser.Info getCreatedBy() {
            return this.createdBy;
        }

        /**
         * Gets when this task was created.
         *
         * @return when this task was created.
         */
        public Date getCreatedAt() {
            return this.createdAt;
        }

        @Override
        void parseJSONMember(JsonObject.Member member) {
            super.parseJSONMember(member);

            String memberName = member.getName();
            JsonValue value = member.getValue();
            try {
                switch (memberName) {
                    case "item":
                        JsonObject itemJSON = value.asObject();
                        String itemID = itemJSON.get("id").asString();
                        BoxFile file = new BoxFile(getAPI(), itemID);
                        this.item = file.new Info(itemJSON);
                        break;
                    case "due_at":
                        this.dueAt = BoxDateFormat.parse(value.asString());
                        break;
                    case "action":
                        this.action = value.asString();
                        break;
                    case "completion_rule":
                        this.completionRule = value.asString();
                        break;
                    case "message":
                        this.message = value.asString();
                        break;
                    case "task_assignment_collection":
                        this.taskAssignments = this.parseTaskAssignmentCollection(value.asObject());
                        break;
                    case "is_completed":
                        this.completed = value.asBoolean();
                        break;
                    case "created_by":
                        JsonObject userJSON = value.asObject();
                        String userID = userJSON.get("id").asString();
                        BoxUser user = new BoxUser(getAPI(), userID);
                        this.createdBy = user.new Info(userJSON);
                        break;
                    case "created_at":
                        this.createdAt = BoxDateFormat.parse(value.asString());
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                throw new BoxDeserializationException(memberName, value.toString(), e);
            }
        }

        private List<BoxTaskAssignment.Info> parseTaskAssignmentCollection(JsonObject jsonObject) {
            int count = jsonObject.get("total_count").asInt();
            List<BoxTaskAssignment.Info> taskAssignmentCollection = new ArrayList<>(count);
            JsonArray entries = jsonObject.get("entries").asArray();
            for (JsonValue value : entries) {
                JsonObject entry = value.asObject();
                String id = entry.get("id").asString();
                BoxTaskAssignment assignment = new BoxTaskAssignment(getAPI(), id);
                taskAssignmentCollection.add(assignment.new Info(entry));
            }

            return taskAssignmentCollection;
        }
    }
}
