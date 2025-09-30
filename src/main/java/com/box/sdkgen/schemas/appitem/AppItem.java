package com.box.sdkgen.schemas.appitem;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class AppItem extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = AppItemTypeField.AppItemTypeFieldDeserializer.class)
  @JsonSerialize(using = AppItemTypeField.AppItemTypeFieldSerializer.class)
  protected EnumWrapper<AppItemTypeField> type;

  @JsonProperty("application_type")
  protected final String applicationType;

  public AppItem(
      @JsonProperty("id") String id, @JsonProperty("application_type") String applicationType) {
    super();
    this.id = id;
    this.applicationType = applicationType;
    this.type = new EnumWrapper<AppItemTypeField>(AppItemTypeField.APP_ITEM);
  }

  protected AppItem(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.applicationType = builder.applicationType;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<AppItemTypeField> getType() {
    return type;
  }

  public String getApplicationType() {
    return applicationType;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AppItem casted = (AppItem) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(applicationType, casted.applicationType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, applicationType);
  }

  @Override
  public String toString() {
    return "AppItem{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "applicationType='"
        + applicationType
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<AppItemTypeField> type;

    protected final String applicationType;

    public Builder(String id, String applicationType) {
      super();
      this.id = id;
      this.applicationType = applicationType;
      this.type = new EnumWrapper<AppItemTypeField>(AppItemTypeField.APP_ITEM);
    }

    public Builder type(AppItemTypeField type) {
      this.type = new EnumWrapper<AppItemTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<AppItemTypeField> type) {
      this.type = type;
      return this;
    }

    public AppItem build() {
      return new AppItem(this);
    }
  }
}
