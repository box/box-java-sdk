package com.box.sdk;

import java.io.*;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private static BoxConfig boxConfig = null;

    private TestConfig() { }

    public static Logger  enableLogger(String levelString) {
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

	//Below properties are loaded from config.json file in the config folder
	public static String getClientID() {
		loadBoxConfig();
		return boxConfig.getClientId();
	}

	public static String getClientSecret() {
		loadBoxConfig();
		return boxConfig.getClientSecret();
	}

	public static String getEnterpriseID() {
		loadBoxConfig();
		return boxConfig.getEnterpriseId();
	}

	public static String getPrivateKey() {
		loadBoxConfig();
		return boxConfig.getJWTEncryptionPreferences().getPrivateKey();
	}

	public static String getPrivateKeyPassword() {
		loadBoxConfig();
		return boxConfig.getJWTEncryptionPreferences().getPrivateKeyPassword();
	}

	public static String getPublicKeyID() {
		loadBoxConfig();
		return boxConfig.getJWTEncryptionPreferences().getPublicKeyID();
	}

	private static void loadBoxConfig() {
		if (boxConfig == null) {
			Reader reader = null;
			try {
				reader = new FileReader("src/test/config/config.json");
				boxConfig = BoxConfig.readFrom(reader);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
