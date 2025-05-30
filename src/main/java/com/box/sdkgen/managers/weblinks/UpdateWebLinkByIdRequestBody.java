package com.box.sdkgen.managers.weblinks;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class UpdateWebLinkByIdRequestBody extends SerializableObject {

  protected String url;

  protected UpdateWebLinkByIdRequestBodyParentField parent;

  protected String name;

  protected String description;

  @JsonProperty("shared_link")
  protected UpdateWebLinkByIdRequestBodySharedLinkField sharedLink;

  public UpdateWebLinkByIdRequestBody() {
    super();
  }

  protected UpdateWebLinkByIdRequestBody(UpdateWebLinkByIdRequestBodyBuilder builder) {
    super();
    this.url = builder.url;
    this.parent = builder.parent;
    this.name = builder.name;
    this.description = builder.description;
    this.sharedLink = builder.sharedLink;
  }

  public String getUrl() {
    return url;
  }

  public UpdateWebLinkByIdRequestBodyParentField getParent() {
    return parent;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public UpdateWebLinkByIdRequestBodySharedLinkField getSharedLink() {
    return sharedLink;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UpdateWebLinkByIdRequestBody casted = (UpdateWebLinkByIdRequestBody) o;
    return Objects.equals(url, casted.url)
        && Objects.equals(parent, casted.parent)
        && Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(sharedLink, casted.sharedLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(url, parent, name, description, sharedLink);
  }

  @Override
  public String toString() {
    return "UpdateWebLinkByIdRequestBody{"
        + "url='"
        + url
        + '\''
        + ", "
        + "parent='"
        + parent
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "sharedLink='"
        + sharedLink
        + '\''
        + "}";
  }

  public static class UpdateWebLinkByIdRequestBodyBuilder {

    protected String url;

    protected UpdateWebLinkByIdRequestBodyParentField parent;

    protected String name;

    protected String description;

    protected UpdateWebLinkByIdRequestBodySharedLinkField sharedLink;

    public UpdateWebLinkByIdRequestBodyBuilder url(String url) {
      this.url = url;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyBuilder parent(
        UpdateWebLinkByIdRequestBodyParentField parent) {
      this.parent = parent;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyBuilder name(String name) {
      this.name = name;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyBuilder description(String description) {
      this.description = description;
      return this;
    }

    public UpdateWebLinkByIdRequestBodyBuilder sharedLink(
        UpdateWebLinkByIdRequestBodySharedLinkField sharedLink) {
      this.sharedLink = sharedLink;
      return this;
    }

    public UpdateWebLinkByIdRequestBody build() {
      return new UpdateWebLinkByIdRequestBody(this);
    }
  }
}
