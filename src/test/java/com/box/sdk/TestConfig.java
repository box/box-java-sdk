package com.box.sdk;

import com.fasterxml.jackson.core.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileReader;
import java.io.BufferedReader;

import static com.github.tomakehurst.wiremock.client.WireMock.*;


final class TestConfig {
    private static Properties configProperties = null;
    private static String accessToken = null;
    private static String refreshToken = null;
    private static String clientID = null;
    private static String clientSecret = null;
    private static String collaborator = null;
    private static String collaboratorID = null;
    private static String enterpriseID = null;
    private static String privateKey = null;
    private static String privateKeyPassword = null;
    private static String publicKeyID = null;
    private static String transactionalAccessToken = null;

    private TestConfig() { }

    public static Logger enableLogger(String levelString) {
        Level level = Level.parse(levelString);
        Logger logger = Logger.getLogger("com.box.sdk");
        logger.setLevel(level);

        boolean hasConsoleHandler = false;
        for (Handler handler : logger.getHandlers()) {
            handler.setLevel(level);
            if (handler instanceof ConsoleHandler) {
                hasConsoleHandler = true;
            }
        }

        if (!hasConsoleHandler) {
            Handler handler = new ConsoleHandler();
            handler.setLevel(level);
            logger.addHandler(handler);
        }
        return logger;
    }

    public static BoxAPIConnection getAPIConnection() {
        BoxAPIConnection api = new BoxAPIConnection("");
        api.setBaseURL("http://localhost:53621/");
        api.setBaseUploadURL("http://localhost:53621/");

        return api;
    }

    public static String getAccessToken() {
        if (accessToken == null || accessToken.equals("")) {
            accessToken = getProperty("accessToken");
        }

        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        TestConfig.accessToken = accessToken;
    }

    public static String getRefreshToken() {
        if (refreshToken == null || refreshToken.equals("")) {
            refreshToken = getProperty("refreshToken");
        }

        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        TestConfig.refreshToken = refreshToken;
    }

    public static String getClientID() {
        if (clientID == null || clientID.equals("")) {
            clientID = getProperty("clientID");
        }

        return clientID;
    }

    public static String getClientSecret() {
        if (clientSecret == null || clientSecret.equals("")) {
            clientSecret = getProperty("clientSecret");
        }

        return clientSecret;
    }

    public static String getCollaborator() {
        if (collaborator == null || collaborator.equals("")) {
            collaborator = getProperty("collaborator");
        }

        return collaborator;
    }

    public static String getCollaboratorID() {
        if (collaboratorID == null || collaboratorID.equals("")) {
            collaboratorID = getProperty("collaboratorID");
        }

        return collaboratorID;
    }

    public static String getEnterpriseID() {
        if (enterpriseID == null || enterpriseID.equals("")) {
            enterpriseID = getProperty("enterpriseID");
        }

        return enterpriseID;
    }

    public static String getPrivateKey() {
        if (privateKey == null || privateKey.equals("")) {
            privateKey = getProperty("privateKey");
        }

        return privateKey;
    }

    public static String getPrivateKeyPassword() {
        if (privateKeyPassword == null || privateKeyPassword.equals("")) {
            privateKeyPassword = getProperty("privateKeyPassword");
        }

        return privateKeyPassword;
    }

    public static String getPublicKeyID() {
        if (publicKeyID == null || publicKeyID.equals("")) {
            publicKeyID = getProperty("publicKeyID");
        }

        return publicKeyID;
    }

    public static String getTransactionalAccessToken() {
        if (transactionalAccessToken == null || transactionalAccessToken.equals("")) {
            transactionalAccessToken = getProperty("transactionalAccessToken");
        }

        return transactionalAccessToken;
    }

    private static String getProperty(String name) {
        Properties configProperties = loadProperties();
        String value = configProperties.getProperty(name);
        if (value.equals("")) {
            throw new IllegalStateException("The " + name + " property wasn't set in "
                + "src/test/config/config.properties.");
        }

        return value;
    }

    private static Properties loadProperties() {
        if (configProperties != null) {
            return configProperties;
        }

        configProperties = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream("src/test/config/config.properties");
            configProperties.load(input);
            input.close();
        } catch (IOException e) {
            throw new IllegalStateException("Couldn't open \"src/test/config/config.properties\".", e);
        }

        return configProperties;
    }

    /***
     * The ability to toggle has been suspended because there does not seem to be a good way to assert against actual
     * data coming back
     *
     * Util function to allow switching between hitting live API vs running against stubs.
     * @return the WireMockRule for either real api or stubs
     */
//    public static WireMockRule getWireMockRule() {
//
//        WireMockRule wireMockOffRule = new WireMockRule(53620);
//        // read in env flag
//        String testFlag = System.getProperty("USE_REAL_API");
//
//        // Mocking is off so stub to api.box.com
//        if (testFlag!=null && testFlag.equals("true")) {
//            wireMockOffRule.stubFor(any(anyUrl()).atPriority(1)
//                    .willReturn(aResponse().proxiedFrom("https://api.box.com/2.0/")));
//        }
//
//        return wireMockOffRule;
//    }

    public static String getWireMockUrl() {
        String wireMockUrl = "http://localhost:53621/";
        return wireMockUrl;
    }

    /**
     *  Util function to help get JSON fixtures for tests.
     */
    public static String getFixture(String fixtureName) throws IOException {
        String fixtureFullPath = "./src/test/Fixtures/" + fixtureName + ".json";
        BufferedReader reader = new BufferedReader(new FileReader(fixtureFullPath));
        try {
            StringBuilder builder = new StringBuilder();
            String line = reader.readLine();

            while (line != null) {
                builder.append(line);
                builder.append("\n");
                line = reader.readLine();
            }
            return builder.toString();
        } finally {
            reader.close();
        }
    }
}
