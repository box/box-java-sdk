package com.box.sdkgen.schemas.v2026r0.notesconvertrequestbodyv2026r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2026r0.folderreferencev2026r0.FolderReferenceV2026R0;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/** Request body for converting source content into a Box Note file. */
@JsonFilter("nullablePropertyFilter")
public class NotesConvertRequestBodyV2026R0 extends SerializableObject {

  /** The content to convert to a note. See the `content_format` field for supported formats. */
  protected final String content;

  /** Format of the content to convert. */
  @JsonDeserialize(
      using =
          NotesConvertRequestBodyV2026R0ContentFormatField
              .NotesConvertRequestBodyV2026R0ContentFormatFieldDeserializer.class)
  @JsonSerialize(
      using =
          NotesConvertRequestBodyV2026R0ContentFormatField
              .NotesConvertRequestBodyV2026R0ContentFormatFieldSerializer.class)
  @JsonProperty("content_format")
  protected EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> contentFormat;

  protected final FolderReferenceV2026R0 parent;

  /** The name for the created note. The `.boxnote` extension is appended automatically. */
  protected final String name;

  public NotesConvertRequestBodyV2026R0(
      @JsonProperty("content") String content,
      @JsonProperty("parent") FolderReferenceV2026R0 parent,
      @JsonProperty("name") String name) {
    super();
    this.content = content;
    this.parent = parent;
    this.name = name;
    this.contentFormat =
        new EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>(
            NotesConvertRequestBodyV2026R0ContentFormatField.MARKDOWN);
  }

  protected NotesConvertRequestBodyV2026R0(Builder builder) {
    super();
    this.content = builder.content;
    this.contentFormat = builder.contentFormat;
    this.parent = builder.parent;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getContent() {
    return content;
  }

  public EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> getContentFormat() {
    return contentFormat;
  }

  public FolderReferenceV2026R0 getParent() {
    return parent;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    NotesConvertRequestBodyV2026R0 casted = (NotesConvertRequestBodyV2026R0) o;
    return Objects.equals(content, casted.content)
        && Objects.equals(contentFormat, casted.contentFormat)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(content, contentFormat, parent, name);
  }

  @Override
  public String toString() {
    return "NotesConvertRequestBodyV2026R0{"
        + "content='"
        + content
        + '\''
        + ", "
        + "contentFormat='"
        + contentFormat
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String content;

    protected EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> contentFormat;

    protected final FolderReferenceV2026R0 parent;

    protected final String name;

    public Builder(String content, FolderReferenceV2026R0 parent, String name) {
      super();
      this.content = content;
      this.parent = parent;
      this.name = name;
    }

    public Builder contentFormat(NotesConvertRequestBodyV2026R0ContentFormatField contentFormat) {
      this.contentFormat =
          new EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>(contentFormat);
      return this;
    }

    public Builder contentFormat(
        EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField> contentFormat) {
      this.contentFormat = contentFormat;
      return this;
    }

    public NotesConvertRequestBodyV2026R0 build() {
      if (this.contentFormat == null) {
        this.contentFormat =
            new EnumWrapper<NotesConvertRequestBodyV2026R0ContentFormatField>(
                NotesConvertRequestBodyV2026R0ContentFormatField.MARKDOWN);
      }
      return new NotesConvertRequestBodyV2026R0(this);
    }
  }
}
