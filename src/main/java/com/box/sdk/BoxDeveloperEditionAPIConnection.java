package com.box.sdk;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.jwt.NumericDate;
import org.jose4j.lang.JoseException;

/**
 * Represents an authenticated Box Developer Edition connection to the Box API.
 *
 * <p>This class handles everything for Box Developer Edition that isn't already handled by BoxAPIConnection.</p>
 */
public class BoxDeveloperEditionAPIConnection extends BoxAPIConnection {

    private static final String JWT_AUDIENCE = "https://api.box.com/oauth2/token";
    private static final String JWT_GRANT_TYPE =
        "grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer&client_id=%s&client_secret=%s&assertion=%s";
    private static final int DEFAULT_MAX_ENTRIES = 100;

    private final String entityID;
    private final DeveloperEditionEntityType entityType;
    private final EncryptionAlgorithm encryptionAlgorithm;
    private final String publicKeyID;
    private final String privateKey;
    private final String privateKeyPassword;
    private BackoffCounter backoffCounter;
    private final IAccessTokenCache accessTokenCache;
    private final IPrivateKeyDecryptor privateKeyDecryptor;

    /**
     * Constructs a new BoxDeveloperEditionAPIConnection leveraging an access token cache.
     *
     * @param entityId         enterprise ID or a user ID.
     * @param entityType       the type of entityId.
     * @param clientID         the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret     the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref   the encryption preferences for signing the JWT.
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     */
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
        this.privateKeyDecryptor = encryptionPref.getPrivateKeyDecryptor();
        this.accessTokenCache = accessTokenCache;
        this.backoffCounter = new BackoffCounter(new Time());
    }

    /**
     * Constructs a new BoxDeveloperEditionAPIConnection.
     * Uses {@link InMemoryLRUAccessTokenCache} with a size of 100 to prevent unneeded
     * requests to Box for access tokens.
     *
     * @param entityId       enterprise ID or a user ID.
     * @param entityType     the type of entityId.
     * @param clientID       the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret   the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref the encryption preferences for signing the JWT.
     */
    public BoxDeveloperEditionAPIConnection(
        String entityId,
        DeveloperEditionEntityType entityType,
        String clientID,
        String clientSecret,
        JWTEncryptionPreferences encryptionPref
    ) {

        this(
            entityId,
            entityType,
            clientID,
            clientSecret,
            encryptionPref,
            new InMemoryLRUAccessTokenCache(DEFAULT_MAX_ENTRIES)
        );
    }

    /**
     * Constructs a new BoxDeveloperEditionAPIConnection.
     *
     * @param entityId         enterprise ID or a user ID.
     * @param entityType       the type of entityId.
     * @param boxConfig        box configuration settings object
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     */
    public BoxDeveloperEditionAPIConnection(String entityId, DeveloperEditionEntityType entityType,
                                            BoxConfig boxConfig, IAccessTokenCache accessTokenCache) {

        this(entityId, entityType, boxConfig.getClientId(), boxConfig.getClientSecret(),
            boxConfig.getJWTEncryptionPreferences(), accessTokenCache);
    }

    /**
     * Creates a new Box Developer Edition connection with enterprise token leveraging an access token cache.
     *
     * @param enterpriseId     the enterprise ID to use for requesting access token.
     * @param clientId         the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret     the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref   the encryption preferences for signing the JWT.
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(
        String enterpriseId,
        String clientId,
        String clientSecret,
        JWTEncryptionPreferences encryptionPref,
        IAccessTokenCache accessTokenCache
    ) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(enterpriseId,
            DeveloperEditionEntityType.ENTERPRISE, clientId, clientSecret, encryptionPref, accessTokenCache);

        connection.tryRestoreUsingAccessTokenCache();

        return connection;
    }

    /**
     * Creates a new Box Developer Edition connection with enterprise token.
     * Uses {@link InMemoryLRUAccessTokenCache} with a size of 100 to prevent unneeded
     * requests to Box for access tokens.
     *
     * @param enterpriseId   the enterprise ID to use for requesting access token.
     * @param clientId       the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret   the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref the encryption preferences for signing the JWT.
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(
        String enterpriseId,
        String clientId,
        String clientSecret,
        JWTEncryptionPreferences encryptionPref
    ) {

        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(
            enterpriseId,
            DeveloperEditionEntityType.ENTERPRISE,
            clientId,
            clientSecret,
            encryptionPref
        );

        connection.authenticate();

        return connection;
    }

    /**
     * Creates a new Box Developer Edition connection with enterprise token leveraging BoxConfig and access token cache.
     *
     * @param boxConfig        box configuration settings object
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(BoxConfig boxConfig,
                                                                              IAccessTokenCache accessTokenCache) {

        return getAppEnterpriseConnection(
            boxConfig.getEnterpriseId(),
            boxConfig.getClientId(),
            boxConfig.getClientSecret(),
            boxConfig.getJWTEncryptionPreferences(),
            accessTokenCache
        );
    }

    /**
     * Creates a new Box Developer Edition connection with enterprise token leveraging BoxConfig.
     * Uses {@link InMemoryLRUAccessTokenCache} with a size of 100 to prevent unneeded
     * requests to Box for access tokens.
     *
     * @param boxConfig box configuration settings object
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getAppEnterpriseConnection(BoxConfig boxConfig) {

        return getAppEnterpriseConnection(
            boxConfig.getEnterpriseId(),
            boxConfig.getClientId(),
            boxConfig.getClientSecret(),
            boxConfig.getJWTEncryptionPreferences()
        );
    }

    /**
     * Creates a new Box Developer Edition connection with App User or Managed User token.
     *
     * @param userId           the user ID to use for an App User.
     * @param clientId         the client ID to use when exchanging the JWT assertion for an access token.
     * @param clientSecret     the client secret to use when exchanging the JWT assertion for an access token.
     * @param encryptionPref   the encryption preferences for signing the JWT.
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getUserConnection(
        String userId,
        String clientId,
        String clientSecret,
        JWTEncryptionPreferences encryptionPref,
        IAccessTokenCache accessTokenCache
    ) {
        BoxDeveloperEditionAPIConnection connection = new BoxDeveloperEditionAPIConnection(
            userId,
            DeveloperEditionEntityType.USER,
            clientId,
            clientSecret,
            encryptionPref,
            accessTokenCache
        );

        connection.tryRestoreUsingAccessTokenCache();

        return connection;
    }

    /**
     * Creates a new Box Developer Edition connection with App User or Managed User token leveraging BoxConfig
     * and access token cache.
     *
     * @param userId           the user ID to use for an App User.
     * @param boxConfig        box configuration settings object
     * @param accessTokenCache the cache for storing access token information (to minimize fetching new tokens)
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getUserConnection(
        String userId,
        BoxConfig boxConfig,
        IAccessTokenCache accessTokenCache
    ) {
        return getUserConnection(
            userId,
            boxConfig.getClientId(),
            boxConfig.getClientSecret(),
            boxConfig.getJWTEncryptionPreferences(),
            accessTokenCache
        );
    }

    /**
     * Creates a new Box Developer Edition connection with App User or Managed User token.
     * Uses {@link InMemoryLRUAccessTokenCache} with a size of 100 to prevent unneeded
     * requests to Box for access tokens.
     *
     * @param userId    the user ID to use for an App User.
     * @param boxConfig box configuration settings object
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionAPIConnection getUserConnection(String userId, BoxConfig boxConfig) {
        return getUserConnection(
            userId,
            boxConfig.getClientId(),
            boxConfig.getClientSecret(),
            boxConfig.getJWTEncryptionPreferences(),
            new InMemoryLRUAccessTokenCache(DEFAULT_MAX_ENTRIES));
    }

    /**
     * Disabling the non-Box Developer Edition authenticate method.
     *
     * @param authCode an auth code obtained from the first half of the OAuth process.
     */
    public void authenticate(String authCode) {
        throw new BoxAPIException("BoxDeveloperEditionAPIConnection does not allow authenticating with an auth code.");
    }

    /**
     * Authenticates the API connection for Box Developer Edition.
     */
    public void authenticate() {
        URL url;
        try {
            url = new URL(this.getTokenURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid token URL indicates a bug in the SDK.", e);
        }

        this.backoffCounter.reset(this.getMaxRetryAttempts() + 1);
        NumericDate jwtTime = null;
        String jwtAssertion;
        String urlParameters;
        BoxAPIRequest request;
        String json = null;
        final BoxLogger logger = BoxLogger.defaultLogger();

        while (this.backoffCounter.getAttemptsRemaining() > 0) {
            // Reconstruct the JWT assertion, which regenerates the jti claim, with the new "current" time
            jwtAssertion = this.constructJWTAssertion(jwtTime);
            urlParameters = String.format(JWT_GRANT_TYPE, this.getClientID(), this.getClientSecret(), jwtAssertion);

            request = new BoxAPIRequest(this, url, "POST");
            request.shouldAuthenticate(false);
            request.setBody(urlParameters);

            try (BoxJSONResponse response = (BoxJSONResponse) request.sendWithoutRetry()) {
                // authentication uses form url encoded but response is JSON
                json = response.getJSON();
                break;
            } catch (BoxAPIException apiException) {
                long responseReceivedTime = System.currentTimeMillis();

                if (!this.backoffCounter.decrement()
                    || (!BoxAPIRequest.isRequestRetryable(apiException) && !isResponseRetryable(apiException))) {
                    throw apiException;
                }

                logger.warn(String.format(
                    "Retrying authentication request due to transient error status=%d body=%s",
                    apiException.getResponseCode(),
                    apiException.getResponse()
                ));

                try {
                    List<String> retryAfterHeader = apiException.getHeaders().get("Retry-After");
                    if (retryAfterHeader == null) {
                        this.backoffCounter.waitBackoff();
                    } else {
                        int retryAfterDelay = Integer.parseInt(retryAfterHeader.get(0)) * 1000;
                        this.backoffCounter.waitBackoff(retryAfterDelay);
                    }
                } catch (InterruptedException interruptedException) {
                    Thread.currentThread().interrupt();
                    throw apiException;
                }

                long endWaitTime = System.currentTimeMillis();
                long secondsSinceResponseReceived = (endWaitTime - responseReceivedTime) / 1000;

                try {
                    // Use the Date advertised by the Box server in the exception
                    // as the current time to synchronize clocks
                    jwtTime = this.getDateForJWTConstruction(apiException, secondsSinceResponseReceived);
                } catch (Exception e) {
                    throw apiException;
                }

            }
        }

        if (json == null) {
            throw new RuntimeException("Unable to read authentication response in SDK.");
        }

        JsonObject jsonObject = Json.parse(json).asObject();
        this.setAccessToken(jsonObject.get("access_token").asString());
        this.setLastRefresh(System.currentTimeMillis());
        this.setExpires(jsonObject.get("expires_in").asLong() * 1000);

        //if token cache is specified, save to cache
        if (this.accessTokenCache != null) {
            String key = this.getAccessTokenCacheKey();
            JsonObject accessTokenCacheInfo = new JsonObject()
                .add("accessToken", this.getAccessToken())
                .add("lastRefresh", this.getLastRefresh())
                .add("expires", this.getExpires());

            this.accessTokenCache.put(key, accessTokenCacheInfo.toString());
        }
    }

    private boolean isResponseRetryable(BoxAPIException apiException) {
        return BoxAPIRequest.isResponseRetryable(apiException.getResponseCode(), apiException)
            || isJtiNonUniqueError(apiException);
    }

    private boolean isJtiNonUniqueError(BoxAPIException apiException) {
        return apiException.getResponseCode() == 400
            && apiException.getResponse().contains("A unique 'jti' value is required");
    }

    private NumericDate getDateForJWTConstruction(BoxAPIException apiException, long secondsSinceResponseDateReceived) {
        NumericDate currentTime;
        List<String> responseDates = apiException.getHeaders().get("Date");

        if (responseDates != null) {
            String responseDate = responseDates.get(0);
            SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz");
            try {
                Date date = dateFormat.parse(responseDate);
                currentTime = NumericDate.fromMilliseconds(date.getTime());
                currentTime.addSeconds(secondsSinceResponseDateReceived);
            } catch (ParseException e) {
                currentTime = NumericDate.now();
            }
        } else {
            currentTime = NumericDate.now();
        }
        return currentTime;
    }

    void setBackoffCounter(BackoffCounter counter) {
        this.backoffCounter = counter;
    }

    /**
     * BoxDeveloperEditionAPIConnection can always refresh, but this method is required elsewhere.
     *
     * @return true always.
     */
    public boolean canRefresh() {
        return true;
    }

    /**
     * Refresh's this connection's access token using Box Developer Edition.
     *
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

    private String getAccessTokenCacheKey() {
        return String.format("/%s/%s/%s/%s", this.getUserAgent(), this.getClientID(),
            this.entityType.toString(), this.entityID);
    }

    /**
     * Tries to restore the connection using the access token cache.
     */
    public void tryRestoreUsingAccessTokenCache() {
        if (this.accessTokenCache == null) {
            //no cache specified so force authentication
            this.authenticate();
        } else {
            String cachedTokenInfo = this.accessTokenCache.get(this.getAccessTokenCacheKey());
            if (cachedTokenInfo == null) {
                //not found; probably first time for this client config so authenticate; info will then be cached
                this.authenticate();
            } else {
                //pull access token cache info; authentication will occur as needed (if token is expired)
                JsonObject json = Json.parse(cachedTokenInfo).asObject();
                this.setAccessToken(json.get("accessToken").asString());
                this.setLastRefresh(json.get("lastRefresh").asLong());
                this.setExpires(json.get("expires").asLong());
            }
        }
    }

    private String constructJWTAssertion(NumericDate now) {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(this.getClientID());
        claims.setAudience(JWT_AUDIENCE);
        if (now == null) {
            claims.setExpirationTimeMinutesInTheFuture(0.5f);
        } else {
            now.addSeconds(30L);
            claims.setExpirationTime(now);
        }
        claims.setSubject(this.entityID);
        claims.setClaim("box_sub_type", this.entityType.toString());
        claims.setGeneratedJwtId(64);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(this.privateKeyDecryptor.decryptPrivateKey(this.privateKey, this.privateKeyPassword));
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
}
