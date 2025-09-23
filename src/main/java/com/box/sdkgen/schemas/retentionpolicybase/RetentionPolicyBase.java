package com.box.sdkgen.schemas.retentionpolicybase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class RetentionPolicyBase extends SerializableObject {

  protected final String id;

  @JsonDeserialize(
      using = RetentionPolicyBaseTypeField.RetentionPolicyBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = RetentionPolicyBaseTypeField.RetentionPolicyBaseTypeFieldSerializer.class)
  protected EnumWrapper<RetentionPolicyBaseTypeField> type;

  public RetentionPolicyBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<RetentionPolicyBaseTypeField>(
            RetentionPolicyBaseTypeField.RETENTION_POLICY);
  }

  protected RetentionPolicyBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<RetentionPolicyBaseTypeField> getType() {
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
    RetentionPolicyBase casted = (RetentionPolicyBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "RetentionPolicyBase{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<RetentionPolicyBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<RetentionPolicyBaseTypeField>(
              RetentionPolicyBaseTypeField.RETENTION_POLICY);
    }

    public Builder type(RetentionPolicyBaseTypeField type) {
      this.type = new EnumWrapper<RetentionPolicyBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<RetentionPolicyBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public RetentionPolicyBase build() {
      return new RetentionPolicyBase(this);
    }
  }
}
