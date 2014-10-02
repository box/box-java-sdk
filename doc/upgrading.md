Upgrading to v4
===============

v4 of the Box Java SDK introduces a lot of new design changes. Since this is a
major version bump, there will be breaking changes that will require you to
update your application.

What's New
----------

### Features

* __Automatic rate-limiting and error retry.__ API requests will automatically
  be retried with exponential back off if a 500+ (server error) or 429 (too many
  requests) response code is returned.
* __OAuth redesign.__ OAuth should now be easier to use, allowing you to
  authenticate with an access token, auth code, or developer token.
* __New EventStream class.__ This class makes it easier to listen for API events
  by allowing you to specify listeners that will be notified when an event
  occurs.
* __New classes for making custom API requests.__ The BoxAPIRequest and
  BoxAPIResponse classes make it easy to send custom requests to the API while
  still having OAuth, rate-limiting back off, error handling and response
  parsing automatically handled.

### General Improvements

* __Simpler and more intuitive design.__ We aimed to make the overall design of
  the SDK more intuitive and easier to learn.
* __More documentation and examples.__ The Javadocs have been completely
  overhauled and there are new guides explaining how to accomplish common tasks
  with the SDK.
* __SDK size has been dramatically decreased.__ Many of the SDK's dependencies
  have been removed and its overall size has been reduced - making it more
  suitable for mobile apps.
* __Easier integration.__ With a single build process, it's easier and simpler
  to get the SDK building with other applications. It also follows the standard
  directory layout, making it easier to import into various IDEs or build
  systems.

Authentication
--------------

Authentication has been simplified by allowing you to provide tokens or auth
codes directly. All authentication is now done by creating a `BoxAPIConnection`
in order to establish an authenticated connection with the API.

Connect to the API using a developer token:

```java
BoxAPIConnection api = new BoxAPIConnection("YOUR-DEVELOPER-TOKEN");
```

Connect to the API using access and refresh tokens:

```java
BoxAPIConnection api = new BoxAPIConnection("CLIENT-ID", "CLIENT-SECRET",
    "ACCESS-TOKEN", "REFRESH-TOKEN");
```

Connect to the API using an auth code:

```java
BoxAPIConnection api = new BoxAPIConnection("CLIENT-ID", "CLIENT-SECRET",
    "AUTH-CODE");
```

More information on authentication can be found [here](authentication.md).

New Resource Types
------------------

Previously, interaction with the API was done through managers and request
objects. For example:

```java
BoxClient client = new BoxClient(...);
IBoxFilesManager filesManager = boxClient.getFilesManager();

BoxDefaultRequestObject requestObj = new BoxDefaultRequestObject();
requestObj.getRequestExtras().addField(BoxFile.FIELD_SHA1);
requestObj.getRequestExtras().addField(BoxFile.FIELD_DESCRIPTION);

BoxFile file = filesManager.getFile(fileId, requestObj);
```

These objects have been removed and interacting with the API has been
simplified. Resources can now be manipulated directly without needing to use
managers or build custom requests.

```java
BoxAPIConnection api = new BoxAPIConnection(...);
BoxFile file = new BoxFile(api, fileID);
BoxFile.Info fileInfo = file.getInfo("sha1", "description");
```

More information on resource types can be found [here](resource-types.md).

Custom Requests
---------------

The SDK now provides request and response objects that allow you to easily make
custom requests to the Box API. These objects will handle authentication,
automatic retry, rate-limiting and errors out-of-the-box, giving you the
flexibility to make your own API requests without having to worry about handling
these things yourself.

```java
BoxAPIConnection api = new BoxAPIConnection(...);
URL url = new URL("https://api.box.com/2.0/folders/0/items?fields=name,created_at")
BoxAPIRequest request = new BoxAPIRequest(api, url, "GET");
BoxJSONResponse response = (BoxJSONResponse) request.send();
String json = response.getJSON();
```

More information on resource types can be found [here](overview.md).
