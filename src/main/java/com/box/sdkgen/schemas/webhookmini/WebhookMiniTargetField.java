package com.box.sdkgen.schemas.webhookmini;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WebhookMiniTargetField extends SerializableObject {

  protected String id;

  @JsonDeserialize(using = WebhookMiniTargetTypeField.WebhookMiniTargetTypeFieldDeserializer.class)
  @JsonSerialize(using = WebhookMiniTargetTypeField.WebhookMiniTargetTypeFieldSerializer.class)
  protected EnumWrapper<WebhookMiniTargetTypeField> type;

  public WebhookMiniTargetField() {
    super();
  }

  protected WebhookMiniTargetField(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WebhookMiniTargetTypeField> getType() {
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
    WebhookMiniTargetField casted = (WebhookMiniTargetField) o;
    return Objects.equals(id, casted.id) && Objects.equals(type, casted.type);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type);
  }

  @Override
  public String toString() {
    return "WebhookMiniTargetField{" + "id='" + id + '\'' + ", " + "type='" + type + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String id;

    protected EnumWrapper<WebhookMiniTargetTypeField> type;

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(WebhookMiniTargetTypeField type) {
      this.type = new EnumWrapper<WebhookMiniTargetTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WebhookMiniTargetTypeField> type) {
      this.type = type;
      return this;
    }

    public WebhookMiniTargetField build() {
      return new WebhookMiniTargetField(this);
    }
  }
}
