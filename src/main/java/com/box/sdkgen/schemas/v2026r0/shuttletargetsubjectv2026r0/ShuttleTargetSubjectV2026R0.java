package com.box.sdkgen.schemas.v2026r0.shuttletargetsubjectv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A Box user or group that a source subject is mapped to. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleTargetSubjectV2026R0 extends SerializableObject {

  /** Whether the Box target is a user or a group. */
  @JsonDeserialize(
      using =
          ShuttleTargetSubjectV2026R0TypeField.ShuttleTargetSubjectV2026R0TypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShuttleTargetSubjectV2026R0TypeField.ShuttleTargetSubjectV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<ShuttleTargetSubjectV2026R0TypeField> type;

  /**
   * The Box user or group ID. `null` when the mapping was not created through this API and is
   * identified by email (user) or name (group) instead. Skip is represented by `target: null`, not
   * a missing `id`. Create and update always send `id`.
   */
  @Nullable protected String id;

  /** The email address of the target. `null` when unknown. */
  @Nullable protected String email;

  /** The display name of the target. `null` when unknown. */
  @Nullable protected String name;

  public ShuttleTargetSubjectV2026R0(ShuttleTargetSubjectV2026R0TypeField type) {
    super();
    this.type = new EnumWrapper<ShuttleTargetSubjectV2026R0TypeField>(type);
  }

  public ShuttleTargetSubjectV2026R0(
      @JsonProperty("type") EnumWrapper<ShuttleTargetSubjectV2026R0TypeField> type) {
    super();
    this.type = type;
  }

  protected ShuttleTargetSubjectV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.email = builder.email;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleTargetSubjectV2026R0TypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  public String getEmail() {
    return email;
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
    ShuttleTargetSubjectV2026R0 casted = (ShuttleTargetSubjectV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(email, casted.email)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, email, name);
  }

  @Override
  public String toString() {
    return "ShuttleTargetSubjectV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "email='"
        + email
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<ShuttleTargetSubjectV2026R0TypeField> type;

    protected String id;

    protected String email;

    protected String name;

    public Builder(ShuttleTargetSubjectV2026R0TypeField type) {
      super();
      this.type = new EnumWrapper<ShuttleTargetSubjectV2026R0TypeField>(type);
    }

    public Builder(EnumWrapper<ShuttleTargetSubjectV2026R0TypeField> type) {
      super();
      this.type = type;
    }

    public Builder id(String id) {
      this.id = id;
      this.markNullableFieldAsSet("id");
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      this.markNullableFieldAsSet("email");
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      this.markNullableFieldAsSet("name");
      return this;
    }

    public ShuttleTargetSubjectV2026R0 build() {
      return new ShuttleTargetSubjectV2026R0(this);
    }
  }
}
