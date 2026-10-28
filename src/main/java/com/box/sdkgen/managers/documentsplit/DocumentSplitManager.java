package com.box.sdkgen.managers.documentsplit;

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
import com.box.sdkgen.schemas.v2026r0.documentsplitcreaterequestv2026r0.DocumentSplitCreateRequestV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitcreateresponsev2026r0.DocumentSplitCreateResponseV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitsv2026r0.DocumentSplitsV2026R0;
import com.box.sdkgen.schemas.v2026r0.documentsplitv2026r0.DocumentSplitV2026R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class DocumentSplitManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public DocumentSplitManager() {
    this.networkSession = new NetworkSession();
  }

  protected DocumentSplitManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * Initiates a Document Split job for the supplied source file. The job runs asynchronously;
   * clients can poll the returned job ID to track progress.
   *
   * <p>All split-specific input lives under `split_input`. When `split_input.split_type` is
   * `manual`, provide `split_input.split_definition` (continuous page ranges; `version` may be
   * omitted or `v_1`). When `split_input.split_type` is `page_selection`, provide
   * `split_input.page_selection` (non-linear page segments; `version` may be omitted or `v_2`).
   * `split_input.prompt` must not be provided for either of those variants. When
   * `split_input.split_type` is `smart`, `split_input.prompt` is required and neither
   * `split_input.split_definition` nor `split_input.page_selection` must be provided.
   *
   * @param requestBody Request body of createDocumentSplitV2026R0 method
   */
  public DocumentSplitCreateResponseV2026R0 createDocumentSplitV2026R0(
      DocumentSplitCreateRequestV2026R0 requestBody) {
    return createDocumentSplitV2026R0(requestBody, new CreateDocumentSplitV2026R0Headers());
  }

  /**
   * Initiates a Document Split job for the supplied source file. The job runs asynchronously;
   * clients can poll the returned job ID to track progress.
   *
   * <p>All split-specific input lives under `split_input`. When `split_input.split_type` is
   * `manual`, provide `split_input.split_definition` (continuous page ranges; `version` may be
   * omitted or `v_1`). When `split_input.split_type` is `page_selection`, provide
   * `split_input.page_selection` (non-linear page segments; `version` may be omitted or `v_2`).
   * `split_input.prompt` must not be provided for either of those variants. When
   * `split_input.split_type` is `smart`, `split_input.prompt` is required and neither
   * `split_input.split_definition` nor `split_input.page_selection` must be provided.
   *
   * @param requestBody Request body of createDocumentSplitV2026R0 method
   * @param headers Headers of createDocumentSplitV2026R0 method
   */
  public DocumentSplitCreateResponseV2026R0 createDocumentSplitV2026R0(
      DocumentSplitCreateRequestV2026R0 requestBody, CreateDocumentSplitV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/document_splits"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocumentSplitCreateResponseV2026R0.class);
  }

  /** Returns a paginated list of Document Split jobs accessible to the authenticated user. */
  public DocumentSplitsV2026R0 getDocumentSplitsV2026R0() {
    return getDocumentSplitsV2026R0(
        new GetDocumentSplitsV2026R0QueryParams(), new GetDocumentSplitsV2026R0Headers());
  }

  /**
   * Returns a paginated list of Document Split jobs accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getDocumentSplitsV2026R0 method
   */
  public DocumentSplitsV2026R0 getDocumentSplitsV2026R0(
      GetDocumentSplitsV2026R0QueryParams queryParams) {
    return getDocumentSplitsV2026R0(queryParams, new GetDocumentSplitsV2026R0Headers());
  }

  /**
   * Returns a paginated list of Document Split jobs accessible to the authenticated user.
   *
   * @param headers Headers of getDocumentSplitsV2026R0 method
   */
  public DocumentSplitsV2026R0 getDocumentSplitsV2026R0(GetDocumentSplitsV2026R0Headers headers) {
    return getDocumentSplitsV2026R0(new GetDocumentSplitsV2026R0QueryParams(), headers);
  }

  /**
   * Returns a paginated list of Document Split jobs accessible to the authenticated user.
   *
   * @param queryParams Query parameters of getDocumentSplitsV2026R0 method
   * @param headers Headers of getDocumentSplitsV2026R0 method
   */
  public DocumentSplitsV2026R0 getDocumentSplitsV2026R0(
      GetDocumentSplitsV2026R0QueryParams queryParams, GetDocumentSplitsV2026R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("marker", convertToString(queryParams.getMarker())),
                entryOf("limit", convertToString(queryParams.getLimit()))));
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/document_splits"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocumentSplitsV2026R0.class);
  }

  /**
   * Returns details for a single Document Split job identified by its ID, including the current
   * status, the input definition, and any output files produced by the job.
   *
   * @param documentSplitId The unique identifier for the Document Split job. Example: "1748426"
   */
  public DocumentSplitV2026R0 getDocumentSplitByIdV2026R0(String documentSplitId) {
    return getDocumentSplitByIdV2026R0(documentSplitId, new GetDocumentSplitByIdV2026R0Headers());
  }

  /**
   * Returns details for a single Document Split job identified by its ID, including the current
   * status, the input definition, and any output files produced by the job.
   *
   * @param documentSplitId The unique identifier for the Document Split job. Example: "1748426"
   * @param headers Headers of getDocumentSplitByIdV2026R0 method
   */
  public DocumentSplitV2026R0 getDocumentSplitByIdV2026R0(
      String documentSplitId, GetDocumentSplitByIdV2026R0Headers headers) {
    Map<String, String> headersMap =
        prepareParams(
            mergeMaps(
                mapOf(entryOf("box-version", convertToString(headers.getBoxVersion()))),
                headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/document_splits/",
                            convertToString(documentSplitId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocumentSplitV2026R0.class);
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

    public DocumentSplitManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new DocumentSplitManager(this);
    }
  }
}
