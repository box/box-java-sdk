package com.box.sdkgen.schemas.fileorfolderscope;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.fileminiorfoldermini.FileMiniOrFolderMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileOrFolderScope extends SerializableObject {

  @JsonDeserialize(
      using = FileOrFolderScopeScopeField.FileOrFolderScopeScopeFieldDeserializer.class)
  @JsonSerialize(using = FileOrFolderScopeScopeField.FileOrFolderScopeScopeFieldSerializer.class)
  protected EnumWrapper<FileOrFolderScopeScopeField> scope;

  protected FileMiniOrFolderMini object;

  public FileOrFolderScope() {
    super();
  }

  protected FileOrFolderScope(Builder builder) {
    super();
    this.scope = builder.scope;
    this.object = builder.object;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<FileOrFolderScopeScopeField> getScope() {
    return scope;
  }

  public FileMiniOrFolderMini getObject() {
    return object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileOrFolderScope casted = (FileOrFolderScope) o;
    return Objects.equals(scope, casted.scope) && Objects.equals(object, casted.object);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, object);
  }

  @Override
  public String toString() {
    return "FileOrFolderScope{"
        + "scope='"
        + scope
        + '\''
        + ", "
        + "object='"
        + object
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<FileOrFolderScopeScopeField> scope;

    protected FileMiniOrFolderMini object;

    public Builder scope(FileOrFolderScopeScopeField scope) {
      this.scope = new EnumWrapper<FileOrFolderScopeScopeField>(scope);
      return this;
    }

    public Builder scope(EnumWrapper<FileOrFolderScopeScopeField> scope) {
      this.scope = scope;
      return this;
    }

    public Builder object(FileMini object) {
      this.object = new FileMiniOrFolderMini(object);
      return this;
    }

    public Builder object(FolderMini object) {
      this.object = new FileMiniOrFolderMini(object);
      return this;
    }

    public Builder object(FileMiniOrFolderMini object) {
      this.object = object;
      return this;
    }

    public FileOrFolderScope build() {
      return new FileOrFolderScope(this);
    }
  }
}
