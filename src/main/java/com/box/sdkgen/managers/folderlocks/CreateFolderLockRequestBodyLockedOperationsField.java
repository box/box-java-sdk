package com.box.sdkgen.managers.folderlocks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFolderLockRequestBodyLockedOperationsField extends SerializableObject {

  /** Whether moving the folder should be locked. */
  protected final boolean move;

  /** Whether deleting the folder should be locked. */
  protected final boolean delete;

  public CreateFolderLockRequestBodyLockedOperationsField(
      @JsonProperty("move") boolean move, @JsonProperty("delete") boolean delete) {
    super();
    this.move = move;
    this.delete = delete;
  }

  public boolean getMove() {
    return move;
  }

  public boolean getDelete() {
    return delete;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFolderLockRequestBodyLockedOperationsField casted =
        (CreateFolderLockRequestBodyLockedOperationsField) o;
    return Objects.equals(move, casted.move) && Objects.equals(delete, casted.delete);
  }

  @Override
  public int hashCode() {
    return Objects.hash(move, delete);
  }

  @Override
  public String toString() {
    return "CreateFolderLockRequestBodyLockedOperationsField{"
        + "move='"
        + move
        + '\''
        + ", "
        + "delete='"
        + delete
        + '\''
        + "}";
  }
}
