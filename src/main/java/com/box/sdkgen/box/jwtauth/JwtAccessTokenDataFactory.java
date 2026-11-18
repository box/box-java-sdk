package com.box.sdkgen.box.jwtauth;

import static com.box.sdkgen.internal.utils.UtilsManager.createJwtAssertion;
import static com.box.sdkgen.internal.utils.UtilsManager.entryOf;
import static com.box.sdkgen.internal.utils.UtilsManager.getEpochTimeInSeconds;
import static com.box.sdkgen.internal.utils.UtilsManager.getUuid;
import static com.box.sdkgen.internal.utils.UtilsManager.mapOf;

import com.box.sdkgen.internal.utils.JwtKey;
import com.box.sdkgen.internal.utils.JwtSignOptions;
import com.box.sdkgen.networking.fetchoptions.RequestDataFactory;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2Token;
import com.box.sdkgen.schemas.postoauth2token.PostOAuth2TokenGrantTypeField;
import com.box.sdkgen.serialization.json.JsonManager;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.Map;

/**
 * Builds the body of a JWT bearer access token request. Each call signs a new assertion with a
 * unique `jti`, so a retried request is not rejected as a replay.
 */
public class JwtAccessTokenDataFactory implements RequestDataFactory {

  /** JWT configuration used to sign the assertion */
  public final JWTConfig config;

  /** The ID of the user or enterprise to authenticate as */
  public String subjectId;

  /** The type of the subject ID, either 'user' or 'enterprise' */
  public String subjectType;

  public JwtAccessTokenDataFactory(JWTConfig config) {
    this.config = config;
  }

  protected JwtAccessTokenDataFactory(Builder builder) {
    this.config = builder.config;
    this.subjectId = builder.subjectId;
    this.subjectType = builder.subjectType;
  }

  /** Creates the access token request body with a newly signed JWT assertion. */
  @Override
  public JsonNode create() {
    Map<String, Object> claims =
        mapOf(
            entryOf("exp", getEpochTimeInSeconds() + 30),
            entryOf("box_sub_type", this.subjectType));
    JwtSignOptions jwtOptions =
        new JwtSignOptions(
            this.config.getAlgorithm(),
            "https://api.box.com/oauth2/token",
            this.config.getClientId(),
            this.subjectId,
            getUuid(),
            this.config.getJwtKeyId(),
            this.config.getPrivateKeyDecryptor());
    JwtKey jwtKey = new JwtKey(this.config.getPrivateKey(), this.config.getPrivateKeyPassphrase());
    String assertion = createJwtAssertion(claims, jwtKey, jwtOptions);
    PostOAuth2Token requestBody =
        new PostOAuth2Token.Builder(
                PostOAuth2TokenGrantTypeField.URN_IETF_PARAMS_OAUTH_GRANT_TYPE_JWT_BEARER)
            .assertion(assertion)
            .clientId(this.config.getClientId())
            .clientSecret(this.config.getClientSecret())
            .build();
    return JsonManager.serialize(requestBody);
  }

  public JWTConfig getConfig() {
    return config;
  }

  public static class Builder {

    protected final JWTConfig config;

    protected String subjectId;

    protected String subjectType;

    public Builder(JWTConfig config) {
      this.config = config;
    }

    public Builder subjectId(String subjectId) {
      this.subjectId = subjectId;
      return this;
    }

    public Builder subjectType(String subjectType) {
      this.subjectType = subjectType;
      return this;
    }

    public JwtAccessTokenDataFactory build() {
      return new JwtAccessTokenDataFactory(this);
    }
  }
}
