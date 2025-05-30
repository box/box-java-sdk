package com.box.sdkgen.schemas.signtemplate;

import com.box.sdkgen.internal.SerializableObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class SignTemplateCustomBrandingField extends SerializableObject {

  @JsonProperty("company_name")
  protected String companyName;

  @JsonProperty("logo_uri")
  protected String logoUri;

  @JsonProperty("branding_color")
  protected String brandingColor;

  @JsonProperty("email_footer_text")
  protected String emailFooterText;

  public SignTemplateCustomBrandingField() {
    super();
  }

  protected SignTemplateCustomBrandingField(SignTemplateCustomBrandingFieldBuilder builder) {
    super();
    this.companyName = builder.companyName;
    this.logoUri = builder.logoUri;
    this.brandingColor = builder.brandingColor;
    this.emailFooterText = builder.emailFooterText;
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

  public static class SignTemplateCustomBrandingFieldBuilder {

    protected String companyName;

    protected String logoUri;

    protected String brandingColor;

    protected String emailFooterText;

    public SignTemplateCustomBrandingFieldBuilder companyName(String companyName) {
      this.companyName = companyName;
      return this;
    }

    public SignTemplateCustomBrandingFieldBuilder logoUri(String logoUri) {
      this.logoUri = logoUri;
      return this;
    }

    public SignTemplateCustomBrandingFieldBuilder brandingColor(String brandingColor) {
      this.brandingColor = brandingColor;
      return this;
    }

    public SignTemplateCustomBrandingFieldBuilder emailFooterText(String emailFooterText) {
      this.emailFooterText = emailFooterText;
      return this;
    }

    public SignTemplateCustomBrandingField build() {
      return new SignTemplateCustomBrandingField(this);
    }
  }
}
