# Migration guide from v4 to v5 version of `box-java-sdk`

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
- [Supported Environments](#supported-environments)
- [Highlighting the Key Differences](#highlighting-the-key-differences)
  - [Using the Box Java SDK v5](#using-the-box-java-sdk-v5)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

The v5 release of the Box Java SDK is a transitional version designed to help developers migrate from
the manually maintained v4 SDK to the modern, auto-generated v10+ SDK.

This release combines two packages into a single artifact:

- `com.box.sdk` - the manually maintained package from v4.
- `com.box.sdkgen` - the new, auto-generated module built from the official OpenAPI specification (and the sole component of the v10 SDK).

This hybrid approach allows you to gradually adopt the new `com.box.sdkgen` features
while continuing to use your existing v4 integration, eliminating the need for an immediate full rewrite.

## Installation

To install v5 version of Box Java SDK, you can use Maven or Gradle. The library is available in the
[Maven Central Repository](https://search.maven.org/artifact/com.box/box-java-sdk).

### Maven

To upgrade from v4 to v5, in you Maven project just bump the version of the Box Java SDK library
in `pom.xml`to 5.0.0 or higher:

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk</artifactId>
    <version>5.0.0</version>
</dependency>
```

### Gradle

To upgrade from v4 to v5 of the Box Java SDK, simply update the library version in your Maven project’s `pom.xml` file
to 5.0.0 or higher:

```groovy
implementation 'com.box:box-java-sdk:5.0.0'
```

## Supported Environments

Both v4 and v5 of the Box Java SDK share the same Java version requirement: Java 8 or higher.
No changes to your environment are needed when upgrading from v4 to v5.

## Highlighting the Key Differences

The `com.box.sdk` package usage in v5 remains the same as in v4 and is not covered in this document.
If you are migrating code from `com.box.sdk` to `com.box.sdkgen`, which we recommend,
the key differences between the packages are documented in:

- [Migration guide: com.box.sdk → com.box.sdkgen](./from-com-box-sdk-to-com-box-sdkgen.md)

### Using the Box Java SDK v5

After migration to Box Java SDK v5, you can use both the manual Box Java SDK package `com.box.sdk` and the generated one `com.box.sdkgen`.
You just need to import the required classes from the appropriate package depending on which SDK you intend to use.
If both packages contain classes with the same name, you can use fully qualified names to resolve any naming conflicts.

```java
import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;

import com.box.sdk.BoxFolder;
import com.box.sdkgen.box.jwtauth.BoxJWTAuth;
import com.box.sdkgen.box.jwtauth.JWTConfig;
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.managers.folders.UpdateFolderByIdRequestBody;
import com.box.sdkgen.schemas.folder.Folder;

import java.io.FileReader;
import java.io.Reader;

public class Main {
  public static void main(String[] args) throws Exception {

    Reader reader = new FileReader("src/example/config/config.json");
    BoxConfig boxConfig = BoxConfig.readFrom(reader);
    BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);

    JWTConfig config = JWTConfig.fromConfigFile("src/example/config/config.json");
    BoxJWTAuth auth = new BoxJWTAuth(config);
    BoxClient client = new BoxClient(auth);

    BoxFolder rootFolder = new BoxFolder(api, "0");
    BoxFolder.Info subfolder = rootFolder.createFolder("My Subfolder");
    Folder updatedFolder = client.getFolders().updateFolderById(
            subfolder.getID(),
            new UpdateFolderByIdRequestBody.Builder().name("My Updated Subfolder").build()
    );
    System.out.println("Created folder with ID " + subfolder.getID() + " has been updated to " + updatedFolder.getName());
  }
}
```
