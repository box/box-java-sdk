package com.box.sdkgen.managers.filemetadata;

public class GetFileMetadataByIdQueryParams {

  /**
   * Taxonomy field values are returned in `API view` by default, meaning the value is represented
   * with a taxonomy node identifier. To retrieve the `Hydrated view`, where taxonomy values are
   * represented with the full taxonomy node information, set this parameter to `hydrated`. This is
   * the only supported value for this parameter.
   */
  public String view;

  public GetFileMetadataByIdQueryParams() {}

  protected GetFileMetadataByIdQueryParams(Builder builder) {
    this.view = builder.view;
  }

  public String getView() {
    return view;
  }

  public static class Builder {

    protected String view;

    public Builder view(String view) {
      this.view = view;
      return this;
    }

    public GetFileMetadataByIdQueryParams build() {
      return new GetFileMetadataByIdQueryParams(this);
    }
  }
}
