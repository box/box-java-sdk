package com.box.sdkgen.schemas.v2026r0.enterprisereferencev2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A reference to an enterprise, used when nested within another resource. */
@JsonFilter("nullablePropertyFilter")
public class EnterpriseReferenceV2026R0 extends SerializableObject {

  /** The unique identifier for this enterprise. */
  protected final String id;

  /** The value will always be `enterprise`. */
  @JsonDeserialize(
      using =
          EnterpriseReferenceV2026R0TypeField.EnterpriseReferenceV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          EnterpriseReferenceV2026R0TypeField.EnterpriseReferenceV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<EnterpriseReferenceV2026R0TypeField> type;

  public EnterpriseReferenceV2026R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<EnterpriseReferenceV2026R0TypeField>(
            EnterpriseReferenceV2026R0TypeField.ENTERPRISE);
  }

  protected EnterpriseReferenceV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<EnterpriseReferenceV2026R0TypeField> getType() {
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
    EnterpriseReferenceV2026R0 casted = (EnterpriseReferenceV2026R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "EnterpriseReferenceV2026R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<EnterpriseReferenceV2026R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(EnterpriseReferenceV2026R0TypeField type) {
      this.type = new EnumWrapper<EnterpriseReferenceV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<EnterpriseReferenceV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public EnterpriseReferenceV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<EnterpriseReferenceV2026R0TypeField>(
                EnterpriseReferenceV2026R0TypeField.ENTERPRISE);
      }
      return new EnterpriseReferenceV2026R0(this);
    }
  }
}
