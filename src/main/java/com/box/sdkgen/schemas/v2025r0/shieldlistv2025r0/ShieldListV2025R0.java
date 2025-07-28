package com.box.sdkgen.schemas.v2025r0.shieldlistv2025r0;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.internal.utils.DateTimeUtils;
import com.box.sdkgen.schemas.v2025r0.enterprisereferencev2025r0.EnterpriseReferenceV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentcountryv2025r0.ShieldListContentCountryV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentdomainv2025r0.ShieldListContentDomainV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentemailv2025r0.ShieldListContentEmailV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentintegrationv2025r0.ShieldListContentIntegrationV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentipv2025r0.ShieldListContentIpV2025R0;
import com.box.sdkgen.schemas.v2025r0.shieldlistcontentv2025r0.ShieldListContentV2025R0;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class ShieldListV2025R0 extends SerializableObject {

  protected final String id;

  protected final String type;

  protected final String name;

  protected final EnterpriseReferenceV2025R0 enterprise;

  protected String description;

  @JsonProperty("created_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final Date createdAt;

  @JsonProperty("updated_at")
  @JsonSerialize(using = DateTimeUtils.DateTimeSerializer.class)
  @JsonDeserialize(using = DateTimeUtils.DateTimeDeserializer.class)
  protected final Date updatedAt;

  protected final ShieldListContentV2025R0 content;

  public ShieldListV2025R0(
      String id,
      String type,
      String name,
      EnterpriseReferenceV2025R0 enterprise,
      Date createdAt,
      Date updatedAt,
      ShieldListContentCountryV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = new ShieldListContentV2025R0(content);
  }

  public ShieldListV2025R0(
      String id,
      String type,
      String name,
      EnterpriseReferenceV2025R0 enterprise,
      Date createdAt,
      Date updatedAt,
      ShieldListContentDomainV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = new ShieldListContentV2025R0(content);
  }

  public ShieldListV2025R0(
      String id,
      String type,
      String name,
      EnterpriseReferenceV2025R0 enterprise,
      Date createdAt,
      Date updatedAt,
      ShieldListContentEmailV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = new ShieldListContentV2025R0(content);
  }

  public ShieldListV2025R0(
      String id,
      String type,
      String name,
      EnterpriseReferenceV2025R0 enterprise,
      Date createdAt,
      Date updatedAt,
      ShieldListContentIpV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = new ShieldListContentV2025R0(content);
  }

  public ShieldListV2025R0(
      String id,
      String type,
      String name,
      EnterpriseReferenceV2025R0 enterprise,
      Date createdAt,
      Date updatedAt,
      ShieldListContentIntegrationV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = new ShieldListContentV2025R0(content);
  }

  public ShieldListV2025R0(
      @JsonProperty("id") String id,
      @JsonProperty("type") String type,
      @JsonProperty("name") String name,
      @JsonProperty("enterprise") EnterpriseReferenceV2025R0 enterprise,
      @JsonProperty("created_at") Date createdAt,
      @JsonProperty("updated_at") Date updatedAt,
      @JsonProperty("content") ShieldListContentV2025R0 content) {
    super();
    this.id = id;
    this.type = type;
    this.name = name;
    this.enterprise = enterprise;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.content = content;
  }

  protected ShieldListV2025R0(Builder builder) {
    super();
    this.id = builder.id;
    this.type = builder.type;
    this.name = builder.name;
    this.enterprise = builder.enterprise;
    this.description = builder.description;
    this.createdAt = builder.createdAt;
    this.updatedAt = builder.updatedAt;
    this.content = builder.content;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public EnterpriseReferenceV2025R0 getEnterprise() {
    return enterprise;
  }

  public String getDescription() {
    return description;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public ShieldListContentV2025R0 getContent() {
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
    ShieldListV2025R0 casted = (ShieldListV2025R0) o;
    return Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(name, casted.name)
        && Objects.equals(enterprise, casted.enterprise)
        && Objects.equals(description, casted.description)
        && Objects.equals(createdAt, casted.createdAt)
        && Objects.equals(updatedAt, casted.updatedAt)
        && Objects.equals(content, casted.content);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, enterprise, description, createdAt, updatedAt, content);
  }

  @Override
  public String toString() {
    return "ShieldListV2025R0{"
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "name='"
        + name
        + '\''
        + ", "
        + "enterprise='"
        + enterprise
        + '\''
        + ", "
        + "description='"
        + description
        + '\''
        + ", "
        + "createdAt='"
        + createdAt
        + '\''
        + ", "
        + "updatedAt='"
        + updatedAt
        + '\''
        + ", "
        + "content='"
        + content
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected final String id;

    protected final String type;

    protected final String name;

    protected final EnterpriseReferenceV2025R0 enterprise;

    protected String description;

    protected final Date createdAt;

    protected final Date updatedAt;

    protected final ShieldListContentV2025R0 content;

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentCountryV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = new ShieldListContentV2025R0(content);
    }

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentDomainV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = new ShieldListContentV2025R0(content);
    }

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentEmailV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = new ShieldListContentV2025R0(content);
    }

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentIpV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = new ShieldListContentV2025R0(content);
    }

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentIntegrationV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = new ShieldListContentV2025R0(content);
    }

    public Builder(
        String id,
        String type,
        String name,
        EnterpriseReferenceV2025R0 enterprise,
        Date createdAt,
        Date updatedAt,
        ShieldListContentV2025R0 content) {
      super();
      this.id = id;
      this.type = type;
      this.name = name;
      this.enterprise = enterprise;
      this.createdAt = createdAt;
      this.updatedAt = updatedAt;
      this.content = content;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public ShieldListV2025R0 build() {
      return new ShieldListV2025R0(this);
    }
  }
}
