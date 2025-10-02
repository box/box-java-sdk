package com.box.sdkgen.schemas.termsofservice;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TermsOfServiceEnterpriseField extends SerializableObject {

  /** The unique identifier for this enterprise. */
  protected String id;

  /** The value will always be `enterprise`. */
  @JsonDeserialize(
      using = TermsOfServiceEnterpriseTypeField.TermsOfServiceEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(
      using = TermsOfServiceEnterpriseTypeField.TermsOfServiceEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<TermsOfServiceEnterpriseTypeField> type;

  /** The name of the enterprise. */
  protected String name;

  public TermsOfServiceEnterpriseField() {
    super();
  }

  protected TermsOfServiceEnterpriseField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<TermsOfServiceEnterpriseTypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TermsOfServiceEnterpriseField casted = (TermsOfServiceEnterpriseField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name);
  }

  @Override
  public String toString() {
    return "TermsOfServiceEnterpriseField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<TermsOfServiceEnterpriseTypeField> type;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(TermsOfServiceEnterpriseTypeField type) {
      this.type = new EnumWrapper<TermsOfServiceEnterpriseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TermsOfServiceEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public TermsOfServiceEnterpriseField build() {
      return new TermsOfServiceEnterpriseField(this);
    }
  }
}
