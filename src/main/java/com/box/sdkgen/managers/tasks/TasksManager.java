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

  public Tasks getFileTasks(String fileId) {
    return getFileTasks(fileId, new GetFileTasksHeaders());
  }

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

  public Task createTask(CreateTaskRequestBody requestBody) {
    return createTask(requestBody, new CreateTaskHeaders());
  }

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

  public Task getTaskById(String taskId) {
    return getTaskById(taskId, new GetTaskByIdHeaders());
  }

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

  public Task updateTaskById(String taskId) {
    return updateTaskById(taskId, new UpdateTaskByIdRequestBody(), new UpdateTaskByIdHeaders());
  }

  public Task updateTaskById(String taskId, UpdateTaskByIdRequestBody requestBody) {
    return updateTaskById(taskId, requestBody, new UpdateTaskByIdHeaders());
  }

  public Task updateTaskById(String taskId, UpdateTaskByIdHeaders headers) {
    return updateTaskById(taskId, new UpdateTaskByIdRequestBody(), headers);
  }

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

  public void deleteTaskById(String taskId) {
    deleteTaskById(taskId, new DeleteTaskByIdHeaders());
  }

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
