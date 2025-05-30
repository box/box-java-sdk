package com.box.sdkgen.managers.users;

import com.box.sdkgen.serialization.json.EnumWrapper;
import java.util.List;

public class GetUsersQueryParams {

  public String filterTerm;

  public EnumWrapper<GetUsersQueryParamsUserTypeField> userType;

  public String externalAppUserId;

  public List<String> fields;

  public Long offset;

  public Long limit;

  public Boolean usemarker;

  public String marker;

  public GetUsersQueryParams() {}

  protected GetUsersQueryParams(GetUsersQueryParamsBuilder builder) {
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

  public static class GetUsersQueryParamsBuilder {

    protected String filterTerm;

    protected EnumWrapper<GetUsersQueryParamsUserTypeField> userType;

    protected String externalAppUserId;

    protected List<String> fields;

    protected Long offset;

    protected Long limit;

    protected Boolean usemarker;

    protected String marker;

    public GetUsersQueryParamsBuilder filterTerm(String filterTerm) {
      this.filterTerm = filterTerm;
      return this;
    }

    public GetUsersQueryParamsBuilder userType(GetUsersQueryParamsUserTypeField userType) {
      this.userType = new EnumWrapper<GetUsersQueryParamsUserTypeField>(userType);
      return this;
    }

    public GetUsersQueryParamsBuilder userType(
        EnumWrapper<GetUsersQueryParamsUserTypeField> userType) {
      this.userType = userType;
      return this;
    }

    public GetUsersQueryParamsBuilder externalAppUserId(String externalAppUserId) {
      this.externalAppUserId = externalAppUserId;
      return this;
    }

    public GetUsersQueryParamsBuilder fields(List<String> fields) {
      this.fields = fields;
      return this;
    }

    public GetUsersQueryParamsBuilder offset(Long offset) {
      this.offset = offset;
      return this;
    }

    public GetUsersQueryParamsBuilder limit(Long limit) {
      this.limit = limit;
      return this;
    }

    public GetUsersQueryParamsBuilder usemarker(Boolean usemarker) {
      this.usemarker = usemarker;
      return this;
    }

    public GetUsersQueryParamsBuilder marker(String marker) {
      this.marker = marker;
      return this;
    }

    public GetUsersQueryParams build() {
      return new GetUsersQueryParams(this);
    }
  }
}
