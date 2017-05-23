package com.box.sdk;

import com.eclipsesource.json.JsonArray;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;

/**
 *
 */
public class ScopedToken extends BoxJSONObject {
    private String accessToken;
    private long expiresIn;
    private String tokenType;
    private String issuedTokenType;
    private JsonArray restrictedTo;
    private long obtainedAt;

    /**
     * Constructs a ScopedToken object from a parsed JsonObject.
     * @param jsonObject parsed json object from response of token exchange
     */
    public ScopedToken(JsonObject jsonObject) {
        super(jsonObject);
    }

    @Override
    protected void parseJSONMember(JsonObject.Member member) {
        String memberName = member.getName();
        JsonValue value = member.getValue();
        if (memberName.equals("access_token")) {
            this.accessToken = value.asString();
        } else if (memberName.equals("token_type")) {
            this.tokenType = value.asString();
        } else if (memberName.equals("issued_token_type")) {
            this.issuedTokenType = value.asString();
        } else if (memberName.equals("restricted_to")) {
            this.restrictedTo = value.asArray();
        }
    }

    /**
     * Gets the lower scoped token.
     * @return lower scoped access token
     */
    public String getAccessToken() {
        return this.accessToken;
    }

    /**
     * Gets the expires in time in milliseconds.
     * @return the time in milliseconds after which the token expires
     */
    public long getExpiresIn() {
        return this.expiresIn;
    }

    /**
     * Gets the token type.
     * @return the token type
     */
    public String getTokenType() {
        return this.tokenType;
    }

    /**
     * Gets the issued token type as per ietf namespace.
     * @return the issued token type as per ietf namespace
     */
    public String getIssuedTokenType() {
        return this.issuedTokenType;
    }

    /**
     * Gets the restricted to information for the scoped token.
     * @return the restricted to information
     */
    public JsonArray getRestrictedTo() {
        return this.restrictedTo;
    }

    /**
     * Gets the time in milliseconds when the token was obtained.
     * @return the time in millinseconds when the token was obtained
     */
    public long getObtainedAt() {
        return this.obtainedAt;
    }

    /**
     * Sets the time in milliseconds in which this token will expire.
     * @param milliseconds the number of milliseconds for which the access token is valid.
     */
    public void setExpiresIn(long milliseconds) {
        this.expiresIn = milliseconds;
    }

    /**
     *
     * @param milliseconds the time in milliseconds at which it was obtained
     */
    public void setObtainedAt(long milliseconds) {
        this.obtainedAt = milliseconds;
    }
}
