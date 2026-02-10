package com.box.sdkgen.managers.skills;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.skillcardsmetadata.SkillCardsMetadata;
import com.box.sdkgen.serialization.json.JsonManager;
import java.util.List;
import java.util.Map;

public class SkillsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public SkillsManager() {
    this.networkSession = new NetworkSession();
  }

  protected SkillsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  /**
   * List the Box Skills metadata cards that are attached to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public SkillCardsMetadata getBoxSkillCardsOnFile(String fileId) {
    return getBoxSkillCardsOnFile(fileId, new GetBoxSkillCardsOnFileHeaders());
  }

  /**
   * List the Box Skills metadata cards that are attached to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of getBoxSkillCardsOnFile method
   */
  public SkillCardsMetadata getBoxSkillCardsOnFile(
      String fileId, GetBoxSkillCardsOnFileHeaders headers) {
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
                            "/metadata/global/boxSkillsCards"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SkillCardsMetadata.class);
  }

  /**
   * Applies one or more Box Skills metadata cards to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of createBoxSkillCardsOnFile method
   */
  public SkillCardsMetadata createBoxSkillCardsOnFile(
      String fileId, CreateBoxSkillCardsOnFileRequestBody requestBody) {
    return createBoxSkillCardsOnFile(fileId, requestBody, new CreateBoxSkillCardsOnFileHeaders());
  }

  /**
   * Applies one or more Box Skills metadata cards to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of createBoxSkillCardsOnFile method
   * @param headers Headers of createBoxSkillCardsOnFile method
   */
  public SkillCardsMetadata createBoxSkillCardsOnFile(
      String fileId,
      CreateBoxSkillCardsOnFileRequestBody requestBody,
      CreateBoxSkillCardsOnFileHeaders headers) {
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
                            "/metadata/global/boxSkillsCards"),
                        "POST")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SkillCardsMetadata.class);
  }

  /**
   * Updates one or more Box Skills metadata cards to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateBoxSkillCardsOnFile method
   */
  public SkillCardsMetadata updateBoxSkillCardsOnFile(
      String fileId, List<UpdateBoxSkillCardsOnFileRequestBody> requestBody) {
    return updateBoxSkillCardsOnFile(fileId, requestBody, new UpdateBoxSkillCardsOnFileHeaders());
  }

  /**
   * Updates one or more Box Skills metadata cards to a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param requestBody Request body of updateBoxSkillCardsOnFile method
   * @param headers Headers of updateBoxSkillCardsOnFile method
   */
  public SkillCardsMetadata updateBoxSkillCardsOnFile(
      String fileId,
      List<UpdateBoxSkillCardsOnFileRequestBody> requestBody,
      UpdateBoxSkillCardsOnFileHeaders headers) {
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
                            "/metadata/global/boxSkillsCards"),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json-patch+json")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), SkillCardsMetadata.class);
  }

  /**
   * Removes any Box Skills cards metadata from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   */
  public void deleteBoxSkillCardsFromFile(String fileId) {
    deleteBoxSkillCardsFromFile(fileId, new DeleteBoxSkillCardsFromFileHeaders());
  }

  /**
   * Removes any Box Skills cards metadata from a file.
   *
   * @param fileId The unique identifier that represents a file.
   *     <p>The ID for any file can be determined by visiting a file in the web application and
   *     copying the ID from the URL. For example, for the URL `https://*.app.box.com/files/123` the
   *     `file_id` is `123`. Example: "12345"
   * @param headers Headers of deleteBoxSkillCardsFromFile method
   */
  public void deleteBoxSkillCardsFromFile(
      String fileId, DeleteBoxSkillCardsFromFileHeaders headers) {
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
                            "/metadata/global/boxSkillsCards"),
                        "DELETE")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.NO_CONTENT)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
  }

  /**
   * An alternative method that can be used to overwrite and update all Box Skill metadata cards on
   * a file.
   *
   * @param skillId The ID of the skill to apply this metadata for. Example: "33243242"
   * @param requestBody Request body of updateAllSkillCardsOnFile method
   */
  public void updateAllSkillCardsOnFile(
      String skillId, UpdateAllSkillCardsOnFileRequestBody requestBody) {
    updateAllSkillCardsOnFile(skillId, requestBody, new UpdateAllSkillCardsOnFileHeaders());
  }

  /**
   * An alternative method that can be used to overwrite and update all Box Skill metadata cards on
   * a file.
   *
   * @param skillId The ID of the skill to apply this metadata for. Example: "33243242"
   * @param requestBody Request body of updateAllSkillCardsOnFile method
   * @param headers Headers of updateAllSkillCardsOnFile method
   */
  public void updateAllSkillCardsOnFile(
      String skillId,
      UpdateAllSkillCardsOnFileRequestBody requestBody,
      UpdateAllSkillCardsOnFileHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/skill_invocations/",
                            convertToString(skillId)),
                        "PUT")
                    .headers(headersMap)
                    .data(JsonManager.serialize(requestBody))
                    .contentType("application/json")
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

    public Builder() {}

    public Builder auth(Authentication auth) {
      this.auth = auth;
      return this;
    }

    public Builder networkSession(NetworkSession networkSession) {
      this.networkSession = networkSession;
      return this;
    }

    public SkillsManager build() {
      if (this.networkSession == null) {
        this.networkSession = new NetworkSession();
      }
      return new SkillsManager(this);
    }
  }
}
