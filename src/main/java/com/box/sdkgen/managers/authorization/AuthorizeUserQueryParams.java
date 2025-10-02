package com.box.sdkgen.managers.authorization;

import com.box.sdkgen.serialization.json.EnumWrapper;

public class AuthorizeUserQueryParams {

  /** The type of response we'd like to receive. */
  public final EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType;

  /**
   * The Client ID of the application that is requesting to authenticate the user. To get the Client
   * ID for your application, log in to your Box developer console and click the **Edit
   * Application** link for the application you're working with. In the OAuth 2.0 Parameters section
   * of the configuration page, find the item labelled `client_id`. The text of that item is your
   * application's Client ID.
   */
  public final String clientId;

  /**
   * The URI to which Box redirects the browser after the user has granted or denied the application
   * permission. This URI match one of the redirect URIs in the configuration of your application.
   * It must be a valid HTTPS URI and it needs to be able to handle the redirection to complete the
   * next step in the OAuth 2.0 flow. Although this parameter is optional, it must be a part of the
   * authorization URL if you configured multiple redirect URIs for the application in the developer
   * console. A missing parameter causes a `redirect_uri_missing` error after the user grants
   * application access.
   */
  public String redirectUri;

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

  public AuthorizeUserQueryParams(
      AuthorizeUserQueryParamsResponseTypeField responseType, String clientId) {
    this.responseType = new EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>(responseType);
    this.clientId = clientId;
  }

  public AuthorizeUserQueryParams(
      EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType, String clientId) {
    this.responseType = responseType;
    this.clientId = clientId;
  }

  protected AuthorizeUserQueryParams(Builder builder) {
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

  public static class Builder {

    protected final EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType;

    protected final String clientId;

    protected String redirectUri;

    protected String state;

    protected String scope;

    public Builder(AuthorizeUserQueryParamsResponseTypeField responseType, String clientId) {
      this.responseType = new EnumWrapper<AuthorizeUserQueryParamsResponseTypeField>(responseType);
      this.clientId = clientId;
    }

    public Builder(
        EnumWrapper<AuthorizeUserQueryParamsResponseTypeField> responseType, String clientId) {
      this.responseType = responseType;
      this.clientId = clientId;
    }

    public Builder redirectUri(String redirectUri) {
      this.redirectUri = redirectUri;
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

    public AuthorizeUserQueryParams build() {
      return new AuthorizeUserQueryParams(this);
    }
  }
}
