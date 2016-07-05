package com.box.sdk;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import com.eclipsesource.json.JsonObject;

/**
 * Represents an authenticated transactional connection to the Box API.
 *
 * <p>This class handles everything for transactional API that isn't already handled by BoxAPIConnection.</p>
 */
public class BoxTransactionalAPIConnection extends BoxAPIConnection {

    private static final String SUBJECT_TOKEN_TYPE = "urn:ietf:params:oauth:token-type:access_token";
    private static final String GRANT_TYPE = "urn:ietf:params:oauth:grant-type:token-exchange";

    /**
     * Constructs a new BoxTransactionalAPIConnection that authenticates with an access token.
     * @param  accessToken a transactional auth access token.
     */
    public BoxTransactionalAPIConnection(String accessToken) {
        super(accessToken);
        super.setAutoRefresh(false);
    }

    /**
     * Request a scoped transactional token.
     * @param accessToken application access token.
     * @param scope scope of transactional token.
     * @return a BoxAPIConnection which can be used to perform transactional requests.
     */
    public static BoxAPIConnection getTransactionConnection(String accessToken, String scope) {
        return BoxTransactionalAPIConnection.getTransactionConnection(accessToken, scope, null);
    }

    /**
     * Request a scoped transactional token for a particular resource.
     * @param accessToken application access token.
     * @param scope scope of transactional token.
     * @param resource resource transactional token has access to.
     * @return a BoxAPIConnection which can be used to perform transactional requests.
     */
    public static BoxAPIConnection getTransactionConnection(String accessToken, String scope, String resource) {
        BoxAPIConnection apiConnection = new BoxAPIConnection(accessToken);

        URL url;
        try {
            url = new URL(apiConnection.getTokenURL());
        } catch (MalformedURLException e) {
            assert false : "An invalid token URL indicates a bug in the SDK.";
            throw new RuntimeException("An invalid token URL indicates a bug in the SDK.", e);
        }

        String urlParameters;
        try {
            urlParameters = String.format("grant_type=%s&subject_token=%s&subject_token_type=%s&scope=%s", GRANT_TYPE,
                    URLEncoder.encode(accessToken, "UTF-8"), SUBJECT_TOKEN_TYPE, URLEncoder.encode(scope, "UTF-8"));

            if (resource != null) {
                urlParameters += "&resource=" + URLEncoder.encode(resource, "UTF-8");
            }
        } catch (UnsupportedEncodingException e) {
            throw new BoxAPIException(
                    "An error occurred while attempting to encode url parameters for a transactional token request"
            );
        }

        BoxAPIRequest request = new BoxAPIRequest(apiConnection, url, "POST");
        request.shouldAuthenticate(false);
        request.setBody(urlParameters);

        BoxJSONResponse response = (BoxJSONResponse) request.send();
        JsonObject responseJSON = JsonObject.readFrom(response.getJSON());

        final String fileToken = responseJSON.get("access_token").asString();
        BoxTransactionalAPIConnection transactionConnection = new BoxTransactionalAPIConnection(fileToken);
        transactionConnection.setExpires(responseJSON.get("expires_in").asLong() * 1000);

        return transactionConnection;
    }

    /**
     * Disabling the non-Box Developer Edition authenticate method.
     * @param authCode an auth code obtained from the first half of the OAuth process.
     * @throws UnsupportedOperationException Box Transactional API does not support authentication with an auth code
     */
    @Override
    public void authenticate(String authCode) {
        throw new UnsupportedOperationException(
                "BoxTransactionalAPIConnection does not support the authenticate method."
        );
    }

    /**
     * BoxTransactionalAPIConnection can never refresh.
     * @return false always.
     */
    @Override
    public boolean canRefresh() {
        return false;
    }

    /**
     * Auto refresh is not available for transactional auth.
     * @param autoRefresh true to enable auto token refresh; otherwise false.
     * @throws UnsupportedOperationException Box Transactional API tokens can not be refreshed
     */
    @Override
    public void setAutoRefresh(boolean autoRefresh) {
        throw new UnsupportedOperationException(
                "BoxTransactionalAPIConnection does not support token refreshing, "
                        + "access tokens can be generated in the developer console."
        );
    }

    /**
     * Transactional auth does not support token refreshes.
     * @throws UnsupportedOperationException Box Transactional API tokens can not be refreshed
     */
    @Override
    public void refresh() {
        throw new UnsupportedOperationException(
                "BoxTransactionalAPIConnection does not support token refreshing, "
                        + "access tokens can be generated in the developer console."
        );
    }
}
