package com.box.sdkgen.managers.transfer;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class TransferOwnedFolderRequestBodyOwnedByField extends SerializableObject {

  /** The ID of the user who the folder will be transferred to. */
  protected final String id;

  public TransferOwnedFolderRequestBodyOwnedByField(@JsonProperty("id") String id) {
    super();
    this.id = id;
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
    TransferOwnedFolderRequestBodyOwnedByField casted =
        (TransferOwnedFolderRequestBodyOwnedByField) o;
    return Objects.equals(id, casted.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "TransferOwnedFolderRequestBodyOwnedByField{" + "id='" + id + '\'' + "}";
  }
}
