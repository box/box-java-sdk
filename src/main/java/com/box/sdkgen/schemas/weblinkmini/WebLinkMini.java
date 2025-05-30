package com.box.sdkgen.schemas.weblinkmini;

import com.box.sdkgen.schemas.weblinkbase.WebLinkBase;
import com.box.sdkgen.schemas.weblinkbase.WebLinkBaseTypeField;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class WebLinkMini extends WebLinkBase {

  protected String url;

  @JsonProperty("sequence_id")
  protected String sequenceId;

  protected String name;

  public WebLinkMini(@JsonProperty("id") String id) {
    super(id);
  }

  protected WebLinkMini(WebLinkMiniBuilder builder) {
    super(builder);
    this.url = builder.url;
    this.sequenceId = builder.sequenceId;
    this.name = builder.name;
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

  public static class WebLinkMiniBuilder extends WebLinkBaseBuilder {

    protected String url;

    protected String sequenceId;

    protected String name;

    public WebLinkMiniBuilder(String id) {
      super(id);
    }

    public WebLinkMiniBuilder url(String url) {
      this.url = url;
      return this;
    }

    public WebLinkMiniBuilder sequenceId(String sequenceId) {
      this.sequenceId = sequenceId;
      return this;
    }

    public WebLinkMiniBuilder name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public WebLinkMiniBuilder type(WebLinkBaseTypeField type) {
      this.type = new EnumWrapper<WebLinkBaseTypeField>(type);
      return this;
    }

    @Override
    public WebLinkMiniBuilder type(EnumWrapper<WebLinkBaseTypeField> type) {
      this.type = type;
      return this;
    }

    @Override
    public WebLinkMiniBuilder etag(String etag) {
      this.etag = etag;
      return this;
    }

    public WebLinkMini build() {
      return new WebLinkMini(this);
    }
  }
}
