package com.box.sdkgen.managers.termsofservices;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class GetTermsOfServiceQueryParams {

  public EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> tosType;

  public GetTermsOfServiceQueryParams() {}

  protected GetTermsOfServiceQueryParams(Builder builder) {
    this.tosType = builder.tosType;
  }

  public EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> getTosType() {
    return tosType;
  }

  public static class Builder {

    protected EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> tosType;

    public Builder tosType(GetTermsOfServiceQueryParamsTosTypeField tosType) {
      this.tosType = new EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField>(tosType);
      return this;
    }

    public Builder tosType(EnumWrapper<GetTermsOfServiceQueryParamsTosTypeField> tosType) {
      this.tosType = tosType;
      return this;
    }

    public GetTermsOfServiceQueryParams build() {
      return new GetTermsOfServiceQueryParams(this);
    }
  }
}
