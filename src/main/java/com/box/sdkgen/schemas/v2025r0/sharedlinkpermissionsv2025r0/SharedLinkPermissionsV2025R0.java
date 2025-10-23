package com.box.sdkgen.schemas.v2025r0.sharedlinkpermissionsv2025r0;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The shared link permissions for the enterprise. */
@JsonFilter("nullablePropertyFilter")
public class SharedLinkPermissionsV2025R0 extends SerializableObject {

  /** The selected option for shared links permissions. */
  @JsonProperty("shared_links_option")
  @Nullable
  protected String sharedLinksOption;

  /** The default shared link type. */
  @JsonProperty("default_shared_link_type")
  @Nullable
  protected String defaultSharedLinkType;

  /** The selected option for notes shared links permissions. */
  @JsonProperty("notes_shared_link_option")
  @Nullable
  protected String notesSharedLinkOption;

  /** The default notes shared link type. */
  @JsonProperty("default_notes_shared_link_type")
  @Nullable
  protected String defaultNotesSharedLinkType;

  public SharedLinkPermissionsV2025R0() {
    super();
  }

  protected SharedLinkPermissionsV2025R0(Builder builder) {
    super();
    this.sharedLinksOption = builder.sharedLinksOption;
    this.defaultSharedLinkType = builder.defaultSharedLinkType;
    this.notesSharedLinkOption = builder.notesSharedLinkOption;
    this.defaultNotesSharedLinkType = builder.defaultNotesSharedLinkType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getSharedLinksOption() {
    return sharedLinksOption;
  }

  public String getDefaultSharedLinkType() {
    return defaultSharedLinkType;
  }

  public String getNotesSharedLinkOption() {
    return notesSharedLinkOption;
  }

  public String getDefaultNotesSharedLinkType() {
    return defaultNotesSharedLinkType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SharedLinkPermissionsV2025R0 casted = (SharedLinkPermissionsV2025R0) o;
    return Objects.equals(sharedLinksOption, casted.sharedLinksOption)
        && Objects.equals(defaultSharedLinkType, casted.defaultSharedLinkType)
        && Objects.equals(notesSharedLinkOption, casted.notesSharedLinkOption)
        && Objects.equals(defaultNotesSharedLinkType, casted.defaultNotesSharedLinkType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        sharedLinksOption,
        defaultSharedLinkType,
        notesSharedLinkOption,
        defaultNotesSharedLinkType);
  }

  @Override
  public String toString() {
    return "SharedLinkPermissionsV2025R0{"
        + "sharedLinksOption='"
        + sharedLinksOption
        + '\''
        + ", "
        + "defaultSharedLinkType='"
        + defaultSharedLinkType
        + '\''
        + ", "
        + "notesSharedLinkOption='"
        + notesSharedLinkOption
        + '\''
        + ", "
        + "defaultNotesSharedLinkType='"
        + defaultNotesSharedLinkType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String sharedLinksOption;

    protected String defaultSharedLinkType;

    protected String notesSharedLinkOption;

    protected String defaultNotesSharedLinkType;

    public Builder sharedLinksOption(String sharedLinksOption) {
      this.sharedLinksOption = sharedLinksOption;
      this.markNullableFieldAsSet("shared_links_option");
      return this;
    }

    public Builder defaultSharedLinkType(String defaultSharedLinkType) {
      this.defaultSharedLinkType = defaultSharedLinkType;
      this.markNullableFieldAsSet("default_shared_link_type");
      return this;
    }

    public Builder notesSharedLinkOption(String notesSharedLinkOption) {
      this.notesSharedLinkOption = notesSharedLinkOption;
      this.markNullableFieldAsSet("notes_shared_link_option");
      return this;
    }

    public Builder defaultNotesSharedLinkType(String defaultNotesSharedLinkType) {
      this.defaultNotesSharedLinkType = defaultNotesSharedLinkType;
      this.markNullableFieldAsSet("default_notes_shared_link_type");
      return this;
    }

    public SharedLinkPermissionsV2025R0 build() {
      return new SharedLinkPermissionsV2025R0(this);
    }
  }
}
