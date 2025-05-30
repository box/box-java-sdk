package com.box.sdkgen.schemas.legalholdpolicymini;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class LegalHoldPolicyMini extends SerializableObject {

  protected final String id;

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

  protected LegalHoldPolicyMini(LegalHoldPolicyMiniBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
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

  public static class LegalHoldPolicyMiniBuilder {

    protected final String id;

    protected EnumWrapper<LegalHoldPolicyMiniTypeField> type;

    public LegalHoldPolicyMiniBuilder(String id) {
      this.id = id;
      this.type =
          new EnumWrapper<LegalHoldPolicyMiniTypeField>(
              LegalHoldPolicyMiniTypeField.LEGAL_HOLD_POLICY);
    }

    public LegalHoldPolicyMiniBuilder type(LegalHoldPolicyMiniTypeField type) {
      this.type = new EnumWrapper<LegalHoldPolicyMiniTypeField>(type);
      return this;
    }

    public LegalHoldPolicyMiniBuilder type(EnumWrapper<LegalHoldPolicyMiniTypeField> type) {
      this.type = type;
      return this;
    }

    public LegalHoldPolicyMini build() {
      return new LegalHoldPolicyMini(this);
    }
  }
}
