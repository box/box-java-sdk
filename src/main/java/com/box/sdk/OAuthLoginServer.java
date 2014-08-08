package com.box.sdk;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.security.SecureRandom;

public class OAuthLoginServer {
    private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();
    private static final int MIN_PORT = 49152;
    private static final int MAX_PORT = 65535;
    private static final int BACKLOG = 8;
    private static final int STATE_LENGTH = 20;

    private final ServerSocket socket;
    private final int port;
    private final String clientID;
    private final String state;

    public OAuthLoginServer(String clientID) throws IOException {
        this.clientID = clientID;

        SecureRandom random = new SecureRandom();
        byte[] buffer = new byte[STATE_LENGTH];
        random.nextBytes(buffer);
        this.state = bytesToHex(buffer);

        InetAddress address = InetAddress.getLocalHost();
        ServerSocket socket = null;
        int port;
        for (port = MIN_PORT; port < MAX_PORT; ++port) {
            try {
                socket = new ServerSocket(port, BACKLOG, address);
                port = port;
                break;
            } catch (IOException e) {
                continue;
            }
        }

        this.socket = socket;
        this.port = port;
    }

    public String getRedirectURI() {
        return String.format("https://localhost:{0}", this.port);
    }

    public String getLoginURL() {
        return String.format("https://app.box.com/api/oauth2/authorize?response_type=code&client_id={0}&state={1}",
            this.clientID, this.state);
    }

    public void listen() throws IOException {
        this.socket.accept();
    }

    public void stop() throws IOException {
        this.socket.close();
    }

    private static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int i = 0; i < bytes.length; ++i) {
            int v = bytes[i] & 0xFF;
            hexChars[i * 2] = HEX_ARRAY[v >>> 4];
            hexChars[i * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }
}
