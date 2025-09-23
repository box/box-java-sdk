package com.box.sdkgen.schemas.devicepinner;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.usermini.UserMini;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class DevicePinner extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = DevicePinnerTypeField.DevicePinnerTypeFieldDeserializer.class)
  @JsonSerialize(using = DevicePinnerTypeField.DevicePinnerTypeFieldSerializer.class)
  protected EnumWrapper<DevicePinnerTypeField> type;

  @JsonProperty("owned_by")
  protected UserMini ownedBy;

  @JsonProperty("product_name")
  protected String productName;

  public DevicePinner() {
    super();
  }

  protected DevicePinner(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.ownedBy = builder.ownedBy;
    this.productName = builder.productName;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<DevicePinnerTypeField> getType() {
    return type;
  }

  public UserMini getOwnedBy() {
    return ownedBy;
  }

  public String getProductName() {
    return productName;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DevicePinner casted = (DevicePinner) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(ownedBy, casted.ownedBy)
        && Objects.equals(productName, casted.productName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, ownedBy, productName);
  }

  @Override
  public String toString() {
    return "DevicePinner{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "ownedBy='"
        + ownedBy
        + '\''
        + ", "
        + "productName='"
        + productName
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<DevicePinnerTypeField> type;

    protected UserMini ownedBy;

    protected String productName;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(DevicePinnerTypeField type) {
      this.type = new EnumWrapper<DevicePinnerTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<DevicePinnerTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder ownedBy(UserMini ownedBy) {
      this.ownedBy = ownedBy;
      return this;
    }

    public Builder productName(String productName) {
      this.productName = productName;
      return this;
    }

    public DevicePinner build() {
      return new DevicePinner(this);
    }
  }
}
