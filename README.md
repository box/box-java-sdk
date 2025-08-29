<p align="center">
  <img src="https://github.com/box/sdks/blob/master/images/box-dev-logo.png" alt= “box-dev-logo” width="30%" height="50%">
</p>

# Box Java SDK

[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)
![build](https://github.com/box/box-java-sdk/actions/workflows/build.yml/badge.svg?branch=sdk-gen)
![Maven Central Version](https://img.shields.io/maven-central/v/com.box/box-java-sdk)
![Platform](https://img.shields.io/badge/java-%3E%3D8-blue)
[![Coverage](https://coveralls.io/repos/github/box/box-java-sdk/badge.svg?branch=sdk-gen)](https://coveralls.io/github/box/box-java-sdk-gen?branch=sdk-gen)

We are excited to introduce the stable Release of the latest generation of Box Java SDK, designed to elevate the developer experience and streamline your integration with the Box Content Cloud.

With this SDK, you’ll have access to:

1. Full API Support: The new generation of Box SDKs empowers developers with complete coverage of the Box API ecosystem. You can now access all the latest features and functionalities offered by Box, allowing you to build even more sophisticated and feature-rich applications.
2. Rapid API Updates: Say goodbye to waiting for new Box APIs to be incorporated into the SDK. With our new auto-generation development approach, we can now add new Box APIs to the SDK at a much faster pace (in a matter of days). This means you can leverage the most up-to-date features in your applications without delay.
3. Embedded Documentation: We understand that easy access to information is crucial for developers. With our new approach, we have included comprehensive documentation for all objects and parameters directly in the source code of the SDK. This means you no longer need to look up this information on the developer portal, saving you time and streamlining your development process.
4. Enhanced Convenience Methods: Our commitment to enhancing your development experience continues with the introduction of convenience methods. These methods cover various aspects such as chunk uploads, classification, and much more.
5. Seamless Start: The new SDKs integrate essential functionalities like authentication, automatic retries with exponential backoff, exception handling, request cancellation, and type checking, enabling you to focus solely on your application's business logic.

Embrace the new generation of Box SDKs and unlock the full potential of the Box Content Cloud.

# Table of contents

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Box Java SDK](#box-java-sdk)
- [Table of contents](#table-of-contents)
- [Installing](#installing)
- [Getting Started](#getting-started)
- [Documentation](#documentation)
- [Upgrades](#upgrades)
- [Integration Tests](#integration-tests)
  - [Running integration tests locally](#running-integration-tests-locally)
    - [Create Platform Application](#create-platform-application)
    - [Export configuration](#export-configuration)
    - [Start integration tests](#start-integration-tests)
- [3rd Party Libraries \& Licenses](#3rd-party-libraries--licenses)
- [Questions, Bugs, and Feature Requests?](#questions-bugs-and-feature-requests)
- [Copyright and License](#copyright-and-license)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

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

# Documentation

Browse the [docs](docs/README.md) or see [API Reference](https://developer.box.com/reference/) for more information.

# Upgrades

The SDK is updated regularly to include new features, enhancements, and bug fixes. If you are upgrading from manual SDK to this new generated SDK checkout the [migration guide](migration-guides/from-v4-to-v10.md) and [changelog](CHANGELOG.md) for more information.

# Integration Tests

## Running integration tests locally

### Create Platform Application

To run integration tests locally you will need a `Custom App` created in the [Box Developer
Console](https://app.box.com/developers/console) with `Server Authentication (with JWT)` selected as authentication method.
Once created you can edit properties of the application:

- In section `App Access Level` select `App + Enterprise Access`. You can enable all `Application Scopes`.
- In section `Advanced Features` enable `Make API calls using the as-user header` and `Generate user access tokens`.

Now select `Authorization` and submit application to be reviewed by account admin.

### Export configuration

1. Select `Configuration` tab and in the bottom in the section `App Settings`
   download your app configuration settings as JSON.
2. Encode configuration file to Base64, e.g. using command: `base64 -i path_to_json_file`
3. Set environment variable: `JWT_CONFIG_BASE_64` with base64 encoded jwt configuration file
4. Set environment variable: `BOX_FILE_REQUEST_ID` with ID of file request already created in the user account, `BOX_EXTERNAL_USER_EMAIL` with email of free external user which not belongs to any enterprise and `BOX_EXTERNAL_USER_ID` with its ID.
5. Set environment variable: `WORKFLOW_FOLDER_ID` with the ID of the Relay workflow that deletes the file that triggered the workflow. The workflow should have a manual start to be able to start it from the API.
6. Set environment variable: `APP_ITEM_ASSOCIATION_FILE_ID` to the ID of the file with associated app item and `APP_ITEM_ASSOCIATION_FOLDER_ID` to the ID of the folder with associated app item.
7. Set environment variable: `APP_ITEM_SHARED_LINK` to the shared link associated with app item.
8. Set environment variable: `SLACK_AUTOMATION_USER_ID` to the ID of the user responsible for the Slack automation, `SLACK_ORG_ID` to the ID of the Slack organization and `SLACK_PARTNER_ITEM_ID` to the ID of the Slack partner item.

### Start integration tests

To run integration tests locally, you can use the following command:

```console
./gradlew test --stacktrace
```

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
7. [bcprov-jdk18on v1.78.1](https://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk18on/1.78.1)
   Maven: `org.bouncycastle:bcprov-jdk18on:1.78.1`
   Licence: [MIT](https://opensource.org/licenses/MIT)
8. [bcpkix-jdk18on v1.78.1](https://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk18on/1.78.1)
   Maven: `org.bouncycastle:bcpkix-jdk18on:1.78.1`
   Licence: [MIT](https://opensource.org/licenses/MIT)

The following libraries are required for running tests:

1. [junit-jupiter-api v5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-api/5.10.0)
   Maven: `org.junit.jupiter:junit-jupiter-api:5.10.0`
   Licence: [EPL 2.0](https://www.eclipse.org/legal/epl-2.0/)
2. [junit-jupiter-engine v5.10.0](https://mvnrepository.com/artifact/org.junit.jupiter/junit-jupiter-engine/5.10.0)
   Maven: `org.junit.jupiter:junit-jupiter-engine:5.10.0`
   Licence: [EPL 2.0](https://www.eclipse.org/legal/epl-2.0/)

# Questions, Bugs, and Feature Requests?

Need to contact us directly? [Browse the issues
tickets](https://github.com/box/box-java-sdk/issues)! Or, if that
doesn't work, [file a new
one](https://github.com/box/box-java-sdk/issues/new) and we will get
back to you. If you have general questions about the Box API, you can
post to the [Box Developer Forum](https://forum.box.com/).

# Copyright and License

Copyright 2023 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
