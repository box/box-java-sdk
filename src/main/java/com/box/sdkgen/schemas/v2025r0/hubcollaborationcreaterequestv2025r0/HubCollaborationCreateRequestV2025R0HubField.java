package com.box.sdkgen.schemas.v2025r0.hubcollaborationcreaterequestv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class HubCollaborationCreateRequestV2025R0HubField extends SerializableObject {

  @JsonDeserialize(
      using =
          HubCollaborationCreateRequestV2025R0HubTypeField
              .HubCollaborationCreateRequestV2025R0HubTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          HubCollaborationCreateRequestV2025R0HubTypeField
              .HubCollaborationCreateRequestV2025R0HubTypeFieldSerializer.class)
  protected EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> type;

  protected final String id;

  public HubCollaborationCreateRequestV2025R0HubField(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>(
            HubCollaborationCreateRequestV2025R0HubTypeField.HUBS);
  }

  protected HubCollaborationCreateRequestV2025R0HubField(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubCollaborationCreateRequestV2025R0HubField casted =
        (HubCollaborationCreateRequestV2025R0HubField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "HubCollaborationCreateRequestV2025R0HubField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>(
              HubCollaborationCreateRequestV2025R0HubTypeField.HUBS);
    }

    public Builder type(HubCollaborationCreateRequestV2025R0HubTypeField type) {
      this.type = new EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubCollaborationCreateRequestV2025R0HubTypeField> type) {
      this.type = type;
      return this;
    }

    public HubCollaborationCreateRequestV2025R0HubField build() {
      return new HubCollaborationCreateRequestV2025R0HubField(this);
    }
  }
}
