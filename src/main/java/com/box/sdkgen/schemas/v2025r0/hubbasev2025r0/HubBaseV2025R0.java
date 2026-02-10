package com.box.sdkgen.schemas.v2025r0.hubbasev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The bare basic representation of a Box Hub. */
@JsonFilter("nullablePropertyFilter")
public class HubBaseV2025R0 extends SerializableObject {

  /**
   * The unique identifier that represent a Box Hub.
   *
   * <p>The ID for any Box Hub can be determined by visiting a Box Hub in the web application and
   * copying the ID from the URL. For example, for the URL `https://*.app.box.com/hubs/123` the
   * `hub_id` is `123`.
   */
  protected final String id;

  /** The value will always be `hubs`. */
  @JsonDeserialize(using = HubBaseV2025R0TypeField.HubBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(using = HubBaseV2025R0TypeField.HubBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<HubBaseV2025R0TypeField> type;

  public HubBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<HubBaseV2025R0TypeField>(HubBaseV2025R0TypeField.HUBS);
  }

  protected HubBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<HubBaseV2025R0TypeField> getType() {
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
    HubBaseV2025R0 casted = (HubBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "HubBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<HubBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(HubBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<HubBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public HubBaseV2025R0 build() {
      if (this.type == null) {
        this.type = new EnumWrapper<HubBaseV2025R0TypeField>(HubBaseV2025R0TypeField.HUBS);
      }
      return new HubBaseV2025R0(this);
    }
  }
}
