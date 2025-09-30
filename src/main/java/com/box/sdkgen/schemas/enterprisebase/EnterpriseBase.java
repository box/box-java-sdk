package com.box.sdkgen.schemas.enterprisebase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class EnterpriseBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = EnterpriseBaseTypeField.EnterpriseBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = EnterpriseBaseTypeField.EnterpriseBaseTypeFieldSerializer.class)
  protected EnumWrapper<EnterpriseBaseTypeField> type;

  public EnterpriseBase() {
    super();
  }

  protected EnterpriseBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<EnterpriseBaseTypeField> getType() {
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
    EnterpriseBase casted = (EnterpriseBase) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "EnterpriseBase{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<EnterpriseBaseTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(EnterpriseBaseTypeField type) {
      this.type = new EnumWrapper<EnterpriseBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<EnterpriseBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public EnterpriseBase build() {
      return new EnterpriseBase(this);
    }
  }
}
