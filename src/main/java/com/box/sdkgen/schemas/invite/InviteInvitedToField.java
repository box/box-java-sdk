package com.box.sdkgen.schemas.invite;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class InviteInvitedToField extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = InviteInvitedToTypeField.InviteInvitedToTypeFieldDeserializer.class)
  @JsonSerialize(using = InviteInvitedToTypeField.InviteInvitedToTypeFieldSerializer.class)
  protected EnumWrapper<InviteInvitedToTypeField> type;

  protected String name;

  public InviteInvitedToField() {
    super();
  }

  protected InviteInvitedToField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<InviteInvitedToTypeField> getType() {
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
    InviteInvitedToField casted = (InviteInvitedToField) o;
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
    return "InviteInvitedToField{"
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

    protected EnumWrapper<InviteInvitedToTypeField> type;

    protected String name;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(InviteInvitedToTypeField type) {
      this.type = new EnumWrapper<InviteInvitedToTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<InviteInvitedToTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public InviteInvitedToField build() {
      return new InviteInvitedToField(this);
    }
  }
}
