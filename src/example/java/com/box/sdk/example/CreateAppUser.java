package com.box.sdk.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxUser;
import com.box.sdk.CreateUserParams;
import com.box.sdk.EncryptionAlgorithm;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.JWTEncryptionPreferences;

public final class CreateAppUser {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String ENTERPRISE_ID = "";
    private static final String PUBLIC_KEY_ID = "";
    private static final String PRIVATE_KEY_FILE = "";
    private static final String PRIVATE_KEY_PASSWORD = "";
    private static final String APP_USER_NAME = "";
    private static final int MAX_CACHE_ENTRIES = 100;

    private CreateAppUser() { }

    public static void main(String[] args) throws IOException {
        // Turn off logging to prevent polluting the output.
        Logger.getLogger("com.box.sdk").setLevel(Level.OFF);

        String privateKey = new String(Files.readAllBytes(Paths.get(PRIVATE_KEY_FILE)));

        JWTEncryptionPreferences encryptionPref = new JWTEncryptionPreferences();
        encryptionPref.setPublicKeyID(PUBLIC_KEY_ID);
        encryptionPref.setPrivateKey(privateKey);
        encryptionPref.setPrivateKeyPassword(PRIVATE_KEY_PASSWORD);
        encryptionPref.setEncryptionAlgorithm(EncryptionAlgorithm.RSA_SHA_256);

        //It is a best practice to use an access token cache to prevent unneeded requests to Box for access tokens.
        //For production applications it is recommended to use a distributed cache like Memcached or Redis, and to
        //implement IAccessTokenCache to store and retrieve access tokens appropriately for your environment.
        IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);

        BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(
            ENTERPRISE_ID, CLIENT_ID, CLIENT_SECRET, encryptionPref, accessTokenCache);

        CreateUserParams params = new CreateUserParams();
        params.setSpaceAmount(1073741824); //1 GB
        BoxUser.Info user = BoxUser.createAppUser(api, APP_USER_NAME, params);

        System.out.format("User created with name %s and id %s\n\n", APP_USER_NAME, user.getID());
    }
}
