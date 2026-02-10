package com.box.sdkgen.schemas.postoauth2tokenrefreshaccesstoken;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A request to refresh an Access Token. Use this API to refresh an expired Access Token using a
 * valid Refresh Token.
 */
@JsonFilter("nullablePropertyFilter")
public class PostOAuth2TokenRefreshAccessToken extends SerializableObject {

  /** The type of request being made, in this case a refresh request. */
  @JsonDeserialize(
      using =
          PostOAuth2TokenRefreshAccessTokenGrantTypeField
              .PostOAuth2TokenRefreshAccessTokenGrantTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          PostOAuth2TokenRefreshAccessTokenGrantTypeField
              .PostOAuth2TokenRefreshAccessTokenGrantTypeFieldSerializer.class)
  @JsonProperty("grant_type")
  protected EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> grantType;

  /** The client ID of the application requesting to refresh the token. */
  @JsonProperty("client_id")
  protected final String clientId;

  /** The client secret of the application requesting to refresh the token. */
  @JsonProperty("client_secret")
  protected final String clientSecret;

  /** The refresh token to refresh. */
  @JsonProperty("refresh_token")
  protected final String refreshToken;

  public PostOAuth2TokenRefreshAccessToken(
      @JsonProperty("client_id") String clientId,
      @JsonProperty("client_secret") String clientSecret,
      @JsonProperty("refresh_token") String refreshToken) {
    super();
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.refreshToken = refreshToken;
    this.grantType =
        new EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>(
            PostOAuth2TokenRefreshAccessTokenGrantTypeField.REFRESH_TOKEN);
  }

  protected PostOAuth2TokenRefreshAccessToken(Builder builder) {
    super();
    this.grantType = builder.grantType;
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.refreshToken = builder.refreshToken;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> getGrantType() {
    return grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostOAuth2TokenRefreshAccessToken casted = (PostOAuth2TokenRefreshAccessToken) o;
    return Objects.equals(grantType, casted.grantType)
        && Objects.equals(clientId, casted.clientId)
        && Objects.equals(clientSecret, casted.clientSecret)
        && Objects.equals(refreshToken, casted.refreshToken);
  }

  @Override
  public int hashCode() {
    return Objects.hash(grantType, clientId, clientSecret, refreshToken);
  }

  @Override
  public String toString() {
    return "PostOAuth2TokenRefreshAccessToken{"
        + "grantType='"
        + grantType
        + '\''
        + ", "
        + "clientId='"
        + clientId
        + '\''
        + ", "
        + "clientSecret='"
        + clientSecret
        + '\''
        + ", "
        + "refreshToken='"
        + refreshToken
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> grantType;

    protected final String clientId;

    protected final String clientSecret;

    protected final String refreshToken;

    public Builder(String clientId, String clientSecret, String refreshToken) {
      super();
      this.clientId = clientId;
      this.clientSecret = clientSecret;
      this.refreshToken = refreshToken;
    }

    public Builder grantType(PostOAuth2TokenRefreshAccessTokenGrantTypeField grantType) {
      this.grantType = new EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>(grantType);
      return this;
    }

    public Builder grantType(
        EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField> grantType) {
      this.grantType = grantType;
      return this;
    }

    public PostOAuth2TokenRefreshAccessToken build() {
      if (this.grantType == null) {
        this.grantType =
            new EnumWrapper<PostOAuth2TokenRefreshAccessTokenGrantTypeField>(
                PostOAuth2TokenRefreshAccessTokenGrantTypeField.REFRESH_TOKEN);
      }
      return new PostOAuth2TokenRefreshAccessToken(this);
    }
  }
}
