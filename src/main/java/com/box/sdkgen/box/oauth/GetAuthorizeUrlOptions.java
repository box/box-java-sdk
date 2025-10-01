package com.box.sdkgen.box.oauth;

/** Options of getAuthorizeUrl method */
public class GetAuthorizeUrlOptions {

  /** Box API key used for identifying the application the user is authenticating with */
  public String clientId;

  /**
   * The URI to which Box redirects the browser after the user has granted or denied the application
   * permission. This URI match one of the redirect URIs in the configuration of your application.
   */
  public String redirectUri;

  /** The type of response we would like to receive. */
  public String responseType;

  /**
   * A custom string of your choice. Box will pass the same string to the redirect URL when
   * authentication is complete. This parameter can be used to identify a user on redirect, as well
   * as protect against hijacked sessions and other exploits.
   */
  public String state;

  /**
   * A space-separated list of application scopes you'd like to authenticate the user for. This
   * defaults to all the scopes configured for the application in its configuration page.
   */
  public String scope;

  public GetAuthorizeUrlOptions() {}

  protected GetAuthorizeUrlOptions(Builder builder) {
    this.clientId = builder.clientId;
    this.redirectUri = builder.redirectUri;
    this.responseType = builder.responseType;
    this.state = builder.state;
    this.scope = builder.scope;
  }

  public String getClientId() {
    return clientId;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public String getResponseType() {
    return responseType;
  }

  public String getState() {
    return state;
  }

  public String getScope() {
    return scope;
  }

  public static class Builder {

    protected String clientId;

    protected String redirectUri;

    protected String responseType;

    protected String state;

    protected String scope;

    public Builder clientId(String clientId) {
      this.clientId = clientId;
      return this;
    }

    public Builder redirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
      return this;
    }

    public Builder responseType(String responseType) {
      this.responseType = responseType;
      return this;
    }

    public Builder state(String state) {
      this.state = state;
      return this;
    }

    public Builder scope(String scope) {
      this.scope = scope;
      return this;
    }

    public GetAuthorizeUrlOptions build() {
      return new GetAuthorizeUrlOptions(this);
    }
  }
}
