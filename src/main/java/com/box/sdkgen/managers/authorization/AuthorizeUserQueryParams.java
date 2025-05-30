package com.box.sdkgen.managers.authorization;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class AuthorizeUserQueryParams {

  public final EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType;

  public final String clientId;

  public String redirectUri;

  public String state;

  public String scope;

  public AuthorizeUserQueryParams(
      EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType, String clientId) {
    this.responseType = responseType;
    this.clientId = clientId;
  }

  public AuthorizeUserQueryParams(
      AuthorizeUserQueryParamsResponseTypeField responseType, String clientId) {
    this.responseType = new EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>(responseType);
    this.clientId = clientId;
  }

  protected AuthorizeUserQueryParams(AuthorizeUserQueryParamsBuilder builder) {
    this.responseType = builder.responseType;
    this.clientId = builder.clientId;
    this.redirectUri = builder.redirectUri;
    this.state = builder.state;
    this.scope = builder.scope;
  }

  public EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> getResponseType() {
    return responseType;
  }

  public String getClientId() {
    return clientId;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public String getState() {
    return state;
  }

  public String getScope() {
    return scope;
  }

  public static class AuthorizeUserQueryParamsBuilder {

    protected final EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType;

    protected final String clientId;

    protected String redirectUri;

    protected String state;

    protected String scope;

    public AuthorizeUserQueryParamsBuilder(
        EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType, String clientId) {
      this.responseType = responseType;
      this.clientId = clientId;
    }

    public AuthorizeUserQueryParamsBuilder(
        AuthorizeUserQueryParamsResponseTypeField responseType, String clientId) {
      this.responseType = new EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>(responseType);
      this.clientId = clientId;
    }

    public AuthorizeUserQueryParamsBuilder redirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    public AuthorizeUserQueryParamsBuilder state(String state) {
      this.state = state;
      return this;
    }

    public AuthorizeUserQueryParamsBuilder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public AuthorizeUserQueryParams build() {
      return new AuthorizeUserQueryParams(this);
    }
  }
}
