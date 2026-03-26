package com.box.sdkgen.schemas.v2025r0.hubsectiontitletextblockv2025r0;

import com.box.sdkgen.schemas.v2025r0.hubdocumentblockv2025r0.HubDocumentBlockV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** A section title block in the Box Hub Document. */
@JsonFilter("nullablePropertyFilter")
public class HubSectionTitleTextBlockV2025R0 extends HubDocumentBlockV2025R0 {

  /** The type of this block. The value is always `section_title`. */
  @JsonDeserialize(
      using =
          HubSectionTitleTextBlockV2025R0TypeField
              .HubSectionTitleTextBlockV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          HubSectionTitleTextBlockV2025R0TypeField
              .HubSectionTitleTextBlockV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField> type;

  /** Text content of the block. Includes rich text formatting. */
  protected final String fragment;

  public HubSectionTitleTextBlockV2025R0(
      @JsonProperty("id") String id, @JsonProperty("fragment") String fragment) {
    super(id);
    this.fragment = fragment;
    this.type =
        new EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField>(
            HubSectionTitleTextBlockV2025R0TypeField.SECTION_TITLE);
  }

  protected HubSectionTitleTextBlockV2025R0(Builder builder) {
    super(builder);
    this.type = builder.type;
    this.fragment = builder.fragment;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField> getType() {
    return type;
  }

  public String getFragment() {
    return fragment;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    HubSectionTitleTextBlockV2025R0 casted = (HubSectionTitleTextBlockV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(parentId, casted.parentId)
        && Objects.equals(type, casted.type)
        && Objects.equals(fragment, casted.fragment);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, type, fragment);
  }

  @Override
  public String toString() {
    return "HubSectionTitleTextBlockV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "parentId='"
        + parentId
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "fragment='"
        + fragment
        + '\''
        + "}";
  }

  public static class Builder extends HubDocumentBlockV2025R0.Builder {

    protected EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField> type;

    protected final String fragment;

    public Builder(String id, String fragment) {
      super(id);
      this.fragment = fragment;
    }

    public Builder type(HubSectionTitleTextBlockV2025R0TypeField type) {
      this.type = new EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder parentId(String parentId) {
      this.parentId = parentId;
      this.markNullableFieldAsSet("parent_id");
      return this;
    }

    public HubSectionTitleTextBlockV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<HubSectionTitleTextBlockV2025R0TypeField>(
                HubSectionTitleTextBlockV2025R0TypeField.SECTION_TITLE);
      }
      return new HubSectionTitleTextBlockV2025R0(this);
    }
  }
}
