# Migration guide from v4 to v10 version of `box-java-sdk`

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->

- [Introduction](#introduction)
- [Installation](#installation)
  - [Maven](#maven)
  - [Gradle](#gradle)
- [Supported Environments](#supported-environments)
- [Highlighting the Key Differences](#highlighting-the-key-differences)

<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Introduction

The v10 release of `box-java-sdk` library helps Java developers to conveniently integrate with Box API.
In the contrary to the previous versions (v4 or lower), it is not manually maintained, but auto-generated
based on Open API Specification. This means you can leverage the most up-to-date Box API features in your
applications without delay. We introduced this major version bump to reflect the significant codebase changes
and to align with other Box SDKs, which will also adopt generated code starting from their v10 releases.
More information and benefits of using the new can be found in the
[README](../README.md) file.

## Installation

To install v10 version of Box Java SDK, you can use Maven or Gradle. The library is available in the
[Maven Central Repository](https://search.maven.org/artifact/com.box/box-java-sdk).

We have also introduced v5 version of Box Java SDK that consolidates both the manually maintained
`com.box.sdk` package from v4 and the new, auto-generated `com.box.sdkgen` package from v10.
If you would like to use a feature available only in the new SDK, you won't need to necessarily migrate all your code
to use generated SDK at once. You will be able to use a new feature from the `com.box.sdkgen` package,
while keeping the rest of your code unchanged. However, we recommend to fully migrate to the v10 of the SDK eventually.
More information about v4 version can be found in the [migration guide from v4 to v5](./from-v4-to-v5.md).

### Maven

To start using generated version of the SDK in you Maven project just bump the version of the Box Java SDK library
in `pom.xml`to 10.0.0 or higher:

```xml
<dependency>
    <groupId>com.box</groupId>
    <artifactId>box-java-sdk</artifactId>
    <version>10.0.0</version>
</dependency>
```

### Gradle

To bump a dependency in your Gradle project, bump the version used in your `build.gradle` file:

```groovy
implementation 'com.box:box-java-sdk:10.0.0'
```

## Supported Environments

Both v4 and v10 of the Box Java SDK share the same Java version requirement: Java 8 or higher.
No changes to your environment are needed when upgrading from v4 to v10.

## Highlighting the Key Differences

There are important differences between the `com.box.sdk` (v4) and the generated `com.box.sdkgen` (v10) packages.
We have prepared a separate document that presents the main differences and provides guidance to help you migrate.
For side-by-side code examples, see: [Migration guide: migrate from `com.box.sdk` to `com.box.sdkgen` package](./from-com-box-sdk-to-com-box-sdkgen.md).
