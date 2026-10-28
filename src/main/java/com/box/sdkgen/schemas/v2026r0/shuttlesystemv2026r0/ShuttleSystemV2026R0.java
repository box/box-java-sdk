package com.box.sdkgen.schemas.v2026r0.shuttlesystemv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A standard representation of a shuttle system. A shuttle system describes a source or destination
 * that Shuttle can migrate content to or from, along with its current connection state.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttleSystemV2026R0 extends SerializableObject {

  /** The unique identifier for the shuttle system. */
  protected final String id;

  /** The value will always be `shuttle_system`. */
  @JsonDeserialize(
      using = ShuttleSystemV2026R0TypeField.ShuttleSystemV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleSystemV2026R0TypeField.ShuttleSystemV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttleSystemV2026R0TypeField> type;

  /** The display name of the shuttle system. */
  protected final String name;

  /**
   * Identifies the kind of source or destination this system represents, for example `box`,
   * `windows`, `sharepoint`, or `amazon_s3`. New system types are added over time, so clients must
   * tolerate values they do not recognize rather than treating this as a fixed set.
   */
  @JsonProperty("system_type")
  protected final String systemType;

  /** A server-computed display label for the system. */
  protected String label;

  /** The current connection state of the shuttle system. */
  @JsonDeserialize(
      using = ShuttleSystemV2026R0StateField.ShuttleSystemV2026R0StateFieldDeserializer.class)
  @JsonSerialize(
      using = ShuttleSystemV2026R0StateField.ShuttleSystemV2026R0StateFieldSerializer.class)
  protected final EnumWrapper<ShuttleSystemV2026R0StateField> state;

  public ShuttleSystemV2026R0(
      String id, String name, String systemType, ShuttleSystemV2026R0StateField state) {
    super();
    this.id = id;
    this.name = name;
    this.systemType = systemType;
    this.state = new EnumWrapper<ShuttleSystemV2026R0StateField>(state);
    this.type =
        new EnumWrapper<ShuttleSystemV2026R0TypeField>(
            ShuttleSystemV2026R0TypeField.SHUTTLE_SYSTEM);
  }

  public ShuttleSystemV2026R0(
      @JsonProperty("id") String id,
      @JsonProperty("name") String name,
      @JsonProperty("system_type") String systemType,
      @JsonProperty("state") EnumWrapper<ShuttleSystemV2026R0StateField> state) {
    super();
    this.id = id;
    this.name = name;
    this.systemType = systemType;
    this.state = state;
    this.type =
        new EnumWrapper<ShuttleSystemV2026R0TypeField>(
            ShuttleSystemV2026R0TypeField.SHUTTLE_SYSTEM);
  }

  protected ShuttleSystemV2026R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.systemType = builder.systemType;
    this.label = builder.label;
    this.state = builder.state;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ShuttleSystemV2026R0TypeField> getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getSystemType() {
    return systemType;
  }

  public String getLabel() {
    return label;
  }

  public EnumWrapper<ShuttleSystemV2026R0StateField> getState() {
    return state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttleSystemV2026R0 casted = (ShuttleSystemV2026R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(systemType, casted.systemType)
        && Objects.equals(label, casted.label)
        && Objects.equals(state, casted.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, systemType, label, state);
  }

  @Override
  public String toString() {
    return "ShuttleSystemV2026R0{"
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
        + ", "
        + "systemType='"
        + systemType
        + '\''
        + ", "
        + "label='"
        + label
        + '\''
        + ", "
        + "state='"
        + state
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<ShuttleSystemV2026R0TypeField> type;

    protected final String name;

    protected final String systemType;

    protected String label;

    protected final EnumWrapper<ShuttleSystemV2026R0StateField> state;

    public Builder(
        String id, String name, String systemType, ShuttleSystemV2026R0StateField state) {
      super();
      this.id = id;
      this.name = name;
      this.systemType = systemType;
      this.state = new EnumWrapper<ShuttleSystemV2026R0StateField>(state);
    }

    public Builder(
        String id,
        String name,
        String systemType,
        EnumWrapper<ShuttleSystemV2026R0StateField> state) {
      super();
      this.id = id;
      this.name = name;
      this.systemType = systemType;
      this.state = state;
    }

    public Builder type(ShuttleSystemV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttleSystemV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttleSystemV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder label(String label) {
      this.label = label;
      return this;
    }

    public ShuttleSystemV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttleSystemV2026R0TypeField>(
                ShuttleSystemV2026R0TypeField.SHUTTLE_SYSTEM);
      }
      return new ShuttleSystemV2026R0(this);
    }
  }
}
