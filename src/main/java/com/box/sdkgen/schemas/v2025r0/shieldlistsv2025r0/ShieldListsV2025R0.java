package com.box.sdkgen.schemas.v2025r0.shieldlistsv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.shieldlistminiv2025r0.ShieldListMiniV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldListsV2025R0 extends SerializableObject {

  protected List<ShieldListMiniV2025R0> entries;

  public ShieldListsV2025R0() {
    super();
  }

  protected ShieldListsV2025R0(Builder builder) {
    super();
    this.entries = builder.entries;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public List<ShieldListMiniV2025R0> getEntries() {
    return entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListsV2025R0 casted = (ShieldListsV2025R0) o;
    return Objects.equals(entries, casted.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(entries);
  }

  @Override
  public String toString() {
    return "ShieldListsV2025R0{" + "entries='" + entries + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected List<ShieldListMiniV2025R0> entries;

    public Builder entries(List<ShieldListMiniV2025R0> entries) {
      this.entries = entries;
      return this;
    }

    public ShieldListsV2025R0 build() {
      return new ShieldListsV2025R0(this);
    }
  }
}
