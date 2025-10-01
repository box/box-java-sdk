package com.box.sdkgen.schemas.filefull;

import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class FileFullRepresentationsEntriesContentField extends SerializableObject {

  /**
   * The download URL that can be used to fetch the representation. Make sure to make an
   * authenticated API call to this endpoint.
   *
   * <p>This URL is a template and will require the `{+asset_path}` to be replaced by a path. In
   * general, for unpaged representations it can be replaced by an empty string.
   *
   * <p>For paged representations, replace the `{+asset_path}` with the page to request plus the
   * extension for the file, for example `1.pdf`.
   *
   * <p>When requesting the download URL the following additional query params can be passed along.
   *
   * <p>* `set_content_disposition_type` - Sets the `Content-Disposition` header in the API response
   * with the specified disposition type of either `inline` or `attachment`. If not supplied, the
   * `Content-Disposition` header is not included in the response.
   *
   * <p>* `set_content_disposition_filename` - Allows the application to define the representation's
   * file name used in the `Content-Disposition` header. If not defined, the filename is derived
   * from the source file name in Box combined with the extension of the representation.
   */
  @JsonProperty("url_template")
  protected String urlTemplate;

  public FileFullRepresentationsEntriesContentField() {
    super();
  }

  protected FileFullRepresentationsEntriesContentField(Builder builder) {
    super();
    this.urlTemplate = builder.urlTemplate;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getUrlTemplate() {
    return urlTemplate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FileFullRepresentationsEntriesContentField casted =
        (FileFullRepresentationsEntriesContentField) o;
    return Objects.equals(urlTemplate, casted.urlTemplate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(urlTemplate);
  }

  @Override
  public String toString() {
    return "FileFullRepresentationsEntriesContentField{"
        + "urlTemplate='"
        + urlTemplate
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String urlTemplate;

    public Builder urlTemplate(String urlTemplate) {
      this.urlTemplate = urlTemplate;
      return this;
    }

    public FileFullRepresentationsEntriesContentField build() {
      return new FileFullRepresentationsEntriesContentField(this);
    }
  }
}
