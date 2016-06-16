package com.box.sdk.samples.appusers.helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 */
public final class ConfigHelper {

    private static Object syncObject = new Object();
    private static Properties properties = null;

    /**
     * Utility Classe ConfigHelper.  Do not allow
     * default constructor to be called.
     */

    private ConfigHelper() {

    }

    /**
     * @return properties a key value map
     */
    protected static Properties properties() {
        synchronized (syncObject) {
            if (properties == null) {
                Properties prop = new Properties();
                try {
                    InputStream input = new FileInputStream("dsm-config.properties");
                    prop.load(input);
                    input.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                properties = prop;
            }

            return properties;
        }
    }
}
