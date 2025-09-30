package com.box.sdkgen.managers.comments;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.commentfull.CommentFull;
import com.box.sdkgen.schemas.comments.Comments;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class CommentsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public CommentsManager() {
    this.networkSession = new NetworkSession();
  }

  protected CommentsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public Comments getFileComments(String fileId) {
    return getFileComments(fileId, new GetFileCommentsQueryParams(), new GetFileCommentsHeaders());
  }

  public Comments getFileComments(String fileId, GetFileCommentsQueryParams queryParams) {
    return getFileComments(fileId, queryParams, new GetFileCommentsHeaders());
  }

  public Comments getFileComments(String fileId, GetFileCommentsHeaders headers) {
    return getFileComments(fileId, new GetFileCommentsQueryParams(), headers);
  }

  public Comments getFileComments(
      String fileId, GetFileCommentsQueryParams queryParams, GetFileCommentsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("fields", convertToString(queryParams.getFields())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("offset", convertToString(queryParams.getOffset()))));
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
                            "/comments"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), Comments.class);
  }

  public CommentFull getCommentById(String commentId) {
    return getCommentById(commentId, new GetCommentByIdQueryParams(), new GetCommentByIdHeaders());
  }

  public CommentFull getCommentById(String commentId, GetCommentByIdQueryParams queryParams) {
    return getCommentById(commentId, queryParams, new GetCommentByIdHeaders());
  }

  public CommentFull getCommentById(String commentId, GetCommentByIdHeaders headers) {
    return getCommentById(commentId, new GetCommentByIdQueryParams(), headers);
  }

  public CommentFull getCommentById(
      String commentId, GetCommentByIdQueryParams queryParams, GetCommentByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/comments/",
                            convertToString(commentId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CommentFull.class);
  }

  public CommentFull updateCommentById(String commentId) {
    return updateCommentById(
        commentId,
        new UpdateCommentByIdRequestBody(),
        new UpdateCommentByIdQueryParams(),
        new UpdateCommentByIdHeaders());
  }

  public CommentFull updateCommentById(String commentId, UpdateCommentByIdRequestBody requestBody) {
    return updateCommentById(
        commentId, requestBody, new UpdateCommentByIdQueryParams(), new UpdateCommentByIdHeaders());
  }

  public CommentFull updateCommentById(String commentId, UpdateCommentByIdQueryParams queryParams) {
    return updateCommentById(
        commentId, new UpdateCommentByIdRequestBody(), queryParams, new UpdateCommentByIdHeaders());
  }

  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdRequestBody requestBody,
      UpdateCommentByIdQueryParams queryParams) {
    return updateCommentById(commentId, requestBody, queryParams, new UpdateCommentByIdHeaders());
  }

  public CommentFull updateCommentById(String commentId, UpdateCommentByIdHeaders headers) {
    return updateCommentById(
        commentId, new UpdateCommentByIdRequestBody(), new UpdateCommentByIdQueryParams(), headers);
  }

  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdRequestBody requestBody,
      UpdateCommentByIdHeaders headers) {
    return updateCommentById(commentId, requestBody, new UpdateCommentByIdQueryParams(), headers);
  }

  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdQueryParams queryParams,
      UpdateCommentByIdHeaders headers) {
    return updateCommentById(commentId, new UpdateCommentByIdRequestBody(), queryParams, headers);
  }

  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdRequestBody requestBody,
      UpdateCommentByIdQueryParams queryParams,
      UpdateCommentByIdHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/comments/",
                            convertToString(commentId)),
                        "PUT")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CommentFull.class);
  }

  public void deleteCommentById(String commentId) {
    deleteCommentById(commentId, new DeleteCommentByIdHeaders());
  }

  public void deleteCommentById(String commentId, DeleteCommentByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/comments/",
                            convertToString(commentId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public CommentFull createComment(CreateCommentRequestBody requestBody) {
    return createComment(requestBody, new CreateCommentQueryParams(), new CreateCommentHeaders());
  }

  public CommentFull createComment(
      CreateCommentRequestBody requestBody, CreateCommentQueryParams queryParams) {
    return createComment(requestBody, queryParams, new CreateCommentHeaders());
  }

  public CommentFull createComment(
      CreateCommentRequestBody requestBody, CreateCommentHeaders headers) {
    return createComment(requestBody, new CreateCommentQueryParams(), headers);
  }

  public CommentFull createComment(
      CreateCommentRequestBody requestBody,
      CreateCommentQueryParams queryParams,
      CreateCommentHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(mapOf(entryOf("fields", convertToString(queryParams.getFields()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/comments"),
                        "POST")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), CommentFull.class);
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

    public CommentsManager build() {
      return new CommentsManager(this);
    }
  }
}
