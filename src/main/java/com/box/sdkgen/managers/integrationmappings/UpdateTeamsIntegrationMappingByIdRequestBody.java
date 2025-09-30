package com.box.sdkgen.managers.integrationmappings;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.folderreference.FolderReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class UpdateTeamsIntegrationMappingByIdRequestBody extends SerializableObject {

  @JsonProperty("box_item")
  protected FolderReference boxItem;

  public UpdateTeamsIntegrationMappingByIdRequestBody() {
    super();
  }

  protected UpdateTeamsIntegrationMappingByIdRequestBody(Builder builder) {
    super();
    this.boxItem = builder.boxItem;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public FolderReference getBoxItem() {
    return boxItem;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateTeamsIntegrationMappingByIdRequestBody casted =
        (UpdateTeamsIntegrationMappingByIdRequestBody) o;
    return Objects.equals(boxItem, casted.boxItem);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boxItem);
  }

  @Override
  public String toString() {
    return "UpdateTeamsIntegrationMappingByIdRequestBody{" + "boxItem='" + boxItem + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected FolderReference boxItem;

    public Builder boxItem(FolderReference boxItem) {
      this.boxItem = boxItem;
      return this;
    }

    public UpdateTeamsIntegrationMappingByIdRequestBody build() {
      return new UpdateTeamsIntegrationMappingByIdRequestBody(this);
    }
  }
}
