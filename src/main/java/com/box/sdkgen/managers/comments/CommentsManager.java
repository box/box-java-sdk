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

  /**
   * Retrieves a list of comments for a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public Comments getFileComments(String fileId) {
    return getFileComments(fileId, new GetFileCommentsQueryParams(), new GetFileCommentsHeaders());
  }

  /**
   * Retrieves a list of comments for a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileComments method
   */
  public Comments getFileComments(String fileId, GetFileCommentsQueryParams queryParams) {
    return getFileComments(fileId, queryParams, new GetFileCommentsHeaders());
  }

  /**
   * Retrieves a list of comments for a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getFileComments method
   */
  public Comments getFileComments(String fileId, GetFileCommentsHeaders headers) {
    return getFileComments(fileId, new GetFileCommentsQueryParams(), headers);
  }

  /**
   * Retrieves a list of comments for a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param queryParams Query parameters of getFileComments method
   * @param headers Headers of getFileComments method
   */
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

  /**
   * Retrieves the message and metadata for a specific comment, as well as information on the user
   * who created the comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   */
  public CommentFull getCommentById(String commentId) {
    return getCommentById(commentId, new GetCommentByIdQueryParams(), new GetCommentByIdHeaders());
  }

  /**
   * Retrieves the message and metadata for a specific comment, as well as information on the user
   * who created the comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param queryParams Query parameters of getCommentById method
   */
  public CommentFull getCommentById(String commentId, GetCommentByIdQueryParams queryParams) {
    return getCommentById(commentId, queryParams, new GetCommentByIdHeaders());
  }

  /**
   * Retrieves the message and metadata for a specific comment, as well as information on the user
   * who created the comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param headers Headers of getCommentById method
   */
  public CommentFull getCommentById(String commentId, GetCommentByIdHeaders headers) {
    return getCommentById(commentId, new GetCommentByIdQueryParams(), headers);
  }

  /**
   * Retrieves the message and metadata for a specific comment, as well as information on the user
   * who created the comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param queryParams Query parameters of getCommentById method
   * @param headers Headers of getCommentById method
   */
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

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   */
  public CommentFull updateCommentById(String commentId) {
    return updateCommentById(
        commentId,
        new UpdateCommentByIdRequestBody(),
        new UpdateCommentByIdQueryParams(),
        new UpdateCommentByIdHeaders());
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param requestBody Request body of updateCommentById method
   */
  public CommentFull updateCommentById(String commentId, UpdateCommentByIdRequestBody requestBody) {
    return updateCommentById(
        commentId, requestBody, new UpdateCommentByIdQueryParams(), new UpdateCommentByIdHeaders());
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param queryParams Query parameters of updateCommentById method
   */
  public CommentFull updateCommentById(String commentId, UpdateCommentByIdQueryParams queryParams) {
    return updateCommentById(
        commentId, new UpdateCommentByIdRequestBody(), queryParams, new UpdateCommentByIdHeaders());
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param requestBody Request body of updateCommentById method
   * @param queryParams Query parameters of updateCommentById method
   */
  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdRequestBody requestBody,
      UpdateCommentByIdQueryParams queryParams) {
    return updateCommentById(commentId, requestBody, queryParams, new UpdateCommentByIdHeaders());
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param headers Headers of updateCommentById method
   */
  public CommentFull updateCommentById(String commentId, UpdateCommentByIdHeaders headers) {
    return updateCommentById(
        commentId, new UpdateCommentByIdRequestBody(), new UpdateCommentByIdQueryParams(), headers);
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param requestBody Request body of updateCommentById method
   * @param headers Headers of updateCommentById method
   */
  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdRequestBody requestBody,
      UpdateCommentByIdHeaders headers) {
    return updateCommentById(commentId, requestBody, new UpdateCommentByIdQueryParams(), headers);
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param queryParams Query parameters of updateCommentById method
   * @param headers Headers of updateCommentById method
   */
  public CommentFull updateCommentById(
      String commentId,
      UpdateCommentByIdQueryParams queryParams,
      UpdateCommentByIdHeaders headers) {
    return updateCommentById(commentId, new UpdateCommentByIdRequestBody(), queryParams, headers);
  }

  /**
   * Update the message of a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param requestBody Request body of updateCommentById method
   * @param queryParams Query parameters of updateCommentById method
   * @param headers Headers of updateCommentById method
   */
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

  /**
   * Permanently deletes a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   */
  public void deleteCommentById(String commentId) {
    deleteCommentById(commentId, new DeleteCommentByIdHeaders());
  }

  /**
   * Permanently deletes a comment.
   *
   * @param commentId The ID of the comment. Example: "12345"
   * @param headers Headers of deleteCommentById method
   */
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

  /**
   * Adds a comment by the user to a specific file, or as a reply to an other comment.
   *
   * @param requestBody Request body of createComment method
   */
  public CommentFull createComment(CreateCommentRequestBody requestBody) {
    return createComment(requestBody, new CreateCommentQueryParams(), new CreateCommentHeaders());
  }

  /**
   * Adds a comment by the user to a specific file, or as a reply to an other comment.
   *
   * @param requestBody Request body of createComment method
   * @param queryParams Query parameters of createComment method
   */
  public CommentFull createComment(
      CreateCommentRequestBody requestBody, CreateCommentQueryParams queryParams) {
    return createComment(requestBody, queryParams, new CreateCommentHeaders());
  }

  /**
   * Adds a comment by the user to a specific file, or as a reply to an other comment.
   *
   * @param requestBody Request body of createComment method
   * @param headers Headers of createComment method
   */
  public CommentFull createComment(
      CreateCommentRequestBody requestBody, CreateCommentHeaders headers) {
    return createComment(requestBody, new CreateCommentQueryParams(), headers);
  }

  /**
   * Adds a comment by the user to a specific file, or as a reply to an other comment.
   *
   * @param requestBody Request body of createComment method
   * @param queryParams Query parameters of createComment method
   * @param headers Headers of createComment method
   */
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public CommentsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new CommentsManager(this);
    }
  }
}
