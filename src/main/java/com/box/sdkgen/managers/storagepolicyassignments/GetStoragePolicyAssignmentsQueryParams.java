package com.box.sdkgen.managers.storagepolicyassignments;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetStoragePolicyAssignmentsQueryParams {

  public String marker;

  public final EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>
      resolvedForType;

  public final String resolvedForId;

  public GetStoragePolicyAssignmentsQueryParams(
      GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField resolvedForType,
      String resolvedForId) {
    this.resolvedForType =
        new EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>(
            resolvedForType);
    this.resolvedForId = resolvedForId;
  }

  public GetStoragePolicyAssignmentsQueryParams(
      EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField> resolvedForType,
      String resolvedForId) {
    this.resolvedForType = resolvedForType;
    this.resolvedForId = resolvedForId;
  }

  protected GetStoragePolicyAssignmentsQueryParams(Builder builder) {
    this.marker = builder.marker;
    this.resolvedForType = builder.resolvedForType;
    this.resolvedForId = builder.resolvedForId;
  }

  public String getMarker() {
    return marker;
  }

  public EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>
      getResolvedForType() {
    return resolvedForType;
  }

  public String getResolvedForId() {
    return resolvedForId;
  }

  public static class Builder {

    protected String marker;

    protected final EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>
        resolvedForType;

    protected final String resolvedForId;

    public Builder(
        GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField resolvedForType,
        String resolvedForId) {
      this.resolvedForType =
          new EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField>(
              resolvedForType);
      this.resolvedForId = resolvedForId;
    }

    public Builder(
        EnumWrapper<GetStoragePolicyAssignmentsQueryParamsResolvedForTypeField> resolvedForType,
        String resolvedForId) {
      this.resolvedForType = resolvedForType;
      this.resolvedForId = resolvedForId;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetStoragePolicyAssignmentsQueryParams build() {
      return new GetStoragePolicyAssignmentsQueryParams(this);
    }
  }
}
