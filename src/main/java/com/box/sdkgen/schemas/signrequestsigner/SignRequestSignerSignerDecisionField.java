package com.box.sdkgen.schemas.signrequestsigner;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class SignRequestSignerSignerDecisionField extends SerializableObject {

  @JsonDeserialize(
      using =
          SignRequestSignerSignerDecisionTypeField
              .SignRequestSignerSignerDecisionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerSignerDecisionTypeField
              .SignRequestSignerSignerDecisionTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerSignerDecisionTypeField> type;

  @JsonProperty("finalized_at")
  protected String finalizedAt;

  @JsonProperty("additional_info")
  protected String additionalInfo;

  public SignRequestSignerSignerDecisionField() {
    super();
  }

  protected SignRequestSignerSignerDecisionField(
      SignRequestSignerSignerDecisionFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.finalizedAt = builder.finalizedAt;
    this.additionalInfo = builder.additionalInfo;
  }

  public EnumWrapper<SignRequestSignerSignerDecisionTypeField> getType() {
    return type;
  }

  public String getFinalizedAt() {
    return finalizedAt;
  }

  public String getAdditionalInfo() {
    return additionalInfo;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignRequestSignerSignerDecisionField casted = (SignRequestSignerSignerDecisionField) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(finalizedAt, casted.finalizedAt)
        && Objects.equals(additionalInfo, casted.additionalInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, finalizedAt, additionalInfo);
  }

  @Override
  public String toString() {
    return "SignRequestSignerSignerDecisionField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "finalizedAt='"
        + finalizedAt
        + '\''
        + ", "
        + "additionalInfo='"
        + additionalInfo
        + '\''
        + "}";
  }

  public static class SignRequestSignerSignerDecisionFieldBuilder {

    protected EnumWrapper<SignRequestSignerSignerDecisionTypeField> type;

    protected String finalizedAt;

    protected String additionalInfo;

    public SignRequestSignerSignerDecisionFieldBuilder type(
        SignRequestSignerSignerDecisionTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerSignerDecisionTypeField>(type);
      return this;
    }

    public SignRequestSignerSignerDecisionFieldBuilder type(
        EnumWrapper<SignRequestSignerSignerDecisionTypeField> type) {
      this.type = type;
      return this;
    }

    public SignRequestSignerSignerDecisionFieldBuilder finalizedAt(String finalizedAt) {
      this.finalizedAt = finalizedAt;
      return this;
    }

    public SignRequestSignerSignerDecisionFieldBuilder additionalInfo(String additionalInfo) {
      this.additionalInfo = additionalInfo;
      return this;
    }

    public SignRequestSignerSignerDecisionField build() {
      return new SignRequestSignerSignerDecisionField(this);
    }
  }
}
