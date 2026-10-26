package com.box.sdkgen.schemas.v2026r0.shuttlepathentrysharedspacescopev2026r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The mapping scope of a path entry that lives under a shared container such as a site, team drive,
 * bucket, or file share. `type` is the discriminator. Copy `space` onto a shared-space path mapping
 * location when creating a mapping from this row.
 */
@JsonFilter("nullablePropertyFilter")
public class ShuttlePathEntrySharedSpaceScopeV2026R0 extends SerializableObject {

  /** The value will always be `shared_space`. */
  @JsonDeserialize(
      using =
          ShuttlePathEntrySharedSpaceScopeV2026R0TypeField
              .ShuttlePathEntrySharedSpaceScopeV2026R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          ShuttlePathEntrySharedSpaceScopeV2026R0TypeField
              .ShuttlePathEntrySharedSpaceScopeV2026R0TypeFieldSerializer.class)
  protected EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField> type;

  /**
   * The display name of the shared container this entry belongs to. `null` when the system has a
   * single implicit container. Maps to `space` on a shared-space path mapping location.
   */
  @Nullable protected String space;

  public ShuttlePathEntrySharedSpaceScopeV2026R0() {
    super();
    this.type =
        new EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField>(
            ShuttlePathEntrySharedSpaceScopeV2026R0TypeField.SHARED_SPACE);
  }

  protected ShuttlePathEntrySharedSpaceScopeV2026R0(Builder builder) {
    super();
    this.type = builder.type;
    this.space = builder.space;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField> getType() {
    return type;
  }

  public String getSpace() {
    return space;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShuttlePathEntrySharedSpaceScopeV2026R0 casted = (ShuttlePathEntrySharedSpaceScopeV2026R0) o;
    return Objects.equals(type, casted.type) && Objects.equals(space, casted.space);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, space);
  }

  @Override
  public String toString() {
    return "ShuttlePathEntrySharedSpaceScopeV2026R0{"
        + "type='"
        + type
        + '\''
        + ", "
        + "space='"
        + space
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField> type;

    protected String space;

    public Builder() {
      super();
    }

    public Builder type(ShuttlePathEntrySharedSpaceScopeV2026R0TypeField type) {
      this.type = new EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField> type) {
      this.type = type;
      return this;
    }

    public Builder space(String space) {
      this.space = space;
      this.markNullableFieldAsSet("space");
      return this;
    }

    public ShuttlePathEntrySharedSpaceScopeV2026R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<ShuttlePathEntrySharedSpaceScopeV2026R0TypeField>(
                ShuttlePathEntrySharedSpaceScopeV2026R0TypeField.SHARED_SPACE);
      }
      return new ShuttlePathEntrySharedSpaceScopeV2026R0(this);
    }
  }
}
