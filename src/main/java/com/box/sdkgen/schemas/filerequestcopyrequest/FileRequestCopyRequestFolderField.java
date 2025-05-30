package com.box.sdkgen.schemas.filerequestcopyrequest;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

public class FileRequestCopyRequestFolderField extends SerializableObject {

  @JsonDeserialize(
      using =
          FileRequestCopyRequestFolderTypeField.FileRequestCopyRequestFolderTypeFieldDeserializer
              .class)
  @JsonSerialize(
      using =
          FileRequestCopyRequestFolderTypeField.FileRequestCopyRequestFolderTypeFieldSerializer
              .class)
  protected EnumWrapper<FileRequestCopyRequestFolderTypeField> type;

  protected final String id;

  public FileRequestCopyRequestFolderField(@JsonProperty("id") String id) {
    super();
    this.id = id;
  }

  protected FileRequestCopyRequestFolderField(FileRequestCopyRequestFolderFieldBuilder builder) {
    super();
    this.type = builder.type;
    this.id = builder.id;
  }

  public EnumWrapper<FileRequestCopyRequestFolderTypeField> getType() {
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
    FileRequestCopyRequestFolderField casted = (FileRequestCopyRequestFolderField) o;
    return Objects.equals(type, casted.type) && Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id);
  }

  @Override
  public String toString() {
    return "FileRequestCopyRequestFolderField{"
        + "type='"
        + type
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + "}";
  }

  public static class FileRequestCopyRequestFolderFieldBuilder {

    protected EnumWrapper<FileRequestCopyRequestFolderTypeField> type;

    protected final String id;

    public FileRequestCopyRequestFolderFieldBuilder(String id) {
      this.id = id;
    }

    public FileRequestCopyRequestFolderFieldBuilder type(
        FileRequestCopyRequestFolderTypeField type) {
      this.type = new EnumWrapper<FileRequestCopyRequestFolderTypeField>(type);
      return this;
    }

    public FileRequestCopyRequestFolderFieldBuilder type(
        EnumWrapper<FileRequestCopyRequestFolderTypeField> type) {
      this.type = type;
      return this;
    }

    public FileRequestCopyRequestFolderField build() {
      return new FileRequestCopyRequestFolderField(this);
    }
  }
}
