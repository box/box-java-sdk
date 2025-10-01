package com.box.sdkgen.managers.tasks;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.task.Task;
import com.box.sdkgen.schemas.tasks.Tasks;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class TasksManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public TasksManager() {
    this.networkSession = new NetworkSession();
  }

  protected TasksManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Retrieves a list of all the tasks for a file. This endpoint does not support pagination.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Tasks getFileTasks(String fileId) {
    return getFileTasks(fileId, new GetFileTasksHeaders());
  }

  /**
   * Retrieves a list of all the tasks for a file. This endpoint does not support pagination.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileTasks method
   */
  public Tasks getFileTasks(String fileId, GetFileTasksHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/files/",
                            convertToString(fileId),
                            "/tasks"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Tasks.class);
  }

  /**
   * Creates a single task on a file. This task is not assigned to any user and will need to be
   * assigned separately.
   *
   * @param requestBody Request body of createTask method
   */
  public Task createTask(CreateTaskRequestBody requestBody) {
    return createTask(requestBody, new CreateTaskHeaders());
  }

  /**
   * Creates a single task on a file. This task is not assigned to any user and will need to be
   * assigned separately.
   *
   * @param requestBody Request body of createTask method
   * @param headers Headers of createTask method
   */
  public Task createTask(CreateTaskRequestBody requestBody, CreateTaskHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/tasks"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Task.class);
  }

  /**
   * Retrieves information about a specific task.
   *
   * @param taskId The ID of the task. Example: "12345"
   */
  public Task getTaskById(String taskId) {
    return getTaskById(taskId, new GetTaskByIdHeaders());
  }

  /**
   * Retrieves information about a specific task.
   *
   * @param taskId The ID of the task. Example: "12345"
   * @param headers Headers of getTaskById method
   */
  public Task getTaskById(String taskId, GetTaskByIdHeaders headers) {
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
                            convertToString(taskId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Task.class);
  }

  /**
   * Updates a task. This can be used to update a task's configuration, or to update its completion
   * state.
   *
   * @param taskId The ID of the task. Example: "12345"
   */
  public Task updateTaskById(String taskId) {
    return updateTaskById(taskId, new UpdateTaskByIdRequestBody(), new UpdateTaskByIdHeaders());
  }

  /**
   * Updates a task. This can be used to update a task's configuration, or to update its completion
   * state.
   *
   * @param taskId The ID of the task. Example: "12345"
   * @param requestBody Request body of updateTaskById method
   */
  public Task updateTaskById(String taskId, UpdateTaskByIdRequestBody requestBody) {
    return updateTaskById(taskId, requestBody, new UpdateTaskByIdHeaders());
  }

  /**
   * Updates a task. This can be used to update a task's configuration, or to update its completion
   * state.
   *
   * @param taskId The ID of the task. Example: "12345"
   * @param headers Headers of updateTaskById method
   */
  public Task updateTaskById(String taskId, UpdateTaskByIdHeaders headers) {
    return updateTaskById(taskId, new UpdateTaskByIdRequestBody(), headers);
  }

  /**
   * Updates a task. This can be used to update a task's configuration, or to update its completion
   * state.
   *
   * @param taskId The ID of the task. Example: "12345"
   * @param requestBody Request body of updateTaskById method
   * @param headers Headers of updateTaskById method
   */
  public Task updateTaskById(
      String taskId, UpdateTaskByIdRequestBody requestBody, UpdateTaskByIdHeaders headers) {
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
                            convertToString(taskId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Task.class);
  }

  /**
   * Removes a task from a file.
   *
   * @param taskId The ID of the task. Example: "12345"
   */
  public void deleteTaskById(String taskId) {
    deleteTaskById(taskId, new DeleteTaskByIdHeaders());
  }

  /**
   * Removes a task from a file.
   *
   * @param taskId The ID of the task. Example: "12345"
   * @param headers Headers of deleteTaskById method
   */
  public void deleteTaskById(String taskId, DeleteTaskByIdHeaders headers) {
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
                            convertToString(taskId)),
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

    public TasksManager build() {
      return new TasksManager(this);
    }
  }
}
