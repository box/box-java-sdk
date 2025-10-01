package com.box.sdkgen.managers.users;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetUsersQueryParams {

  /**
   * Limits the results to only users who's `name` or `login` start with the search term.
   *
   * <p>For externally managed users, the search term needs to completely match the in order to find
   * the user, and it will only return one user at a time.
   */
  public String filterTerm;

  /**
   * Limits the results to the kind of user specified.
   *
   * <p>* `all` returns every kind of user for whom the `login` or `name` partially matches the
   * `filter_term`. It will only return an external user if the login matches the `filter_term`
   * completely, and in that case it will only return that user. * `managed` returns all managed and
   * app users for whom the `login` or `name` partially matches the `filter_term`. * `external`
   * returns all external users for whom the `login` matches the `filter_term` exactly.
   */
  public EnumWrapper<GetUsersQueryParamsUserTypeField> userType;

  /**
   * Limits the results to app users with the given `external_app_user_id` value.
   *
   * <p>When creating an app user, an `external_app_user_id` value can be set. This value can then
   * be used in this endpoint to find any users that match that `external_app_user_id` value.
   */
  public String externalAppUserId;

  /**
   * A comma-separated list of attributes to include in the response. This can be used to request
   * fields that are not normally returned in a standard response.
   *
   * <p>Be aware that specifying this parameter will have the effect that none of the standard
   * fields are returned in the response unless explicitly specified, instead only fields for the
   * mini representation are returned, additional to the fields requested.
   */
  public List<String> fields;

  /**
   * The offset of the item at which to begin the response.
   *
   * <p>Queries with offset parameter value exceeding 10000 will be rejected with a 400 response.
   */
  public Long offset;

  /** The maximum number of items to return per page. */
  public Long limit;

  /**
   * Specifies whether to use marker-based pagination instead of offset-based pagination. Only one
   * pagination method can be used at a time.
   *
   * <p>By setting this value to true, the API will return a `marker` field that can be passed as a
   * parameter to this endpoint to get the next page of the response.
   */
  public Boolean usemarker;

  /**
   * Defines the position marker at which to begin returning results. This is used when paginating
   * using marker-based pagination.
   *
   * <p>This requires `usemarker` to be set to `true`.
   */
  public String marker;

  public GetUsersQueryParams() {}

  protected GetUsersQueryParams(Builder builder) {
    this.filterTerm = builder.filterTerm;
    this.userType = builder.userType;
    this.externalAppUserId = builder.externalAppUserId;
    this.fields = builder.fields;
    this.offset = builder.offset;
    this.limit = builder.limit;
    this.usemarker = builder.usemarker;
    this.marker = builder.marker;
  }

  public String getFilterTerm() {
    return filterTerm;
  }

  public EnumWrapper<GetUsersQueryParamsUserTypeField> getUserType() {
    return userType;
  }

  public String getExternalAppUserId() {
    return externalAppUserId;
  }

  public List<String> getFields() {
    return fields;
  }

  public Long getOffset() {
    return offset;
  }

  public Long getLimit() {
    return limit;
  }

  public Boolean getUsemarker() {
    return usemarker;
  }

  public String getMarker() {
    return marker;
  }

  public static class Builder {

    protected String filterTerm;

    protected EnumWrapper<GetUsersQueryParamsUserTypeField> userType;

    protected String externalAppUserId;

    protected List<String> fields;

    protected Long offset;

    protected Long limit;

    protected Boolean usemarker;

    protected String marker;

    public Builder filterTerm(String filterTerm) {
      this.filterTerm = filterTerm;
      return this;
    }

    public Builder userType(GetUsersQueryParamsUserTypeField userType) {
      this.userType = new EnumWrapper<GetUsersQueryParamsUserTypeField>(userType);
      return this;
    }

    public Builder userType(EnumWrapper<GetUsersQueryParamsUserTypeField> userType) {
      this.userType = userType;
      return this;
    }

    public Builder externalAppUserId(String externalAppUserId) {
      this.externalAppUserId = externalAppUserId;
      return this;
    }

    public Builder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public Builder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public Builder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public Builder usemarker(Boolean usemarker) {
      this.usemarker = usemarker;
      return this;
    }

    public Builder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetUsersQueryParams build() {
      return new GetUsersQueryParams(this);
    }
  }
}
