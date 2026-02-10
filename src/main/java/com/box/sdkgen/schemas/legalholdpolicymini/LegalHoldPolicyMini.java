package com.box.sdkgen.schemas.legalholdpolicymini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A mini legal hold policy. */
@JsonFilter("nullablePropertyFilter")
public class LegalHoldPolicyMini extends SerializableObject {

  /** The unique identifier for this legal hold policy. */
  protected final String id;

  /** The value will always be `legal_hold_policy`. */
  @JsonDeserialize(
      using = LegalHoldPolicyMiniTypeField.LegalHoldPolicyMiniTypeFieldDeserializer.class)
  @JsonSerialize(using = LegalHoldPolicyMiniTypeField.LegalHoldPolicyMiniTypeFieldSerializer.class)
  protected EnumWrapper<LegalHoldPolicyMiniTypeField> type;

  public LegalHoldPolicyMini(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<LegalHoldPolicyMiniTypeField>(
            LegalHoldPolicyMiniTypeField.LEGAL_HOLD_POLICY);
  }

  protected LegalHoldPolicyMini(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<LegalHoldPolicyMiniTypeField> getType() {
    return type;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LegalHoldPolicyMini casted = (LegalHoldPolicyMini) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "LegalHoldPolicyMini{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<LegalHoldPolicyMiniTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(LegalHoldPolicyMiniTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyMiniTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<LegalHoldPolicyMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicyMini build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<LegalHoldPolicyMiniTypeField>(
                LegalHoldPolicyMiniTypeField.LEGAL_HOLD_POLICY);
      }
      return new LegalHoldPolicyMini(this);
    }
  }
}
