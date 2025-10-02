package com.box.sdkgen.schemas.resourcescope;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.filemini.FileMini;
import com.box.sdkgen.schemas.foldermini.FolderMini;
import com.box.sdkgen.schemas.resource.Resource;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Objects;

/**
 * A relation between a resource (file or folder) and the scopes for which the resource can be
 * accessed.
 */
@JsonFilter("nullablePropertyFilter")
public class ResourceScope extends SerializableObject {

  /** The scopes for the resource access. */
  @JsonDeserialize(using = ResourceScopeScopeField.ResourceScopeScopeFieldDeserializer.class)
  @JsonSerialize(using = ResourceScopeScopeField.ResourceScopeScopeFieldSerializer.class)
  protected EnumWrapper<ResourceScopeScopeField> scope;

  protected Resource object;

  public ResourceScope() {
    super();
  }

  protected ResourceScope(Builder builder) {
    super();
    this.scope = builder.scope;
    this.object = builder.object;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<ResourceScopeScopeField> getScope() {
    return scope;
  }

  public Resource getObject() {
    return object;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ResourceScope casted = (ResourceScope) o;
    return Objects.equals(scope, casted.scope) && Objects.equals(object, casted.object);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, object);
  }

  @Override
  public String toString() {
    return "ResourceScope{" + "scope='" + scope + '\'' + ", " + "object='" + object + '\'' + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<ResourceScopeScopeField> scope;

    protected Resource object;

    public Builder scope(ResourceScopeScopeField scope) {
      this.scope = new EnumWrapper<ResourceScopeScopeField>(scope);
      return this;
    }

    public Builder scope(EnumWrapper<ResourceScopeScopeField> scope) {
      this.scope = scope;
      return this;
    }

    public Builder object(FolderMini object) {
      this.object = new Resource(object);
      return this;
    }

    public Builder object(FileMini object) {
      this.object = new Resource(object);
      return this;
    }

    public Builder object(Resource object) {
      this.object = object;
      return this;
    }

    public ResourceScope build() {
      return new ResourceScope(this);
    }
  }
}
