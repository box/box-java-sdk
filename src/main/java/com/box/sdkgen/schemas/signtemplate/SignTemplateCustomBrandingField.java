package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.Nullable;
import com.box.sdkgen.internal.NullableFieldTracker;
import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

@JsonFilter("nullablePropertyFilter")
public class SignTemplateCustomBrandingField extends SerializableObject {

  @JsonProperty("company_name")
  @Nullable
  protected String companyName;

  @JsonProperty("logo_uri")
  @Nullable
  protected String logoUri;

  @JsonProperty("branding_color")
  @Nullable
  protected String brandingColor;

  @JsonProperty("email_footer_text")
  @Nullable
  protected String emailFooterText;

  public SignTemplateCustomBrandingField() {
    super();
  }

  protected SignTemplateCustomBrandingField(Builder builder) {
    super();
    this.companyName = builder.companyName;
    this.logoUri = builder.logoUri;
    this.brandingColor = builder.brandingColor;
    this.emailFooterText = builder.emailFooterText;
    markNullableFieldsAsSet(builder.getExplicitlySetNullableFields());
  }

  public String getCompanyName() {
    return companyName;
  }

  public String getLogoUri() {
    return logoUri;
  }

  public String getBrandingColor() {
    return brandingColor;
  }

  public String getEmailFooterText() {
    return emailFooterText;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SignTemplateCustomBrandingField casted = (SignTemplateCustomBrandingField) o;
    return Objects.equals(companyName, casted.companyName)
        && Objects.equals(logoUri, casted.logoUri)
        && Objects.equals(brandingColor, casted.brandingColor)
        && Objects.equals(emailFooterText, casted.emailFooterText);
  }

  @Override
  public int hashCode() {
    return Objects.hash(companyName, logoUri, brandingColor, emailFooterText);
  }

  @Override
  public String toString() {
    return "SignTemplateCustomBrandingField{"
        + "companyName='"
        + companyName
        + '\''
        + ", "
        + "logoUri='"
        + logoUri
        + '\''
        + ", "
        + "brandingColor='"
        + brandingColor
        + '\''
        + ", "
        + "emailFooterText='"
        + emailFooterText
        + '\''
        + "}";
  }

  public static class Builder extends NullableFieldTracker {

    protected String companyName;

    protected String logoUri;

    protected String brandingColor;

    protected String emailFooterText;

    public Builder companyName(String companyName) {
      this.companyName = companyName;
      this.markNullableFieldAsSet("company_name");
      return this;
    }

    public Builder logoUri(String logoUri) {
      this.logoUri = logoUri;
      this.markNullableFieldAsSet("logo_uri");
      return this;
    }

    public Builder brandingColor(String brandingColor) {
      this.brandingColor = brandingColor;
      this.markNullableFieldAsSet("branding_color");
      return this;
    }

    public Builder emailFooterText(String emailFooterText) {
      this.emailFooterText = emailFooterText;
      this.markNullableFieldAsSet("email_footer_text");
      return this;
    }

    public SignTemplateCustomBrandingField build() {
      return new SignTemplateCustomBrandingField(this);
    }
  }
}
