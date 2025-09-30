package com.box.sdk;

import static okhttp3.ConnectionSpec.CLEARTEXT;
import static okhttp3.ConnectionSpec.MODERN_TLS;

import java.util.Arrays;
import okhttp3.OkHttpClient;

class BoxAPIConnectionForTests extends BoxAPIConnection {
  BoxAPIConnectionForTests(String accessToken) {
    super(accessToken);
    configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
  }

  BoxAPIConnectionForTests(
      String clientID, String clientSecret, String accessToken, String refreshToken) {
    super(clientID, clientSecret, accessToken, refreshToken);
    configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
  }

  BoxAPIConnectionForTests(String clientID, String clientSecret, String authCode) {
    super(clientID, clientSecret, authCode);
    configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
  }

  BoxAPIConnectionForTests(String clientID, String clientSecret) {
    super(clientID, clientSecret);
    configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
  }

  BoxAPIConnectionForTests(BoxConfig boxConfig) {
    super(boxConfig);
    configureSslCertificatesValidation(new TrustAllTrustManager(), new AcceptAllHostsVerifier());
  }

  @Override
  protected OkHttpClient.Builder modifyHttpClientBuilder(OkHttpClient.Builder httpClientBuilder) {
    return httpClientBuilder.connectionSpecs(Arrays.asList(MODERN_TLS, CLEARTEXT));
  }
}
