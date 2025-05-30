package com.box.sdkgen.schemas.userfull;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class UserFullEnterpriseField extends SerializableObject {

  protected String id;

  @JsonDeserialize(
      using = UserFullEnterpriseTypeField.UserFullEnterpriseTypeFieldDeserializer.class)
  @JsonSerialize(using = UserFullEnterpriseTypeField.UserFullEnterpriseTypeFieldSerializer.class)
  protected EnumWrapper<UserFullEnterpriseTypeField> type;

  protected String name;

  public UserFullEnterpriseField() {
    super();
  }

  protected UserFullEnterpriseField(UserFullEnterpriseFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<UserFullEnterpriseTypeField> getType() {
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
    UserFullEnterpriseField casted = (UserFullEnterpriseField) o;
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
    return "UserFullEnterpriseField{"
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

  public static class UserFullEnterpriseFieldBuilder {

    protected String id;

    protected EnumWrapper<UserFullEnterpriseTypeField> type;

    protected String name;

    public UserFullEnterpriseFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UserFullEnterpriseFieldBuilder type(UserFullEnterpriseTypeField type) {
      this.type = new EnumWrapper<UserFullEnterpriseTypeField>(type);
      return this;
    }

    public UserFullEnterpriseFieldBuilder type(EnumWrapper<UserFullEnterpriseTypeField> type) {
      this.type = type;
      return this;
    }

    public UserFullEnterpriseFieldBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UserFullEnterpriseField build() {
      return new UserFullEnterpriseField(this);
    }
  }
}
