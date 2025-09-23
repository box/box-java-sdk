package com.box.sdkgen.schemas.postoauth2token;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class PostOAuth2Token extends SerializableObject {

  @JsonDeserialize(
      using = PostOAuth2TokenGrantTypeField.PostOAuth2TokenGrantTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenGrantTypeField.PostOAuth2TokenGrantTypeFieldSerializer.class)
  @JsonProperty("grant_type")
  protected final EnumWrapper<PostOAuth2TokenGrantTypeField> grantType;

  @JsonProperty("client_id")
  protected String clientId;

  @JsonProperty("client_secret")
  protected String clientSecret;

  protected String code;

  @JsonProperty("refresh_token")
  protected String refreshToken;

  protected String assertion;

  @JsonProperty("subject_token")
  protected String subjectToken;

  @JsonDeserialize(
      using =
          PostOAuth2TokenSubjectTokenTypeField.PostOAuth2TokenSubjectTokenTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          PostOAuth2TokenSubjectTokenTypeField.PostOAuth2TokenSubjectTokenTypeFieldSerializer.class)
  @JsonProperty("subject_token_type")
  protected EnumWrapper<PostOAuth2TokenSubjectTokenTypeField> subjectTokenType;

  @JsonProperty("actor_token")
  protected String actorToken;

  @JsonDeserialize(
      using =
          PostOAuth2TokenActorTokenTypeField.PostOAuth2TokenActorTokenTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenActorTokenTypeField.PostOAuth2TokenActorTokenTypeFieldSerializer.class)
  @JsonProperty("actor_token_type")
  protected EnumWrapper<PostOAuth2TokenActorTokenTypeField> actorTokenType;

  protected String scope;

  protected String resource;

  @JsonDeserialize(
      using =
          PostOAuth2TokenBoxSubjectTypeField.PostOAuth2TokenBoxSubjectTypeFieldDeserializer.class)
  @JsonSerialize(
      using = PostOAuth2TokenBoxSubjectTypeField.PostOAuth2TokenBoxSubjectTypeFieldSerializer.class)
  @JsonProperty("box_subject_type")
  protected EnumWrapper<PostOAuth2TokenBoxSubjectTypeField> boxSubjectType;

  @JsonProperty("box_subject_id")
  protected String boxSubjectId;

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
