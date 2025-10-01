package com.box.sdkgen.schemas.zipdownload;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ZipDownloadNameConflictsField extends SerializableObject {

  /** The identifier of the item. */
  protected String id;

  /** The type of this item. */
  @JsonDeserialize(
      using = ZipDownloadNameConflictsTypeField.ZipDownloadNameConflictsTypeFieldDeserializer.class)
  @JsonSerialize(
      using = ZipDownloadNameConflictsTypeField.ZipDownloadNameConflictsTypeFieldSerializer.class)
  protected EnumWrapper<ZipDownloadNameConflictsTypeField> type;

  /** Box Developer Documentation. */
  @JsonProperty("original_name")
  protected String originalName;

  /** The new name of this item as it will appear in the downloaded `zip` archive. */
  @JsonProperty("download_name")
  protected String downloadName;

  public ZipDownloadNameConflictsField() {
    super();
  }

  protected ZipDownloadNameConflictsField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.originalName = builder.originalName;
    this.downloadName = builder.downloadName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<ZipDownloadNameConflictsTypeField> getType() {
    return type;
  }

  public String getOriginalName() {
    return originalName;
  }

  public String getDownloadName() {
    return downloadName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ZipDownloadNameConflictsField casted = (ZipDownloadNameConflictsField) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(originalName, casted.originalName)
        && Objects.equals(downloadName, casted.downloadName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, originalName, downloadName);
  }

  @Override
  public String toString() {
    return "ZipDownloadNameConflictsField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "originalName='"
        + originalName
        + '\''
        + ", "
        + "downloadName='"
        + downloadName
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<ZipDownloadNameConflictsTypeField> type;

    protected String originalName;

    protected String downloadName;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(ZipDownloadNameConflictsTypeField type) {
      this.type = new EnumWrapper<ZipDownloadNameConflictsTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<ZipDownloadNameConflictsTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder originalName(String originalName) {
      this.originalName = originalName;
      return this;
    }

    public Builder downloadName(String downloadName) {
      this.downloadName = downloadName;
      return this;
    }

    public ZipDownloadNameConflictsField build() {
      return new ZipDownloadNameConflictsField(this);
    }
  }
}
