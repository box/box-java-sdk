package com.box.sdkgen.schemas.v2025r0.shieldlistscreatev2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentcountryv2025r0.ShieldListContentCountryV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentdomainv2025r0.ShieldListContentDomainV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentemailv2025r0.ShieldListContentEmailV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentipv2025r0.ShieldListContentIpV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentrequestv2025r0.ShieldListContentRequestV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The schema for Shield List create request. */
@JsonFilter("nullablePropertyFilter")
public class ShieldListsCreateV2025R0 extends SerializableObject {

  /** The name of the shield list. */
  protected final String name;

  /** Optional description of Shield List. */
  protected String description;

  protected final ShieldListContentRequestV2025R0 content;

  public ShieldListsCreateV2025R0(String name, ShieldListContentCountryV2025R0 content) {
    super();
    this.name = name;
    this.content = new ShieldListContentRequestV2025R0(content);
  }

  public ShieldListsCreateV2025R0(String name, ShieldListContentDomainV2025R0 content) {
    super();
    this.name = name;
    this.content = new ShieldListContentRequestV2025R0(content);
  }

  public ShieldListsCreateV2025R0(String name, ShieldListContentEmailV2025R0 content) {
    super();
    this.name = name;
    this.content = new ShieldListContentRequestV2025R0(content);
  }

  public ShieldListsCreateV2025R0(String name, ShieldListContentIpV2025R0 content) {
    super();
    this.name = name;
    this.content = new ShieldListContentRequestV2025R0(content);
  }

  public ShieldListsCreateV2025R0(
      @JsonProperty("name") String name,
      @JsonProperty("content") ShieldListContentRequestV2025R0 content) {
    super();
    this.name = name;
    this.content = content;
  }

  protected ShieldListsCreateV2025R0(Builder builder) {
    super();
    this.name = builder.name;
    this.description = builder.description;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public ShieldListContentRequestV2025R0 getContent() {
    return content;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ShieldListsCreateV2025R0 casted = (ShieldListsCreateV2025R0) o;
    return Objects.equals(name, casted.name)
        && Objects.equals(description, casted.description)
        && Objects.equals(content, casted.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, description, content);
  }

  @Override
  public String toString() {
    return "ShieldListsCreateV2025R0{"
        + "name='"
        + name
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "content='"
        + content
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String name;

    protected String description;

    protected final ShieldListContentRequestV2025R0 content;

    public Builder(String name, ShieldListContentCountryV2025R0 content) {
      super();
      this.name = name;
      this.content = new ShieldListContentRequestV2025R0(content);
    }

    public Builder(String name, ShieldListContentDomainV2025R0 content) {
      super();
      this.name = name;
      this.content = new ShieldListContentRequestV2025R0(content);
    }

    public Builder(String name, ShieldListContentEmailV2025R0 content) {
      super();
      this.name = name;
      this.content = new ShieldListContentRequestV2025R0(content);
    }

    public Builder(String name, ShieldListContentIpV2025R0 content) {
      super();
      this.name = name;
      this.content = new ShieldListContentRequestV2025R0(content);
    }

    public Builder(String name, ShieldListContentRequestV2025R0 content) {
      super();
      this.name = name;
      this.content = content;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public ShieldListsCreateV2025R0 build() {
      return new ShieldListsCreateV2025R0(this);
    }
  }
}
