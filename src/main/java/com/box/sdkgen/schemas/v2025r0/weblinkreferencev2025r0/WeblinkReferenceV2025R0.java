package com.box.sdkgen.schemas.v2025r0.weblinkreferencev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Web link reference. */
@JsonFilter("nullablePropertyFilter")
public class WeblinkReferenceV2025R0 extends SerializableObject {

  /** The value will always be `web_link`. */
  @JsonDeserialize(
      using = WeblinkReferenceV2025R0TypeField.WeblinkReferenceV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = WeblinkReferenceV2025R0TypeField.WeblinkReferenceV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<WeblinkReferenceV2025R0TypeField> type;

  /** ID of the web link. */
  protected final String id;

  public WeblinkReferenceV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<WeblinkReferenceV2025R0TypeField>(
            WeblinkReferenceV2025R0TypeField.WEB_LINK);
  }

  protected WeblinkReferenceV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<WeblinkReferenceV2025R0TypeField> getType() {
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
    WeblinkReferenceV2025R0 casted = (WeblinkReferenceV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "WeblinkReferenceV2025R0{" + "type='" + type + '\'' + ", " + "id='" + id + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<WeblinkReferenceV2025R0TypeField> type;

    protected final String id;

    public Builder(String id) {
      super();
      this.id = id;
    }

    public Builder type(WeblinkReferenceV2025R0TypeField type) {
      this.type = new EnumWrapper<WeblinkReferenceV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WeblinkReferenceV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public WeblinkReferenceV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<WeblinkReferenceV2025R0TypeField>(
                WeblinkReferenceV2025R0TypeField.WEB_LINK);
      }
      return new WeblinkReferenceV2025R0(this);
    }
  }
}
