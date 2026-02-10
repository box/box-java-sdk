package com.box.sdkgen.schemas.v2025r0.shieldlistcontentipv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Representation of content of a Shield List that contains ip addresses data. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListContentIpV2025R0 extends SerializableObject {

  /** The type of content in the shield list. */
  @JsonDeserialize(
      using =
          ShieldListContentIpV2025R0TypeField.ShieldListContentIpV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldListContentIpV2025R0TypeField.ShieldListContentIpV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<ShieldListContentIpV2025R0TypeField> type;

  /** List of ip addresses and CIDRs. */
  @JsonProperty("ip_addresses")
  protected final List<String> ipAddresses;

  public ShieldListContentIpV2025R0(@JsonProperty("ip_addresses") List<String> ipAddresses) {
    super();
    this.ipAddresses = ipAddresses;
    this.type =
        new EnumWrapper<ShieldListContentIpV2025R0TypeField>(
            ShieldListContentIpV2025R0TypeField.IP);
  }

  protected ShieldListContentIpV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.ipAddresses = builder.ipAddresses;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldListContentIpV2025R0TypeField> getType() {
    return type;
  }

  public List<String> getIpAddresses() {
    return ipAddresses;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentIpV2025R0 casted = (ShieldListContentIpV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(ipAddresses, casted.ipAddresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, ipAddresses);
  }

  @Override
  public String toString() {
    return "ShieldListContentIpV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "ipAddresses='"
        + ipAddresses
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldListContentIpV2025R0TypeField> type;

    protected final List<String> ipAddresses;

    public Builder(List<String> ipAddresses) {
      super();
      this.ipAddresses = ipAddresses;
    }

    public Builder type(ShieldListContentIpV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListContentIpV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListContentIpV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListContentIpV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShieldListContentIpV2025R0TypeField>(
                ShieldListContentIpV2025R0TypeField.IP);
      }
      return new ShieldListContentIpV2025R0(this);
    }
  }
}
