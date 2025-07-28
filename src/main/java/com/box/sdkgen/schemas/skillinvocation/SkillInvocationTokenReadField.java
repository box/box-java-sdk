package com.box.sdkgen.schemas.skillinvocation;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SkillInvocationTokenReadField extends SerializableObject {

  @JsonProperty("access_token")
  protected String accessToken;

  @JsonProperty("expires_in")
  protected Long expiresIn;

  @JsonDeserialize(
      using =
          SkillInvocationTokenReadTokenTypeField.SkillInvocationTokenReadTokenTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          SkillInvocationTokenReadTokenTypeField.SkillInvocationTokenReadTokenTypeFieldSerializer
              .class)
  @JsonProperty("token_type")
  protected EnumWrapper<SkillInvocationTokenReadTokenTypeField> tokenType;

  @JsonProperty("restricted_to")
  protected String restrictedTo;

  public SkillInvocationTokenReadField() {
    super();
  }

  protected SkillInvocationTokenReadField(Builder builder) {
    super();
    this.accessToken = builder.accessToken;
    this.expiresIn = builder.expiresIn;
    this.tokenType = builder.tokenType;
    this.restrictedTo = builder.restrictedTo;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getAccessToken() {
    return accessToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public EnumWrapper<SkillInvocationTokenReadTokenTypeField> getTokenType() {
    return tokenType;
  }

  public String getRestrictedTo() {
    return restrictedTo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SkillInvocationTokenReadField casted = (SkillInvocationTokenReadField) o;
    return Objects.equals(accessToken, casted.accessToken)
        && Objects.equals(expiresIn, casted.expiresIn)
        && Objects.equals(tokenType, casted.tokenType)
        && Objects.equals(restrictedTo, casted.restrictedTo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, expiresIn, tokenType, restrictedTo);
  }

  @Override
  public String toString() {
    return "SkillInvocationTokenReadField{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String accessToken;

    protected Long expiresIn;

    protected EnumWrapper<SkillInvocationTokenReadTokenTypeField> tokenType;

    protected String restrictedTo;

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder expiresIn(Long expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder tokenType(SkillInvocationTokenReadTokenTypeField tokenType) {
      this.tokenType = new EnumWrapper<SkillInvocationTokenReadTokenTypeField>(tokenType);
      return this;
    }

    public Builder tokenType(EnumWrapper<SkillInvocationTokenReadTokenTypeField> tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder restrictedTo(String restrictedTo) {
      this.restrictedTo = restrictedTo;
      return this;
    }

    public SkillInvocationTokenReadField build() {
      return new SkillInvocationTokenReadField(this);
    }
  }
}
