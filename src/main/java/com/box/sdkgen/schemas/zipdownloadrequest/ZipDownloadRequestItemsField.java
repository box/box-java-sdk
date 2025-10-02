package com.box.sdkgen.schemas.zipdownloadrequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ZipDownloadRequestItemsField extends SerializableObject {

  /** The type of the item to add to the archive. */
  @JsonDeserialize(
      using = ZipDownloadRequestItemsTypeField.ZipDownloadRequestItemsTypeFieldDeserializer.class)
  @JsonSerialize(
      using = ZipDownloadRequestItemsTypeField.ZipDownloadRequestItemsTypeFieldSerializer.class)
  protected final EnumWrapper<ZipDownloadRequestItemsTypeField> type;

  /**
   * The identifier of the item to add to the archive. When this item is a folder then this can not
   * be the root folder with ID `0`.
   */
  protected final String id;

  public ZipDownloadRequestItemsField(ZipDownloadRequestItemsTypeField type, String id) {
    super();
    this.type = new EnumWrapper<ZipDownloadRequestItemsTypeField>(type);
    this.id = id;
  }

  public ZipDownloadRequestItemsField(
      @JsonProperty("type") EnumWrapper<ZipDownloadRequestItemsTypeField> type,
      @JsonProperty("id") String id) {
    super();
    this.type = type;
    this.id = id;
  }

  public EnumWrapper<ZipDownloadRequestItemsTypeField> getType() {
    return type;
  }

  public String getId() {
    return id;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZipDownloadRequestItemsField casted = (ZipDownloadRequestItemsField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "ZipDownloadRequestItemsField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }
}
