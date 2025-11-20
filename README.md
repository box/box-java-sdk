<p align="center">
  <img src="https://github.com/box/sdks/blob/master/images/box-dev-logo.png" alt= “box-dev-logo” width="30%" height="50%">
</p>

# Box Java SDK v10

[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)
![build](https://github.com/box/box-java-sdk/actions/workflows/build.yml/badge.svg?branch=main)
![Maven Central Version](https://img.shields.io/maven-central/v/com.box/box-java-sdk)
![Platform](https://img.shields.io/badge/java-%3E%3D8-blue)
[![Coverage](https://coveralls.io/repos/github/box/box-java-sdk/badge.svg?branch=main)](https://coveralls.io/github/box/box-java-sdk-gen?branch=main)

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Supported versions](#supported-versions)
  - [Version v5](#version-v5)
  - [Version v10](#version-v10)
  - [Which Version Should I Use?](#which-version-should-i-use)
- [Installing](#installing)
- [Getting Started](#getting-started)
- [Authentication](#authentication)
- [Documentation](#documentation)
- [Migration guides](#migration-guides)
- [Versioning](#versioning)
  - [Version schedule](#version-schedule)
- [Contributing](#contributing)
- [3rd Party Libraries & Licenses](#3rd-party-libraries--licenses)
- [FIPS 140-2 Compliance](#fips-140-2-compliance)
- [Questions, Bugs, and Feature Requests?](#questions-bugs-and-feature-requests)
- [Copyright and License](#copyright-and-license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

# Introduction

We are excited to introduce the v10 major release of the Box Java SDK,
designed to elevate the developer experience and streamline your integration with the Box Content Cloud.

With this SDK version, we provide the `com.box.sdkgen` package, which gives you access to:

1. Full API Support: The new generation of Box SDKs empowers developers with complete coverage of the Box API ecosystem. You can now access all the latest features and functionalities offered by Box, allowing you to build even more sophisticated and feature-rich applications.
2. Rapid API Updates: Say goodbye to waiting for new Box APIs to be incorporated into the SDK. With our new auto-generation development approach, we can now add new Box APIs to the SDK at a much faster pace (in a matter of days). This means you can leverage the most up-to-date features in your applications without delay.
3. Embedded Documentation: We understand that easy access to information is crucial for developers. With our new approach, we have included comprehensive documentation for all objects and parameters directly in the source code of the SDK. This means you no longer need to look up this information on the developer portal, saving you time and streamlining your development process.
4. Enhanced Convenience Methods: Our commitment to enhancing your development experience continues with the introduction of convenience methods. These methods cover various aspects such as chunk uploads, classification, and much more.
5. Seamless Start: The new SDKs integrate essential functionalities like authentication, automatic retries with exponential backoff, exception handling, request cancellation, and type checking, enabling you to focus solely on your application's business logic.

Embrace the new generation of Box SDKs and unlock the full potential of the Box Content Cloud.

# Supported versions

To enhance developer experience, we have introduced the new generated codebase through the `com.box.sdkgen` package.
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
The codebase for v10 of the Box Java SDK is currently available on the [main](https://github.com/box/box-java-sdk/tree/main) branch.

Version v10 is intended for:

- New users of the Box Java SDK.
- Developers already working with the generated Box Java SDK previously available under the [Box Java SDK Gen repository](https://github.com/box/box-java-sdk-gen).

## Which Version Should I Use?

| Scenario                                                                                                                                                                                   | Recommended Version                                                    | Example gradle dependency     |
| ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | ---------------------------------------------------------------------- | ----------------------------- |
| Creating a new application                                                                                                                                                                 | Use [v10](https://github.com/box/box-java-sdk/tree/main)               | `com.box:box-java-sdk:10.0.0` |
| App using [box-java-sdk-gen](https://central.sonatype.com/artifact/com.box/box-java-sdk-gen) artifact                                                                                      | Migrate to [v10](https://github.com/box/box-java-sdk/tree/main)        | `com.box:box-java-sdk:10.0.0` |
| App using both [box-java-sdk-gen](https://central.sonatype.com/artifact/com.box/box-java-sdk-gen) and [box-java-sdk](https://central.sonatype.com/artifact/com.box/box-java-sdk) artifacts | Upgrade to [v5](https://github.com/box/box-java-sdk/tree/combined-sdk) | `com.box:box-java-sdk:5.0.0`  |
| App using v4 of [box-java-sdk](https://central.sonatype.com/artifact/com.box/box-java-sdk) artifact                                                                                        | Upgrade to [v5](https://github.com/box/box-java-sdk/tree/combined-sdk) | `com.box:box-java-sdk:5.0.0`  |

For full guidance on SDK versioning, see the [Box SDK Versioning Guide](https://developer.box.com/guides/tooling/sdks/sdk-versioning/).

# Installing

The SDK is available on [Maven Central Repository](https://mvnrepository.com/artifact/com.box/box-java-sdk). To include the SDK in your project, add the following dependency to your `pom.xml` file:

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

Where `VERSION` is the version of the SDK you want to use. The next generation of the SDK starts with version `10.0.0`.
You can find the latest version in the [Maven Central Repository](https://mvnrepository.com/artifact/com.box/box-java-sdk).

# Getting Started

To get started with the SDK, get a Developer Token from the Configuration page of your app in the [Box Developer
Console](https://app.box.com/developers/console). You can use this token to make test calls for your own Box account.

The SDK provides an `BoxDeveloperTokenAuth` class, which allows you to authenticate using your Developer Token.
Use instance of `BoxDeveloperTokenAuth` to initialize `BoxClient` object.
Using `BoxClient` object you can access managers, which allow you to perform some operations on your Box account.

The example below demonstrates how to authenticate with Developer Token and print names of all items inside a root folder.

```java
BoxDeveloperTokenAuth auth = new BoxDeveloperTokenAuth("DEVELOPER_TOKEN");
BoxClient client = new BoxClient(auth);
client.folders.getFolderItems("0").getEntries().forEach(item -> {
   System.out.println(item.toString());
});
```

# Authentication

Box Java SDK v10 supports multiple authentication methods including Developer Token, OAuth 2.0,
Client Credentials Grant, and JSON Web Token (JWT).

You can find detailed instructions and example code for each authentication method in
[Authentication](./docs/Authentication.md) document.

# Documentation

Browse the [docs](docs/README.md) or see [API Reference](https://developer.box.com/reference/) for more information.

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

| Version | Supported Environments | State     | First Release | EOL/Terminated         |
| ------- | ---------------------- | --------- | ------------- | ---------------------- |
| 10      | Java 8 and up          | Supported | 17 Sep 2025   | TBD                    |
| 5       | Java 8 and up          | Supported | 23 Oct 2025   | 2027 or v6 is released |
| 4       | Java 8 and up          | EOL       | 17 Jan 2023   | 23 Oct 2025            |
| 3       | Java 8 and up          | EOL       | 17 Jan 2022   | 17 Jan 2023            |
| 2       |                        | EOL       | 07 Jan 2016   | 17 Jan 2022            |
| 1       |                        | EOL       | 15 Apr 2015   | 07 Jan 2016            |

# Contributing

See [CONTRIBUTING.md](./CONTRIBUTING.md).

# 3rd Party Libraries & Licenses

The Java SDK uses third-party libraries that are required for usage. Their licenses are listed below:

1. [jackson-annotations v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-annotations/2.17.2)
   Maven: `com.fasterxml.jackson.core:jackson-annotations:2.17.2`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
2. [jackson-core v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-core/2.17.2)
   Maven: `com.fasterxml.jackson.core:jackson-core:2.17.2`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
3. [jackson-databind v2.17.2](https://mvnrepository.com/artifact/com.fasterxml.jackson.core/jackson-databind/2.17.2)
   Maven: `com.fasterxml.jackson.core:jackson-databind:2.17.2`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
4. [okhttp v4.12.0](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.12.0)
   Maven: `com.squareup.okhttp3:okhttp:4.12.0`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
5. [okio v3.5.0](https://mvnrepository.com/artifact/com.squareup.okio/okio/3.5.0)
   Maven: `com.squareup.okio:okio:3.5.0`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
6. [jose4j v0.9.6](https://mvnrepository.com/artifact/org.bitbucket.b_c/jose4j/0.9.6)
   Maven: `org.bitbucket.b_c:jose4j:0.9.6`
   Licence: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0)
7. [bcprov-jdk18on v1.82](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on/1.82)
   Maven: `org.bouncycastle:bcprov-jdk18on:1.82`
   Licence: [MIT](https://opensource.org/licenses/MIT)
8. [bcpkix-jdk18on v1.82](https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk18on/1.82)
   Maven: `org.bouncycastle:bcpkix-jdk18on:1.82`
   Licence: [MIT](https://opensource.org/licenses/MIT)

The following libraries are required for running tests:

1. [junit-jupiter-api v5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0)
   Maven: `org.junit.jupiter:junit-jupiter-api:5.10.0`
   Licence: [EPL 2.0](https://www.eclipse.org/legal/epl-2.0/)
2. [junit-jupiter-engine v5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.10.0)
   Maven: `org.junit.jupiter:junit-jupiter-engine:5.10.0`
   Licence: [EPL 2.0](https://www.eclipse.org/legal/epl-2.0/)

# FIPS 140-2 Compliance

To generate a Json Web Signature used for retrieving tokens in the JWT authentication method, the Box Java SDK decrypts an encrypted private key.
For this purpose, Box Java SDK uses libraries (`org.bouncycastle:bcpkix-jdk18on:1.82` and `org.bouncycastle:bcprov-jdk18on:1.82`)
that are NOT compatible with FIPS 140-2 validated cryptographic library (`org.bouncycastle:bc-fips`).

There are two ways of ensuring that decryption operation is FIPS-compiant.

1. You can provide a custom implementation of the `IPrivateKeyDecryptor` interface,
   which performs the decryption operation using FIPS-certified library of your choice.
   The interface requires the implementation of just one method:

```java
PrivateKey decryptPrivateKey(String encryptedPrivateKey, String passphrase);
```

After implementing the custom decryptor, you need to set your custom decryptor class:

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
   exclude group: 'org.bouncycastle', module: 'bcprov-jdk18on'
   exclude group: 'org.bouncycastle', module: 'bcpkix-jdk18on'
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
          <artifactId>bcprov-jdk18on</artifactId>
        </exclusion>
         <exclusion>
            <groupId>org.bouncycastle</groupId>
            <artifactId>bcpkix-jdk18on</artifactId>
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

# Copyright and License

Copyright 2025 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
