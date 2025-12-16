package com.box.sdkgen.managers.metadatataxonomies;

import java.util.List;

public class GetMetadataTemplateFieldOptionsQueryParams {

  /**
   * Filters results by taxonomy level. Multiple values can be provided. Results include nodes that
   * match any of the specified values.
   */
  public List<Long> level;

  /**
   * Node identifier of a direct parent node. Multiple values can be provided. Results include nodes
   * that match any of the specified values.
   */
  public List<String> parent;

  /**
   * Node identifier of any ancestor node. Multiple values can be provided. Results include nodes
   * that match any of the specified values.
   */
  public List<String> ancestor;

  /** Query text to search for the taxonomy nodes. */
  public String query;

  /**
   * When set to `true` this provides the total number of nodes that matched the query. The response
   * will compute counts of up to 10,000 elements. Defaults to `false`.
   */
  public Boolean includeTotalResultCount;

  /**
   * When set to `true`, this only returns valid selectable options for this template taxonomy
   * field. Otherwise, it returns all taxonomy nodes, whether or not they are selectable. Defaults
   * to `true`.
   */
  public Boolean onlySelectableOptions;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /** The maximum number of items to return per page. */
  public Long limit;

  public GetMetadataTemplateFieldOptionsQueryParams() {}

  protected GetMetadataTemplateFieldOptionsQueryParams(Builder builder) {
    this.level = builder.level;
    this.parent = builder.parent;
    this.ancestor = builder.ancestor;
    this.query = builder.query;
    this.includeTotalResultCount = builder.includeTotalResultCount;
    this.onlySelectableOptions = builder.onlySelectableOptions;
    this.marker = builder.marker;
    this.limit = builder.limit;
  }

  public List<Long> getLevel() {
    return level;
  }

  public List<String> getParent() {
    return parent;
  }

  public List<String> getAncestor() {
    return ancestor;
  }

  public String getQuery() {
    return query;
  }

  public Boolean getIncludeTotalResultCount() {
    return includeTotalResultCount;
  }

  public Boolean getOnlySelectableOptions() {
    return onlySelectableOptions;
  }

  public String getMarker() {
    return marker;
  }

  public Long getLimit() {
    return limit;
  }

  public static class Builder {

    protected List<Long> level;

    protected List<String> parent;

    protected List<String> ancestor;

    protected String query;

    protected Boolean includeTotalResultCount;

    protected Boolean onlySelectableOptions;

    protected String marker;

    protected Long limit;

    public Builder level(List<Long> level) {
      this.level = level;
      return this;
    }

    public Builder parent(List<String> parent) {
      this.parent = parent;
      return this;
    }

    public Builder ancestor(List<String> ancestor) {
      this.ancestor = ancestor;
      return this;
    }

    public Builder query(String query) {
      this.query = query;
      return this;
    }

    public Builder includeTotalResultCount(Boolean includeTotalResultCount) {
      this.includeTotalResultCount = includeTotalResultCount;
      return this;
    }

    public Builder onlySelectableOptions(Boolean onlySelectableOptions) {
      this.onlySelectableOptions = onlySelectableOptions;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetMetadataTemplateFieldOptionsQueryParams build() {
      return new GetMetadataTemplateFieldOptionsQueryParams(this);
    }
  }
}
