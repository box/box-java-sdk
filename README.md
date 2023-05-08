<p align="center">
  <img src="https://github.com/box/sdks/blob/master/images/box-dev-logo.png" alt= “box-dev-logo” width="30%" height="50%">
</p>

# Box Java SDK

[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)
![Platform](https://img.shields.io/badge/java-%3E%3D8-blue)
[![License](https://img.shields.io/badge/license-Apache2-blue)](https://raw.githubusercontent.com/box/box-java-sdk/main/LICENSE)
[![Build main](https://github.com/box/box-java-sdk/actions/workflows/build-main.yml/badge.svg)](https://github.com/box/box-java-sdk/actions/workflows/build-main.yml)
[![Coverage](https://coveralls.io/repos/github/box/box-java-sdk/badge.svg?branch=main)](https://coveralls.io/github/box/box-java-sdk?branch=main)

The Box Java SDK for interacting with the
[Box Content API](https://developers.box.com/docs/).

## Latest Release
Latest release can be found [here](https://github.com/box/box-java-sdk/tree/v4.1.0).

## Upgrades
You can read about how to migrate to the 4 version [here](doc/upgrades/3.x.x%20to%204.x.x.md).

## Versions
We use a modified version of [Semantic Versioning](https://semver.org/) for all changes. See [version strategy](VERSIONS.md) for details which is effective from 30 July 2022.

### Supported Version

Only the current MAJOR version of SDK is supported. New features, functionality, bug fixes, and security updates will only be added to the current MAJOR version.

A current release is on the leading edge of our SDK development, and is intended for customers who are in active development and want the latest and greatest features.  Instead of stating a release date for a new feature, we set a fixed minor or patch release cadence of maximum 2-3 months (while we may release more often). At the same time, there is no schedule for major or breaking release. Instead, we will communicate one quarter in advance the upcoming breaking change to allow customers to plan for the upgrade. We always recommend that all users run the latest available minor release for whatever major version is in use. We highly recommend upgrading to the latest SDK major release at the earliest convenient time and before the EOL date.

### Version schedule

| Version | Supported Environments                                  | State     | First Release | EOL/Terminated |
|---------|---------------------------------------------------------|-----------|---------------|----------------|
| 4       | Java 8 and up                                           | Supported | 17 Jan 2023   | TBD            |
| 3       | Java 8 and up                                           | EOL       | 17 Jan 2022   | 17 Jan 2023    |
| 2       |                                                         | EOL       | 07 Jan 2016   | 17 Jan 2022    |
| 1       |                                                         | EOL       | 15 Apr 2015   | 07 Jan 2016    |



## Getting Started
Getting Started Docs: https://developer.box.com/guides/tooling/sdks/java/
API Reference: https://developer.box.com/reference/

## JVM

The SDK can be obtained by adding it as a [maven dependency](http://opensource.box.com/box-java-sdk/),
cloning the source into your project, or by downloading one of the precompiled JARs from the
[releases page on GitHub](https://github.com/box/box-java-sdk/releases).

If you are developing application for Android visit our [Android guide](doc/android.md)

**IF YOU USE THE JAR, you'll also need to include several dependencies:**

1. [minimal-json v0.9.5](https://github.com/ralfstx/minimal-json)
   Maven: `com.eclipsesource.minimal-json:minimal-json:0.9.5`
2. [jose4j v0.9.0](https://bitbucket.org/b_c/jose4j/wiki/Home)
   Maven: `org.bitbucket.b_c:jose4j:0.9.0`
3. [bouncycastle bcprov-jdk15on v1.57](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on/1.57)
   Maven: `org.bouncycastle:bcprov-jdk15on:1.57`
4. [bouncycastle bcpkix-jdk15on v1.57](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on/1.57)
   Maven: `org.bouncycastle:bcpkix-jdk15on:1.57`
5. [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
   If you don't install this, you'll get an exception about key length or exception about parsing PKCS private key for Box Developer Edition. This is not a Box thing, this is a U.S. Government requirement concerning strong encryption.
   The listed jar is for Oracle JRE. There might be other similar JARs for different JRE versions like the one below for IBM JDK
   [Java Cryptography Extension for IBM JDK](https://www14.software.ibm.com/webapp/iwm/web/preLogin.do?source=jcesdk)
6. [okhttp v4.10.0](https://mvnrepository.com/artifact/com.squareup.okhttp3/okhttp/4.10.0)
   Maven: `com.squareup.okhttp3:okhttp:4.10.0`
7. [okio-jvm v3.2.0](https://mvnrepository.com/artifact/com.squareup.okio/okio-jvm/3.2.0)
   Maven: `com.squareup.okio:okio-jvm:3.2.0`
8. [kotlin-stdlib v1.6.20](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib/1.6.20) 
   Maven: `org.jetbrains.kotlin:kotlin-stdlib:1.6.20`
9. [kotlin-stdlib-common v1.6.20](https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-stdlib-common/1.6.20)
   Maven: `org.jetbrains.kotlin:kotlin-stdlib-common:1.6.20`

An app has to be authorized by the admin of the enterprise before these tests. It's always good to begin with the
[Getting Started Section](https://developer.box.com/docs/setting-up-a-jwt-app) at Box's developer website.

## Android
If you are developing application for Android visit our [Android guide](doc/android.md).

## Quick Test

**Following things work only if the app has been configured and authorized as mentioned [here](https://developer.box.com/docs/setting-up-a-jwt-app)**

Here is a simple example of how to authenticate with the API using a developer
token and then print the ID and name of each item in your root folder.

```java
BoxAPIConnection api = new BoxAPIConnection("developer-token");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem.Info itemInfo : rootFolder) {
    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
}
```

For more details on how to get started, check out the [overview guide](doc/overview.md).
It has a short explanation of how the SDK works and how you can get started using it.

### Sample Projects

Three sample projects can be found in `src/example`.

#### Main

This project will output your name and a list of the files and folders in your root directory.

To run the project, first provide a developer token in
`src/example/java/com/box/sdk/example/Main.java`. You can obtain a developer
token from your application's [developer console](https://app.box.com/developers/services).

```java
public final class Main {
    private static final String DEVELOPER_TOKEN = "<YOUR_DEVELOPER_TOKEN>";

    // ...
}
```

Then just invoke `gradle runExample` to run the Main example!

### Other projects

Below projects need app configurations stored in JSON format in `config.json` file at location `src/example/config/`.

This configuration file can be downloaded from your application's `Configuration` tab in the
[developer console](https://app.box.com/developers/console)

#### CreateAppUser

This project will output the user id of enterprise admin and will create a new App User for the enterprise.

To run the project, first provide the name of the app user in `src/example/java/com/box/sdk/example/CreateAppUser.java`.

```java
public final class CreateAppUser {

    private static final String APP_USER_NAME = "";
    private static final String EXTERNAL_APP_USER_ID = "";

    // ...
}
```

Then just invoke `gradle runCreateAppUser` to run the CreateAppUser example!

Note: The JCE bundled with oracle JRE supports keys upto 128 bit length only. To use larger cryptographic keys, install [JCE Unlimited Strength Jurisdiction Policy Files](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html).

#### AccessAsAppUser

This project will retrieve the information of the given App User and will list the files/folders under root folder.

To run the project, first provide the Id of the app user in `src/example/java/com/box/sdk/example/CreateAppUser.java`.

```java
public final class AccessAsAppUser {

    private static final String USER_ID = "";

    // ...
}
```

Then just invoke `gradle runAccessAsAppUser` to run the AccessAsAppUser example!

Note: The JCE bundled with oracle JRE supports keys upto 128 bit length only. To use larger cryptographic keys, install [JCE Unlimited Strength Jurisdiction Policy Files](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html).

#### BoxDeveloperEditionAPIConnectionAsEnterpriseUser

This example shows how to get tokens for an enterprise user, say admin of the enterprise and do actions on behalf of admin. 

To run the project, follow below steps

1. Turn on `Enterprise` in `Application Access` section in Developer Console for the app 

2. Turn on `Generate User Access Tokens` in `Advanced Features` section in Developer Console for the app

3. Provide the Id of the admin user (or any enterprise user) in `src/example/java/com/box/sdk/example/BoxDeveloperEditionAPIConnectionAsEnterpriseUser.java`.

```java 
public final class BoxDeveloperEditionAPIConnectionAsEnterpriseUser {

   private static final String USER_ID = "";
   // ...
   Reader reader = new FileReader("src/example/config/config.json");
   BoxConfig boxConfig = BoxConfig.readFrom(reader);
   IAccessTokenCache accessTokenCache = new InMemoryLRUAccessTokenCache(10);

   BoxDeveloperEditionAPIConnection api = new BoxDeveloperEditionAPIConnection(
           USER_ID,
           DeveloperEditionEntityType.USER,
           boxConfig,
           accessTokenCache
   );
}
```

## Compatibility

The Box Java SDK is compatible with Java 8 and up.

## Building

The SDK uses Gradle for its build system. SDK comes with Gradle wrapper. Running `./gradlew build` from the root
of the repository will compile, lint, and test the SDK.

```bash
$ ./gradlew build
```

The SDK also includes integration tests which make real API calls, and therefore
are run separately from unit tests. Integration tests should be run against a
test account since they create and delete data. To run the integration tests,
remove the `.template` extension from
`src/test/config/config.properties.template` and fill in your test account's
information. Then run:

```bash
$ ./gradlew integrationTest
```

## Documentation

You can find guides and tutorials in the `doc` directory.

* [BUILD ON BOX PLATFORM](https://developer.box.com/guides/getting-started/)
* [Javadocs](http://box.github.io/box-java-sdk/javadoc/com/box/sdk/package-summary.html)
* [Overview](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/overview.md)
* [Configuration](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/configuration.md)
* [Logging](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/logging.md)
* [Authentication](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/authentication.md)
* [Files](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/files.md)
* [Folders](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/folders.md)
* [Comments](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/comments.md)
* [Collaborations](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/collaborations.md)
* [Collaboration Allowlists](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/collaboration_allowlists.md)
* [Events](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/events.md)
* [Search](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/search.md)
* [Users](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/users.md)
* [Groups](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/groups.md)
* [Tasks](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/tasks.md)
* [Trash](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/trash.md)
* [Collections](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/collections.md)
* [Devices](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/devices.md)
* [Retention Policies](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/retention_policies.md)
* [Legal Holds Policy](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/legal_holds.md)
* [Watermarking](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/watermarking.md)
* [Webhooks](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/webhooks.md)
* [Web Links](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/weblinks.md)
* [Metadata Templates](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/metadata_template.md)
* [Classifications](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/classifications.md)
* [Recent Items](https://github.com/box/box-java-sdk/blob/v4.1.0/doc/recent_items.md)


Javadocs are generated when `gradle javadoc` is run and can be found in
`build/doc/javadoc`.

## Vulnerabilities in Bouncycastle libraries
In Box Java SDK we are using:
 - `org.bouncycastle:bcpkix-jdk15on:1.57`
 - `org.bouncycastle:bcprov-jdk15on:1.57`

There are some moderate vulnerabilities reported against those versions:
 - [CVE-2020-26939](https://github.com/advisories/GHSA-72m5-fvvv-55m6) - Observable Differences in Behavior to Error Inputs in Bouncy Castle
 - [CVE-2020-15522](https://github.com/advisories/GHSA-6xx3-rg99-gc3p) - Timing based private key exposure in Bouncy Castle

We cannot upgrade those libraries as they are working with [FIPS 140-2 certified](https://csrc.nist.gov/projects/cryptographic-module-validation-program/certificate/3514)
cryptographic module. Some of our customers require certified cryptography module and our SDK must work with it.

If you want to use modern `bcpkix-jdk15on` and `bcprov-jdk15on` than you can exclude them while importing Java Box SDK and provide you own versions:

Gradle example
```groovy
implementation('com.box:box-java-sdk:x.y.z') {
   exclude group: 'org.bouncycastle', module: 'bcprov-jdk15on'
   exclude group: 'org.bouncycastle', module: 'bcpkix-jdk15on'
}
runtimeOnly('org.bouncycastle:bcprov-jdk15on:1.70')
runtimeOnly('org.bouncycastle:bcpkix-jdk15on:1.70')
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
      <version>1.70</version>
      <scope>runtime</scope>
   </dependency>
   <dependency>
      <groupId>org.bouncycastle</groupId>
      <artifactId>bcpkix-jdk15on</artifactId>
      <version>1.70</version>
      <scope>runtime</scope>
   </dependency>
</dependencies>
```


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
