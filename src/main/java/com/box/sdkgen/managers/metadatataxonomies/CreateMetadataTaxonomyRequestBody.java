package com.box.sdkgen.managers.metadatataxonomies;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class CreateMetadataTaxonomyRequestBody extends SerializableObject {

  /**
   * The taxonomy key. If it is not provided in the request body, it will be generated from the
   * `displayName`. The `displayName` would be converted to lower case, and all spaces and
   * non-alphanumeric characters replaced with underscores.
   */
  protected String key;

  /** The display name of the taxonomy. */
  protected final String displayName;

  /** The namespace of the metadata taxonomy to create. */
  protected final String namespace;

  public CreateMetadataTaxonomyRequestBody(
      @JsonProperty("displayName") String displayName,
      @JsonProperty("namespace") String namespace) {
    super();
    this.displayName = displayName;
    this.namespace = namespace;
  }

  protected CreateMetadataTaxonomyRequestBody(Builder builder) {
    super();
    this.key = builder.key;
    this.displayName = builder.displayName;
    this.namespace = builder.namespace;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getKey() {
    return key;
  }

  public String getDisplayName() {
    return displayName;
  }

  public String getNamespace() {
    return namespace;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CreateMetadataTaxonomyRequestBody casted = (CreateMetadataTaxonomyRequestBody) o;
    return Objects.equals(key, casted.key)
        && Objects.equals(displayName, casted.displayName)
        && Objects.equals(namespace, casted.namespace);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, displayName, namespace);
  }

  @Override
  public String toString() {
    return "CreateMetadataTaxonomyRequestBody{"
        + "key='"
        + key
        + '\''
        + ", "
        + "displayName='"
        + displayName
        + '\''
        + ", "
        + "namespace='"
        + namespace
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String key;

    protected final String displayName;

    protected final String namespace;

    public Builder(String displayName, String namespace) {
      super();
      this.displayName = displayName;
      this.namespace = namespace;
    }

    public Builder key(String key) {
      this.key = key;
      return this;
    }

    public CreateMetadataTaxonomyRequestBody build() {
      return new CreateMetadataTaxonomyRequestBody(this);
    }
  }
}
