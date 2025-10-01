package com.box.sdkgen.schemas.v2025r0.shieldlistcontentdomainv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

/** Representation of content of a Shield List that contains domains data. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListContentDomainV2025R0 extends SerializableObject {

  /** The type of content in the shield list. */
  @JsonDeserialize(
      using =
          ShieldListContentDomainV2025R0TypeField
              .ShieldListContentDomainV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShieldListContentDomainV2025R0TypeField.ShieldListContentDomainV2025R0TypeFieldSerializer
              .class)
  protected EnumWrapper<ShieldListContentDomainV2025R0TypeField> type;

  /** List of domain. */
  protected final List<String> domains;

  public ShieldListContentDomainV2025R0(@JsonProperty("domains") List<String> domains) {
    super();
    this.domains = domains;
    this.type =
        new EnumWrapper<ShieldListContentDomainV2025R0TypeField>(
            ShieldListContentDomainV2025R0TypeField.DOMAIN);
  }

  protected ShieldListContentDomainV2025R0(Builder builder) {
    super();
    this.type = builder.type;
    this.domains = builder.domains;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShieldListContentDomainV2025R0TypeField> getType() {
    return type;
  }

  public List<String> getDomains() {
    return domains;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListContentDomainV2025R0 casted = (ShieldListContentDomainV2025R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(domains, casted.domains);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, domains);
  }

  @Override
  public String toString() {
    return "ShieldListContentDomainV2025R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "domains='"
        + domains
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShieldListContentDomainV2025R0TypeField> type;

    protected final List<String> domains;

    public Builder(List<String> domains) {
      super();
      this.domains = domains;
      this.type =
          new EnumWrapper<ShieldListContentDomainV2025R0TypeField>(
              ShieldListContentDomainV2025R0TypeField.DOMAIN);
    }

    public Builder type(ShieldListContentDomainV2025R0TypeField type) {
      this.type = new EnumWrapper<ShieldListContentDomainV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShieldListContentDomainV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    public ShieldListContentDomainV2025R0 build() {
      return new ShieldListContentDomainV2025R0(this);
    }
  }
}
