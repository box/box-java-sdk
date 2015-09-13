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
public class BoxDeveloperEditionConnection extends BoxAPIConnection {
    private final String entityID;
    private final String entityType;
    private final String privateKey;
    private final String privateKeyPassword;

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     */
    public BoxDeveloperEditionConnection(String accessToken) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when refreshing the access token.
     * @param  clientSecret the client secret to use when refreshing the access token.
     * @param  accessToken  an initial access token to use for authenticating with the API.
     * @param  refreshToken an initial refresh token to use when refreshing the access token.
     */
    public BoxDeveloperEditionConnection(String clientID, String clientSecret, String accessToken,
        String refreshToken) {

        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret the client secret to use when exchanging the auth code for an access token.
     * @param  authCode     an auth code obtained from the first half of the OAuth process.
     */
    public BoxDeveloperEditionConnection(String clientID, String clientSecret, String authCode) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionConnection.");
    }

    /**
     * Disabling an invalid constructor for Box Developer Edition.
     * @param  clientID     the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret the client secret to use when exchanging the auth code for an access token.
     */
    public BoxDeveloperEditionConnection(String clientID, String clientSecret) {
        super(null);
        throw new BoxAPIException("This constructor is not available for BoxDeveloperEditionConnection.");
    }

    /**
     * Constructs a new BoxDeveloperEditionConnection.
     * @param  entityId           An enterprise ID or a user ID.
     * @param  entityType         "enterprise" or "user", corresponding to entityId.
     * @param  clientID           the client ID to use when exchanging the auth code for an access token.
     * @param  clientSecret       the client secret to use when exchanging the auth code for an access token.
     * @param  privateKey         the private key corresponding to the public key configured with Box Developer Edition.
     * @param  privateKeyPassword the password for the private key.
     */
    public BoxDeveloperEditionConnection(String entityId, String entityType, String clientID, String clientSecret,
        String privateKey, String privateKeyPassword) {

        super(clientID, clientSecret);

        this.entityID = entityId;
        this.entityType = entityType;
        this.privateKey = privateKey;
        this.privateKeyPassword = privateKeyPassword;
    }

    /**
     * Creates a new Box Developer Edition connection for App Auth.
     * @param enterpriseId       the enterprise ID to use for App Auth.
     * @param clientId           the client ID to use when refreshing the access token.
     * @param clientSecret       the client secret to use when refreshing the access token.
     * @param privateKey         the private key corresponding to the public key configured with Box Developer Edition.
     * @param privateKeyPassword the password for the private key.
     * @return                   a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionConnection getAppAuthConnection(String enterpriseId, String clientId,
        String clientSecret, String privateKey, String privateKeyPassword) {

        BoxDeveloperEditionConnection connection = new BoxDeveloperEditionConnection(enterpriseId, "enterprise",
            clientId, clientSecret, privateKey, privateKeyPassword);

        connection.authenticate();

        return connection;
    }

    /**
     * Creates a new Box Developer Edition connection for an App User.
     * @param userId             the user ID to use for an App User.
     * @param clientId           the client ID to use when refreshing the access token.
     * @param clientSecret       the client secret to use when refreshing the access token.
     * @param privateKey         the private key corresponding to the public key configured with Box Developer Edition.
     * @param privateKeyPassword the password for the private key.
     * @return a new instance of BoxAPIConnection.
     */
    public static BoxDeveloperEditionConnection getAppUserConnection(String userId, String clientId,
        String clientSecret, String privateKey, String privateKeyPassword) {

        BoxDeveloperEditionConnection connection = new BoxDeveloperEditionConnection(userId, "user", clientId,
            clientSecret, privateKey, privateKeyPassword);

        connection.authenticate();

        return connection;
    }

    /**
     * Disabling the non-Box Developer Edition authenticate method.
     * @param authCode an auth code obtained from the first half of the OAuth process.
     */
    public void authenticate(String authCode) {
        throw new BoxAPIException("BoxDeveloperEditionConnection does not allow authenticating with an auth code.");
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

        String jwtAssertion = this.jwtConstructAssertion();

        String urlParameters = String.format("grant_type=urn:ietf:params:oauth:grant-type:jwt-bearer"
                                           + "&client_id=%s&client_secret=%s&assertion=%s",
            this.getClientID(), this.getClientSecret(), jwtAssertion);

        BoxAPIRequest request = new BoxAPIRequest(this, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        String json = response.getJSON();
        System.out.println(json);

        JsonObject jsonObject = JsonObject.readFrom(json);
        this.setAccessToken(jsonObject.get("access_token").asString());
        this.setLastRefresh(System.currentTimeMillis());
        this.setExpires(jsonObject.get("expires_in").asLong() * 1000);
    }

    /**
     * BoxDeveloperEditionConnection can always refresh, but this method is required elsewhere.
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

    String jwtConstructAssertion() {
        JwtClaims claims = new JwtClaims();
        claims.setIssuer(this.getClientID());
        claims.setAudience(this.getTokenURL());
        claims.setExpirationTimeMinutesInTheFuture(0.2f);
        claims.setSubject(this.entityID);
        claims.setClaim("box_sub_type", this.entityType);
        claims.setGeneratedJwtId(64);

        JsonWebSignature jws = new JsonWebSignature();
        jws.setPayload(claims.toJson());
        jws.setKey(this.decryptPrivateKey());
        jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);

        String assertion;

        try {
            assertion = jws.getCompactSerialization();
        } catch (JoseException e) {
            throw new BoxAPIException("Error serializing JSON Web Token assertion.", e);
        }

        return assertion;
    }

    PrivateKey decryptPrivateKey() {
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
}
