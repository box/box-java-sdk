package com.box.sdkgen.schemas.metadata;

import com.box.sdkgen.schemas.metadatabase.MetadataBase;
import java.util.Objects;

public class Metadata extends MetadataBase {

  public Metadata() {
    super();
  }

  protected Metadata(MetadataBuilder builder) {
    super(builder);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Metadata casted = (Metadata) o;
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
    return "Metadata{"
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

  public static class MetadataBuilder extends MetadataBaseBuilder {

    @Override
    public MetadataBuilder parent(String parent) {
      this.parent = parent;
      return this;
    }

    @Override
    public MetadataBuilder template(String template) {
      this.template = template;
      return this;
    }

    @Override
    public MetadataBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    @Override
    public MetadataBuilder version(Long version) {
      this.version = version;
      return this;
    }

    public Metadata build() {
      return new Metadata(this);
    }
  }
}
