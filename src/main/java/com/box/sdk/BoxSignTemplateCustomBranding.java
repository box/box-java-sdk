package com.box.sdk;

/**
 * Custom branding applied to notifications and signature requests.
 */
public class BoxSignTemplateCustomBranding {
    private final String brandingColor;
    private final String companyName;
    private final String emailFooterText;
    private final String logoUri;

    /**
     * Constructs a BoxSignTemplateCustomBranding object with the provided information.
     *
     * @param brandingColor   the branding color.
     * @param companyName     the company name.
     * @param emailFooterText the email footer text.
     * @param logoUri         the logo URI.
     */
    public BoxSignTemplateCustomBranding(String brandingColor, String companyName, String emailFooterText,
                                         String logoUri) {
        this.brandingColor = brandingColor;
        this.companyName = companyName;
        this.emailFooterText = emailFooterText;
        this.logoUri = logoUri;
    }

    /**
     * Gets the branding color.
     *
     * @return the branding color.
     */
    public String getBrandingColor() {
        return this.brandingColor;
    }

    /**
     * Gets the company name.
     *
     * @return the company name.
     */
    public String getCompanyName() {
        return this.companyName;
    }

    /**
     * Gets the email footer text.
     *
     * @return the email footer text.
     */
    public String getEmailFooterText() {
        return this.emailFooterText;
    }

    /**
     * Gets the logo URI.
     *
     * @return the logo URI.
     */
    public String getLogoUri() {
        return this.logoUri;
    }
}
