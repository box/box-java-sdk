package com.box.sdkgen.schemas.weblinkbase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class WebLinkBase extends SerializableObject {

  protected final String id;

  @JsonDeserialize(using = WebLinkBaseTypeField.WebLinkBaseTypeFieldDeserializer.class)
  @JsonSerialize(using = WebLinkBaseTypeField.WebLinkBaseTypeFieldSerializer.class)
  protected EnumWrapper<WebLinkBaseTypeField> type;

  protected String etag;

  public WebLinkBase(@JsonProperty("id") String id) {
    super();
    this.id = id;
    this.type = new EnumWrapper<WebLinkBaseTypeField>(WebLinkBaseTypeField.WEB_LINK);
  }

  protected WebLinkBase(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.etag = builder.etag;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public EnumWrapper<WebLinkBaseTypeField> getType() {
    return type;
  }

  public String getEtag() {
    return etag;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebLinkBase casted = (WebLinkBase) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(etag, casted.etag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, etag);
  }

  @Override
  public String toString() {
    return "WebLinkBase{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "etag='"
        + etag
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected EnumWrapper<WebLinkBaseTypeField> type;

    protected String etag;

    public Builder(String id) {
      super();
      this.id = id;
      this.type = new EnumWrapper<WebLinkBaseTypeField>(WebLinkBaseTypeField.WEB_LINK);
    }

    public Builder type(WebLinkBaseTypeField type) {
      this.type = new EnumWrapper<WebLinkBaseTypeField>(type);
      return this;
    }

    public Builder type(EnumWrapper<WebLinkBaseTypeField> type) {
      this.type = type;
      return this;
    }

    public Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public WebLinkBase build() {
      return new WebLinkBase(this);
    }
  }
}
