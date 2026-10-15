package com.box.sdkgen.managers.chunkeduploads;

import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.uploadpart.UploadPart;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateFileUploadSessionCommitByUrlRequestBody extends SerializableObject {

  /**
   * The list details for the uploaded parts.
   *
   * <p>Each entry can include `sha1` or `sha512`. Include `sha512` on every entry or on none of
   * them. A request where only some entries include `sha512` is rejected.
   *
   * <p>Include parts that the server already has, returned as `hits` by the [`Plan upload
   * session`](https://developer.box.com/reference/post-files-upload-sessions-id-plan) endpoint. Use
   * the `part_id`, `offset`, and `size` from the plan response. If the other entries include
   * `sha512`, include the `sha512` value from the plan response. Otherwise, leave the hash out of
   * these entries.
   */
  protected final List<UploadPart> parts;

  public CreateFileUploadSessionCommitByUrlRequestBody(
      @JsonProperty("parts") List<UploadPart> parts) {
    super();
    this.parts = parts;
  }

  public List<UploadPart> getParts() {
    return parts;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateFileUploadSessionCommitByUrlRequestBody casted =
        (CreateFileUploadSessionCommitByUrlRequestBody) o;
    return Objects.equals(parts, casted.parts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parts);
  }

  @Override
  public String toString() {
    return "CreateFileUploadSessionCommitByUrlRequestBody{" + "parts='" + parts + '\'' + "}";
  }
}
