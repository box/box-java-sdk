package com.box.sdkgen.schemas.signrequestsigner;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.time.OffsetDateTime;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignRequestSignerSignerDecisionField extends SerializableObject {

  /** Type of decision made by the signer. */
  @JsonDeserialize(
      using =
          SignRequestSignerSignerDecisionTypeField
              .SignRequestSignerSignerDecisionTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          SignRequestSignerSignerDecisionTypeField
              .SignRequestSignerSignerDecisionTypeFieldSerializer.class)
  protected EnumWrapper<SignRequestSignerSignerDecisionTypeField> type;

  /** Date and Time that the decision was made. */
  @JsonProperty("finalized_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected OffsetDateTime finalizedAt;

  /** Additional info about the decision, such as the decline reason from the signer. */
  @JsonProperty("additional_info")
  @Nullable
  protected String additionalInfo;

  public SignRequestSignerSignerDecisionField() {
    super();
  }

  protected SignRequestSignerSignerDecisionField(Builder builder) {
    super();
    this.type = builder.type;
    this.finalizedAt = builder.finalizedAt;
    this.additionalInfo = builder.additionalInfo;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<SignRequestSignerSignerDecisionTypeField> getType() {
    return type;
  }

  public OffsetDateTime getFinalizedAt() {
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

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<SignRequestSignerSignerDecisionTypeField> type;

    protected OffsetDateTime finalizedAt;

    protected String additionalInfo;

    public Builder type(SignRequestSignerSignerDecisionTypeField type) {
      this.type = new EnumWrapper<SignRequestSignerSignerDecisionTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<SignRequestSignerSignerDecisionTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder finalizedAt(OffsetDateTime finalizedAt) {
      this.finalizedAt = finalizedAt;
      return this;
    }

    public Builder additionalInfo(String additionalInfo) {
      this.additionalInfo = additionalInfo;
      this.markNullableFieldAsSet("additional_info");
      return this;
    }

    public SignRequestSignerSignerDecisionField build() {
      return new SignRequestSignerSignerDecisionField(this);
    }
  }
}
