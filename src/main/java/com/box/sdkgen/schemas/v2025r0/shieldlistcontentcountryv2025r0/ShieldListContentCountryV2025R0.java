package com.box.sdkgen.schemas.v2025r0.shieldlistcontentcountryv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Representation of content of a Shield List that contains countries data. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListContentCountryV2025R0 extends SerializableObject {

  /** The type of content in the shield list. */
  @JsonDeserialize(
      using =
          ShieldListContentCountryV2025R0TypeField
              .ShieldListContentCountryV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldListContentCountryV2025R0TypeField
              .ShieldListContentCountryV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ShieldListContentCountryV2025R0TypeField> type;

  /** List of country codes values. */
  @JsonProperty("country_codes")
  protected final List<String> countryCodes;

  public ShieldListContentCountryV2025R0(@JsonProperty("country_codes") List<String> countryCodes) {
    super();
    this.countryCodes = countryCodes;
    this.type =
        new EnumWrapper<ShieldListContentCountryV2025R0TypeField>(
            ShieldListContentCountryV2025R0TypeField.COUNTRY);
  }

  protected ShieldListContentCountryV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.countryCodes = builder.countryCodes;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldListContentCountryV2025R0TypeField> getType() {
    return type;
  }

  public List<String> getCountryCodes() {
    return countryCodes;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentCountryV2025R0 casted = (ShieldListContentCountryV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(countryCodes, casted.countryCodes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, countryCodes);
  }

  @Override
  public String toString() {
    return "ShieldListContentCountryV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "countryCodes='"
        + countryCodes
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldListContentCountryV2025R0TypeField> type;

    protected final List<String> countryCodes;

    public Builder(List<String> countryCodes) {
      super();
      this.countryCodes = countryCodes;
      this.type =
          new EnumWrapper<ShieldListContentCountryV2025R0TypeField>(
              ShieldListContentCountryV2025R0TypeField.COUNTRY);
    }

    public Builder type(ShieldListContentCountryV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListContentCountryV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListContentCountryV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListContentCountryV2025R0 build() {
      return new ShieldListContentCountryV2025R0(this);
    }
  }
}
