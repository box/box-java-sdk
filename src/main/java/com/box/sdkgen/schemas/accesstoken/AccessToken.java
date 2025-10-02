package com.box.sdkgen.schemas.accesstoken;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.resourcescope.ResourceScope;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** A token that can be used to make authenticated API calls. */
@JsonFilter("nullablePropertyFilter")
public class AccessToken extends SerializableObject {

  /** The requested access token. */
  @JsonProperty("access_token")
  protected String accessToken;

  /** The time in seconds by which this token will expire. */
  @JsonProperty("expires_in")
  protected Long expiresIn;

  /** The type of access token returned. */
  @JsonDeserialize(using = AccessTokenTokenTypeField.AccessTokenTokenTypeFieldDeserializer.class)
  @JsonSerialize(using = AccessTokenTokenTypeField.AccessTokenTokenTypeFieldSerializer.class)
  @JsonProperty("token_type")
  protected EnumWrapper<AccessTokenTokenTypeField> tokenType;

  /**
   * The permissions that this access token permits, providing a list of resources (files, folders,
   * etc) and the scopes permitted for each of those resources.
   */
  @JsonProperty("restricted_to")
  protected List<ResourceScope> restrictedTo;

  /**
   * The refresh token for this access token, which can be used to request a new access token when
   * the current one expires.
   */
  @JsonProperty("refresh_token")
  protected String refreshToken;

  /**
   * The type of downscoped access token returned. This is only returned if an access token has been
   * downscoped.
   */
  @JsonDeserialize(
      using = AccessTokenIssuedTokenTypeField.AccessTokenIssuedTokenTypeFieldDeserializer.class)
  @JsonSerialize(
      using = AccessTokenIssuedTokenTypeField.AccessTokenIssuedTokenTypeFieldSerializer.class)
  @JsonProperty("issued_token_type")
  protected EnumWrapper<AccessTokenIssuedTokenTypeField> issuedTokenType;

  public AccessToken() {
    super();
  }

  protected AccessToken(Builder builder) {
    super();
    this.accessToken = builder.accessToken;
    this.expiresIn = builder.expiresIn;
    this.tokenType = builder.tokenType;
    this.restrictedTo = builder.restrictedTo;
    this.refreshToken = builder.refreshToken;
    this.issuedTokenType = builder.issuedTokenType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getAccessToken() {
    return accessToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public EnumWrapper<AccessTokenTokenTypeField> getTokenType() {
    return tokenType;
  }

  public List<ResourceScope> getRestrictedTo() {
    return restrictedTo;
  }

  public String getRefreshToken() {
    return refreshToken;
  }

  public EnumWrapper<AccessTokenIssuedTokenTypeField> getIssuedTokenType() {
    return issuedTokenType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AccessToken casted = (AccessToken) o;
    return Objects.equals(accessToken, casted.accessToken)
        && Objects.equals(expiresIn, casted.expiresIn)
        && Objects.equals(tokenType, casted.tokenType)
        && Objects.equals(restrictedTo, casted.restrictedTo)
        && Objects.equals(refreshToken, casted.refreshToken)
        && Objects.equals(issuedTokenType, casted.issuedTokenType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        accessToken, expiresIn, tokenType, restrictedTo, refreshToken, issuedTokenType);
  }

  @Override
  public String toString() {
    return "AccessToken{"
        + "accessToken='"
        + accessToken
        + '\''
        + ", "
        + "expiresIn='"
        + expiresIn
        + '\''
        + ", "
        + "tokenType='"
        + tokenType
        + '\''
        + ", "
        + "restrictedTo='"
        + restrictedTo
        + '\''
        + ", "
        + "refreshToken='"
        + refreshToken
        + '\''
        + ", "
        + "issuedTokenType='"
        + issuedTokenType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String accessToken;

    protected Long expiresIn;

    protected EnumWrapper<AccessTokenTokenTypeField> tokenType;

    protected List<ResourceScope> restrictedTo;

    protected String refreshToken;

    protected EnumWrapper<AccessTokenIssuedTokenTypeField> issuedTokenType;

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder expiresIn(Long expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder tokenType(AccessTokenTokenTypeField tokenType) {
      this.tokenType = new EnumWrapper<AccessTokenTokenTypeField>(tokenType);
      return this;
    }

    public Builder tokenType(EnumWrapper<AccessTokenTokenTypeField> tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder restrictedTo(List<ResourceScope> restrictedTo) {
      this.restrictedTo = restrictedTo;
      return this;
    }

    public Builder refreshToken(String refreshToken) {
      this.refreshToken = refreshToken;
      return this;
    }

    public Builder issuedTokenType(AccessTokenIssuedTokenTypeField issuedTokenType) {
      this.issuedTokenType = new EnumWrapper<AccessTokenIssuedTokenTypeField>(issuedTokenType);
      return this;
    }

    public Builder issuedTokenType(EnumWrapper<AccessTokenIssuedTokenTypeField> issuedTokenType) {
      this.issuedTokenType = issuedTokenType;
      return this;
    }

    public AccessToken build() {
      return new AccessToken(this);
    }
  }
}
