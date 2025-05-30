package com.box.sdkgen.managers.folders;

import com.box.sdkgen.internal.SerializableObject;
import java.util.Objects;

public class UpdateFolderByIdRequestBodyCollectionsField extends SerializableObject {

  protected String id;

  protected String type;

  public UpdateFolderByIdRequestBodyCollectionsField() {
    super();
  }

  protected UpdateFolderByIdRequestBodyCollectionsField(
      UpdateFolderByIdRequestBodyCollectionsFieldBuilder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
  }

  public String getId() {
    return id;
  }

  public String getType() {
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
    UpdateFolderByIdRequestBodyCollectionsField casted =
        (UpdateFolderByIdRequestBodyCollectionsField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "UpdateFolderByIdRequestBodyCollectionsField{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + "}";
  }

  public static class UpdateFolderByIdRequestBodyCollectionsFieldBuilder {

    protected String id;

    protected String type;

    public UpdateFolderByIdRequestBodyCollectionsFieldBuilder id(String id) {
      this.id = id;
      return this;
    }

    public UpdateFolderByIdRequestBodyCollectionsFieldBuilder type(String type) {
      this.type = type;
      return this;
    }

    public UpdateFolderByIdRequestBodyCollectionsField build() {
      return new UpdateFolderByIdRequestBodyCollectionsField(this);
    }
  }
}
