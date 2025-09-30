package com.box.sdkgen.managers.avatars;

import static com.box.sdkgen.internal.utils.UtilsManager.convertToString;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;
import static com.box.sdkgen.internal.utils.UtilsManager.mergeMaps;
import static com.box.sdkgen.internal.utils.UtilsManager.prepareParams;

import com.box.sdkgen.networking.auth.Authentication;
import com.box.sdkgen.networking.fetchoptions.FetchOptions;
import com.box.sdkgen.networking.fetchoptions.MultipartItem;
import com.box.sdkgen.networking.fetchoptions.ResponseFormat;
import com.box.sdkgen.networking.fetchresponse.FetchResponse;
import com.box.sdkgen.networking.network.NetworkSession;
import com.box.sdkgen.schemas.useravatar.UserAvatar;
import com.box.sdkgen.serialization.json.JsonManager;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

public class AvatarsManager {

  public Authentication auth;

  public NetworkSession networkSession;

  public AvatarsManager() {
    this.networkSession = new NetworkSession();
  }

  protected AvatarsManager(Builder builder) {
    this.auth = builder.auth;
    this.networkSession = builder.networkSession;
  }

  public InputStream getUserAvatar(String userId) {
    return getUserAvatar(userId, new GetUserAvatarHeaders());
  }

  public InputStream getUserAvatar(String userId, GetUserAvatarHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/avatar"),
                        "GET")
                    .headers(headersMap)
                    .responseFormat(ResponseFormat.BINARY)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return response.getContent();
  }

  public UserAvatar createUserAvatar(String userId, CreateUserAvatarRequestBody requestBody) {
    return createUserAvatar(userId, requestBody, new CreateUserAvatarHeaders());
  }

  public UserAvatar createUserAvatar(
      String userId, CreateUserAvatarRequestBody requestBody, CreateUserAvatarHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/avatar"),
                        "POST")
                    .headers(headersMap)
                    .multipartData(
                        Arrays.asList(
                            new MultipartItem.Builder("pic")
                                .fileStream(requestBody.getPic())
                                .fileName(requestBody.getPicFileName())
                                .contentType(requestBody.getPicContentType())
                                .build()))
                    .contentType("multipart/form-data")
                    .responseFormat(ResponseFormat.JSON)
                    .auth(this.auth)
                    .networkSession(this.networkSession)
                    .build());
    return JsonManager.deserialize(response.getData(), UserAvatar.class);
  }

  public void deleteUserAvatar(String userId) {
    deleteUserAvatar(userId, new DeleteUserAvatarHeaders());
  }

  public void deleteUserAvatar(String userId, DeleteUserAvatarHeaders headers) {
    Map<String, String> headersMap = prepareParams(mergeMaps(mapOf(), headers.getExtraHeaders()));
    FetchResponse response =
        this.networkSession
            .getNetworkClient()
            .fetch(
                new FetchOptions.Builder(
                        String.join(
                            "",
                            this.networkSession.getBaseUrls().getBaseUrl(),
                            "/2.0/users/",
                            convertToString(userId),
                            "/avatar"),
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

    public AvatarsManager build() {
      return new AvatarsManager(this);
    }
  }
}
