package com.box.sdkgen.schemas.enterprisebase;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class EnterpriseBase extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = EnterpriseBaseTypeField.EnterpriseBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = EnterpriseBaseTypeField.EnterpriseBaseTypeFieldSerializer.class)
  protected EnumWrapper<EnterpriseBaseTypeField> type;

  public EnterpriseBase() {
    super();
  }

  protected EnterpriseBase(EnterpriseBaseBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
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

  public static class EnterpriseBaseBuilder {

    protected String id;

    protected EnumWrapper<EnterpriseBaseTypeField> type;

    public EnterpriseBaseBuilder id(String id) {
      this.id = id;
      return this;
    }

    public EnterpriseBaseBuilder type(EnterpriseBaseTypeField type) {
      this.type = new EnumWrapper<EnterpriseBaseTypeField>(type);
      return this;
    }

    public EnterpriseBaseBuilder type(EnumWrapper<EnterpriseBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public EnterpriseBase build() {
      return new EnterpriseBase(this);
    }
  }
}
