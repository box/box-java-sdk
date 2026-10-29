package com.box.sdkgen.schemas.v2026r0.shuttlesubjectv2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.shuttlesubjectrolev2026r0.ShuttleSubjectRoleV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.box.sdkgen.serialization.json.Valuable;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** A subject discovered on the source migration system, such as a user or group. */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSubjectV2026R0 extends SerializableObject {

  /** Whether this subject is a user or a group. */
  @JsonDeserialize(
      using = ShuttleSubjectV2026R0TypeField.ShuttleSubjectV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleSubjectV2026R0TypeField.ShuttleSubjectV2026R0TypeFieldSerializer.class)
  protected final EnumWrapper<ShuttleSubjectV2026R0TypeField> type;

  /** The platform-native identifier of the subject. `null` when the system does not provide one. */
  @Nullable protected String id;

  /** The email address of the subject. `null` when unknown. */
  @Nullable protected String email;

  /** The display name of the subject. `null` when unknown. */
  @Nullable protected String name;

  /** Roles this subject holds on the source. `null` when no roles are known. */
  @JsonDeserialize(using = RolesDeserializer.class)
  @JsonSerialize(using = RolesSerializer.class)
  @Nullable
  protected List<EnumWrapper<ShuttleSubjectRoleV2026R0>> roles;

  public ShuttleSubjectV2026R0(ShuttleSubjectV2026R0TypeField type) {
    super();
    this.type = new EnumWrapper<ShuttleSubjectV2026R0TypeField>(type);
  }

  public ShuttleSubjectV2026R0(
      @JsonProperty("type") EnumWrapper<ShuttleSubjectV2026R0TypeField> type) {
    super();
    this.type = type;
  }

  protected ShuttleSubjectV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    this.email = builder.email;
    this.name = builder.name;
    this.roles = builder.roles;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttleSubjectV2026R0TypeField> getType() {
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

  public List<EnumWrapper<ShuttleSubjectRoleV2026R0>> getRoles() {
    return roles;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleSubjectV2026R0 casted = (ShuttleSubjectV2026R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(id, casted.id)
        && Objects.equals(email, casted.email)
        && Objects.equals(name, casted.name)
        && Objects.equals(roles, casted.roles);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, email, name, roles);
  }

  @Override
  public String toString() {
    return "ShuttleSubjectV2026R0{"
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
        + ", "
        + "roles='"
        + roles
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final EnumWrapper<ShuttleSubjectV2026R0TypeField> type;

    protected String id;

    protected String email;

    protected String name;

    protected List<EnumWrapper<ShuttleSubjectRoleV2026R0>> roles;

    public Builder(ShuttleSubjectV2026R0TypeField type) {
      super();
      this.type = new EnumWrapper<ShuttleSubjectV2026R0TypeField>(type);
    }

    public Builder(EnumWrapper<ShuttleSubjectV2026R0TypeField> type) {
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

    public Builder roles(List<? extends Valuable> roles) {
      this.roles = EnumWrapper.wrapValuableEnumList(roles, ShuttleSubjectRoleV2026R0.class);
      this.markNullableFieldAsSet("roles");
      return this;
    }

    public ShuttleSubjectV2026R0 build() {
      return new ShuttleSubjectV2026R0(this);
    }
  }

  public static class RolesDeserializer
      extends JsonDeserializer<List<EnumWrapper<ShuttleSubjectRoleV2026R0>>> {

    public final JsonDeserializer<EnumWrapper<ShuttleSubjectRoleV2026R0>> elementDeserializer;

    public RolesDeserializer() {
      super();
      this.elementDeserializer =
          new ShuttleSubjectRoleV2026R0.ShuttleSubjectRoleV2026R0Deserializer();
    }

    @Override
    public List<EnumWrapper<ShuttleSubjectRoleV2026R0>> deserialize(
        JsonParser p, DeserializationContext ctxt) throws IOException {
      JsonNode node = p.getCodec().readTree(p);
      List<EnumWrapper<ShuttleSubjectRoleV2026R0>> elements = new ArrayList<>();
      for (JsonNode item : node) {
        JsonParser pa = item.traverse(p.getCodec());
        pa.nextToken();
        elements.add(elementDeserializer.deserialize(pa, ctxt));
      }
      return elements;
    }
  }

  public static class RolesSerializer
      extends JsonSerializer<List<EnumWrapper<ShuttleSubjectRoleV2026R0>>> {

    public final JsonSerializer<EnumWrapper<ShuttleSubjectRoleV2026R0>> elementSerializer;

    public RolesSerializer() {
      super();
      this.elementSerializer = new ShuttleSubjectRoleV2026R0.ShuttleSubjectRoleV2026R0Serializer();
    }

    @Override
    public void serialize(
        List<EnumWrapper<ShuttleSubjectRoleV2026R0>> value,
        JsonGenerator gen,
        SerializerProvider serializers)
        throws IOException {
      gen.writeStartArray();
      for (EnumWrapper<ShuttleSubjectRoleV2026R0> item : value) {
        elementSerializer.serialize(item, gen, serializers);
      }
      gen.writeEndArray();
    }
  }
}
