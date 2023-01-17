package com.box.sdk;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

class AcceptAllHostsVerifier implements HostnameVerifier {
    @Override
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
