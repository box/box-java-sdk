package com.box.sdkgen.managers.metadatacascadepolicies;

public class GetMetadataCascadePoliciesQueryParams {

  public final String folderId;

  public String ownerEnterpriseId;

  public String marker;

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
