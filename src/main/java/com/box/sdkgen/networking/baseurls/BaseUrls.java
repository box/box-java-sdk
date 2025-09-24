package com.box.sdkgen.networking.baseurls;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class BaseUrls {
  @JsonProperty("base_url")
  protected String baseUrl = "https://api.box.com";

  @JsonProperty("upload_url")
  protected String uploadUrl = "https://upload.box.com/api";

  @JsonProperty("oauth2_url")
  protected String oauth2Url = "https://account.box.com/api/oauth2";

  public BaseUrls() {}

  protected BaseUrls(Builder builder) {
    this.baseUrl = builder.baseUrl;
    this.uploadUrl = builder.uploadUrl;
    this.oauth2Url = builder.oauth2Url;
  }

  public String getBaseUrl() {
    return baseUrl;
  }

  public String getUploadUrl() {
    return uploadUrl;
  }

  public String getOauth2Url() {
    return oauth2Url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BaseUrls casted = (BaseUrls) o;
    return Objects.equals(baseUrl, casted.baseUrl)
        && Objects.equals(uploadUrl, casted.uploadUrl)
        && Objects.equals(oauth2Url, casted.oauth2Url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(baseUrl, uploadUrl, oauth2Url);
  }

  @Override
  public String toString() {
    return "BaseUrls{"
        + "baseUrl='"
        + baseUrl
        + '\''
        + ", "
        + "uploadUrl='"
        + uploadUrl
        + '\''
        + ", "
        + "oauth2Url='"
        + oauth2Url
        + '\''
        + "}";
  }

  public static class Builder {

    protected String baseUrl = "https://api.box.com";

    protected String uploadUrl = "https://upload.box.com/api";

    protected String oauth2Url = "https://account.box.com/api/oauth2";

    public Builder baseUrl(String baseUrl) {
      this.baseUrl = baseUrl;
      return this;
    }

    public Builder uploadUrl(String uploadUrl) {
      this.uploadUrl = uploadUrl;
      return this;
    }

    public Builder oauth2Url(String oauth2Url) {
      this.oauth2Url = oauth2Url;
      return this;
    }

    public BaseUrls build() {
      return new BaseUrls(this);
    }
  }
}
