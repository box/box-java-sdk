package com.box.sdk.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.EncryptionAlgorithm;

public final class CreateAppUser {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String ENTERPRISE_ID = "";
    private static final String PRIVATE_KEY_FILE = "";
    private static final String PRIVATE_KEY_PASSWORD = "";
    private static final String APP_USER_NAME = "";

    private CreateAppUser() { }

    public static void main(String[] args) throws IOException {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.OFF);

        String privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
            ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, privateKey, PRIVATE_KEY_PASSWORD, EncryptionAlgorithm.RSA_SHA_256);

        BoxUser eAdmin = BoxUser.getCurrentUser(api);
        System.out.format("User Id of enterprise admin user is %s\n\n", eAdmin.getID());

        BoxUser.Info user = BoxUser.createAppUser(api, APP_USER_NAME);
        System.out.format("User created with name %s and id %s\n\n", APP_USER_NAME, user.getID());

    }
}
