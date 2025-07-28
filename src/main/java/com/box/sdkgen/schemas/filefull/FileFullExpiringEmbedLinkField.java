package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.box.sdkgen.schemas.fileorfolderscope.FileOrFolderScope;
import com.box.sdkgen.serialization.json.EnumWrapper;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullExpiringEmbedLinkField extends SerializableObject {

  @JsonProperty("access_token")
  protected String accessToken;

  @JsonProperty("expires_in")
  protected Long expiresIn;

  @JsonDeserialize(
      using =
          FileFullExpiringEmbedLinkTokenTypeField
              .FileFullExpiringEmbedLinkTokenTypeFieldDeserializer.class)
  @JsonSerialize(
      using =
          FileFullExpiringEmbedLinkTokenTypeField.FileFullExpiringEmbedLinkTokenTypeFieldSerializer
              .class)
  @JsonProperty("token_type")
  protected EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> tokenType;

  @JsonProperty("restricted_to")
  protected List<FileOrFolderScope> restrictedTo;

  protected String url;

  public FileFullExpiringEmbedLinkField() {
    super();
  }

  protected FileFullExpiringEmbedLinkField(Builder builder) {
    super();
    this.accessToken = builder.accessToken;
    this.expiresIn = builder.expiresIn;
    this.tokenType = builder.tokenType;
    this.restrictedTo = builder.restrictedTo;
    this.url = builder.url;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getAccessToken() {
    return accessToken;
  }

  public Long getExpiresIn() {
    return expiresIn;
  }

  public EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> getTokenType() {
    return tokenType;
  }

  public List<FileOrFolderScope> getRestrictedTo() {
    return restrictedTo;
  }

  public String getUrl() {
    return url;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullExpiringEmbedLinkField casted = (FileFullExpiringEmbedLinkField) o;
    return Objects.equals(accessToken, casted.accessToken)
        && Objects.equals(expiresIn, casted.expiresIn)
        && Objects.equals(tokenType, casted.tokenType)
        && Objects.equals(restrictedTo, casted.restrictedTo)
        && Objects.equals(url, casted.url);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, expiresIn, tokenType, restrictedTo, url);
  }

  @Override
  public String toString() {
    return "FileFullExpiringEmbedLinkField{"
        + "accessToken='"
        + accessToken
        + '\''
        + ", "
        + "expiresIn='"
        + expiresIn
        + '\''
        + ", "
        + "tokenType='"
        + tokenType
        + '\''
        + ", "
        + "restrictedTo='"
        + restrictedTo
        + '\''
        + ", "
        + "url='"
        + url
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String accessToken;

    protected Long expiresIn;

    protected EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> tokenType;

    protected List<FileOrFolderScope> restrictedTo;

    protected String url;

    public Builder accessToken(String accessToken) {
      this.accessToken = accessToken;
      return this;
    }

    public Builder expiresIn(Long expiresIn) {
      this.expiresIn = expiresIn;
      return this;
    }

    public Builder tokenType(FileFullExpiringEmbedLinkTokenTypeField tokenType) {
      this.tokenType = new EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField>(tokenType);
      return this;
    }

    public Builder tokenType(EnumWrapper<FileFullExpiringEmbedLinkTokenTypeField> tokenType) {
      this.tokenType = tokenType;
      return this;
    }

    public Builder restrictedTo(List<FileOrFolderScope> restrictedTo) {
      this.restrictedTo = restrictedTo;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public FileFullExpiringEmbedLinkField build() {
      return new FileFullExpiringEmbedLinkField(this);
    }
  }
}
