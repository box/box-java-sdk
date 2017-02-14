[![Project Status](http://opensource.box.com/badges/active.svg)](http://opensource.box.com/badges)

Box Java SDK
============

The Box Java SDK for interacting with the
[Box Content API](https://developers.box.com/docs/).

Quickstart
----------

The SDK can be obtained by adding it as a [maven dependency]
(http://opensource.box.com/box-java-sdk/), cloning the source into your project,
or by downloading one of the precompiled JARs from the [releases page on GitHub]
(https://github.com/box/box-java-sdk/releases).

If you use the JAR, you'll also need to include several dependencies:

1. [minimal-json v0.9.1](https://github.com/ralfstx/minimal-json)
   Maven: `com.eclipsesource.minimal-json:minimal-json:0.9.1`
2. [jose4j v0.4.4](https://bitbucket.org/b_c/jose4j/wiki/Home)
   Maven: `org.bitbucket.b_c:jose4j:0.4.4`
3. [bouncycastle bcprov-jdk15on v1.52](http://mvnrepository.com/artifact/org.bouncycastle/bcprov-jdk15on)
   Maven: `org.bouncycastle:bcprov-jdk15on:1.52`
4. [bouncycastle bcpkix-jdk15on v1.52](http://mvnrepository.com/artifact/org.bouncycastle/bcpkix-jdk15on)
   Maven: `org.bouncycastle:bcpkix-jdk15on:1.52`
5. [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
   If you don't install this, you'll get an exception about key length. This is not a Box thing, this is a U.S. Government requirement concerning strong encryption.

Here is a simple example of how to authenticate with the API using a developer
token and then print the ID and name of each item in your root folder.

```java
BoxAPIConnection api = new BoxAPIConnection("developer-token");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem.Info itemInfo : rootFolder) {
    System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
}
```

For more details on how to get started, check out the [overview
guide](doc/overview.md). It has a short explanation of how the SDK works and how
you can get started using it.

### Sample Projects

Three sample projects can be found in `src/example`.

#### Main

This project will output your name and a list of the files and folders in your root directory.

To run the project, first provide a developer token in
`src/example/java/com/box/sdk/example/Main.java`. You can obtain a developer
token from your application's [developer
console](https://app.box.com/developers/services).

```java
public final class Main {
    private static final String DEVELOPER_TOKEN = "<YOUR_DEVELOPER_TOKEN>";

    // ...
}
```

Then just invoke `gradle runExample` to run the Main example!

#### CreateAppUser

This project will output the user id of enterprise admin and will create a new App User for the enterprise.

To run the project, first provide following in `src/example/java/com/box/sdk/example/CreateAppUser.java`.
* Client Id: From application's [developer console](https://app.box.com/developers/services).
* Client Secret: From application's [developer console](https://app.box.com/developers/services).
* Enterprise Id: From Admin Console [Account Info tab](https://app.box.com/master/settings).
* Public key Id: From application's [developer console](https://app.box.com/developers/services).
* Private key file name with path: Corresponding to the public key uploaded in application's [developer console](https://app.box.com/developers/services).
* Private key password (if any): Password for the private key.
* Name of App User: This will be used as the name of the newly created App User.

```java
public final class CreateAppUser {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String ENTERPRISE_ID = "";
    private static final String PUBLIC_KEY_ID = "";
    private static final String PRIVATE_KEY_FILE = "";
    private static final String PRIVATE_KEY_PASSWORD = "";
    private static final String APP_USER_NAME = "";

    // ...
}
```

Then just invoke `gradle runCreateAppUser` to run the CreateAppUser example!

Note: The JCE bundled with oracle JRE supports keys upto 128 bit length only. To use larger crytographic keys, install [JCE Unlimited Strength Jurisdiction Policy Files](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html).

#### AccessAsAppUser

This project will retrieve the information of the given App User and will list the files/folders under root folder.

To run the project, first provide following in `src/example/java/com/box/sdk/example/CreateAppUser.java`.
* Client Id: From application's [developer console](https://app.box.com/developers/services).
* Client Secret: From application's [developer console](https://app.box.com/developers/services).
* User Id: Id of the user whose data will be accessed.
* Public key Id: From application's [developer console](https://app.box.com/developers/services).
* Private key file name with path: Corresponding to the public key uploaded in application's [developer console](https://app.box.com/developers/services).
* Private key password (if any): Password for the private key.

```java
public final class AccessAsAppUser {

    private static final String CLIENT_ID = "";
    private static final String CLIENT_SECRET = "";
    private static final String USER_ID = "";
    private static final String PUBLIC_KEY_ID = "";
    private static final String PRIVATE_KEY_FILE = "";
    private static final String PRIVATE_KEY_PASSWORD = "";

    // ...
}
```

Then just invoke `gradle runAccessAsAppUser` to run the AccessAsAppUser example!

Note: The JCE bundled with oracle JRE supports keys upto 128 bit length only. To use larger cryptographic keys, install [JCE Unlimited Strength Jurisdiction Policy Files](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html).

Building
--------

The SDK uses Gradle for its build system. Running `gradle build` from the root
of the repository will compile, lint, and test the SDK.

```bash
$ gradle build
```

The SDK also includes integration tests which make real API calls, and therefore
are run separately from unit tests. Integration tests should be run against a
test account since they create and delete data. To run the integration tests,
remove the `.template` extension from
`src/test/config/config.properties.template` and fill in your test account's
information. Then run:

```bash
$ gradle integrationTest
```

Documentation
-------------

You can find guides and tutorials in the `doc` directory.

* [Javadocs](http://box.github.io/box-java-sdk/javadoc/com/box/sdk/package-summary.html)
* [Overview](doc/overview.md)
* [Authentication](doc/authentication.md)
* [Files](doc/files.md)
* [Folders](doc/folders.md)
* [Comments](doc/comments.md)
* [Collaborations](doc/collaborations.md)
* [Events](doc/events.md)
* [Search](doc/search.md)
* [Users](doc/users.md)
* [Groups](doc/groups.md)
* [Tasks](doc/tasks.md)
* [Collections](doc/collections.md)
* [Devices](doc/devices.md)
* [Retention Policies](doc/retention_policies.md)
* [Legal Holds Policy](doc/legal_holds.md)
* [Watermarking](doc/watermarking.md)
* [Webhooks](doc/webhooks.md)
* [Web Links](doc/weblinks.md)
* [Metadata Templates](doc/metadata_template.md)


Javadocs are generated when `gradle javadoc` is run and can be found in
`build/doc/javadoc`.

Copyright and License
---------------------

Copyright 2016 Box, Inc. All rights reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
