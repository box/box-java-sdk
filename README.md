<p align="center">
  <img src="https://github.com/box/sdks/blob/master/images/box-dev-logo.png" alt= “box-dev-logo” width="30%" height="50%">
</p>

# Box Java SDK

[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)
![Platform](https://img.shields.io/badge/java-%3E%3D8-blue)
[![License](https://img.shields.io/badge/license-Apache2-blue)](https://raw.githubusercontent.com/box/box-java-sdk/main/LICENSE)
[![Build main](https://github.com/box/box-java-sdk/actions/workflows/build-main.yml/badge.svg)](https://github.com/box/box-java-sdk/actions/workflows/build-main.yml)
[![Coverage](https://coveralls.io/repos/github/box/box-java-sdk/badge.svg?branch=main)](https://coveralls.io/github/box/box-java-sdk?branch=main)

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Supported versions](#supported-versions)
  - [Version v5](#version-v5)
  - [Version v10](#version-v10)
  - [Deprecation of `com.box.sdk`](#deprecation-of-comboxsdk)
  - [Which Version Should I Use?](#which-version-should-i-use)
- [Installing](#installing)
- [Getting Started](#getting-started)
  - [With com.box.sdkgen package (recommended)](#with-comboxsdkgen-package-recommended)
  - [With com.box.sdk package (deprecated)](#with-comboxsdk-package-deprecated)
- [Authentication](#authentication)
- [Using both com.box.sdkgen and com.box.sdk packages simultaneously](#using-both-comboxsdkgen-and-comboxsdk-packages-simultaneously)
- [Documentation](#documentation)
- [Migration guides](#migration-guides)
- [Versioning](#versioning)
  - [Version schedule](#version-schedule)
    - [Version schedule](#version-schedule-1)
- [Contributing](#contributing)
- [3rd Party Libraries & Licenses](#3rd-party-libraries--licenses)
  - [Android](#android)
  - [FIPS 140-2 Compliance](#fips-140-2-compliance)
- [Questions, Bugs, and Feature Requests?](#questions-bugs-and-feature-requests)
  - [Copyright and License](#copyright-and-license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Supported versions

To enhance developer experience, provide full Box API coverage, and rapid updates we have introduced the new generated codebase through the `com.box.sdkgen` package.
The `com.box.sdkgen` package is available in two major supported versions: v5 and v10.

## Version v5

In v5 of the Box Java SDK, we are introducing a version that consolidates both the manually written package (`com.box.sdk`)
and the new generated package (`com.box.sdkgen`). This allows developers to use both packages simultaneously within a single project.

The codebase for v5 of the Box Java SDK is currently available on the [combined-sdk](https://github.com/box/box-java-sdk/tree/combined-sdk) branch.
Migration guide which would help with migration from `com.box.sdk` to `com.box.sdkgen` can be found [here](./migration-guides/from-com.box.sdk-to-com.box.sdkgen.md).

Version v5 is intended for:
- Existing developers of the Box Java SDK v4 who want to access new API features while keeping their current codebase largely unchanged.
- Existing developers who are in the process of migrating to `com.box.sdkgen`, but do not want to move all their code to the new package immediately.

## Version v10

Starting with v10, the SDK is built entirely on the generated `com.box.sdkgen` package, which fully and exclusively replaces the old `com.box.sdk` package.
The codebase for v10 of the Box Java SDK is currently available on the [sdk-gen](https://github.com/box/box-java-sdk/tree/sdk-gen) branch.

Version v10 is intended for:
- New users of the Box Java SDK.
- Developers already working with the generated Box Java SDK previously available under the [Box Java SDK Gen repository](https://github.com/box/box-java-sdk-gen).

## Deprecation of `com.box.sdk`

The `com.box.sdk` package will be marked as deprecated, will receive only bug fixes and security patches, and reach end of support in 2027.
All new features and support for new Box APIs will be provided exclusively in the `com.box.sdkgen` package.

## Which Version Should I Use?

| Scenario                                                                                                                                                                                   | Recommended Version                                                    | Example gradle dependency     |
|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------|-------------------------------|
| Creating a new application                                                                                                                                                                 | Use [v10](https://github.com/box/box-java-sdk/tree/sdk-gen)            | `com.box:box-java-sdk:10.0.0` |
| App using [box-java-sdk-gen](https://central.sonatype.com/artifact/com.box/box-java-sdk-gen) artifact                                                                                      | Migrate to [v10](https://github.com/box/box-java-sdk/tree/sdk-gen)     | `com.box:box-java-sdk:10.0.0` |
| App using both [box-java-sdk-gen](https://central.sonatype.com/artifact/com.box/box-java-sdk-gen) and [box-java-sdk](https://central.sonatype.com/artifact/com.box/box-java-sdk) artifacts | Upgrade to [v5](https://github.com/box/box-java-sdk/tree/combined-sdk) | `com.box:box-java-sdk:5.0.0`  |
| App using v4 of [box-java-sdk](https://central.sonatype.com/artifact/com.box/box-java-sdk) artifact                                                                                        | Upgrade to [v5](https://github.com/box/box-java-sdk/tree/combined-sdk) | `com.box:box-java-sdk:5.0.0`  |

For full guidance on SDK versioning, see the [Box SDK Versioning Guide](https://developer.box.com/guides/tooling/sdks/sdk-versioning/).

# Installing

The SDK is available on [Maven Central Repository](https://central.sonatype.com/artifact/com.box/box-java-sdk).

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk</artifactId>
    <version>VERSION</version>
</dependency>
```

To include the SDK in your project using Gradle, add the following dependency to your `build.gradle` file:

```gradle
implementation 'com.box:box-java-sdk:VERSION'
```

To include in your project the v5 version of Box Java SDK that consolidates both the manual package (`com.box.sdk`)
and the new generated package (`com.box.sdkgen`), replace `VERSION` with `5.0.0` or later version of v5 major.

The Box Java SDK is compatible with Java 8 and up.

# Getting Started

To get started with the SDK, get a Developer Token from the Configuration page of your app in the [Box Developer Console](https://app.box.com/developers/console).
Developer Tokens are short-lived and expire after 60 minutes, which is good for testing but not for production use.
To learn about other authentication methods, see the [Authentication](#authentication) section below.

The examples below demonstrate how to authenticate with Developer Token and print names of all items inside a root folder.

## With com.box.sdkgen package (recommended)

The SDK provides an `BoxDeveloperTokenAuth` class, which allows you to authenticate using your Developer Token.
Use instance of `BoxDeveloperTokenAuth` to initialize `BoxClient` object.
Using `BoxClient` object you can access managers, which allow you to perform some operations on your Box account.


```java
import com.box.sdkgen.client.BoxClient;
import com.box.sdkgen.box.developertokenauth.BoxDeveloperTokenAuth;

BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("DEVELOPER_TOKEN");
BoxClient client = new BoxClient(auth);
client.folders.getFolderItems("0").getEntries().forEach(item -> {
   System.out.println(item.toString());
});
```

## With com.box.sdk package (deprecated)

```java
import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;

BoxAPIConnection api = new BoxAPIConnection("DEVELOPER_TOKEN");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem.Info itemInfo : rootFolder) {
    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
}
```

# Authentication

Both the `com.box.sdkgen` and `com.box.sdk` packages support multiple authentication methods, including
Developer Token, OAuth 2.0, Client Credentials Grant, and JSON Web Token (JWT).

You can find detailed instructions and example code for each authentication method in the following documentation:
- [Authentication for the `com.box.sdkgen` package](./docs/sdkgen/Authentication.md)
- [Authentication for the `com.box.sdk` package](./docs/sdk/authentication.md)

# Using both com.box.sdkgen and com.box.sdk packages simultaneously

With v5 of the Box Java SDK, you can use both the `com.box.sdkgen` and `com.box.sdk` packages in the same project.
This allows you to gradually migrate your codebase to the new generated package while still using the manual package for existing functionality.

``` java
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

# Documentation

Full documentation of the available functionality, along with example code can be found:
- for the `com.box.sdkgen` package, is available [here](./docs/sdk).
- for the `com.box.sdk` package can be found [here](./docs/sdkgen/README.md).

You can also see the [API Reference](https://developer.box.com/reference/) for additional information.

# Migration guides

Migration guides which help you to migrate to supported major SDK versions can be found [here](./migration-guides).

# Versioning

We use a modified version of [Semantic Versioning](https://semver.org/) for all changes. See [version strategy](VERSIONS.md) for details which is effective from 30 July 2022.

A current release is on the leading edge of our SDK development, and is intended for customers who are in active development and want the latest and greatest features.  
Instead of stating a release date for a new feature, we set a fixed minor or patch release cadence of maximum 2-3 months (while we may release more often).
At the same time, there is no schedule for major or breaking release. Instead, we will communicate one quarter in advance the upcoming breaking change to allow customers to plan for the upgrade.

We always recommend that all users run the latest available minor release for whatever major version is in use.
We highly recommend upgrading to the latest SDK major release at the earliest convenient time and before the EOL date.

## Version schedule

### Version schedule

| Version | Supported Environments | State     | First Release | EOL/Terminated         |
|---------|------------------------|-----------|---------------|------------------------|
| 10      | Java 8 and up          | Supported | 17 Sep 2025   | TBD                    |
| 5       | Java 8 and up          | Supported | 23 Oct 2025   | 2027 or v6 is released |
| 4       | Java 8 and up          | Supported | 17 Jan 2023   | 23 Oct 2025            |
| 3       | Java 8 and up          | EOL       | 17 Jan 2022   | 17 Jan 2023            |
| 2       |                        | EOL       | 07 Jan 2016   | 17 Jan 2022            |
| 1       |                        | EOL       | 15 Apr 2015   | 07 Jan 2016            |

# Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md).

# 3rd Party Libraries & Licenses

The Java SDK uses third-party libraries that are required for usage. Their licenses are listed below:

1. [minimal-json v0.9.5](https://github.com/ralfstx/minimal-json)
   Maven: `com.eclipsesource.minimal-json:minimal-json:0.9.5`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
2. [jose4j v0.9.4](https://bitbucket.org/b_c/jose4j/wiki/Home)
   Maven: `org.bitbucket.b_c:jose4j:0.9.4`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
3. [bouncycastle bcprov-jdk18on v1.82](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on/1.82)
   Maven: `org.bouncycastle:bcprov-jdk18on:1.82`
   Licence: [MIT](https://opensource.org/licenses/MIT)
4. [bouncycastle bcpkix-jdk18on v1.82](https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk18on/1.82)
   Maven: `org.bouncycastle:bcpkix-jdk18on:1.82`
   Licence: [MIT](https://opensource.org/licenses/MIT)
5. [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
   If you don't install this, you'll get an exception about key length or exception about parsing PKCS private key for Box Developer Edition. This is not a Box thing, this is a U.S. Government requirement concerning strong encryption.
   The listed jar is for Oracle JRE. There might be other similar JARs for different JRE versions like the one below for IBM JDK
   [Java Cryptography Extension for IBM JDK](https://www14.software.ibm.com/webapp/iwm/web/preLogin.do?source=jcesdk)
   Licence: [Oracle Binary Code Licence](http://www.oracle.com/technetwork/licenses/index.html)
6. [okhttp v4.12.0](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.12.0)
   Maven: `com.squareup.okhttp3:okhttp:4.12.0`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
7. [okio-jvm v3.2.0](https://mvnrepository.com/artifact/com.squareup.okio/okio-jvm/3.2.0)
   Maven: `com.squareup.okio:okio-jvm:3.2.0`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
8. [kotlin-stdlib v1.6.20](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib/1.6.20)
   Maven: `org.jetbrains.kotlin:kotlin-stdlib:1.6.20`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
9. [kotlin-stdlib-common v1.6.20](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib-common/1.6.20)
   Maven: `org.jetbrains.kotlin:kotlin-stdlib-common:1.6.20`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
10. [jackson-annotations v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations/2.17.2)
    Maven: `com.fasterxml.jackson.core:jackson-annotations:2.17.2`
    Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
11. [jackson-core v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.17.2)
    Maven: `com.fasterxml.jackson.core:jackson-core:2.17.2`
    Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
12. [jackson-databind v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.17.2)
    Maven: `com.fasterxml.jackson.core:jackson-databind:2.17.2`
    Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
13. [okio v3.5.0](https://mvnrepository.com/artifact/com.squareup.okio/okio/3.5.0)
    Maven: `com.squareup.okio:okio:3.5.0`
    Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
14. [jose4j v0.9.6](https://mvnrepository.com/artifact/org.bitbucket.b_c/jose4j/0.9.6)
    Maven: `org.bitbucket.b_c:jose4j:0.9.6`
    Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)

## Android
If you are developing application for Android visit our [Android guide](./docs/android.md).

## FIPS 140-2 Compliance

To generate a Json Web Signature used for retrieving tokens in the JWT authentication method, the Box Java SDK decrypts an encrypted private key.
For this purpose, Box Java SDK uses libraries (`org.bouncycastle:bcpkix-jdk18on:1.82` and `org.bouncycastle:bcprov-jdk18on:1.82`)
that are NOT compatible with FIPS 140-2 validated cryptographic library (`org.bouncycastle:bc-fips`).

There are two ways of ensuring that decryption operation is FIPS-compiant.

1. You can provide a custom implementation of the `IPrivateKeyDecryptor` interface, 
which performs the decryption operation using FIPS-certified library of your choice. 
`IPrivateKeyDecryptor` interface is availiable both in `com.box.sdk` and `com.box.sdkgen` packages.
The interface requires the implementation of just one method:
```java
PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase);
```
After implementing the custom decryptor, you need to set your custom decryptor class:

- For `com.box.sdk` package, you can set the custom decryptor in the `BoxConfig` object 
before creating the `BoxDeveloperEditionAPIConnection`.
```java
Reader reader = new FileReader(JWT_CONFIG_PATH);
BoxConfig boxConfig = BoxConfig.readFrom(reader);
boxConfig.setPrivateKeyDecryptor(customDecryptor);
BoxDeveloperEditionAPIConnection api = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig);
```

- For `com.box.sdkgen` package, you can set the custom decryptor in the `JWTConfig` object
```java
JWTConfig newConfig = JWTConfig.fromConfigFile(JWT_CONFIG_PATH, customDecryptor);
BoxJWTAuth auth = new BoxJWTAuth(jwtConfig);
BoxClient client = new BoxClient(auth);
```

2. Alternative method is to override the Bouncy Castle libraries to the v.1.57 version, 
which are compatible with the FIPS 140-2 validated cryptographic library (`org.bouncycastle:bc-fips`).

NOTE: This solution is not recommended as Bouncy Castle v.1.57 has some moderate vulnerabilities reported against those versions, including:
 - [CVE-2020-26939](https://github.com/advisories/GHSA-72m5-fvvv-55m6) - Observable Differences in Behavior to Error Inputs in Bouncy Castle
 - [CVE-2020-15522](https://github.com/advisories/GHSA-6xx3-rg99-gc3p) - Timing based private key exposure in Bouncy Castle

Furthermore,using Bouncy Castle v.1.57 may lead to [Bouncycastle BadPaddingException for JWT auth](#bouncycastle-badPaddingException-for-jWT-auth).

Gradle example
```groovy
implementation('com.box:box-java-sdk:x.y.z') {
   exclude group: 'org.bouncycastle', module: 'bcprov-jdk15on'
   exclude group: 'org.bouncycastle', module: 'bcpkix-jdk15on'
}
runtimeOnly('org.bouncycastle:bcprov-jdk15on:1.57')
runtimeOnly('org.bouncycastle:bcpkix-jdk15on:1.57')
```

Maven example:
```xml
<dependencies>
   <dependency>
      <groupId>com.box</groupId>
      <artifactId>box-java-sdk</artifactId>
      <version>x.y.z</version>
      <scope>compile</scope>
      <exclusions>
        <exclusion>
          <groupId>org.bouncycastle</groupId>
          <artifactId>bcprov-jdk15on</artifactId>
        </exclusion>
         <exclusion>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk15on</artifactId>
         </exclusion>
      </exclusions> 
   </dependency>
   <dependency>
      <groupId>org.bouncycastle</groupId>
      <artifactId>bcprov-jdk15on</artifactId>
      <version>1.57</version>
      <scope>runtime</scope>
   </dependency>
   <dependency>
      <groupId>org.bouncycastle</groupId>
      <artifactId>bcpkix-jdk15on</artifactId>
      <version>1.57</version>
      <scope>runtime</scope>
   </dependency>
</dependencies>
```

# Questions, Bugs, and Feature Requests?

Need to contact us directly? [Browse the issues tickets](https://github.com/box/box-java-sdk/issues)! Or, if that
doesn't work, [file a new one](https://github.com/box/box-java-sdk/issues/new), and we will get
back to you. If you have general questions about the Box API, you can post to the [Box Developer Forum](https://community.box.com/box-platform-5).

## Copyright and License

Copyright 2019 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
