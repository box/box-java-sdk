package com.box.sdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

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
  private static String userID = null;

  private TestConfig() {}

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
    if (collaborator == null || collaborator.isEmpty()) {
      collaborator = System.getenv("JAVA_COLLABORATOR");
    }
    if (collaborator == null || collaborator.isEmpty()) {
      collaborator = getProperty("collaborator");
    }

    return collaborator;
  }

  public static String getCollaboratorID() {
    if (collaboratorID == null || collaboratorID.isEmpty()) {
      collaboratorID = System.getenv("JAVA_COLLABORATOR_ID");
    }
    if (collaboratorID == null || collaboratorID.isEmpty()) {
      collaboratorID = getProperty("collaboratorID");
    }

    return collaboratorID;
  }

  public static String getEnterpriseID() {
    if (enterpriseID == null || enterpriseID.isEmpty()) {
      enterpriseID = System.getenv("JAVA_ENTERPRISE_ID");
    }
    if (enterpriseID == null || enterpriseID.isEmpty()) {
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

  public static String getUserId() {
    if (userID == null || userID.isEmpty()) {
      userID = System.getenv("JAVA_USER_ID");
    }
    if (userID == null || userID.isEmpty()) {
      userID = getProperty("userID");
    }

    return userID;
  }

  private static String getProperty(String name) {
    Properties configProperties = loadProperties();
    String value = configProperties.getProperty(name);
    if (value.equals("")) {
      throw new IllegalStateException(
          "The " + name + " property wasn't set in " + "src/test/config/config.properties.");
    }

    return value;
  }

  private static Properties loadProperties() {
    if (configProperties != null) {
      return configProperties;
    }

    configProperties = new Properties();

    try (InputStream input = Files.newInputStream(Paths.get("src/test/config/config.properties"))) {
      configProperties.load(input);
    } catch (IOException e) {
      throw new IllegalStateException("Couldn't open \"src/test/config/config.properties\".", e);
    }

    return configProperties;
  }

  /** Util function to help get JSON fixtures for tests. */
  public static String getFixture(String fixtureName) throws IOException {
    String fixtureFullPath = "./src/test/Fixtures/" + fixtureName + ".json";
    try (BufferedReader reader = new BufferedReader(new FileReader(fixtureFullPath))) {
      StringBuilder builder = new StringBuilder();
      String line = reader.readLine();

      while (line != null) {
        builder.append(line);
        builder.append("\n");
        line = reader.readLine();
      }
      return builder.toString();
    }
  }
}
