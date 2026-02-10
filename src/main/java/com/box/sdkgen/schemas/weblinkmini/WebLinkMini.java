package com.box.sdkgen.schemas.weblinkmini;

import com.box.sdkgen.schemas.weblinkbase.WebLinkBase;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/**
 * Web links are objects that point to URLs. These objects are also known as bookmarks within the
 * Box web application.
 *
 * <p>Web link objects are treated similarly to file objects, they will also support most actions
 * that apply to regular files.
 */
@JsonFilter("nullablePropertyFilter")
public class WebLinkMini extends WebLinkBase {

  /** The URL this web link points to. */
  protected String url;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  /** The name of the web link. */
  protected String name;

  public WebLinkMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected WebLinkMini(Builder builder) {
    super(builder);
    this.url = builder.url;
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUrl() {
    return url;
  }

  public String getSequenceId() {
    return sequenceId;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    WebLinkMini casted = (WebLinkMini) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(etag, casted.etag)
        && Objects.equals(url, casted.url)
        && Objects.equals(sequenceId, casted.sequenceId)
        && Objects.equals(name, casted.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, etag, url, sequenceId, name);
  }

  @Override
  public String toString() {
    return "WebLinkMini{"
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
        + ", "
        + "url='"
        + url
        + '\''
        + ", "
        + "sequenceId='"
        + sequenceId
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + "}";
  }

  public static class Builder extends WebLinkBase.Builder {

    protected String url;

    protected String sequenceId;

    protected String name;

    public Builder(String id) {
      super(id);
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public Builder type(WebLinkBaseTypeField type) {
      this.type = new EnumWrapper<WebLinkBaseTypeField>(type);
      return this;
    }

    @Override
    public Builder type(EnumWrapper<WebLinkBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public Builder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public WebLinkMini build() {
      if (this.type == null) {
        this.type = new EnumWrapper<WebLinkBaseTypeField>(WebLinkBaseTypeField.WEB_LINK);
      }
      return new WebLinkMini(this);
    }
  }
}
