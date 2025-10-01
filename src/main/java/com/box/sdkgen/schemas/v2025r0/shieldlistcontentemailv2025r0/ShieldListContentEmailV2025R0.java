package com.box.sdkgen.schemas.v2025r0.shieldlistcontentemailv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Representation of content of a Shield List that contains email addresses data. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListContentEmailV2025R0 extends SerializableObject {

  /** The type of content in the shield list. */
  @JsonDeserialize(
      using =
          ShieldListContentEmailV2025R0TypeField.ShieldListContentEmailV2025R0TypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          ShieldListContentEmailV2025R0TypeField.ShieldListContentEmailV2025R0TypeFieldSerializer
              .class)
  protected EnumWrapper<ShieldListContentEmailV2025R0TypeField> type;

  /** List of emails. */
  @JsonProperty("email_addresses")
  protected final List<String> emailAddresses;

  public ShieldListContentEmailV2025R0(
      @JsonProperty("email_addresses") List<String> emailAddresses) {
    super();
    this.emailAddresses = emailAddresses;
    this.type =
        new EnumWrapper<ShieldListContentEmailV2025R0TypeField>(
            ShieldListContentEmailV2025R0TypeField.EMAIL);
  }

  protected ShieldListContentEmailV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.emailAddresses = builder.emailAddresses;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldListContentEmailV2025R0TypeField> getType() {
    return type;
  }

  public List<String> getEmailAddresses() {
    return emailAddresses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentEmailV2025R0 casted = (ShieldListContentEmailV2025R0) o;
    return Objects.equals(type, casted.type)
        && Objects.equals(emailAddresses, casted.emailAddresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, emailAddresses);
  }

  @Override
  public String toString() {
    return "ShieldListContentEmailV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "emailAddresses='"
        + emailAddresses
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldListContentEmailV2025R0TypeField> type;

    protected final List<String> emailAddresses;

    public Builder(List<String> emailAddresses) {
      super();
      this.emailAddresses = emailAddresses;
      this.type =
          new EnumWrapper<ShieldListContentEmailV2025R0TypeField>(
              ShieldListContentEmailV2025R0TypeField.EMAIL);
    }

    public Builder type(ShieldListContentEmailV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListContentEmailV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListContentEmailV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListContentEmailV2025R0 build() {
      return new ShieldListContentEmailV2025R0(this);
    }
  }
}
