package com.box.sdk;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.PrivateKey;

import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.openssl.PEMDecryptorProvider;
import org.bouncycastle.openssl.PEMEncryptedKeyPair;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.openssl.jcajce.JcePEMDecryptorProviderBuilder;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import com.eclipsesource.json.JsonObject;

/**
 * Represents an authenticated Box Developer Edition connection to the Box API.
 *
 * <p>This class handles everything for Box Developer Edition that isn't already handled by BoxAPIConnection.</p>
 */
public class BoxDeveloperEditionAPIConnection extends BoxAPIConnection {

    private static final String JWT_AUDIENCE = "https://api.box.com/oauth2/token";

    private final String entityID;
    private final DeveloperEditionEntityType entityType;
    private final EncryptionAlgorithm encryptionAlgorithm;
    private final String publicKeyID;
    private final String privateKey;
    private final String privateKeyPassword;

    private IAccessTokenCache accessTokenCache;

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     */
    public BoxDeveloperEditionAPIConnection(String accessToken) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionAPIConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when refreshing the access token.
     * @param  clientSecret the client secret to use when refreshing the access token.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     * @param  refreshToken an initial refresh token to use when refreshing the access token.
     */
    public BoxDeveloperEditionAPIConnection(String clientID, String clientSecret, String accessToken,
        String refreshToken) {

        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionAPIConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret the client secret to use when exchanging the auth code for an access token.
     * @param  authCode     an auth code obtained from the first half of the OAuth process.
     */
    public BoxDeveloperEditionAPIConnection(String clientID, String clientSecret, String authCode) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionAPIConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when requesting an access token.
     * @param  clientSecret the client secret to use when requesting an access token.
     */
    public BoxDeveloperEditionAPIConnection(String clientID, String clientSecret) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionAPIConnection.");
    }

    /**
     * Constructs a new BoxDeveloperEditionAPIConnection.
     * @param entityId             enterprise ID or a user ID.
     * @param entityType           the type of entityId.
     * @param clientID             the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret         the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref       the encryption preferences for signing the JWT.
     */
    public BoxDeveloperEditionAPIConnection(String entityId, DeveloperEditionEntityType entityType,
        String clientID, String clientSecret, JWTEncryptionPreferences encryptionPref) {

        this(entityId, entityType, clientID, clientSecret, encryptionPref, null);
    }

    public BoxDeveloperEditionAPIConnection(String entityId, DeveloperEditionEntityType entityType,
                                            String clientID, String clientSecret,
                                            JWTEncryptionPreferences encryptionPref,
                                            IAccessTokenCache accessTokenCache) {

        super(clientID, clientSecret);

        this.entityID = entityId;
        this.entityType = entityType;
        this.publicKeyID = encryptionPref.getPublicKeyID();
        this.privateKey = encryptionPref.getPrivateKey();
        this.privateKeyPassword = encryptionPref.getPrivateKeyPassword();
        this.encryptionAlgorithm = encryptionPref.getEncryptionAlgorithm();
        this.accessTokenCache = accessTokenCache;
    }

    /**
     * Creates a new Box Developer Edition connection with enterprise token.
     * @param enterpriseId          the enterprise ID to use for requesting access token.
     * @param clientId              the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret          the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref        the encryption preferences for signing the JWT.
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(String enterpriseId, String clientId,
        String clientSecret, JWTEncryptionPreferences encryptionPref) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(enterpriseId,
            DeveloperEditionEntityType.ENTERPRISE, clientId, clientSecret, encryptionPref);

        connection.authenticate();

        return connection;
    }

    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(String enterpriseId, String clientId,
            String clientSecret, JWTEncryptionPreferences encryptionPref, IAccessTokenCache accessTokenCache) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(enterpriseId,
                DeveloperEditionEntityType.ENTERPRISE, clientId, clientSecret, encryptionPref, accessTokenCache);

        connection.tryRestoreUsingAccessTokenCache();

        return connection;
    }

    /**
     * Creates a new Box Developer Edition connection with App User token.
     * @param userId                the user ID to use for an App User.
     * @param clientId              the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret          the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref        the encryption preferences for signing the JWT.
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppUserConnection(String userId, String clientId,
        String clientSecret, JWTEncryptionPreferences encryptionPref) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(userId,
            DeveloperEditionEntityType.USER, clientId, clientSecret, encryptionPref);

        connection.authenticate();

        return connection;
    }

    public static BoxDeveloperEditionAPIConnection getAppUserConnection(String userId, String clientId,
        String clientSecret, JWTEncryptionPreferences encryptionPref, IAccessTokenCache accessTokenCache) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(userId,
                DeveloperEditionEntityType.USER, clientId, clientSecret, encryptionPref, accessTokenCache);

        connection.tryRestoreUsingAccessTokenCache();

        return connection;
    }

    public String getAccessTokenCacheInfo() {
        return String.format("%s/%s/%s", this.clientID, this.entityType.toString(), this.entityID);
    }

    public void tryRestoreUsingAccessTokenCache() {
        if (this.accessTokenCache == null) {
            //no cache specified so force authentication
            this.authenticate();
        } else {
            String cachedTokenInfo = this.accessTokenCache.fetchAccessTokenInfo(this.getAccessTokenCacheInfo());
            if (cachedTokenInfo == null) {
                //not found; probably first time for this client config so authenticate; info will be cached
                this.authenticate();
            } else {
                //pull access token cache info; authentication will occur as needed (if token is expired)
                JsonObject json = JsonObject.readFrom(cachedTokenInfo);
                this.setAccessToken(json.get("accessToken").asString());
                this.setLastRefresh(json.get("lastRefresh").asLong());
                this.setExpires(json.get("expires").asLong());
            }
        }
    }

    /**
     * Disabling the non-Box Developer Edition authenticate method.
     * @param authCode an auth code obtained from the first half of the OAuth process.
     */
    public void authenticate(String authCode) {
        throw new BoxAPIException("BoxDeveloperEditionAPIConnection does not allow authenticating with an auth code.");
    }

    /**
     * Authenticates the API connection for Box Developer Edition.
     */
    public void authenticate() {
        URL url = null;
        try {
            url = new URL(this.getTokenURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid token URL indicates a bug in the SDK.", e);
        }

        String jwtAssertion = this.constructJWTAssertion();

        String urlParameters = String.format("grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer"
                                           + "&client_id=%s&client_secret=%s&assertion=%s",
            this.getClientID(), this.getClientSecret(), jwtAssertion);

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.setAccessToken(jsonObject.get("access_token").asString());
        this.setLastRefresh(System.currentTimeMillis());
        this.setExpires(jsonObject.get("expires_in").asLong() * 1000);

        //if token cache is specified, save to cache
        if (this.accessTokenCache != null) {
            String key = this.getAccessTokenCacheInfo();
            JsonObject accessTokenCacheInfo = new JsonObject()
                    .add("accessToken", this.accessToken)
                    .add("lastRefresh", this.lastRefresh)
                    .add("expires", this.expires);

            this.accessTokenCache.cacheAccessTokenInfo(key, accessTokenCacheInfo.toString());
        }
    }

    /**
     * BoxDeveloperEditionAPIConnection can always refresh, but this method is required elsewhere.
     * @return true always.
     */
    public boolean canRefresh() {
        return true;
    }

    /**
     * Refresh's this connection's access token using Box Developer Edition.
     * @throws IllegalStateException if this connection's access token cannot be refreshed.
     */
    public void refresh() {
        this.getRefreshLock().writeLock().lock();

        try {
            this.authenticate();
        } catch (BoxAPIException e) {
            this.notifyError(e);
            this.getRefreshLock().writeLock().unlock();
            throw e;
        }

        this.notifyRefresh();
        this.getRefreshLock().writeLock().unlock();
    }

    private String constructJWTAssertion() {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(this.getClientID());
        claims.setAudience(JWT_AUDIENCE);
        claims.setExpirationTimeMinutesInTheFuture(1.0f);
        claims.setSubject(this.entityID);
        claims.setClaim("box_sub_type", this.entityType.toString());
        claims.setGeneratedJwtId(64);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(this.decryptPrivateKey());
        jws.setAlgorithmHeaderValue(this.getAlgorithmIdentifier());
        jws.setHeader("typ", "JWT");
        if ((this.publicKeyID != null) && !this.publicKeyID.isEmpty()) {
            jws.setHeader("kid", this.publicKeyID);
        }

        String assertion;

        try {
            assertion = jws.getCompactSerialization();
        } catch (JoseException e) {
            throw new BoxAPIException("Error serializing JSON Web Token assertion.", e);
        }

        return assertion;
    }

    private String getAlgorithmIdentifier() {
        String algorithmId = AlgorithmIdentifiers.RSA_USING_SHA256;
        switch (this.encryptionAlgorithm) {
            case RSA_SHA_384:
                algorithmId = AlgorithmIdentifiers.RSA_USING_SHA384;
                break;
            case RSA_SHA_512:
                algorithmId = AlgorithmIdentifiers.RSA_USING_SHA512;
                break;
            case RSA_SHA_256:
            default:
                break;
        }

        return algorithmId;
    }

    private PrivateKey decryptPrivateKey() {
        PrivateKey decryptedPrivateKey;

        try {
            PEMParser keyReader = new PEMParser(new StringReader(this.privateKey));
            Object keyPair = keyReader.readObject();
            keyReader.close();

            if (keyPair instanceof PEMEncryptedKeyPair) {
                JcePEMDecryptorProviderBuilder builder = new JcePEMDecryptorProviderBuilder();
                PEMDecryptorProvider decryptionProvider = builder.build(this.privateKeyPassword.toCharArray());
                keyPair = ((PEMEncryptedKeyPair) keyPair).decryptKeyPair(decryptionProvider);
            }

            PrivateKeyInfo keyInfo = ((PEMKeyPair) keyPair).getPrivateKeyInfo();
            decryptedPrivateKey = (new JcaPEMKeyConverter()).getPrivateKey(keyInfo);
        } catch (IOException e) {
            throw new BoxAPIException("Error parsing private key for Box Developer Edition.", e);
        }

        return decryptedPrivateKey;
    }

    public interface IAccessTokenCache {
        void cacheAccessTokenInfo(String key, String value);
        String fetchAccessTokenInfo(String key);
    }
}
