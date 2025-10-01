package com.box.sdkgen.schemas.metadatafull;

import com.box.sdkgen.schemas.metadata.Metadata;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;
import java.util.Objects;

/** An instance of a metadata template, which has been applied to a file or folder. */
@JsonFilter("nullablePropertyFilter")
public class MetadataFull extends Metadata {

  /** Whether the user can edit this metadata instance. */
  @JsonProperty("$canEdit")
  protected Boolean canEdit;

  /** A UUID to identify the metadata instance. */
  @JsonProperty("$id")
  protected String id;

  /**
   * A unique identifier for the "type" of this instance. This is an internal system property and
   * should not be used by a client application.
   */
  @JsonProperty("$type")
  protected String type;

  /**
   * The last-known version of the template of the object. This is an internal system property and
   * should not be used by a client application.
   */
  @JsonProperty("$typeVersion")
  protected Long typeVersion;

  @JsonAnyGetter @JsonAnySetter protected Map<String, Object> extraData;

  public MetadataFull() {
    super();
  }

  protected MetadataFull(Builder builder) {
    super(builder);
    this.canEdit = builder.canEdit;
    this.id = builder.id;
    this.type = builder.type;
    this.typeVersion = builder.typeVersion;
    this.extraData = builder.extraData;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public Boolean getCanEdit() {
    return canEdit;
  }

  public String getId() {
    return id;
  }

  public String getType() {
    return type;
  }

  public Long getTypeVersion() {
    return typeVersion;
  }

  public Map<String, Object> getExtraData() {
    return extraData;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataFull casted = (MetadataFull) o;
    return Objects.equals(parent, casted.parent)
        && Objects.equals(template, casted.template)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(version, casted.version)
        && Objects.equals(canEdit, casted.canEdit)
        && Objects.equals(id, casted.id)
        && Objects.equals(type, casted.type)
        && Objects.equals(typeVersion, casted.typeVersion)
        && Objects.equals(extraData, casted.extraData);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        parent, template, scope, version, canEdit, id, type, typeVersion, extraData);
  }

  @Override
  public String toString() {
    return "MetadataFull{"
        + "parent='"
        + parent
        + '\''
        + ", "
        + "template='"
        + template
        + '\''
        + ", "
        + "scope='"
        + scope
        + '\''
        + ", "
        + "version='"
        + version
        + '\''
        + ", "
        + "canEdit='"
        + canEdit
        + '\''
        + ", "
        + "id='"
        + id
        + '\''
        + ", "
        + "type='"
        + type
        + '\''
        + ", "
        + "typeVersion='"
        + typeVersion
        + '\''
        + ", "
        + "extraData='"
        + extraData
        + '\''
        + "}";
  }

  public static class Builder extends Metadata.Builder {

    protected Boolean canEdit;

    protected String id;

    protected String type;

    protected Long typeVersion;

    protected Map<String, Object> extraData;

    public Builder canEdit(Boolean canEdit) {
      this.canEdit = canEdit;
      return this;
    }

    public Builder id(String id) {
      this.id = id;
      return this;
    }

    public Builder type(String type) {
      this.type = type;
      return this;
    }

    public Builder typeVersion(Long typeVersion) {
      this.typeVersion = typeVersion;
      return this;
    }

    public Builder extraData(Map<String, Object> extraData) {
      this.extraData = extraData;
      return this;
    }

    @Override
    public Builder parent(String parent) {
      this.parent = parent;
      return this;
    }

    @Override
    public Builder template(String template) {
      this.template = template;
      return this;
    }

    @Override
    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    @Override
    public Builder version(Long version) {
      this.version = version;
      return this;
    }

    public MetadataFull build() {
      return new MetadataFull(this);
    }
  }
}
