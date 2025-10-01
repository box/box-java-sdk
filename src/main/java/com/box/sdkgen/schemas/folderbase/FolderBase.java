package com.box.sdkgen.schemas.folderbase;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * The bare basic representation of a folder, the minimal amount of fields returned when using the
 * `fields` query parameter.
 */
@JsonFilter("nullablePropertyFilter")
public class FolderBase extends SerializableObject {

  /**
   * The unique identifier that represent a folder.
   *
   * <p>The ID for any folder can be determined by visiting a folder in the web application and
   * copying the ID from the URL. For example, for the URL `https://*.app.box.com/folders/123` the
   * `folder_id` is `123`.
   */
  protected final String id;

  /**
   * The HTTP `etag` of this folder. This can be used within some API endpoints in the `If-Match`
   * and `If-None-Match` headers to only perform changes on the folder if (no) changes have
   * happened.
   */
  @Nullable protected String etag;

  /** The value will always be `folder`. */
  @JsonDeserialize(using = FolderBaseTypeField.FolderBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = FolderBaseTypeField.FolderBaseTypeFieldSerializer.class)
  protected EnumWrapper<FolderBaseTypeField> type;

  public FolderBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<FolderBaseTypeField>(FolderBaseTypeField.FOLDER);
  }

  protected FolderBase(Builder builder) {
    super();
    this.id = builder.id;
    this.etag = builder.etag;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getEtag() {
    return etag;
  }

  public EnumWrapper<FolderBaseTypeField> getType() {
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
    FolderBase casted = (FolderBase) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, etag, type);
  }

  @Override
  public String toString() {
    return "FolderBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected String etag;

    protected EnumWrapper<FolderBaseTypeField> type;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<FolderBaseTypeField>(FolderBaseTypeField.FOLDER);
    }

    public Builder etag(String etag) {
      this.etag = etag;
      this.markNullableFieldAsSet("etag");
      return this;
    }

    public Builder type(FolderBaseTypeField type) {
      this.type = new EnumWrapper<FolderBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<FolderBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public FolderBase build() {
      return new FolderBase(this);
    }
  }
}
