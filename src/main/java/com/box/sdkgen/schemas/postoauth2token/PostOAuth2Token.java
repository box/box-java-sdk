package com.box.sdkgen.schemas.postoauth2token;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A request for a new OAuth 2.0 token. */
@JsonFilter("nullablePropertyFilter")
public class PostOAuth2Token extends SerializableObject {

  /**
   * The type of request being made, either using a client-side obtained authorization code, a
   * refresh token, a JWT assertion, client credentials grant or another access token for the
   * purpose of downscoping a token.
   */
  @JsonDeserialize(
      using = PostOAuth2TokenGrantTypeField.PostOAuth2TokenGrantTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenGrantTypeField.PostOAuth2TokenGrantTypeFieldSerializer.class)
  @JsonProperty("grant_type")
  protected final EnumWrapper<PostOAuth2TokenGrantTypeField> grantType;

  /**
   * The Client ID of the application requesting an access token.
   *
   * <p>Used in combination with `authorization_code`, `client_credentials`, or
   * `urn:ietf:params:oauth:grant-type:jwt-bearer` as the `grant_type`.
   */
  @JsonProperty("client_id")
  protected String clientId;

  /**
   * The client secret of the application requesting an access token.
   *
   * <p>Used in combination with `authorization_code`, `client_credentials`, or
   * `urn:ietf:params:oauth:grant-type:jwt-bearer` as the `grant_type`.
   */
  @JsonProperty("client_secret")
  protected String clientSecret;

  /**
   * The client-side authorization code passed to your application by Box in the browser redirect
   * after the user has successfully granted your application permission to make API calls on their
   * behalf.
   *
   * <p>Used in combination with `authorization_code` as the `grant_type`.
   */
  protected String code;

  /**
   * A refresh token used to get a new access token with.
   *
   * <p>Used in combination with `refresh_token` as the `grant_type`.
   */
  @JsonProperty("refresh_token")
  protected String refreshToken;

  /**
   * A JWT assertion for which to request a new access token.
   *
   * <p>Used in combination with `urn:ietf:params:oauth:grant-type:jwt-bearer` as the `grant_type`.
   */
  protected String assertion;

  /**
   * The token to exchange for a downscoped token. This can be a regular access token, a JWT
   * assertion, or an app token.
   *
   * <p>Used in combination with `urn:ietf:params:oauth:grant-type:token-exchange` as the
   * `grant_type`.
   */
  @JsonProperty("subject_token")
  protected String subjectToken;

  /**
   * The type of `subject_token` passed in.
   *
   * <p>Used in combination with `urn:ietf:params:oauth:grant-type:token-exchange` as the
   * `grant_type`.
   */
  @JsonDeserialize(
      using =
          PostOAuth2TokenSubjectTokenTypeField.PostOAuth2TokenSubjectTokenTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          PostOAuth2TokenSubjectTokenTypeField.PostOAuth2TokenSubjectTokenTypeFieldSerializer.class)
  @JsonProperty("subject_token_type")
  protected EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> subjectTokenType;

  /**
   * The token used to create an annotator token. This is a JWT assertion.
   *
   * <p>Used in combination with `urn:ietf:params:oauth:grant-type:token-exchange` as the
   * `grant_type`.
   */
  @JsonProperty("actor_token")
  protected String actorToken;

  /**
   * The type of `actor_token` passed in.
   *
   * <p>Used in combination with `urn:ietf:params:oauth:grant-type:token-exchange` as the
   * `grant_type`.
   */
  @JsonDeserialize(
      using =
          PostOAuth2TokenActorTokenTypeField.PostOAuth2TokenActorTokenTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenActorTokenTypeField.PostOAuth2TokenActorTokenTypeFieldSerializer.class)
  @JsonProperty("actor_token_type")
  protected EnumWrapper<PostOAuth2TokenActorTokenTypeField> actorTokenType;

  /**
   * The space-delimited list of scopes that you want apply to the new access token.
   *
   * <p>The `subject_token` will need to have all of these scopes or the call will error with **401
   * Unauthorized**..
   */
  protected String scope;

  /** Full URL for the file that the token should be generated for. */
  protected String resource;

  /** Used in combination with `client_credentials` as the `grant_type`. */
  @JsonDeserialize(
      using =
          PostOAuth2TokenBoxSubjectTypeField.PostOAuth2TokenBoxSubjectTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenBoxSubjectTypeField.PostOAuth2TokenBoxSubjectTypeFieldSerializer.class)
  @JsonProperty("box_subject_type")
  protected EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> boxSubjectType;

  /**
   * Used in combination with `client_credentials` as the `grant_type`. Value is determined by
   * `box_subject_type`. If `user` use user ID and if `enterprise` use enterprise ID.
   */
  @JsonProperty("box_subject_id")
  protected String boxSubjectId;

  /** Full URL of the shared link on the file or folder that the token should be generated for. */
  @JsonProperty("box_shared_link")
  protected String boxSharedLink;

  public PostOAuth2Token(PostOAuth2TokenGrantTypeField grantType) {
    super();
    this.grantType = new EnumWrapper<PostOAuth2TokenGrantTypeField>(grantType);
  }

  public PostOAuth2Token(
      @JsonProperty("grant_type") EnumWrapper<PostOAuth2TokenGrantTypeField> grantType) {
    super();
    this.grantType = grantType;
  }

  protected PostOAuth2Token(Builder builder) {
    super();
    this.grantType = builder.grantType;
    this.clientId = builder.clientId;
    this.clientSecret = builder.clientSecret;
    this.code = builder.code;
    this.refreshToken = builder.refreshToken;
    this.assertion = builder.assertion;
    this.subjectToken = builder.subjectToken;
    this.subjectTokenType = builder.subjectTokenType;
    this.actorToken = builder.actorToken;
    this.actorTokenType = builder.actorTokenType;
    this.scope = builder.scope;
    this.resource = builder.resource;
    this.boxSubjectType = builder.boxSubjectType;
    this.boxSubjectId = builder.boxSubjectId;
    this.boxSharedLink = builder.boxSharedLink;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<PostOAuth2TokenGrantTypeField> getGrantType() {
    return grantType;
  }

  public String getClientId() {
    return clientId;
  }

  public String getClientSecret() {
    return clientSecret;
  }

  public String getCode() {
    return code;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public String getAssertion() {
    return assertion;
  }

  public String getSubjectToken() {
    return subjectToken;
  }

  public EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> getSubjectTokenType() {
    return subjectTokenType;
  }

  public String getActorToken() {
    return actorToken;
  }

  public EnumWrapper<PostOAuth2TokenActorTokenTypeField> getActorTokenType() {
    return actorTokenType;
  }

  public String getScope() {
    return scope;
  }

  public String getResource() {
    return resource;
  }

  public EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> getBoxSubjectType() {
    return boxSubjectType;
  }

  public String getBoxSubjectId() {
    return boxSubjectId;
  }

  public String getBoxSharedLink() {
    return boxSharedLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PostOAuth2Token casted = (PostOAuth2Token) o;
    return Objects.equals(grantType, casted.grantType)
        && Objects.equals(clientId, casted.clientId)
        && Objects.equals(clientSecret, casted.clientSecret)
        && Objects.equals(code, casted.code)
        && Objects.equals(refreshToken, casted.refreshToken)
        && Objects.equals(assertion, casted.assertion)
        && Objects.equals(subjectToken, casted.subjectToken)
        && Objects.equals(subjectTokenType, casted.subjectTokenType)
        && Objects.equals(actorToken, casted.actorToken)
        && Objects.equals(actorTokenType, casted.actorTokenType)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(resource, casted.resource)
        && Objects.equals(boxSubjectType, casted.boxSubjectType)
        && Objects.equals(boxSubjectId, casted.boxSubjectId)
        && Objects.equals(boxSharedLink, casted.boxSharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        grantType,
        clientId,
        clientSecret,
        code,
        refreshToken,
        assertion,
        subjectToken,
        subjectTokenType,
        actorToken,
        actorTokenType,
        scope,
        resource,
        boxSubjectType,
        boxSubjectId,
        boxSharedLink);
  }

  @Override
  public String toString() {
    return "PostOAuth2Token{"
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
        + "code='"
        + code
        + '\''
        + ", "
        + "refreshToken='"
        + refreshToken
        + '\''
        + ", "
        + "assertion='"
        + assertion
        + '\''
        + ", "
        + "subjectToken='"
        + subjectToken
        + '\''
        + ", "
        + "subjectTokenType='"
        + subjectTokenType
        + '\''
        + ", "
        + "actorToken='"
        + actorToken
        + '\''
        + ", "
        + "actorTokenType='"
        + actorTokenType
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "resource='"
        + resource
        + '\''
        + ", "
        + "boxSubjectType='"
        + boxSubjectType
        + '\''
        + ", "
        + "boxSubjectId='"
        + boxSubjectId
        + '\''
        + ", "
        + "boxSharedLink='"
        + boxSharedLink
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<PostOAuth2TokenGrantTypeField> grantType;

    protected String clientId;

    protected String clientSecret;

    protected String code;

    protected String refreshToken;

    protected String assertion;

    protected String subjectToken;

    protected EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> subjectTokenType;

    protected String actorToken;

    protected EnumWrapper<PostOAuth2TokenActorTokenTypeField> actorTokenType;

    protected String scope;

    protected String resource;

    protected EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> boxSubjectType;

    protected String boxSubjectId;

    protected String boxSharedLink;

    public Builder(PostOAuth2TokenGrantTypeField grantType) {
      super();
      this.grantType = new EnumWrapper<PostOAuth2TokenGrantTypeField>(grantType);
    }

    public Builder(EnumWrapper<PostOAuth2TokenGrantTypeField> grantType) {
      super();
      this.grantType = grantType;
    }

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder clientSecret(String clientSecret) {
      this.clientSecret = clientSecret;
      return this;
    }

    public Builder code(String code) {
      this.code = code;
      return this;
    }

    public Builder refreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public Builder assertion(String assertion) {
      this.assertion = assertion;
      return this;
    }

    public Builder subjectToken(String subjectToken) {
      this.subjectToken = subjectToken;
      return this;
    }

    public Builder subjectTokenType(PostOAuth2TokenSubjectTokenTypeField subjectTokenType) {
      this.subjectTokenType =
          new EnumWrapper<PostOAuth2TokenSubjectTokenTypeField>(subjectTokenType);
      return this;
    }

    public Builder subjectTokenType(
        EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> subjectTokenType) {
      this.subjectTokenType = subjectTokenType;
      return this;
    }

    public Builder actorToken(String actorToken) {
      this.actorToken = actorToken;
      return this;
    }

    public Builder actorTokenType(PostOAuth2TokenActorTokenTypeField actorTokenType) {
      this.actorTokenType = new EnumWrapper<PostOAuth2TokenActorTokenTypeField>(actorTokenType);
      return this;
    }

    public Builder actorTokenType(EnumWrapper<PostOAuth2TokenActorTokenTypeField> actorTokenType) {
      this.actorTokenType = actorTokenType;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder resource(String resource) {
      this.resource = resource;
      return this;
    }

    public Builder boxSubjectType(PostOAuth2TokenBoxSubjectTypeField boxSubjectType) {
      this.boxSubjectType = new EnumWrapper<PostOAuth2TokenBoxSubjectTypeField>(boxSubjectType);
      return this;
    }

    public Builder boxSubjectType(EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> boxSubjectType) {
      this.boxSubjectType = boxSubjectType;
      return this;
    }

    public Builder boxSubjectId(String boxSubjectId) {
      this.boxSubjectId = boxSubjectId;
      return this;
    }

    public Builder boxSharedLink(String boxSharedLink) {
      this.boxSharedLink = boxSharedLink;
      return this;
    }

    public PostOAuth2Token build() {
      return new PostOAuth2Token(this);
    }
  }
}
