package com.box.sdkgen.schemas.metadatafilter;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.metadatafiltervalue.MetadataFilterValue;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Map;
import java.util.Objects;

/** A metadata template used to filter the search results. */
@JsonFilter("nullablePropertyFilter")
public class MetadataFilter extends SerializableObject {

  /**
   * Specifies the scope of the template to filter search results by.
   *
   * <p>This will be `enterprise_{enterprise_id}` for templates defined for use in this enterprise,
   * and `global` for general templates that are available to all enterprises using Box.
   */
  @JsonDeserialize(using = MetadataFilterScopeField.MetadataFilterScopeFieldDeserializer.class)
  @JsonSerialize(using = MetadataFilterScopeField.MetadataFilterScopeFieldSerializer.class)
  protected EnumWrapper<MetadataFilterScopeField> scope;

  /**
   * The key of the template used to filter search results.
   *
   * <p>In many cases the template key is automatically derived of its display name, for example
   * `Contract Template` would become `contractTemplate`. In some cases the creator of the template
   * will have provided its own template key.
   *
   * <p>Please [list the templates for an enterprise][list], or get all instances on a [file][file]
   * or [folder][folder] to inspect a template's key.
   *
   * <p>[list]: https://developer.box.com/reference/get-metadata-templates-enterprise [file]:
   * https://developer.box.com/reference/get-files-id-metadata [folder]:
   * https://developer.box.com/reference/get-folders-id-metadata
   */
  protected String templateKey;

  /**
   * Specifies which fields on the template to filter the search results by. When more than one
   * field is specified, the query performs a logical `AND` to ensure that the instance of the
   * template matches each of the fields specified.
   */
  protected Map<String, MetadataFilterValue> filters;

  public MetadataFilter() {
    super();
  }

  protected MetadataFilter(Builder builder) {
    super();
    this.scope = builder.scope;
    this.templateKey = builder.templateKey;
    this.filters = builder.filters;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public EnumWrapper<MetadataFilterScopeField> getScope() {
    return scope;
  }

  public String getTemplateKey() {
    return templateKey;
  }

  public Map<String, MetadataFilterValue> getFilters() {
    return filters;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MetadataFilter casted = (MetadataFilter) o;
    return Objects.equals(scope, casted.scope)
        && Objects.equals(templateKey, casted.templateKey)
        && Objects.equals(filters, casted.filters);
  }

  @Override
  public int hashCode() {
    return Objects.hash(scope, templateKey, filters);
  }

  @Override
  public String toString() {
    return "MetadataFilter{"
        + "scope='"
        + scope
        + '\''
        + ", "
        + "templateKey='"
        + templateKey
        + '\''
        + ", "
        + "filters='"
        + filters
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected EnumWrapper<MetadataFilterScopeField> scope;

    protected String templateKey;

    protected Map<String, MetadataFilterValue> filters;

    public Builder scope(MetadataFilterScopeField scope) {
      this.scope = new EnumWrapper<MetadataFilterScopeField>(scope);
      return this;
    }

    public Builder scope(EnumWrapper<MetadataFilterScopeField> scope) {
      this.scope = scope;
      return this;
    }

    public Builder templateKey(String templateKey) {
      this.templateKey = templateKey;
      return this;
    }

    public Builder filters(Map<String, MetadataFilterValue> filters) {
      this.filters = filters;
      return this;
    }

    public MetadataFilter build() {
      return new MetadataFilter(this);
    }
  }
}
