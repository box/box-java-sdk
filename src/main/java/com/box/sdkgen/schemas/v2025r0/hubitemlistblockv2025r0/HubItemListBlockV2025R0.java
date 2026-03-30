package com.box.sdkgen.schemas.v2025r0.hubitemlistblockv2025r0;

import com.box.sdkgen.schemas.v2025r0.hubdocumentblockv2025r0.HubDocumentBlockV2025R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** An item list block in the Box Hub Document. */
@JsonFilter("nullablePropertyFilter")
public class HubItemListBlockV2025R0 extends HubDocumentBlockV2025R0 {

  /** The type of this block. The value is always `item_list`. */
  @JsonDeserialize(
      using = HubItemListBlockV2025R0TypeField.HubItemListBlockV2025R0TypeFieldDeserializer.class)
  @JsonSerialize(
      using = HubItemListBlockV2025R0TypeField.HubItemListBlockV2025R0TypeFieldSerializer.class)
  protected EnumWrapper<HubItemListBlockV2025R0TypeField> type;

  public HubItemListBlockV2025R0(@JsonProperty("id") String id) {
    super(id);
    this.type =
        new EnumWrapper<HubItemListBlockV2025R0TypeField>(
            HubItemListBlockV2025R0TypeField.ITEM_LIST);
  }

  protected HubItemListBlockV2025R0(Builder builder) {
    super(builder);
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<HubItemListBlockV2025R0TypeField> getType() {
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
    HubItemListBlockV2025R0 casted = (HubItemListBlockV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(parentId, casted.parentId)
        && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, parentId, type);
  }

  @Override
  public String toString() {
    return "HubItemListBlockV2025R0{"
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
        + "}";
  }

  public static class Builder extends HubDocumentBlockV2025R0.Builder {

    protected EnumWrapper<HubItemListBlockV2025R0TypeField> type;

    public Builder(String id) {
      super(id);
    }

    public Builder type(HubItemListBlockV2025R0TypeField type) {
      this.type = new EnumWrapper<HubItemListBlockV2025R0TypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<HubItemListBlockV2025R0TypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder parentId(String parentId) {
      this.parentId = parentId;
      this.markNullableFieldAsSet("parent_id");
      return this;
    }

    public HubItemListBlockV2025R0 build() {
      if (this.type == null) {
        this.type =
            new EnumWrapper<HubItemListBlockV2025R0TypeField>(
                HubItemListBlockV2025R0TypeField.ITEM_LIST);
      }
      return new HubItemListBlockV2025R0(this);
    }
  }
}
