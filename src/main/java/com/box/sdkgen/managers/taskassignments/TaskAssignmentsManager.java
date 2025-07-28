package com.box.sdkgen.managers.taskassignments;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.taskassignment.TaskAssignment;
import com.box.sdkgen.schemas.taskassignments.TaskAssignments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TaskAssignmentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TaskAssignmentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected TaskAssignmentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public TaskAssignments getTaskAssignments(String taskId) {
    return getTaskAssignments(taskId, new GetTaskAssignmentsHeaders());
  }

  public TaskAssignments getTaskAssignments(String taskId, GetTaskAssignmentsHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/tasks/",
                            convertToString(taskId),
                            "/assignments"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TaskAssignments.class);
  }

  public TaskAssignment createTaskAssignment(CreateTaskAssignmentRequestBody requestBody) {
    return createTaskAssignment(requestBody, new CreateTaskAssignmentHeaders());
  }

  public TaskAssignment createTaskAssignment(
      CreateTaskAssignmentRequestBody requestBody, CreateTaskAssignmentHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/task_assignments"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TaskAssignment.class);
  }

  public TaskAssignment getTaskAssignmentById(String taskAssignmentId) {
    return getTaskAssignmentById(taskAssignmentId, new GetTaskAssignmentByIdHeaders());
  }

  public TaskAssignment getTaskAssignmentById(
      String taskAssignmentId, GetTaskAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/task_assignments/",
                            convertToString(taskAssignmentId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TaskAssignment.class);
  }

  public TaskAssignment updateTaskAssignmentById(String taskAssignmentId) {
    return updateTaskAssignmentById(
        taskAssignmentId,
        new UpdateTaskAssignmentByIdRequestBody(),
        new UpdateTaskAssignmentByIdHeaders());
  }

  public TaskAssignment updateTaskAssignmentById(
      String taskAssignmentId, UpdateTaskAssignmentByIdRequestBody requestBody) {
    return updateTaskAssignmentById(
        taskAssignmentId, requestBody, new UpdateTaskAssignmentByIdHeaders());
  }

  public TaskAssignment updateTaskAssignmentById(
      String taskAssignmentId, UpdateTaskAssignmentByIdHeaders headers) {
    return updateTaskAssignmentById(
        taskAssignmentId, new UpdateTaskAssignmentByIdRequestBody(), headers);
  }

  public TaskAssignment updateTaskAssignmentById(
      String taskAssignmentId,
      UpdateTaskAssignmentByIdRequestBody requestBody,
      UpdateTaskAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/task_assignments/",
                            convertToString(taskAssignmentId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), TaskAssignment.class);
  }

  public void deleteTaskAssignmentById(String taskAssignmentId) {
    deleteTaskAssignmentById(taskAssignmentId, new DeleteTaskAssignmentByIdHeaders());
  }

  public void deleteTaskAssignmentById(
      String taskAssignmentId, DeleteTaskAssignmentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/task_assignments/",
                            convertToString(taskAssignmentId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public Authentication getAuth() {
    return auth;
  }

  public NetworkSession getNetworkSession() {
    return networkSession;
  }

  public static class Builder {

    protected Authentication auth;

    protected NetworkSession networkSession;

    public Builder() {
      this.networkSession = new NetworkSession();
    }

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public TaskAssignmentsManager build() {
      return new TaskAssignmentsManager(this);
    }
  }
}
