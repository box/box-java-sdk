package com.box.sdkgen.managers.archives;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateArchiveV2025R0RequestBody extends SerializableObject {

  /** The name of the archive. */
  protected final String name;

  /** The description of the archive. */
  protected String description;

  /** The ID of the storage policy that the archive is assigned to. */
  @JsonProperty("storage_policy_id")
  protected String storagePolicyId;

  public CreateArchiveV2025R0RequestBody(@JsonProperty("name") String name) {
    super();
    this.name = name;
  }

  protected CreateArchiveV2025R0RequestBody(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    this.storagePolicyId = builder.storagePolicyId;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public String getStoragePolicyId() {
    return storagePolicyId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateArchiveV2025R0RequestBody casted = (CreateArchiveV2025R0RequestBody) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(storagePolicyId, casted.storagePolicyId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, storagePolicyId);
  }

  @Override
  public String toString() {
    return "CreateArchiveV2025R0RequestBody{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "storagePolicyId='"
        + storagePolicyId
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected String description;

    protected String storagePolicyId;

    public Builder(String name) {
      super();
      this.name = name;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder storagePolicyId(String storagePolicyId) {
      this.storagePolicyId = storagePolicyId;
      return this;
    }

    public CreateArchiveV2025R0RequestBody build() {
      return new CreateArchiveV2025R0RequestBody(this);
    }
  }
}
