package com.box.sdkgen.schemas.v2025r0.termsofservicebasev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** The root-level record that is supposed to represent a single Terms of Service. */
@JsonFilter("nullablePropertyFilter")
public class TermsOfServiceBaseV2025R0 extends SerializableObject {

  /** The unique identifier for this terms of service. */
  protected final String id;

  /** The value will always be `terms_of_service`. */
  @JsonDeserialize(
      using =
          TermsOfServiceBaseV2025R0TypeField.TermsOfServiceBaseV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = TermsOfServiceBaseV2025R0TypeField.TermsOfServiceBaseV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<TermsOfServiceBaseV2025R0TypeField> type;

  public TermsOfServiceBaseV2025R0(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type =
        new EnumWrapper<TermsOfServiceBaseV2025R0TypeField>(
            TermsOfServiceBaseV2025R0TypeField.TERMS_OF_SERVICE);
  }

  protected TermsOfServiceBaseV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<TermsOfServiceBaseV2025R0TypeField> getType() {
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
    TermsOfServiceBaseV2025R0 casted = (TermsOfServiceBaseV2025R0) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "TermsOfServiceBaseV2025R0{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<TermsOfServiceBaseV2025R0TypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type =
          new EnumWrapper<TermsOfServiceBaseV2025R0TypeField>(
              TermsOfServiceBaseV2025R0TypeField.TERMS_OF_SERVICE);
    }

    public Builder type(TermsOfServiceBaseV2025R0TypeField type) {
      this.type = new EnumWrapper<TermsOfServiceBaseV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<TermsOfServiceBaseV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public TermsOfServiceBaseV2025R0 build() {
      return new TermsOfServiceBaseV2025R0(this);
    }
  }
}
