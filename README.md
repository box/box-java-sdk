Box Java SDK
============

This SDK provides a Java interface for the [Box REST
API](https://developers.box.com/docs/). Features from the [previous version of
the Box Java SDK](https://github.com/box/box-java-sdk-v2) are being transitioned
to this SDK.

Quickstart
----------

The SDK can be obtained by either cloning the source into your project, or by
downloading one of the precompiled JARs from the [releases page on GitHub]
(https://gitenterprise.inside-box.net/Box/box-java-sdk/releases).

If you use the JAR, you'll also need to include [minimal-json v0.9.1]
(https://github.com/ralfstx/minimal-json) - which is the SDK's only dependency.
You can get minimal-json from maven with `com.eclipsesource.minimal-json:minimal-json:0.9.1`.

### Example

Here is a simple example of how to authenticate with the API using a developer
token and then print the ID and name of each item in your root folder.

```java
BoxAPIConnection api = new BoxAPIConnection("developer-token");
BoxFolder rootFolder = BoxFolder.getRootFolder(api);
for (BoxItem item : rootFolder) {
    BoxItem.Info info = item.getInfo();
    System.out.format("[%d] %s\n", item.getID(), info.getName());
}
```

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

* [Overview](doc/overview.md)
* [Authentication](doc/authentication.md)
* [Events Stream](doc/events.md)

Javadoc reference documentation is [available here][1]. Javadocs are also
generated when `gradle javadoc` is run and can be found in `build/doc/javadoc`.

[1]:https://gitenterprise.inside-box.net/pages/Box/box-java-sdk/javadoc/com/box/sdk/package-summary.html
