package com.box.sdk;

import javax.net.ssl.X509TrustManager;

class TrustAllTrustManager implements X509TrustManager {
  @Override
  public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

  @Override
  public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {}

  @Override
  public java.security.cert.X509Certificate[] getAcceptedIssuers() {
    return new java.security.cert.X509Certificate[] {};
  }
}
