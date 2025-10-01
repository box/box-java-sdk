package com.box.sdkgen.schemas.postoauth2revoke;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** A request to revoke an OAuth 2.0 token. */
@JsonFilter("nullablePropertyFilter")
public class PostOAuth2Revoke extends SerializableObject {

  /** The Client ID of the application requesting to revoke the access token. */
  @JsonProperty("client_id")
  protected String clientId;

  /** The client secret of the application requesting to revoke an access token. */
  @JsonProperty("client_secret")
  protected String clientSecret;

  /** The access token to revoke. */
  protected String token;

  public PostOAuth2Revoke() {
    super();
  }

  protected PostOAuth2Revoke(Builder builder) {
    super();
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.token = builder.token;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getToken() {
    return token;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostOAuth2Revoke casted = (PostOAuth2Revoke) o;
    return Objects.equals(clientId, casted.clientId)
        && Objects.equals(clientSecret, casted.clientSecret)
        && Objects.equals(token, casted.token);
  }

  @Override
  public int hashCode() {
    return Objects.hash(clientId, clientSecret, token);
  }

  @Override
  public String toString() {
    return "PostOAuth2Revoke{"
        + "clientId='"
        + clientId
        + '\''
        + ", "
        + "clientSecret='"
        + clientSecret
        + '\''
        + ", "
        + "token='"
        + token
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String clientId;

    protected String clientSecret;

    protected String token;

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder token(String token) {
      this.token = token;
      return this;
    }

    public PostOAuth2Revoke build() {
      return new PostOAuth2Revoke(this);
    }
  }
}
