package com.box.sdkgen.schemas.metadatabase;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

/** The base representation of a metadata instance. */
@JsonFilter("nullablePropertyFilter")
public class MetadataBase extends SerializableObject {

  /**
   * The identifier of the item that this metadata instance has been attached to. This combines the
   * `type` and the `id` of the parent in the form `{type}_{id}`.
   */
  @JsonProperty("$parent")
  protected String parent;

  /** The name of the template. */
  @JsonProperty("$template")
  protected String template;

  /**
   * An ID for the scope in which this template has been applied. This will be
   * `enterprise_{enterprise_id}` for templates defined for use in this enterprise, and `global` for
   * general templates that are available to all enterprises using Box.
   */
  @JsonProperty("$scope")
  protected String scope;

  /**
   * The version of the metadata instance. This version starts at 0 and increases every time a
   * user-defined property is modified.
   */
  @JsonProperty("$version")
  protected Long version;

  public MetadataBase() {
    super();
  }

  protected MetadataBase(Builder builder) {
    super();
    this.parent = builder.parent;
    this.template = builder.template;
    this.scope = builder.scope;
    this.version = builder.version;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getParent() {
    return parent;
  }

  public String getTemplate() {
    return template;
  }

  public String getScope() {
    return scope;
  }

  public Long getVersion() {
    return version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataBase casted = (MetadataBase) o;
    return Objects.equals(parent, casted.parent)
        && Objects.equals(template, casted.template)
        && Objects.equals(scope, casted.scope)
        && Objects.equals(version, casted.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(parent, template, scope, version);
  }

  @Override
  public String toString() {
    return "MetadataBase{"
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
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String parent;

    protected String template;

    protected String scope;

    protected Long version;

    public Builder parent(String parent) {
      this.parent = parent;
      return this;
    }

    public Builder template(String template) {
      this.template = template;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public Builder version(Long version) {
      this.version = version;
      return this;
    }

    public MetadataBase build() {
      return new MetadataBase(this);
    }
  }
}
