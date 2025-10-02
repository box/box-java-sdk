package com.box.sdkgen.managers.metadatacascadepolicies;

public class GetMetadataCascadePoliciesQueryParams {

  /**
   * Specifies which folder to return policies for. This can not be used on the root folder with ID
   * `0`.
   */
  public final String folderId;

  /**
   * The ID of the enterprise ID for which to find metadata cascade policies. If not specified, it
   * defaults to the current enterprise.
   */
  public String ownerEnterpriseId;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  public GetMetadataCascadePoliciesQueryParams(String folderId) {
    this.folderId = folderId;
  }

  protected GetMetadataCascadePoliciesQueryParams(Builder builder) {
    this.folderId = builder.folderId;
    this.ownerEnterpriseId = builder.ownerEnterpriseId;
    this.marker = builder.marker;
    this.offset = builder.offset;
  }

  public String getFolderId() {
    return folderId;
  }

  public String getOwnerEnterpriseId() {
    return ownerEnterpriseId;
  }

  public String getMarker() {
    return marker;
  }

  public Long getOffset() {
    return offset;
  }

  public static class Builder {

    protected final String folderId;

    protected String ownerEnterpriseId;

    protected String marker;

    protected Long offset;

    public Builder(String folderId) {
      this.folderId = folderId;
    }

    public Builder ownerEnterpriseId(String ownerEnterpriseId) {
      this.ownerEnterpriseId = ownerEnterpriseId;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetMetadataCascadePoliciesQueryParams build() {
      return new GetMetadataCascadePoliciesQueryParams(this);
    }
  }
}
