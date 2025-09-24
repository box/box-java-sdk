package com.box.sdkgen.managers.docgentemplate;

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
import com.box.sdkgen.schemas.v2025r0.docgenjobsv2025r0.DocGenJobsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentagsv2025r0.DocGenTagsV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatebasev2025r0.DocGenTemplateBaseV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatecreaterequestv2025r0.DocGenTemplateCreateRequestV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatesv2025r0.DocGenTemplatesV2025R0;
import com.box.sdkgen.schemas.v2025r0.docgentemplatev2025r0.DocGenTemplateV2025R0;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.Map;

public class DocgenTemplateManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public DocgenTemplateManager() {
    this.networkSession = new NetworkSession();
  }

  protected DocgenTemplateManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public DocGenTemplateBaseV2025R0 createDocgenTemplateV2025R0(
      DocGenTemplateCreateRequestV2025R0 requestBody) {
    return createDocgenTemplateV2025R0(requestBody, new CreateDocgenTemplateV2025R0Headers());
  }

  public DocGenTemplateBaseV2025R0 createDocgenTemplateV2025R0(
      DocGenTemplateCreateRequestV2025R0 requestBody, CreateDocgenTemplateV2025R0Headers headers) {
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
                            "/2.0/docgen_templates"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenTemplateBaseV2025R0.class);
  }

  public DocGenTemplatesV2025R0 getDocgenTemplatesV2025R0() {
    return getDocgenTemplatesV2025R0(
        new GetDocgenTemplatesV2025R0QueryParams(), new GetDocgenTemplatesV2025R0Headers());
  }

  public DocGenTemplatesV2025R0 getDocgenTemplatesV2025R0(
      GetDocgenTemplatesV2025R0QueryParams queryParams) {
    return getDocgenTemplatesV2025R0(queryParams, new GetDocgenTemplatesV2025R0Headers());
  }

  public DocGenTemplatesV2025R0 getDocgenTemplatesV2025R0(
      GetDocgenTemplatesV2025R0Headers headers) {
    return getDocgenTemplatesV2025R0(new GetDocgenTemplatesV2025R0QueryParams(), headers);
  }

  public DocGenTemplatesV2025R0 getDocgenTemplatesV2025R0(
      GetDocgenTemplatesV2025R0QueryParams queryParams, GetDocgenTemplatesV2025R0Headers headers) {
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
                            "/2.0/docgen_templates"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenTemplatesV2025R0.class);
  }

  public void deleteDocgenTemplateByIdV2025R0(String templateId) {
    deleteDocgenTemplateByIdV2025R0(templateId, new DeleteDocgenTemplateByIdV2025R0Headers());
  }

  public void deleteDocgenTemplateByIdV2025R0(
      String templateId, DeleteDocgenTemplateByIdV2025R0Headers headers) {
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
                            "/2.0/docgen_templates/",
                            convertToString(templateId)),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  public DocGenTemplateV2025R0 getDocgenTemplateByIdV2025R0(String templateId) {
    return getDocgenTemplateByIdV2025R0(templateId, new GetDocgenTemplateByIdV2025R0Headers());
  }

  public DocGenTemplateV2025R0 getDocgenTemplateByIdV2025R0(
      String templateId, GetDocgenTemplateByIdV2025R0Headers headers) {
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
                            "/2.0/docgen_templates/",
                            convertToString(templateId)),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenTemplateV2025R0.class);
  }

  public DocGenTagsV2025R0 getDocgenTemplateTagsV2025R0(String templateId) {
    return getDocgenTemplateTagsV2025R0(
        templateId,
        new GetDocgenTemplateTagsV2025R0QueryParams(),
        new GetDocgenTemplateTagsV2025R0Headers());
  }

  public DocGenTagsV2025R0 getDocgenTemplateTagsV2025R0(
      String templateId, GetDocgenTemplateTagsV2025R0QueryParams queryParams) {
    return getDocgenTemplateTagsV2025R0(
        templateId, queryParams, new GetDocgenTemplateTagsV2025R0Headers());
  }

  public DocGenTagsV2025R0 getDocgenTemplateTagsV2025R0(
      String templateId, GetDocgenTemplateTagsV2025R0Headers headers) {
    return getDocgenTemplateTagsV2025R0(
        templateId, new GetDocgenTemplateTagsV2025R0QueryParams(), headers);
  }

  public DocGenTagsV2025R0 getDocgenTemplateTagsV2025R0(
      String templateId,
      GetDocgenTemplateTagsV2025R0QueryParams queryParams,
      GetDocgenTemplateTagsV2025R0Headers headers) {
    Map<String, String> queryParamsMap =
        prepareParams(
            mapOf(
                entryOf("template_version_id", convertToString(queryParams.getTemplateVersionId())),
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
                            "/2.0/docgen_templates/",
                            convertToString(templateId),
                            "/tags"),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenTagsV2025R0.class);
  }

  public DocGenJobsV2025R0 getDocgenTemplateJobByIdV2025R0(String templateId) {
    return getDocgenTemplateJobByIdV2025R0(
        templateId,
        new GetDocgenTemplateJobByIdV2025R0QueryParams(),
        new GetDocgenTemplateJobByIdV2025R0Headers());
  }

  public DocGenJobsV2025R0 getDocgenTemplateJobByIdV2025R0(
      String templateId, GetDocgenTemplateJobByIdV2025R0QueryParams queryParams) {
    return getDocgenTemplateJobByIdV2025R0(
        templateId, queryParams, new GetDocgenTemplateJobByIdV2025R0Headers());
  }

  public DocGenJobsV2025R0 getDocgenTemplateJobByIdV2025R0(
      String templateId, GetDocgenTemplateJobByIdV2025R0Headers headers) {
    return getDocgenTemplateJobByIdV2025R0(
        templateId, new GetDocgenTemplateJobByIdV2025R0QueryParams(), headers);
  }

  public DocGenJobsV2025R0 getDocgenTemplateJobByIdV2025R0(
      String templateId,
      GetDocgenTemplateJobByIdV2025R0QueryParams queryParams,
      GetDocgenTemplateJobByIdV2025R0Headers headers) {
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
                            "/2.0/docgen_template_jobs/",
                            convertToString(templateId)),
                        "GET")
                    .params(queryParamsMap)
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), DocGenJobsV2025R0.class);
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

    public DocgenTemplateManager build() {
      return new DocgenTemplateManager(this);
    }
  }
}
