package com.box.sdkgen.managers.docgen;

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
import com.box.sdkgen.schemas.v2025r0.docgenbatchbasev2025r0.DocGenBatchBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenbatchcreaterequestv2025r0.DocGenBatchCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobsfullv2025r0.DocGenJobsFullV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobsv2025r0.DocGenJobsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgenjobv2025r0.DocGenJobV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class DocgenManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public DocgenManager() {
    this.networkSession = new NetworkSession();
  }

  protected DocgenManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public DocGenJobV2025R0 getDocgenJobByIdV2025R0(String jobId) {
    return getDocgenJobByIdV2025R0(jobId, new GetDocgenJobByIdV2025R0Headers());
  }

  public DocGenJobV2025R0 getDocgenJobByIdV2025R0(
      String jobId, GetDocgenJobByIdV2025R0Headers headers) {
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
                            "/2.0/docgen_jobs/",
                            convertToString(jobId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenJobV2025R0.class);
  }

  public DocGenJobsFullV2025R0 getDocgenJobsV2025R0() {
    return getDocgenJobsV2025R0(
        new GetDocgenJobsV2025R0QueryParams(), new GetDocgenJobsV2025R0Headers());
  }

  public DocGenJobsFullV2025R0 getDocgenJobsV2025R0(GetDocgenJobsV2025R0QueryParams queryParams) {
    return getDocgenJobsV2025R0(queryParams, new GetDocgenJobsV2025R0Headers());
  }

  public DocGenJobsFullV2025R0 getDocgenJobsV2025R0(GetDocgenJobsV2025R0Headers headers) {
    return getDocgenJobsV2025R0(new GetDocgenJobsV2025R0QueryParams(), headers);
  }

  public DocGenJobsFullV2025R0 getDocgenJobsV2025R0(
      GetDocgenJobsV2025R0QueryParams queryParams, GetDocgenJobsV2025R0Headers headers) {
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
                            "", this.networkSession.getBaseUrls().getBaseUrl(), "/2.0/docgen_jobs"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenJobsFullV2025R0.class);
  }

  public DocGenJobsV2025R0 getDocgenBatchJobByIdV2025R0(String batchId) {
    return getDocgenBatchJobByIdV2025R0(
        batchId,
        new GetDocgenBatchJobByIdV2025R0QueryParams(),
        new GetDocgenBatchJobByIdV2025R0Headers());
  }

  public DocGenJobsV2025R0 getDocgenBatchJobByIdV2025R0(
      String batchId, GetDocgenBatchJobByIdV2025R0QueryParams queryParams) {
    return getDocgenBatchJobByIdV2025R0(
        batchId, queryParams, new GetDocgenBatchJobByIdV2025R0Headers());
  }

  public DocGenJobsV2025R0 getDocgenBatchJobByIdV2025R0(
      String batchId, GetDocgenBatchJobByIdV2025R0Headers headers) {
    return getDocgenBatchJobByIdV2025R0(
        batchId, new GetDocgenBatchJobByIdV2025R0QueryParams(), headers);
  }

  public DocGenJobsV2025R0 getDocgenBatchJobByIdV2025R0(
      String batchId,
      GetDocgenBatchJobByIdV2025R0QueryParams queryParams,
      GetDocgenBatchJobByIdV2025R0Headers headers) {
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
                            "/2.0/docgen_batch_jobs/",
                            convertToString(batchId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenJobsV2025R0.class);
  }

  public DocGenBatchBaseV2025R0 createDocgenBatchV2025R0(
      DocGenBatchCreateRequestV2025R0 requestBody) {
    return createDocgenBatchV2025R0(requestBody, new CreateDocgenBatchV2025R0Headers());
  }

  public DocGenBatchBaseV2025R0 createDocgenBatchV2025R0(
      DocGenBatchCreateRequestV2025R0 requestBody, CreateDocgenBatchV2025R0Headers headers) {
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
                            "/2.0/docgen_batches"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenBatchBaseV2025R0.class);
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

    public DocgenManager build() {
      return new DocgenManager(this);
    }
  }
}
