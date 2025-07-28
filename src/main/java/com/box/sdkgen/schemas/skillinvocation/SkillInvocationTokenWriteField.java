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
public class SkillInvocationTokenWriteField extends SerializableObject {

  @JsonProperty("access_token")
  protected String accessToken;

  @JsonProperty("expires_in")
  protected Long expiresIn;

  @JsonDeserialize(
      using =
          SkillInvocationTokenWriteTokenTypeField
              .SkillInvocationTokenWriteTokenTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SkillInvocationTokenWriteTokenTypeField.SkillInvocationTokenWriteTokenTypeFieldSerializer
              .class)
  @JsonProperty("token_type")
  protected EnumWrapper<SkillInvocationTokenWriteTokenTypeField> tokenType;

  @JsonProperty("restricted_to")
  protected String restrictedTo;

  public SkillInvocationTokenWriteField() {
    super();
  }

  protected SkillInvocationTokenWriteField(Builder builder) {
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

  public EnumWrapper<SkillInvocationTokenWriteTokenTypeField> getTokenType() {
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
    SkillInvocationTokenWriteField casted = (SkillInvocationTokenWriteField) o;
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
    return "SkillInvocationTokenWriteField{"
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

    protected EnumWrapper<SkillInvocationTokenWriteTokenTypeField> tokenType;

    protected String restrictedTo;

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder expiresIn(Long expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder tokenType(SkillInvocationTokenWriteTokenTypeField tokenType) {
      this.tokenType = new EnumWrapper<SkillInvocationTokenWriteTokenTypeField>(tokenType);
      return this;
    }

    public Builder tokenType(EnumWrapper<SkillInvocationTokenWriteTokenTypeField> tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder restrictedTo(String restrictedTo) {
      this.restrictedTo = restrictedTo;
      return this;
    }

    public SkillInvocationTokenWriteField build() {
      return new SkillInvocationTokenWriteField(this);
    }
  }
}
