package com.box.sdk;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


final class TestUtils {
    /**
     * Util function to help get JSON fixtures for tests.
     */
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

    /**
     * Util function to help get JSON fixtures for tests.
     */
    public static String getFixture(String fixtureName, int portNumber) throws IOException {
        String fixture = getFixture(fixtureName);
        return fixture.replaceAll(":53621", ":" + portNumber);
    }
}
