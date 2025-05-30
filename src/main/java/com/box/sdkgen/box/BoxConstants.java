package com.box.sdkgen.box;

import static com.box.sdkgen.networking.version.Version.getVersion;

public class BoxConstants {

  private static final String JAVA_VERSION = System.getProperty("java.version");
  public static final String USER_AGENT_HEADER =
      "Box Java Generated SDK v" + getVersion() + " (Java " + JAVA_VERSION + ")";
  public static final String X_BOX_UA_HEADER =
      "agent=box-java-generated-sdk/" + getVersion() + "; env=Java/" + JAVA_VERSION;
}
