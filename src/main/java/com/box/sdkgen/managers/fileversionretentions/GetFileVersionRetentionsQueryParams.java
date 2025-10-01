package com.box.sdkgen.managers.fileversionretentions;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetFileVersionRetentionsQueryParams {

  /** Filters results by files with this ID. */
  public String fileId;

  /** Filters results by file versions with this ID. */
  public String fileVersionId;

  /** Filters results by the retention policy with this ID. */
  public String policyId;

  /** Filters results by the retention policy with this disposition action. */
  public EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField> dispositionAction;

  /**
   * Filters results by files that will have their disposition come into effect before this date.
   */
  public String dispositionBefore;

  /** Filters results by files that will have their disposition come into effect after this date. */
  public String dispositionAfter;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  public GetFileVersionRetentionsQueryParams() {}

  protected GetFileVersionRetentionsQueryParams(Builder builder) {
    this.fileId = builder.fileId;
    this.fileVersionId = builder.fileVersionId;
    this.policyId = builder.policyId;
    this.dispositionAction = builder.dispositionAction;
    this.dispositionBefore = builder.dispositionBefore;
    this.dispositionAfter = builder.dispositionAfter;
    this.limit = builder.limit;
    this.marker = builder.marker;
  }

  public String getFileId() {
    return fileId;
  }

  public String getFileVersionId() {
    return fileVersionId;
  }

  public String getPolicyId() {
    return policyId;
  }

  public EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>
      getDispositionAction() {
    return dispositionAction;
  }

  public String getDispositionBefore() {
    return dispositionBefore;
  }

  public String getDispositionAfter() {
    return dispositionAfter;
  }

  public Long getLimit() {
    return limit;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected String fileId;

    protected String fileVersionId;

    protected String policyId;

    protected EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>
        dispositionAction;

    protected String dispositionBefore;

    protected String dispositionAfter;

    protected Long limit;

    protected String marker;

    public Builder fileId(String fileId) {
      this.fileId = fileId;
      return this;
    }

    public Builder fileVersionId(String fileVersionId) {
      this.fileVersionId = fileVersionId;
      return this;
    }

    public Builder policyId(String policyId) {
      this.policyId = policyId;
      return this;
    }

    public Builder dispositionAction(
        GetFileVersionRetentionsQueryParamsDispositionActionField dispositionAction) {
      this.dispositionAction =
          new EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField>(
              dispositionAction);
      return this;
    }

    public Builder dispositionAction(
        EnumWrapper<GetFileVersionRetentionsQueryParamsDispositionActionField> dispositionAction) {
      this.dispositionAction = dispositionAction;
      return this;
    }

    public Builder dispositionBefore(String dispositionBefore) {
      this.dispositionBefore = dispositionBefore;
      return this;
    }

    public Builder dispositionAfter(String dispositionAfter) {
      this.dispositionAfter = dispositionAfter;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetFileVersionRetentionsQueryParams build() {
      return new GetFileVersionRetentionsQueryParams(this);
    }
  }
}
