package com.box.sdkgen.managers.signrequests;

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
import com.box.sdkgen.schemas.signrequest.SignRequest;
import com.box.sdkgen.schemas.signrequestcancelrequest.SignRequestCancelRequest;
import com.box.sdkgen.schemas.signrequestcreaterequest.SignRequestCreateRequest;
import com.box.sdkgen.schemas.signrequests.SignRequests;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class SignRequestsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SignRequestsManager() {
    this.networkSession = new NetworkSession();
  }

  protected SignRequestsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Cancels a sign request.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   */
  public SignRequest cancelSignRequest(String signRequestId) {
    return cancelSignRequest(signRequestId, null, new CancelSignRequestHeaders());
  }

  /**
   * Cancels a sign request.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   * @param requestBody Request body of cancelSignRequest method
   */
  public SignRequest cancelSignRequest(String signRequestId, SignRequestCancelRequest requestBody) {
    return cancelSignRequest(signRequestId, requestBody, new CancelSignRequestHeaders());
  }

  /**
   * Cancels a sign request.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   * @param headers Headers of cancelSignRequest method
   */
  public SignRequest cancelSignRequest(String signRequestId, CancelSignRequestHeaders headers) {
    return cancelSignRequest(signRequestId, null, headers);
  }

  /**
   * Cancels a sign request.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   * @param requestBody Request body of cancelSignRequest method
   * @param headers Headers of cancelSignRequest method
   */
  public SignRequest cancelSignRequest(
      String signRequestId,
      SignRequestCancelRequest requestBody,
      CancelSignRequestHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_requests/",
                            convertToString(signRequestId),
                            "/cancel"),
                        "POST")
                    .headers(headersMap)
                    .data((!(requestBody == null) ? JsonManager.serialize(requestBody) : null))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignRequest.class);
  }

  /**
   * Resends a signature request email to all outstanding signers.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   */
  public void resendSignRequest(String signRequestId) {
    resendSignRequest(signRequestId, new ResendSignRequestHeaders());
  }

  /**
   * Resends a signature request email to all outstanding signers.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   * @param headers Headers of resendSignRequest method
   */
  public void resendSignRequest(String signRequestId, ResendSignRequestHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_requests/",
                            convertToString(signRequestId),
                            "/resend"),
                        "POST")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * Gets a sign request by ID.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   */
  public SignRequest getSignRequestById(String signRequestId) {
    return getSignRequestById(signRequestId, new GetSignRequestByIdHeaders());
  }

  /**
   * Gets a sign request by ID.
   *
   * @param signRequestId The ID of the signature request. Example: "33243242"
   * @param headers Headers of getSignRequestById method
   */
  public SignRequest getSignRequestById(String signRequestId, GetSignRequestByIdHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_requests/",
                            convertToString(signRequestId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignRequest.class);
  }

  /**
   * Gets signature requests created by a user. If the `sign_files` and/or `parent_folder` are
   * deleted, the signature request will not return in the list.
   */
  public SignRequests getSignRequests() {
    return getSignRequests(new GetSignRequestsQueryParams(), new GetSignRequestsHeaders());
  }

  /**
   * Gets signature requests created by a user. If the `sign_files` and/or `parent_folder` are
   * deleted, the signature request will not return in the list.
   *
   * @param queryParams Query parameters of getSignRequests method
   */
  public SignRequests getSignRequests(GetSignRequestsQueryParams queryParams) {
    return getSignRequests(queryParams, new GetSignRequestsHeaders());
  }

  /**
   * Gets signature requests created by a user. If the `sign_files` and/or `parent_folder` are
   * deleted, the signature request will not return in the list.
   *
   * @param headers Headers of getSignRequests method
   */
  public SignRequests getSignRequests(GetSignRequestsHeaders headers) {
    return getSignRequests(new GetSignRequestsQueryParams(), headers);
  }

  /**
   * Gets signature requests created by a user. If the `sign_files` and/or `parent_folder` are
   * deleted, the signature request will not return in the list.
   *
   * @param queryParams Query parameters of getSignRequests method
   * @param headers Headers of getSignRequests method
   */
  public SignRequests getSignRequests(
      GetSignRequestsQueryParams queryParams, GetSignRequestsHeaders headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit())),
                entryOf("senders", convertToString(queryParams.getSenders())),
                entryOf("shared_requests", convertToString(queryParams.getSharedRequests()))));
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_requests"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignRequests.class);
  }

  /**
   * Creates a signature request. This involves preparing a document for signing and sending the
   * signature request to signers.
   *
   * @param requestBody Request body of createSignRequest method
   */
  public SignRequest createSignRequest(SignRequestCreateRequest requestBody) {
    return createSignRequest(requestBody, new CreateSignRequestHeaders());
  }

  /**
   * Creates a signature request. This involves preparing a document for signing and sending the
   * signature request to signers.
   *
   * @param requestBody Request body of createSignRequest method
   * @param headers Headers of createSignRequest method
   */
  public SignRequest createSignRequest(
      SignRequestCreateRequest requestBody, CreateSignRequestHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/sign_requests"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SignRequest.class);
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

    public SignRequestsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new SignRequestsManager(this);
    }
  }
}
